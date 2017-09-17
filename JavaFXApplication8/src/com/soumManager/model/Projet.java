package com.soumManager.model;

import java.util.Optional;
import static java.util.stream.Collectors.toCollection;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Projet {
    
    private StringProperty numProjet, nomProjet;
    private ObservableList<Catalog> listCatChapter = FXCollections.observableArrayList();
    private ObservableList<Position> listPosition = FXCollections.observableArrayList();    
    
    public Projet(String numProjet, String nomProjet) {
        this.numProjet = new SimpleStringProperty(numProjet);
        this.nomProjet = new SimpleStringProperty(nomProjet);
        this.listCatChapter.add(0, new Catalog(900, 0, "Récapitulatif", ""));
    }

    // SET
    public void setNumProjet(String num){
        this.numProjet.set(num);
    }
    public void setNomProjet(String nom){
        this.nomProjet.set(nom);
    }

    // ADD
    public void addCatChapter(Catalog cat){
        listCatChapter.add(cat);
    }
    public void addPosition(Position pos){
        
        Optional<Catalog> tmp = listCatChapter
                .stream()
                .filter(p -> p.getRefCatalog().equals(pos.getRefCatalog()))
                .findFirst();
        
        pos.setNumCatalog(tmp.get().getNumCatalog());
        pos.setYearCatalog(tmp.get().getYear());
        
        listPosition.add(pos);
    }
    public void addQuantite(String ref, String quantite){
        Optional<Position> tmp = listPosition
                .stream()
                .filter(p -> p.getIdXml().equals(ref))
                .findFirst();
        
        if(tmp.isPresent())
        {
            float floatQuantite=0;

            quantite = quantite.replaceAll("[^\\d]", "");
            if(quantite.isEmpty())
                tmp.get().setQuantite(0);
            else
                tmp.get().setQuantite(Float.parseFloat(quantite)/1000);
        }
    }
    public void addPrice(String ref, String price){
        Optional<Position> tmp = listPosition
                .stream()
                .filter(p -> p.getIdXml().equals(ref))
                .findFirst();
        
        if(tmp.isPresent())
        {
            float floatPrice=0;

            price = price.replaceAll("[^\\d]", "");
            
            if(price.isEmpty())
                tmp.get().setPrix(0);
            else
                tmp.get().setPrix(Float.parseFloat(price)/1000);
        }
    }
    
    // GET
    public ObservableList<Catalog> getListCatChapter(){
        return this.listCatChapter;
    }
    public ObservableList<Catalog> getListCatChapterToRecap(){
        ObservableList<Catalog> tmp = listCatChapter
                .stream()
                .filter(c -> c.getNumCatalog()!=0)
                .collect(toCollection(FXCollections::observableArrayList));

        return tmp;
    }
    public ObservableList<Position> getListPosition(){
        return this.listPosition;
    }
    public String getTitreProjet(){
        return this.numProjet.get() + " - " + this.nomProjet.get();
    }

    public void addUnit(String ref, String unit) {
        Optional<Position> tmp = listPosition
                .stream()
                .filter(p -> p.getIdXml().equals(ref))
                .findFirst();
        
        if(tmp.isPresent())
            tmp.get().setUnit(unit);
    }
}
