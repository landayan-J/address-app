package dev.ko;

import dev.ko.core.loader.LoaderFactory;
import dev.ko.model.Person;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {
    private Stage applicationStage;
    private BorderPane applicationRoot;

    private ObservableList<Person> personlist;

    @Override
    public void start(Stage stage) throws Exception {
        applicationStage = stage;
        personlist = FXCollections.observableArrayList();
        _init();
       
    }
    private void _init(){
        LoaderFactory.load("Root", this);
        LoaderFactory.load("overview/Person_OverView", this);
    }

    public Stage getApplicationStage() {
        return applicationStage;
    }

    public void setApplicationRoot(BorderPane root) {
        applicationRoot = root;

    }
   
    public BorderPane getApplicationRoot() {
        return applicationRoot;
    }
   
    public ObservableList<Person> getPersonlist() {
        return personlist;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
