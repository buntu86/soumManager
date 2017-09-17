package com.soumManager.model;

import com.soumManager.zdrash.Position;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Projet_CatChapter {
    private String numChapter, nomChapter, versChapter, refChapter;
    private ObservableList<Position> listPosition = FXCollections.observableArrayList();    

    public void CatChapter(){
        this.numChapter = "";
        this.nomChapter = "";
        this.versChapter = "";
        this.refChapter = "";
    }
    
    // SET
    public void setNumChapter(String num){
        this.numChapter = num;
    }
    public void setNomChapter(String nom){
        this.nomChapter = nom;
    }
    public void setVersChapter(String vers){
        this.versChapter = vers;
    }
    public void setRefChapter(String ref){
        this.refChapter = ref;
    }
    
    // ADD
    public void addPosition(Position pos){
        this.listPosition.add(pos);
    }
    
    // GET
    public String getRefChapter(){
        return this.refChapter;
    }
    public String getNumChapter(){
        return this.numChapter;
    }
    public String getNomChapter(){
        return this.nomChapter;
    }    
    
    @Override
    public String toString(){
        return numChapter + " - " + nomChapter;
    }
}
