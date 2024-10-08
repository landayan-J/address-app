package dev.ko.app.overview;

import java.io.IOException;

import dev.ko.App;
import dev.ko.core.loader.Loader;
import javafx.scene.Parent;

public class Person_OverViewLoader extends Loader {

    public Person_OverViewLoader(App app) {
        super("overview/PersonOverview", app);
    }

    @Override
    public void load() {
        try {
            Parent root = loader.load();
            app.getApplicationRoot().setCenter(root);
            root.setOnMousePressed(e -> app.getApplicationRoot().requestFocus());

            Person_OverViewController controller = loader.getController();
            controller.load(app);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
