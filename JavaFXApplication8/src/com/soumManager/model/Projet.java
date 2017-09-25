package com.soumManager.model;

import com.agenda.model.Adresse;
import com.agenda.model.Adresse_type;
import java.util.UUID;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public final class Projet {
    private final String id;
    private final ObservableList<Adresse> listeEntreprises = FXCollections.observableArrayList();
    private final ObservableList<ListePrix> listesPrix = FXCollections.observableArrayList();
    private final ObservableList<Adresse_type> listeTypeAdresses = FXCollections.observableArrayList();
    private int dateAdjudication = 0;
    private String idListePrixAdjudication = null;
    
    private final StringProperty numProjet = new SimpleStringProperty("---");
    private final StringProperty nomProjet = new SimpleStringProperty("inconnu");

    public Projet(String numProjet, String nomProjet){
        this.id = UUID.randomUUID().toString();
        setNomProjet(nomProjet);
        setNumProjet(numProjet);
    }
    
    //ADJUDICATION
    public void setAdjudication(int date, String idListePrix){
        dateAdjudication = date;
        idListePrixAdjudication = idListePrix;
    }
    public String getIdListePrixAdjuication(){
        return idListePrixAdjudication;
    }
    public int getDateAdjudication(){
        return dateAdjudication;
    }

    public ObservableList<ListePrix> getListesPrix(){
        return listesPrix;
    }
    public void addListePrix(ListePrix liste){
        listesPrix.add(liste);
    }
    
    
    public ObservableList<Adresse> getListesEntreprises(){
        return listeEntreprises;
    }
    public void addEntreprise(Adresse adresse){
        listeEntreprises.add(adresse);
    }
    
    public String getIdProjet(){
        return id;
    }
    
    public String getNomProjet() {
        return nomProjet.get();
    }

    public void setNomProjet(String value) {
        nomProjet.set(value);
    }

    public StringProperty nomProjetProperty() {
        return nomProjet;
    }

    public String getNumProjet() {
        return numProjet.get();
    }

    public void setNumProjet(String value) {
        numProjet.set(value);
    }

    public StringProperty numProjetProperty() {
        return numProjet;
    }
}
