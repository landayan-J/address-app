package dev.ko.model.person;

import java.util.LinkedList;
import java.util.List;

import dev.ko.model.Person;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@XmlRootElement(name = "personlist")
public class PersonListWrapper {
    public static List<Person> GENERATE() {
        List<Person> list = new LinkedList<>();
        for (int i = 0; i < 50; i++)
            list.add(Person.GENERATE());
        return list;
    }

    private ObservableList<Person> personlist;

    public PersonListWrapper() {
        personlist = FXCollections.observableArrayList();
    }

    public PersonListWrapper(List<Person> list) {
        personlist = FXCollections.observableArrayList(list);
    }

    @XmlElement(name = "person")
    public ObservableList<Person> getPersonlist() {
        return personlist;
    }

    public void setPersonlist(List<Person> list) {
        personlist.setAll(list);
    }
}
