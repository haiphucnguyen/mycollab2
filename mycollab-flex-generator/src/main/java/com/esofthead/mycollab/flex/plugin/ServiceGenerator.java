package com.esofthead.mycollab.flex.plugin;

import groovy.lang.Writable;
import groovy.text.GStringTemplateEngine;

import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;
import java.lang.reflect.Method;
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
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.persistence.service.IService;

public class ServiceGenerator implements SourceGenerator {

	private static Logger log = LoggerFactory.getLogger(ServiceGenerator.class);

	private static Class<?> getCandidateForRemoteService(Class<?> serviceClass) {
		if (serviceClass.isInterface()) {
			if (isInterfaceBeRemoteService(serviceClass)) {
				return serviceClass;
			}
		} else {
			Class<?>[] interfaces = serviceClass.getInterfaces();
			for (Class<?> interfaceCls : interfaces) {
				if (isInterfaceBeRemoteService(interfaceCls)) {
					return interfaceCls;
				}
			}
		}

		return null;
	}

	private static boolean isInterfaceBeRemoteService(Class<?> cls) {
		if (IService.class.isAssignableFrom(cls)
				&& (cls.getAnnotation(RemotingDestination.class) != null)) {
			return true;
		} else {
			Class<?>[] interfaces = cls.getInterfaces();
			for (Class<?> interfaceCls : interfaces) {
				if (isInterfaceBeRemoteService(interfaceCls)) {
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public void generate() {
		ComponentScanner scanner = new ComponentScanner();
		Set<Class<?>> serviceClasses = scanner.getClasses(new ComponentQuery() {
			@Override
			protected void query() {
				select().from("com.esofthead.mycollab.**.service").returning(
						allImplementing(IService.class));
			}
		});

		log.info("Scan packages to service classes. There are "
				+ serviceClasses.size() + " classes are found");

		try {
			for (Class<?> serviceClass : serviceClasses) {
				Class<?>[] interfaces = serviceClass.getInterfaces();
				Class interfaceCls = getCandidateForRemoteService(serviceClass);
				if (interfaceCls != null) {
					GStringTemplateEngine engine = new GStringTemplateEngine();

					String serviceName = "";

					// Detect service name
					Service serviceAnno = serviceClass
							.getAnnotation(Service.class);
					if (serviceAnno != null) {
						if (serviceAnno.value() != null
								&& !serviceAnno.value().equals("")) {
							serviceName = serviceAnno.value();
						} else {
							serviceName = serviceClass.getSimpleName();
							serviceName = (char) (serviceName.charAt(0) + 'a' - 'A')
									+ serviceName.substring(1);
						}
					} else {
						serviceName = serviceClass.getSimpleName();
						serviceName = (char) (serviceName.charAt(0) + 'a' - 'A')
								+ serviceName.substring(1);
					}

					String packageName = ClassUtils.getPackage(interfaceCls);

					Map binding = new HashMap();
					binding.put("packageName", packageName);
					binding.put("serviceName", serviceName);
					binding.put("className", interfaceCls.getSimpleName());

					Set<String> importClasses = new HashSet<String>();
					importClasses
							.add("com.esofthead.mycollab.core.GenericService");
					importClasses.add("mx.rpc.events.FaultEvent");
					importClasses.add("mx.rpc.events.ResultEvent");
					importClasses
							.add("com.esofthead.mycollab.core.AbstractResponder");
					binding.put("importClasses", importClasses);

					binding.put(
							"methods",
							retrieveAs3MethodsMapping(interfaceCls,
									importClasses));

					Writable template = engine.createTemplate(
							As3GeneratorMojo.class.getClassLoader()
									.getResource("serviceGenerator.tp")).make(
							binding);

					String packagePath = packageName.replace(".", "/");
					String filePath = "src" + "/" + packagePath + "/"
							+ interfaceCls.getSimpleName() + ".as";
					File folder = new File(System.getProperty("user.dir") + "/"
							+ "src" + "/" + packagePath + "/");
					folder.mkdirs();
					FileWriter writer = new FileWriter(new File(folder,
							interfaceCls.getSimpleName() + ".as"));
					writer.write(template.toString());
					writer.close();
					log.info("Generated service class " + filePath);
				} else {
					log.debug("Service class " + serviceClass.getName()
							+ " is not a candidate for remote service");
				}

			}
		} catch (Exception e) {
			log.error("Exception while generating classes", e);
			throw new MyCollabException(e);
		}

	}

	private List<As3Method> retrieveAs3MethodsMapping(Class serviceClass,
			Set<String> importClasses) {
		List<As3Method> result = new ArrayList<As3Method>();
		Method[] methods = serviceClass.getMethods();
		for (Method method : methods) {
			As3Method as3Method = new As3Method(method.getName());
			Class<?>[] parameterTypes = method.getParameterTypes();
			for (int i = 0; i < parameterTypes.length; i++) {
				Class paramCls = parameterTypes[i];
				String typeName;

				if (paramCls == Boolean.TYPE || paramCls == Boolean.class) {
					typeName = "Boolean";
				} else if (paramCls == Integer.TYPE
						|| paramCls == Integer.class) {
					typeName = "int";
				} else if (paramCls == Long.TYPE || paramCls == Long.class) {
					typeName = "int";
				} else if (paramCls == Double.TYPE || paramCls == Double.class) {
					typeName = "Number";
				} else if (paramCls == String.class) {
					typeName = "String";
				} else if (paramCls == Date.class) {
					typeName = "Date";
				} else if (Collection.class.isAssignableFrom(paramCls)) {
					importClasses.add("mx.collections.ArrayCollection");
					typeName = "ArrayCollection";
				} else if (Map.class.isAssignableFrom(paramCls)) {
					typeName = "Object";
				} else if (paramCls == Object.class
						|| paramCls == Serializable.class) {
					typeName = "Object";
				} else {
					importClasses.add(paramCls.getName());
					typeName = paramCls.getSimpleName();
				}
				As3Field as3Field = new As3Field(typeName, "param" + i);
				as3Method.addField(as3Field);
			}

			result.add(as3Method);
		}
		return result;
	}

}
