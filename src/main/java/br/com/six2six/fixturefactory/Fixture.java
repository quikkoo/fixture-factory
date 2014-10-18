package br.com.six2six.fixturefactory;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Fixture {

	private static final Map<Class<?>, TemplateHolder> templates = new LinkedHashMap<Class<?>, TemplateHolder>();
	private static final Map<Class<?>, ObjectFactory> factories = new LinkedHashMap<Class<?>, ObjectFactory>();

	public static TemplateHolder of(Class<?> clazz) {
		TemplateHolder template = templates.get(clazz);
		if (template == null) {
			template = new TemplateHolder(clazz);
			templates.put(clazz, template);
		}

		return template;
	}

	public static ObjectFactory from(Class<?> clazz) {
		ObjectFactory factory = factories.get(clazz);
		if (factory == null) {
			factory = new ObjectFactory(of(clazz));
			factories.put(clazz, factory);
		}

		return factory;
	}

	public static <T> T gimme(String label, Class<T> clazz) {
		return from(clazz).gimme(label);
	}

	public static <T> List<T> gimme(Integer quantity, String label, Class<T> clazz) {
		return from(clazz).gimme(quantity, label);
	}
}
