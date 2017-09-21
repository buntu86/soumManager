package com.soumManager.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Adresse {
    
    private String id = "", idType = "";
    private StringProperty nom1 = new SimpleStringProperty("nom1");
    private StringProperty nom2 = new SimpleStringProperty("nom2");
    private StringProperty tel1 = new SimpleStringProperty("tel1");
    private StringProperty tel2 = new SimpleStringProperty("tel2");
    private StringProperty adresse1 = new SimpleStringProperty("adresse1");
    private StringProperty adresse2 = new SimpleStringProperty("adresse2");
    private StringProperty npa = new SimpleStringProperty("npa");
    private StringProperty lieu = new SimpleStringProperty("lieu");
    private StringProperty mail = new SimpleStringProperty("mail");
    
    
    public Adresse(){
    
    }
}
