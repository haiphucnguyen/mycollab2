package com.esofthead.mycollab.flex.plugin;

import groovy.lang.Writable;
import groovy.text.GStringTemplateEngine;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.extcos.ComponentQuery;
import net.sf.extcos.ComponentScanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.ValuedBean;

public class DomainGenerator implements SourceGenerator {

	private static Logger log = LoggerFactory.getLogger(DomainGenerator.class);

	@Override
	public void generate() {
		ComponentScanner scanner = new ComponentScanner();
		Set<Class<?>> domainClasses = scanner.getClasses(new ComponentQuery() {
			@Override
			protected void query() {
				select().from("com.esofthead.mycollab.**.domain.**").returning(
						allExtending(ValuedBean.class));
			}
		});

		log.info("Scan packages to search domain classes. There are "
				+ domainClasses.size() + " classes are found");
		try {
			for (Class<?> domainClass : domainClasses) {
				if (domainClass == null) {
					return;
				}
				GStringTemplateEngine engine = new GStringTemplateEngine();

				Map binding = new HashMap();
				String packageName = ClassUtils.getPackage(domainClass);
				log.info("Package: " + domainClass + " " + packageName);
				binding.put("packageName", packageName);
				binding.put("aliasClassName", domainClass.getName());
				binding.put("className", domainClass.getSimpleName());

				Set<String> importClasses = new HashSet<String>();
				binding.put("importClasses", importClasses);

				Class<?> superClass = domainClass.getSuperclass();
				if (superClass == ValuedBean.class) {
					importClasses.add(superClass.getName());
					binding.put("superClassName", superClass.getSimpleName());
				} else {
					if (!packageName.equals(ClassUtils.getPackage(superClass))) {
						importClasses.add(superClass.getName());
					}
					binding.put("superClassName", superClass.getSimpleName());
				}

				binding.put("fields",
						retrieveAs3FieldsMapping(domainClass, importClasses));

				Writable template = engine.createTemplate(
						As3GeneratorMojo.class.getClassLoader().getResource(
								"domainGenerator.tp")).make(binding);

				String packagePath = packageName.replace(".", "/");
				String filePath = "src" + "/" + packagePath + "/"
						+ domainClass.getSimpleName() + ".as";
				File folder = new File(System.getProperty("user.dir") + "/"
						+ "src" + "/" + packagePath + "/");
				folder.mkdirs();
				FileWriter writer = new FileWriter(new File(folder,
						domainClass.getSimpleName() + ".as"));
				writer.write(template.toString());
				writer.close();
				log.info("Generated domain class " + filePath);
			}
		} catch (Exception e) {
			log.error("Exception while generating classes", e);
			throw new MyCollabException(e);
		}

	}

	private List<As3Field> retrieveAs3FieldsMapping(Class domainCls,
			Set<String> importClasses) {
		List<As3Field> result = new ArrayList<As3Field>();
		Field[] fields = domainCls.getDeclaredFields();

		for (Field field : fields) {
			As3Field as3Field;

			Class<?> typeCls = field.getType();

			if (Modifier.isStatic(field.getModifiers())) {
				continue;
			}

			if (typeCls == Boolean.TYPE || typeCls == Boolean.class) {
				as3Field = new As3Field("Boolean", field.getName());
			} else if (typeCls == Integer.TYPE || typeCls == Integer.class) {
				as3Field = new As3Field("int", field.getName());
			} else if (typeCls == Long.TYPE || typeCls == Long.class) {
				as3Field = new As3Field("int", field.getName());
			} else if (typeCls == Double.TYPE || typeCls == Double.class) {
				as3Field = new As3Field("Number", field.getName());
			} else if (typeCls == String.class) {
				as3Field = new As3Field("String", field.getName());
			} else if (typeCls == Date.class) {
				as3Field = new As3Field("Date", field.getName());
			} else if (Collection.class.isAssignableFrom(typeCls)) {
				importClasses.add("mx.collections.ArrayCollection");
				as3Field = new As3Field("ArrayCollection", field.getName());
			} else if (Map.class.isAssignableFrom(typeCls)) {
				as3Field = new As3Field("Object", field.getName());
			} else {
				importClasses.add(typeCls.getName());
				as3Field = new As3Field(typeCls.getSimpleName(),
						field.getName());
			}

			result.add(as3Field);
		}
		return result;
	}

}
