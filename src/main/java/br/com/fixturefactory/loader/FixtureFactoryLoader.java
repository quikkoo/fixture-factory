package br.com.fixturefactory.loader;

import static br.com.fixturefactory.util.ClassLoaderUtils.getClassesForPackage;

public class FixtureFactoryLoader {

	public static void loadTemplates(String basePackage) {
        for (Class<?> clazz : getClassesForPackage(basePackage)) {
            if (!clazz.isInterface() && TemplateLoader.class.isAssignableFrom(clazz)) {
                try {
                	((TemplateLoader) clazz.newInstance()).load();
                } catch (Exception e) {
                    throw new RuntimeException(String.format("template %s not loaded", clazz.getName()), e);
                }
            }
        } 
	}
}
