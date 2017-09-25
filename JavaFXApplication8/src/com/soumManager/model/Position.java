package com.soumManager.model;

import java.util.UUID;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public final class Position {
    private String id;
    private final IntegerProperty numPosition = new SimpleIntegerProperty(0);
    private final StringProperty nomPosition = new SimpleStringProperty("position inconnue");
    private final StringProperty unite = new SimpleStringProperty("--");
    private final StringProperty idChapitre = new SimpleStringProperty("-");
    private final BooleanProperty horsSoum = new SimpleBooleanProperty(false);

    public Position(int numPosition, String nomPosition, String unite, String idChapitre, Boolean horsSoum){
        id = UUID.randomUUID().toString();
        setNumPosition(numPosition);
        setNomPosition(nomPosition);
        setUnite(unite);
        setIdChapitre(idChapitre);
        setHorsSoum(horsSoum);
    }
    
    public String getIdPosition(){
        return id;
    }
    
    public boolean isHorsSoum() {
        return horsSoum.get();
    }

    public void setHorsSoum(boolean value) {
        horsSoum.set(value);
    }

    public BooleanProperty horsSoumProperty() {
        return horsSoum;
    }

    public String getIdChapitre() {
        return idChapitre.get();
    }

    public void setIdChapitre(String value) {
        idChapitre.set(value);
    }

    public StringProperty idChapitreProperty() {
        return idChapitre;
    }


    
    public int getNumPosition() {
        return numPosition.get();
    }

    public void setNumPosition(int value) {
        numPosition.set(value);
    }

    public IntegerProperty numPositionProperty() {
        return numPosition;
    }


    public String getNomPosition() {
        return nomPosition.get();
    }

    public void setNomPosition(String value) {
        nomPosition.set(value);
    }

    public StringProperty nomPositionProperty() {
        return nomPosition;
    }


    public String getUnite() {
        return unite.get();
    }

    public void setUnite(String value) {
        unite.set(value);
    }

    public StringProperty uniteProperty() {
        return unite;
    }
    
    
    
    public Position(){
    
    }
}
