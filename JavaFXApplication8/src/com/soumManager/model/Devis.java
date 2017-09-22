package com.soumManager.model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Devis {
    private StringProperty date = new SimpleStringProperty("01.01.2000");
    private FloatProperty escompte = new SimpleFloatProperty(0.00f);
    
    private String id = "0";
    private StringProperty idEntreprise = new SimpleStringProperty("0");
    private FloatProperty rabais1 = new SimpleFloatProperty(0.00f);
    private FloatProperty rabais2 = new SimpleFloatProperty(0.00f);
    private StringProperty remarques = new SimpleStringProperty("");

    public StringProperty dateProperty() {
        return date;
    }
    public FloatProperty escompteProperty() {
        return escompte;
    }
    
    public String getDate() {
        return date.get();
    }
    public float getEscompte() {
        return escompte.get();
    }
    public String getId() {
        return id;
    }
    public String getIdEntreprise() {
        return idEntreprise.get();
    }
    public float getRabais1() {
        return rabais1.get();
    }
    public float getRabais2() {
        return rabais2.get();
    }
    
    public String getRemarques() {
        return remarques.get();
    }
    public StringProperty idEntrepriseProperty() {
        return idEntreprise;
    }
    public FloatProperty rabais1Property() {
        return rabais1;
    }
    public FloatProperty rabais2Property() {
        return rabais2;
    }

    public StringProperty remarquesProperty() {
        return remarques;
    }
    public void setDate(String value) {
        date.set(value);
    }

    public void setEscompte(float value) {
        escompte.set(value);
    }
    public void setId(String id) {
        this.id = id;
    }


    public void setIdEntreprise(String value) {
        idEntreprise.set(value);
    }



    public void setRabais1(float value) {
        rabais1.set(value);
    }
    public void setRabais2(float value) {
        rabais2.set(value);
    }
    public void setRemarques(String value) {
        remarques.set(value);
    }
}
