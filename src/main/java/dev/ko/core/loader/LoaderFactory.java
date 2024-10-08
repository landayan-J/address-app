package dev.ko.core.loader;

import java.io.Serializable;

import dev.ko.App;

public class LoaderFactory {
    public static final String PACKAGE_NAME = "dev.ko.app";

    public static <loader> void load(String name, App app, Serializable... params) {
        name = name.replace("/", ".");
        try {
            Class<?> loaderClass = Class.forName(PACKAGE_NAME + "." + name + "Loader");
            Loader loader = (Loader) loaderClass
                    .getConstructor(App.class)
                    .newInstance(app);

            loader.setParameters(params);
            loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
