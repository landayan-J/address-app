package dev.ko.app;

import java.io.IOException;
import dev.ko.App;
import dev.ko.core.loader.Loader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

public class RootLoader extends Loader {
  public RootLoader(App app) {
    super("ROOT", app);
  }

  @Override
  public void load() {
    try {
      BorderPane root = loader.load();
      Scene scene = new Scene(root);
      scene.getStylesheets().add(getClass().getResource("/dev/ko/assets/theme/SKIN.css")
          .toExternalForm());

      RootController controller = loader.getController();
      controller.load(app);

      
      app.getApplicationStage().getIcons().add(new Image(getClass()
          .getResource("/dev/ko/assets/img/favicon.png")
          .toExternalForm()));
      app.getApplicationStage().setMinHeight(640);
      app.getApplicationStage().setMinWidth(780);
      app.getApplicationStage().setScene(scene);
      app.getApplicationStage().show();

      app.setApplicationRoot(root);
      root.setOnMouseClicked(e -> app.getApplicationRoot().requestFocus());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
