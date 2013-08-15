package com.esofthead.mycollab.flex.plugin;

import java.lang.reflect.Field;
import java.util.Set;

import net.sf.extcos.ComponentQuery;
import net.sf.extcos.ComponentScanner;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Execute;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

import com.esofthead.mycollab.core.utils.ValuedBean;

@Mojo(name = "flex-generator", defaultPhase = LifecyclePhase.COMPILE)
@Execute(goal = "flex-generator", phase = LifecyclePhase.COMPILE)
public class As3GeneratorMojo extends AbstractMojo {

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		getLog().info("Hello world");

		ComponentScanner scanner = new ComponentScanner();
		Set<Class<?>> domainClasses = scanner.getClasses(new ComponentQuery() {
			@Override
			protected void query() {
				select().from("com.esofthead.mycollab.**.domain.**").returning(
						allExtending(ValuedBean.class));
			}
		});

		getLog().info(
				"Scan packages to search domain classes. There are "
						+ domainClasses.size() + " classes are found");

		for (Class<?> domainClass : domainClasses) {
			getLog().info("Domain class" + domainClass.getName());
			Field[] fields = domainClass.getDeclaredFields();
			for (Field field : fields) {
				getLog().info("Field name: " + field.getName());
			}
		}
	}
}
