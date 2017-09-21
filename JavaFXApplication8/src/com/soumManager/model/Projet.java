package com.soumManager.model;

import java.util.UUID;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Projet {
    private String id = "";
    private StringProperty numProjet = new SimpleStringProperty("---");
    private StringProperty nomProjet = new SimpleStringProperty("inconnu");
    private ObservableList<Adresse> listEntreprises = FXCollections.observableArrayList();
    
    public Projet(String numProjet, String nomProjet){
        this.id = UUID.randomUUID().toString();
        this.numProjet = new SimpleStringProperty(numProjet);
        this.nomProjet = new SimpleStringProperty(nomProjet);            
    }
}
