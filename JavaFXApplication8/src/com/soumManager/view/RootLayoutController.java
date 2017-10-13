package com.soumManager.view;

import com.soumManager.MainApp;
import com.soumManager.utils.Config;
import com.soumManager.utils.Log;
import java.io.File;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

public class RootLayoutController {

    private MainApp mainApp;
    private AnchorPane referenceLayout, sia451Layout, agendaLayout;
    private BorderPane rootLayout;    
    
    @FXML
    private void handleExit(){
        System.exit(0);
    }
    
    @FXML
    private void handleCloseRef(){
        rootLayout.getChildren().remove(referenceLayout);
    }
    
    @FXML
    private void handleOpenRef(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/com/soumManager/view/Reference.fxml"));            
            referenceLayout = (AnchorPane) loader.load();     
            ReferenceController controller = loader.getController();
            
            rootLayout.setCenter(referenceLayout);

        } catch (Exception e) {
            Log.msg(1, "showReferences | " + e.getMessage());
        }
    }
    
    @FXML
    private void handleOpenAgenda(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/com/soumManager/view/Agenda.fxml"));            
            agendaLayout = (AnchorPane) loader.load();     
            AgendaController controller = loader.getController();
            
            rootLayout.setCenter(agendaLayout);

        } catch (Exception e) {
            Log.msg(1, "showAgenda | " + e.getMessage());
        }        
    }
    @FXML
    private void handleCloseAgenda(){
        rootLayout.getChildren().remove(agendaLayout);
    }    
    
    @FXML
    private void handleOpenSia451(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("sia451", "*.01s"),
            new FileChooser.ExtensionFilter("sia451", "*.451")
        );
        
        File temp = new File("D:\\temp");
        if(temp.isDirectory())
            fileChooser.setInitialDirectory(temp);
        File selectedFile = fileChooser.showOpenDialog(null);
        if(selectedFile != null)
        {   
            try {
                Config.setFileSia451(selectedFile);
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainApp.class.getResource("/com/soumManager/view/Sia451.fxml"));
                sia451Layout = (AnchorPane) loader.load();
                Sia451Controller controller = loader.getController();
                
                rootLayout.setCenter(sia451Layout);
                
            } catch (Exception e) {
                Log.msg(1, "showSia451 | " + e.getMessage());
            }
        }   
        
        else
            Log.msg(1, "Selection du fichier annulé");
    }    
    @FXML
    private void handleCloseSia451(){
        rootLayout.getChildren().remove(sia451Layout);
    }    
    
    public RootLayoutController(){
        
    }    

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }     

    public void setRootLayout(BorderPane rootLayout) {
        this.rootLayout = rootLayout;
    }  
}