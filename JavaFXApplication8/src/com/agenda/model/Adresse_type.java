package com.agenda.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public final class Adresse_type {
    private int id;
    private final StringProperty categorie = new SimpleStringProperty("catégorie inconnue");

    public Adresse_type(int id, String categorie) {
        setId(id);
        setCategorie(categorie);
    }
    
    public String getCategorie() {
        return categorie.get();
    }

    public void setCategorie(String value) {
        categorie.set(value);
    }

    public StringProperty categorieProperty() {
        return categorie;
    }

    private void setId(int id) {
        this.id = id;
    }
    
    private int getId(){
        return this.id;
    }
}
