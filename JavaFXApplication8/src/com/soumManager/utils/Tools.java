package com.soumManager.utils;

import com.soumManager.MainApp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
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

    public static String ConvertDateToLisible(String str){
        
        int date = stringToInt(str);
        
        Instant inst = Instant.ofEpochSecond(date);
        LocalDateTime dateLisible = LocalDateTime.ofInstant(inst, ZoneOffset.UTC);
        
        str = new String(String.format("%02d", dateLisible.getDayOfMonth()) + "." + String.format("%02d",dateLisible.getMonthValue()) + "." + dateLisible.getYear());
        return  str;
    }    
    
    public static int ConvertDateToSecond(String date){
        if(date!=null)
        {
            String[] parts = date.split("\\.");
        
            if(parts.length==3)
            {
                int day = Integer.parseInt(parts[0]);
                int month = Integer.parseInt(parts[1]);
                int year = Integer.parseInt(parts[2]);

                if(day<32 & day>0 & month>0 & month<13 & year>1900 & year<2100)
                {
                    return (int) LocalDateTime.of(year, month, day, 0, 0).toEpochSecond(ZoneOffset.UTC);
                }
                else
                    return 0;
            }
            else
                return 0;
        }
        else
            return 0;
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
