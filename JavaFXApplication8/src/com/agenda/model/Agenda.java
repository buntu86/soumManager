package com.agenda.model;

import com.agenda.data.Sql_agenda;
import javafx.collections.ObservableList;

public final class Agenda {

    public static ObservableList<Adresse> getListeAdresses(){
        return Sql_agenda.getAdresses();
    }
    
    public static ObservableList<Adresse> getListeAdresses_byIdTypes(int id){
        return Sql_agenda.getAdresses_byIdTypes(id);
    }
    
    public static ObservableList<Adresse_type> getListeTypes(){
        return Sql_agenda.getTypes();
    }    
    
    public static void addAdresse(Adresse adresse) {
        Sql_agenda.add(adresse);
    }
}
