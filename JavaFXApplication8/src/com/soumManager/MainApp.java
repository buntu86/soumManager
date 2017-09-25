package com.soumManager;

import com.agenda.model.Adresse;
import com.agenda.model.Agenda;
import com.soumManager.data.Sql_catalog;
import com.soumManager.utils.Config;
import com.soumManager.utils.Log;
import com.soumManager.view.RootLayoutController;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import static javafx.application.Application.launch;

public class MainApp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    
    public static void main(String[] args) {
        launch(args);               
    }    

    @Override
    public void start(Stage primaryStage) { 
        this.primaryStage = primaryStage;
        setTitlePrimaryStage("");
        
        showRootLayout();        

        Config.iniConfig();
        Sql_catalog.ini();
        Agenda.addAdresse(new Adresse(0, 
            "Nom de l entreprise 2", 
            LocalDateTime.now().toString(),
            "rue de la prog", 
            "nouvelle adresse 2", 
            "ici",
            9999, 
            "0788600766", 
            "0088545555", 
            "mon@email.com", 1));
    }

    public Stage getPrimaryStage() {
        return this.primaryStage;
    }

    public void showRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/com/soumManager/view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);   
            controller.setRootLayout(rootLayout);
            
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            Log.msg(1, "Error RootLayout.fxml | " + e.getMessage());
        }
    }  
    
    public void setTitlePrimaryStage(String str)
    {
        if(!str.isEmpty())
            str = " - " + str;
        this.primaryStage.setTitle("SoumManager" + str);
    }        
}
