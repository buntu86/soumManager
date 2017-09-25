package com.agenda.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Adresse_contact {
        private int id = 0;
        private StringProperty nom = new SimpleStringProperty("nom");
        private StringProperty prenom = new SimpleStringProperty("prenom");
        private StringProperty fonction = new SimpleStringProperty("fonction");
        private StringProperty tel1 = new SimpleStringProperty("tel1");
        private StringProperty tel2 = new SimpleStringProperty("tel2");
        private StringProperty mail1 = new SimpleStringProperty("mail1");
        private StringProperty mail2 = new SimpleStringProperty("mail2");

    public Adresse_contact(){

    }
}
