package com.esofthead.mycollab.flex.plugin;

import groovy.lang.Writable;
import groovy.text.GStringTemplateEngine;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
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
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchField;

public class SearchCriteriaGenerator implements SourceGenerator {
	private static Logger log = LoggerFactory
			.getLogger(SearchCriteriaGenerator.class);

	@Override
	public void generate() {
		ComponentScanner scanner = new ComponentScanner();
		Set<Class<?>> criteriaClasses = scanner
				.getClasses(new ComponentQuery() {
					@Override
					protected void query() {
						select().from(
								"com.esofthead.mycollab.**.domain.criteria.**")
								.returning(allExtending(SearchCriteria.class));
					}
				});

		log.info("Scan packages to search criteria classes. There are "
				+ criteriaClasses.size() + " classes are found");

		try {
			for (Class<?> criteriaClass : criteriaClasses) {
				if (criteriaClass == null) {
					return;
				}
				GStringTemplateEngine engine = new GStringTemplateEngine();

				String packageName = ClassUtils.getPackage(criteriaClass);
				log.info("Package: " + criteriaClass + "  "
						+ ClassUtils.getPackage(criteriaClass));
				Map binding = new HashMap();
				binding.put("packageName", packageName);
				binding.put("aliasClassName", criteriaClass.getName());
				binding.put("className", criteriaClass.getSimpleName());

				Set<String> importClasses = new HashSet<String>();
				binding.put("importClasses", importClasses);

				Class<?> superClass = criteriaClass.getSuperclass();
				if (superClass == SearchCriteria.class) {
					importClasses.add(superClass.getName());
					binding.put("superClassName", superClass.getSimpleName());
				} else {
					binding.put("superClassName", superClass.getSimpleName());
				}

				binding.put("fields",
						retrieveAs3FieldsMapping(criteriaClass, importClasses));

				Writable template = engine.createTemplate(
						As3GeneratorMojo.class.getClassLoader().getResource(
								"searchCriteriaGenerator.template")).make(
						binding);

				String packagePath = packageName.replace(".", "/");
				String filePath = "src" + "/" + packagePath + "/"
						+ criteriaClass.getSimpleName() + ".as";
				File folder = new File(System.getProperty("user.dir") + "/"
						+ "src" + "/" + packagePath + "/");
				folder.mkdirs();
				FileWriter writer = new FileWriter(new File(folder,
						criteriaClass.getSimpleName() + ".as"));
				writer.write(template.toString());
				writer.close();
				log.info("Generated criteria class " + filePath);
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
			if (SearchField.class.isAssignableFrom(typeCls)) {
				importClasses.add(typeCls.getName());
				as3Field = new As3Field(typeCls.getSimpleName(),
						field.getName());
				result.add(as3Field);
			}
		}
		return result;
	}

}
