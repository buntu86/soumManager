package com.soumManager.model;

import com.soumManager.utils.Log;
import java.nio.file.Path;

public class Sia451 {
    
    private Projet projet;    
    
    public Sia451(Path path){
        String filename = path.getFileName().toString().toLowerCase();
        
        if(filename.substring(filename.lastIndexOf('.') + 1).equals("451"))
        {
            Sia451_XML sia = new Sia451_XML(path);
            this.projet = sia.getProjet();
        }

        if(filename.substring(filename.lastIndexOf('.') + 1).equals("01s"))
        {
            Sia451_01s sia = new Sia451_01s(path);
            this.projet = sia.getProjet();
        }  
        
        for(Position element : projet.getListPosition()){
            Log.msg(0, "CAN " + element.getNumCatalog() + " " + element.getNumPos());
        }
    }
    
    public Projet getProjet(){
        return this.projet;
    }
}
