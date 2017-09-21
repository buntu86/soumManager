package com.soumManager.model;

import java.util.UUID;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Chapitre {
    private String id = "";
    private IntegerProperty numChapitre = new SimpleIntegerProperty(0);
    private IntegerProperty annee = new SimpleIntegerProperty(1990);
    private StringProperty nomChapitre = new SimpleStringProperty("inconnu");
    
    public Chapitre(int numChapitre, int annee, String nomChapitre){
        this.id = UUID.randomUUID().toString();
        this.numChapitre.set(numChapitre);
        this.annee.set(annee);
        this.nomChapitre.set(nomChapitre);
    }
}
