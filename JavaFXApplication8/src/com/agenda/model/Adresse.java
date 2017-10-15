package com.agenda.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public final class Adresse {
    private int id, idAdresseType;
    private final StringProperty nom1 = new SimpleStringProperty("nom inconnu");
    private final StringProperty nom2 = new SimpleStringProperty("");
    private final StringProperty adresse1 = new SimpleStringProperty("");
    private final StringProperty adresse2 = new SimpleStringProperty("");
    private final StringProperty lieu = new SimpleStringProperty("");
    private final StringProperty tel1 = new SimpleStringProperty("");
    private final StringProperty tel2 = new SimpleStringProperty("");
   
    private final IntegerProperty npa = new SimpleIntegerProperty(0);
    private final StringProperty mail = new SimpleStringProperty("");

    public Adresse(int id, String nom1, String nom2, String adresse1, String adresse2, String lieu, int npa, String tel1, String tel2, String mail, int idAdresseType){
        this.id = id;
        setNom1(nom1);
        setNom2(nom2);
        setAdresse1(adresse1);
        setAdresse2(adresse2);
        setLieu(lieu);
        setNpa(npa);
        setTel1(tel1);
        setTel2(tel2);
        setMail(mail);
        setIdAdresseType(idAdresseType);
    }

    public int getId(){
        return this.id;
    }

    public String getTel2() {
        return tel2.get();
    }

    public void setTel2(String value) {
        tel2.set(value);
    }

    public StringProperty tel2Property() {
        return tel2;
    }

    public String getTel1() {
        return tel1.get();
    }

    public void setTel1(String value) {
        tel1.set(value);
    }

    public StringProperty tel1Property() {
        return tel1;
    }

    public int getIdAdresseType() {
        return idAdresseType;
    }

    public void setIdAdresseType(int value) {
        idAdresseType = value;
    }
        
    public int getNpa() {
        return npa.get();
    }

    public void setNpa(int value) {
        npa.set(value);
    }

    public IntegerProperty npaProperty() {
        return npa;
    }

    public String getMail() {
        return mail.get();
    }

    public void setMail(String value) {
        mail.set(value);
    }

    public StringProperty mailProperty() {
        return mail;
    }
    
    public String getLieu() {
        return lieu.get();
    }

    public void setLieu(String value) {
        lieu.set(value);
    }

    public StringProperty lieuProperty() {
        return lieu;
    }

    public String getAdresse2() {
        return adresse2.get();
    }

    public void setAdresse2(String value) {
        adresse2.set(value);
    }

    public StringProperty adresse2Property() {
        return adresse2;
    }
    

    public String getAdresse1() {
        return adresse1.get();
    }

    public void setAdresse1(String value) {
        adresse1.set(value);
    }

    public StringProperty adresse1Property() {
        return adresse1;
    }
    

    public String getNom2() {
        return nom2.get();
    }

    public void setNom2(String value) {
        nom2.set(value);
    }

    public StringProperty nom2Property() {
        return nom2;
    }

    public String getNom1() {
        return nom1.get();
    }

    public void setNom1(String value) {
        nom1.set(value);
    }

    public StringProperty nom1Property() {
        return nom1;
    }

    @Override
    public String toString() {
        return getNom1();
    }
}
