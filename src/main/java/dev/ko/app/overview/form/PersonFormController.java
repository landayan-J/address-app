package dev.ko.app.overview.form;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import dev.ko.App;
import dev.ko.core.controller.Controller;
import dev.ko.model.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;

public class PersonFormController extends Controller {
  @FXML
  private TextField firstnameTextField;
  @FXML
  private Label firstnameError;

  @FXML
  private TextField lastnameTextField;
  @FXML
  private Label lastnameError;

  @FXML
  private TextField streetTextField;
  @FXML
  private Label streetError;

  @FXML
  private TextField cityTextField;
  @FXML
  private Label cityError;

  @FXML
  private TextField postalcodeTextField;
  @FXML
  private Label postalcodeError;

  @FXML
  private DatePicker birthdayField;
  @FXML
  private Label birthdayError;

  @FXML
  private Button submitButton;

  @FXML
  private void handleSubmit() {
    if (params.isEmpty()){
      app.getPersonlist().add(0, person);

    }else{
    int personIdx  = app.getPersonlist().indexOf(person);
    app.getPersonlist().set(personIdx, person);
    
    }
    formStage.close();
  }

  @FXML
  private void handleCancel() {
    formStage.close();
  }

  private Person person;
  
  private Stage formStage;
  

  public void load(App app, Stage stage, List<Serializable> params){
    formStage = stage;
    super.load(app, params);
  }
  
  @Override
  protected void load_fields() {
    if (params.isEmpty())
      person = new Person();
    else
    person = new Person((Person) getParameter(0));
  }

  @Override
  protected void load_bindings() {
    firstnameTextField.textProperty().bindBidirectional(person.firstnameProperty());
    firstnameError.visibleProperty().bind(Bindings.isEmpty(person.firstnameProperty()));

    lastnameTextField.textProperty().bindBidirectional(person.lastnameProperty());
    lastnameError.visibleProperty().bind(Bindings.isEmpty(person.lastnameProperty()));

    streetTextField.textProperty().bindBidirectional(person.streetProperty());
    streetError.visibleProperty().bind(Bindings.isEmpty(person.streetProperty()));

    cityTextField.textProperty().bindBidirectional(person.cityProperty());
    cityError.visibleProperty().bind(Bindings.isEmpty(person.cityProperty()));

    postalcodeTextField.textProperty().bindBidirectional(person.postalcodeProperty());
    postalcodeError.visibleProperty().bind(Bindings.isEmpty(person.postalcodeProperty()));

    BooleanBinding checkBirthdayBinding = Bindings.createBooleanBinding(() ->{
      LocalDate birthdate = person.getBirthdate();
      return(birthdate.isAfter(LocalDate.now().minusYears(18)))
      || birthdate.isBefore(LocalDate.now().minusYears(65));
    }, person.birthdateProperty());
    birthdayField.valueProperty().bindBidirectional(person.birthdateProperty());
    birthdayError.visibleProperty().bind(checkBirthdayBinding);

    BooleanBinding checkErrorBinding = Bindings.or(
      firstnameError.visibleProperty(), lastnameError.visibleProperty())
      .or(streetError.visibleProperty())
      .or(cityError.visibleProperty())
      .or(postalcodeError.visibleProperty())
      .or(birthdayError.visibleProperty());
    
    submitButton.disableProperty().bind(checkErrorBinding);
  }

  @Override
  protected void load_listeners() {
    birthdayField.getEditor().setOnMouseClicked(e ->{
      birthdayField.show();
    });
  }

}
