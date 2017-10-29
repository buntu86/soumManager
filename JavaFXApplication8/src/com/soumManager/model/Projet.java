package com.soumManager.model;

import com.soumManager.data.Sql_Projet;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public final class Projet {
    //private final String id;
    //private final ObservableList<Adresse> listeEntreprises = FXCollections.observableArrayList();
    private ObservableList<ListePrix> listesPrix = FXCollections.observableArrayList();
    private int dateAdjudication = 0;
    private int idListePrixAdjudication = 0;
    
    private final StringProperty numProjet = new SimpleStringProperty("---");
    private final StringProperty nomProjet = new SimpleStringProperty("inconnu");
    private final StringProperty pathProjet = new SimpleStringProperty("c:\\");

    //NEW!
    public Projet(String numProjet, String nomProjet, String pathProjet){
        //this.id = UUID.randomUUID().toString();
        setNomProjet(nomProjet);
        setNumProjet(numProjet);
        setPathProjet(pathProjet);
        Sql_Projet.createProjet(numProjet, nomProjet, pathProjet);
    }

    //OPEN!
    public Projet(String pathProjet) {
        setPathProjet(pathProjet);
        Sql_Projet.setPath(pathProjet);
        hydrate();
    }
    
    //ADJUDICATION
    public void setAdjudication(int date, int idListePrix){
        dateAdjudication = date;
        idListePrixAdjudication = idListePrix;
    }
    public int getIdListePrixAdjuication(){
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
    public void setListesPrix(ObservableList<ListePrix> liste){
        this.listesPrix = liste;
    }
    
    
    /*public ObservableList<Adresse> getListesEntreprises(){
        return listeEntreprises;
    }
    public void addEntreprise(Adresse adresse){
        listeEntreprises.add(adresse);
    }*/
    
    /*public String getIdProjet(){
        return id;
    }*/
    
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
    
    public void setPathProjet(String value){
        pathProjet.set(value);
    }

    public void hydrate() {
        setNumProjet(Sql_Projet.getNumProjet());
        setNomProjet(Sql_Projet.getNomProjet());        
        setListesPrix(Sql_Projet.getListePrix());
    }
}
