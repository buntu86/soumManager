package com.soumManager.view;

import com.soumManager.utils.Log;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

public class AgendaEditController implements Initializable {

    private int id=0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Log.msg(0, "id " + id);
    }    

    void setId(int idSelected) {
        this.id = idSelected;
    }
}
