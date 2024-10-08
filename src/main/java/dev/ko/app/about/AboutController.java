package dev.ko.app.about;

import dev.ko.core.controller.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

public class AboutController extends Controller {
    @FXML
    private ImageView logo;
    @FXML 
    private Label nameLabel;
    @FXML
    private  Label addressLabel;
    @FXML
    private Label email;
    @FXML
    private Label cellLabel;
    @FXML
    private TextArea description;

    @Override
    protected void load_fields() {

    }
    

    @Override
    protected void load_bindings() {
       
    }

    @Override
    protected void load_listeners() {
        
    }

}
