package com.soumManager.model;

import java.util.UUID;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public final class ListePrix {
    private final String id;
    private final StringProperty titre = new SimpleStringProperty("titre inconnu");
    private final StringProperty idEntreprise = new SimpleStringProperty("-");
    private final StringProperty remarques = new SimpleStringProperty("");
    private final FloatProperty rabais1 = new SimpleFloatProperty(0.00f);
    private final FloatProperty rabais2 = new SimpleFloatProperty(0.00f);
    private final FloatProperty escompte = new SimpleFloatProperty(0.00f);
    private final IntegerProperty date = new SimpleIntegerProperty(0);
    

    public ListePrix(String titre, String idEntreprise, Float rabais1, Float rabais2, Float escompte, String remarques, int date){
        id = UUID.randomUUID().toString();
        setTitre(titre);
        setIdEntreprise(idEntreprise);
        setRabais1(rabais1);
        setRabais2(rabais2);
        setEscompte(escompte);
        setRemarques(remarques);
        setDate(date);
    }
    
    public String getId(){
        return id;
    }
    
    public String getRemarques() {
        return remarques.get();
    }

    public void setRemarques(String value) {
        remarques.set(value);
    }

    public StringProperty remarquesProperty() {
        return remarques;
    }

    public int getDate() {
        return date.get();
    }

    public void setDate(int value) {
        date.set(value);
    }

    public IntegerProperty dateProperty() {
        return date;
    }

    public float getEscompte() {
        return escompte.get();
    }

    public void setEscompte(float value) {
        escompte.set(value);
    }

    public FloatProperty escompteProperty() {
        return escompte;
    }

    public float getRabais2() {
        return rabais2.get();
    }

    public void setRabais2(float value) {
        rabais2.set(value);
    }

    public FloatProperty rabais2Property() {
        return rabais2;
    }

    public float getRabais1() {
        return rabais1.get();
    }

    public void setRabais1(float value) {
        rabais1.set(value);
    }

    public FloatProperty rabais1Property() {
        return rabais1;
    }

    public String getIdEntreprise() {
        return idEntreprise.get();
    }

    public void setIdEntreprise(String value) {
        idEntreprise.set(value);
    }

    public StringProperty idEntrepriseProperty() {
        return idEntreprise;
    }


    
    public String getTitre() {
        return titre.get();
    }

    public void setTitre(String value) {
        titre.set(value);
    }

    public StringProperty titreProperty() {
        return titre;
    }
    
}
