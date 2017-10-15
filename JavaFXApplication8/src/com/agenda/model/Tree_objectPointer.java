package com.agenda.model;

import com.soumManager.utils.Config;
import javafx.scene.control.TreeItem;

public class Tree_objectPointer extends TreeItem{

    private int sqlId, idParent;
    private String name, type;
    
    public Tree_objectPointer(int sqlId, String name, String type, int idParent) {
        this.sqlId = sqlId;
        this.name = name;
        this.type = type;
        this.idParent = idParent;
    }

    public int getSqlId() {
        return sqlId;
    }

    public void setSqlId(int sqlId) {
        this.sqlId = sqlId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getIdParent(){
        return idParent;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
