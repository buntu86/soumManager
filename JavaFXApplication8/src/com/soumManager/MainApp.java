package com.soumManager;

import com.soumManager.data.Sql_catalog;
import com.soumManager.utils.Config;
import com.soumManager.utils.Log;
import com.soumManager.view.RootLayoutController;
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
