package com.soumManager.utils;

import com.soumManager.MainApp;
import javafx.stage.Stage;

public class Tools {
    public static int stringToInt(String str){
        str = str.replaceAll("[^\\d]", "");
        if(str.isEmpty())
            str = "0";  
        
        return Integer.parseInt(str);
    }    
    
    public static float stringToFloat(String str){
        str = str.replaceAll("[^\\d]", "");
        if(str.isEmpty())
            str = "0";  

        return Float.parseFloat(str);
    }
    
    public static String intToString(int i){
        return "" + i;
    }

    /*private static MainApp main;
    
    public static void setTitlePrimaryStage(String str){
        main.setTitlePrimaryStage(str);
        Log.msg(0, "setTitle");
    }

    public static void setMain(MainApp main) {
        Tools.main = main;
        Log.msg(0, "setMain");
    }
    
    public static Stage getPrimaryStage(){
        return main.getPrimaryStage();
    }*/    

}
