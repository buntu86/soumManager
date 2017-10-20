package com.soumManager.model;

import java.util.UUID;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public final class ListePrix {
    private final String id;
    private final StringProperty titre = new SimpleStringProperty("titre inconnu");
    private final IntegerProperty idEntreprise = new SimpleIntegerProperty(0);
    private final StringProperty remarques = new SimpleStringProperty("");
    private final IntegerProperty rabais1 = new SimpleIntegerProperty(0);
    private final IntegerProperty rabais2 = new SimpleIntegerProperty(0);
    private final IntegerProperty escompte = new SimpleIntegerProperty(0);
    private final IntegerProperty date = new SimpleIntegerProperty(0);
    

    public ListePrix(String titre, int idEntreprise, int rabais1, int rabais2, int escompte, String remarques, int date){
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

    public int getEscompte() {
        return escompte.get();
    }

    public void setEscompte(int value) {
        escompte.set(value);
    }

    public IntegerProperty escompteProperty() {
        return escompte;
    }

    public int getRabais2() {
        return rabais2.get();
    }

    public void setRabais2(int value) {
        rabais2.set(value);
    }

    public IntegerProperty rabais2Property() {
        return rabais2;
    }

    public int getRabais1() {
        return rabais1.get();
    }

    public void setRabais1(int value) {
        rabais1.set(value);
    }

    public IntegerProperty rabais1Property() {
        return rabais1;
    }

    public Integer getIdEntreprise() {
        return idEntreprise.get();
    }

    public void setIdEntreprise(int value) {
        idEntreprise.set(value);
    }

    public IntegerProperty idEntrepriseProperty() {
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
