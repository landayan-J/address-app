package dev.ko.app.overview;

import javafx.beans.binding.Bindings;

import dev.ko.core.controller.Controller;
import dev.ko.core.loader.LoaderFactory;
import dev.ko.model.Person;
import dev.ko.utils.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;

public class Person_OverViewController extends Controller {

    @FXML
    private TableView<Person> personTableView;
    @FXML
    private TableColumn<Person, String> firstnameTableColumn;
    @FXML
    private TableColumn<Person, String> lastnameTableColumn;

    @FXML
    private Label firstnameLabel;
    @FXML
    private Label lastnameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalcodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

    @FXML
    private Button newButton;

    @FXML
    private void handleNew() {
        LoaderFactory.load("overview/form/PersonForm", app);
    }

    @FXML
    private void initialize() {
        firstnameTableColumn.setCellValueFactory(cell -> cell.getValue().firstnameProperty());
        lastnameTableColumn.setCellValueFactory(cell -> cell.getValue().lastnameProperty());
    }

    private Person selectedPerson;

    @Override
    protected void load_fields() {
        personTableView.setItems(app.getPersonlist());
        personTableView.setRowFactory(rowItem -> {
            TableRow<Person> row = new TableRow<>();
            ContextMenu rowMenu = new ContextMenu();

            MenuItem editMenu = new MenuItem("Edit Record");
            editMenu.setOnAction(e -> {
                LoaderFactory.load("overview/form/PersonForm", app, selectedPerson);
            });
            MenuItem deleteMenu = new MenuItem("Delete Record");
            deleteMenu.setOnAction(e -> {
                app.getPersonlist().remove(selectedPerson);
            });

            MenuItem clearMenu = new MenuItem("Clear All Records");
            rowMenu.getItems().addAll(editMenu, deleteMenu, clearMenu);
            clearMenu.setOnAction(e -> {
                app.getPersonlist().clear();
                personTableView.getSelectionModel().select(null);
            });

            row.contextMenuProperty().bind(Bindings.createObjectBinding(() -> {
                if (row.getItem() != null)
                    return rowMenu;
                return null;
            }, row.itemProperty()));

            return row;
        });
    }

    @Override
    protected void load_bindings() {
        _bind_labelProperties();

    }

    @Override
    protected void load_listeners() {
        personTableView.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue) -> {
            selectedPerson = newValue;
            _bind_labelProperties();
        });
    }

    private void _bind_labelProperties() {
        if (selectedPerson != null) {
            firstnameLabel.textProperty().bind(selectedPerson.firstnameProperty());
            lastnameLabel.textProperty().bind(selectedPerson.lastnameProperty());
            streetLabel.textProperty().bind(selectedPerson.streetProperty());
            cityLabel.textProperty().bind(selectedPerson.cityProperty());
            postalcodeLabel.textProperty().bind(selectedPerson.postalcodeProperty());
            birthdayLabel.textProperty().bind(Bindings.createStringBinding(() -> {
                return DateUtil.format(selectedPerson.getBirthdate());
            }, selectedPerson.birthdateProperty()));
        } else {
            firstnameLabel.textProperty().unbind();
            streetLabel.textProperty().unbind();
            lastnameLabel.textProperty().unbind();
            cityLabel.textProperty().unbind();
            postalcodeLabel.textProperty().unbind();
            birthdayLabel.textProperty().unbind();

            firstnameLabel.setText("empty");
            lastnameLabel.setText("empty");
            streetLabel.setText("empty");
            cityLabel.setText("empty");
            postalcodeLabel.setText("empty");
            birthdayLabel.setText("empty");
        }
    }
}
