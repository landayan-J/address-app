package dev.ko.app;

import java.io.IOException;

import dev.ko.App;
import dev.ko.core.loader.Loader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Birthday_StatisticsLoader extends Loader {

    public Birthday_StatisticsLoader(App app) {
        super("BIRTHDAY_STATISTICS", app);
    }

    @Override
    public void load() {
        try {
            StackPane root = loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(
                    getClass().getResource("/dev/ko/assets/theme/SKIN.css")
                            .toExternalForm());

             Birthday_StatisticsController controller = loader.getController();
             controller.load(app);

            Stage statisticsStage = new Stage();
            statisticsStage.setTitle("Address App - Birthday Statistics");
            statisticsStage.getIcons()
                    .add(new Image(
                            getClass().getResource("/dev/ko/assets/img/favicon.png")
                                    .toExternalForm()));
            statisticsStage.initOwner(app.getApplicationStage());
            statisticsStage.initModality(Modality.APPLICATION_MODAL);
            statisticsStage.setResizable(false);
            statisticsStage.setScene(scene);
            statisticsStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
