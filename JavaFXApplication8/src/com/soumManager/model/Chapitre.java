package com.soumManager.model;

import java.util.UUID;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public final class Chapitre {
    private final String id;
    private final StringProperty numChapitre = new SimpleStringProperty("---");
    private final StringProperty nomChapitre = new SimpleStringProperty("chapitre inconnu");

    public Chapitre(String numChapitre, String nomChapitre){
        id = UUID.randomUUID().toString();
        setNumChapitre(numChapitre);
        setNomChapitre(nomChapitre);
    }
    
    public String getIdChapitre() {
        return id;
    }
    
    public String getNumChapitre() {
        return numChapitre.get();
    }
    public void setNumChapitre(String value) {
        numChapitre.set(value);
    }
    public StringProperty numChapitreProperty() {
        return numChapitre;
    }

    public String getNomChapitre() {
        return nomChapitre.get();
    }
    public void setNomChapitre(String value) {
        nomChapitre.set(value);
    }
    public StringProperty nomChapitreProperty() {
        return nomChapitre;
    }
}
