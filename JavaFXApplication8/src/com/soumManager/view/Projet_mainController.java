package com.soumManager.view;

import com.soumManager.MainApp;
import com.soumManager.model.ListePrix;
import com.soumManager.utils.Log;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Projet_mainController implements Initializable {

    @FXML
    private TableView<ListePrix> tableView;
    @FXML
    private TableColumn<ListePrix, String> titre;
    @FXML
    private TableColumn<ListePrix, String> entreprise;
    
    private RootLayoutController rootLayout;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        titre.setCellValueFactory(cellData -> cellData.getValue().titreProperty());
        entreprise.setCellValueFactory(cellData -> cellData.getValue().entrepriseNameProperty());
    }    

    void setRootLayout(RootLayoutController aThis) {
        this.rootLayout = aThis;
        tableView.setItems(rootLayout.getProjet().getListesPrix());        
    }

    @FXML
    private void openAddListePrix(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/com/soumManager/view/Projet_addListePrix.fxml"));            
            AnchorPane addListePrixlayout = (AnchorPane) loader.load();  
            Stage addListePrixStage = new Stage();
            addListePrixStage.setTitle("Ajout liste de prix");
            addListePrixStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(addListePrixlayout);
            addListePrixStage.setScene(scene);            
            Projet_addListePrixController controller = loader.getController();
            controller.setStage(addListePrixStage);
            controller.setRootLayout(this.rootLayout);

            addListePrixStage.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent t) -> {
                if(t.getCode()==KeyCode.ESCAPE)
                {
                    addListePrixStage.close();
                    Log.msg(0, "addListePrixStage : esc");
                }
            });            

            addListePrixStage.showAndWait();

        } catch (Exception e) {
            Log.msg(1, "openAddListePrix | " + e.getMessage());
        }           
    }
}
