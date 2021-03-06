package io.github.oliviercailloux.javaee_jpa_jsf.utils;

import javax.servlet.ServletContext;

import org.ocpsoft.rewrite.annotation.RewriteConfiguration;
import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.config.Direction;
import org.ocpsoft.rewrite.servlet.config.Forward;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.Path;

@RewriteConfiguration
public class RewriteConfigurationProvider extends HttpConfigurationProvider {
	@Override
	public Configuration getConfiguration(final ServletContext context) {
		return ConfigurationBuilder.begin().addRule().when(Direction.isInbound().and(Path.matches("/{viewId}")))
				.perform(Forward.to("/faces/{viewId}.xhtml"));
	}

	@Override
	public int priority() {
		return 10;
	}
}