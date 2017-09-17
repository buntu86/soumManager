package com.soumManager.zdrash;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Position {

    private IntegerProperty numCatalog, yearCatalog, numPos, idTypePos;
    private FloatProperty prix;
    private StringProperty text;    
    
    
    private String idChapter = "";
    private int numPosition = 0;
    
    public Position(String idChapter, String numPos){
        setIdChapter(idChapter);
        setNumPosition(numPos);
    }
    
    //SET
    public void setIdChapter(String id){
        this.idChapter = id;
    }
    public void setNumPosition(int num){
        this.numPosition = num;
    }
    public void setNumPosition(String num){
        if(num!=null)
            this.numPosition = Integer.parseInt(num);
        else
            this.numPosition = 0;
    }
    
    //ADD
    //public void add
}
