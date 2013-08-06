package com.esofthead.mycollab.flex.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Execute;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

@Mojo(name = "flex-generator", defaultPhase = LifecyclePhase.COMPILE)
@Execute(goal="flex-generator", phase=LifecyclePhase.COMPILE)
public class As3GeneratorMojo extends AbstractMojo {

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		getLog().info("Hello world");
	}

}
