package com.soumManager.view;

import com.soumManager.utils.Log;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Projet_openController implements Initializable {

    @FXML
    private TextField tf_path;
    
    @FXML
    private Button open;
    private Stage stage;
    private RootLayoutController rootLayout;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        open.setDisable(true);
        tf_path.textProperty().addListener((ob, old, new1) -> buttonEnable());
    }    

    @FXML
    private void fileSearch(){
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Ouvrir projet");        
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Projet soumManager", "*.psm"));
        File selectedFile = chooser.showOpenDialog(null);
        
        if(selectedFile!=null)
            tf_path.setText(selectedFile.toString());
    }    
    
    @FXML
    private void open(){
        rootLayout.openProjet(tf_path.getText());
        close();
    }
    @FXML    
    private void close(){
        stage.close();
    }
    
    private void buttonEnable() {
        if(!tf_path.getText().trim().isEmpty())
            open.setDisable(false);
        else
            open.setDisable(true);
    }

    void setStage(Stage projetNewStage) {
        this.stage = projetNewStage;
    }
    void setRootLayout(RootLayoutController aThis) {
        this.rootLayout = aThis;
    }
}
