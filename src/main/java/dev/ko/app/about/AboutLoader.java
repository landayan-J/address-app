package dev.ko.app.about;

import java.io.IOException;
import dev.ko.App;
import dev.ko.core.loader.Loader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class AboutLoader extends Loader {

    public AboutLoader(App app) {
        super("about/ABOUT", app);
    }

    @Override
    public void load() {
        try {
            StackPane root = loader.load();
            Scene scene = new Scene(root);
            // scene.getStylesheets().add(
            //         getClass().getResource("/dev/ko/assets/theme/SKIN.css")
            //                 .toExternalForm());

            AboutController controller = loader.getController();
            controller.load(app);

            Stage aboutStage = new Stage();
            aboutStage.setTitle("Address App - About");
            aboutStage.getIcons()
                    .add(new Image(
                            getClass().getResource("/dev/ko/assets/img/favicon.png")
                                    .toExternalForm()));
            aboutStage.initOwner(app.getApplicationStage());
            aboutStage.initModality(Modality.APPLICATION_MODAL);
            aboutStage.setResizable(false);
            aboutStage.setScene(scene);
            aboutStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
