module ko.addressapp {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires net.datafaker;
    requires jakarta.xml.bind;
    requires java.prefs;

    opens dev.ko.app to javafx.fxml;
    opens dev.ko.app.overview to javafx.fxml;
    opens dev.ko.app.overview.form to javafx.fxml;
    opens dev.ko.model.person to jakarta.xml.bind;
    opens dev.ko.app.about to javafx.fxml;


    exports dev.ko;
    exports dev.ko.model;
    exports dev.ko.libs.xml.adapter;
    exports dev.ko.app.about;
}
