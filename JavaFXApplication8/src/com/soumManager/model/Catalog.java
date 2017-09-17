package com.soumManager.model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Catalog {
    private IntegerProperty id, year;
    private StringProperty nomCatalog, numCatalog, ref, prixCatalog;
    
    public Catalog(int id, int num, String nom, String ref){
        this.id = new SimpleIntegerProperty(id);
        this.prixCatalog = new SimpleStringProperty("0.00");
        this.numCatalog = new SimpleStringProperty("" + num);
        this.nomCatalog = new SimpleStringProperty(nom);
        this.ref = new SimpleStringProperty(ref);
        this.year = new SimpleIntegerProperty(0);
    }
    
    // SET
    public void setNumCatalog(int num){
        this.numCatalog = new SimpleStringProperty("" + num);
    }
    public void setNomCatalog(String nom){
        this.nomCatalog = new SimpleStringProperty(nom);
    }
    public void setIdCatalog(int id) {
        this.id = new SimpleIntegerProperty(id);
    }    
    public void setRefCatalog(String ref){
        this.ref = new SimpleStringProperty(ref);
    }
    public void setYear(int year){
        this.year = new SimpleIntegerProperty(year);
    }
    public void setPrixCatalog(float prix){
        this.prixCatalog = new SimpleStringProperty("" + prix);
    }
    
    // GET
    public String getNumCatalogString(){
        return numCatalog.get();
    }
    public int getNumCatalog(){
        if(numCatalog.get().isEmpty())
            numCatalog = new SimpleStringProperty("0");

        return Integer.parseInt(numCatalog.get());
    }
    public String getNomCatalog(){
        return nomCatalog.get();
    }
    public String getRefCatalog(){
        return this.ref.get();
    }
    public int getYear(){
        return this.year.get();
    }
    public String getPrix(){
        return this.prixCatalog.get();
    }
    
    //Property
    public IntegerProperty idProperty(){
        return this.id;
    }
    public IntegerProperty yearProperty(){
        return this.year;
    }
    public StringProperty nomCatProperty(){
        return this.nomCatalog;
    }
    public StringProperty numCatalogProperty(){
        return this.numCatalog;
    }
    public StringProperty refProperty(){
        return this.ref;
    }
    public StringProperty prixCatalogProperty(){
        return this.prixCatalog;
    }
    
    // UPDATE
    public void updateNumCatalog(String num){
        this.numCatalog = new SimpleStringProperty(num);
    }
    public void updateNomCatalog(String nom){
        this.nomCatalog = new SimpleStringProperty(nom);
    }    

    @Override
    public String toString() {
        return getNumCatalog() + " - " + getNomCatalog();
    }
}
