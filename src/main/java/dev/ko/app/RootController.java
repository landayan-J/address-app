package dev.ko.app;

import java.io.File;

import dev.ko.model.person.PersonListWrapper;
import dev.ko.utils.RegUtil;
import dev.ko.App;
import dev.ko.core.controller.Controller;
import dev.ko.core.loader.LoaderFactory;
import dev.ko.libs.xml.JAXBLib;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class RootController extends Controller {
   private static final String REGISTRY_KEY = "xmlFilePath";

   @FXML
   private void handleNew() {
      app.getApplicationStage().setTitle("Address App - New");
      RegUtil.setValueToRegistry(REGISTRY_KEY, null, App.class);
      app.getPersonlist().clear();
   }

   @FXML
   private void handleData() {
      FileChooser fc = new FileChooser();
      fc.getExtensionFilters().add(new ExtensionFilter("XML files(*.xml)", "*.xml"));
      if (RegUtil.getValueFromRegistry(REGISTRY_KEY, App.class) != null) {
         File currentFile = new File(RegUtil.getValueFromRegistry(REGISTRY_KEY, App.class));
         fc.setInitialDirectory(currentFile.getParentFile());
      }
      File file = fc.showOpenDialog(app.getApplicationStage());
      if (file != null) {
         RegUtil.setValueToRegistry(REGISTRY_KEY, file.getPath(), App.class);
         _load_xmlFile(file);
      }
   }

   @FXML
   private void handleSave() {
      if (RegUtil.getValueFromRegistry(REGISTRY_KEY, App.class) != null) {
         File personFile = new File(RegUtil.getValueFromRegistry(REGISTRY_KEY, App.class));
         _save_xmlFile(personFile);
      } else
         handleSaveas();
   }

   @FXML
   private void handleSaveas() {
      FileChooser fc = new FileChooser();
      fc.setInitialDirectory(new File(System.getProperty("user.home") + "/Documents/"));
      fc.getExtensionFilters().add(new ExtensionFilter("XML files(*.xml)", "*.xml"));
      File file = fc.showSaveDialog(app.getApplicationStage());
      if (file != null) {
         if (!file.getPath().endsWith(".xml"))
            file = new File(file.getPath() + ".xml");
         RegUtil.setValueToRegistry(REGISTRY_KEY, file.getPath(), App.class);
         handleSave();
      }
   }

   private void _load_xmlFile(File file) {
      app.getApplicationStage().setTitle("Address App - " + file.getPath());
      PersonListWrapper personlistWrapper = JAXBLib.deserialize(file, PersonListWrapper.class);
      if (personlistWrapper != null) {
         app.getPersonlist().clear();
         app.getPersonlist().addAll(personlistWrapper.getPersonlist());
         RegUtil.setValueToRegistry(REGISTRY_KEY, file.getPath(), App.class);
      } else {
         Alert alert = new Alert(AlertType.ERROR);
         alert.setTitle("Error");
         alert.setHeaderText("Could not load data");
         alert.setContentText("Could not load data from file: " + file.getPath());
         alert.showAndWait();
      }
   }

   private void _save_xmlFile(File file) {
      app.getApplicationStage().setTitle("Address App - " + file.getPath());
      PersonListWrapper personlistWrapper = new PersonListWrapper(app.getPersonlist());
      if (!JAXBLib.serialize(file, personlistWrapper)) {
         Alert alert = new Alert(AlertType.ERROR);
         alert.setTitle("Error");
         alert.setHeaderText("Could not save data");
         alert.setContentText("Could not save data to file: " + file.getPath());
         alert.showAndWait();
      }
   }

   @FXML
   private void handleClose() {
      System.exit(0);
   }

   @FXML
   private void handleStatistic() {
       LoaderFactory.load("Birthday_Statistics", app);
   }

   @FXML
   private void handleRandom() {
      app.getPersonlist().setAll(PersonListWrapper.GENERATE());
   }

   @FXML
   private void handleClearAll() {
      app.getPersonlist().clear();
   }

   @FXML
   private void handleAbout() {
      LoaderFactory.load("about/About", app);
   }

   @Override
   protected void load_fields() {
      _initialize_dataset_from_xmlFile();
   }

   private void _initialize_dataset_from_xmlFile() {
      if (RegUtil.getValueFromRegistry(REGISTRY_KEY, App.class) == null)
         handleNew();
      else {
         File file = new File(RegUtil.getValueFromRegistry(REGISTRY_KEY, App.class));
         if (file.exists())
            _load_xmlFile(file);
         else
            handleNew();
      }
   }

   @Override
   protected void load_bindings() {
   }

   @Override
   protected void load_listeners() {
   }
}