package com.soumManager.data;

import com.soumManager.utils.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javafx.scene.control.Alert;

public class Unzip {
    public static File unzip(Path input) {
        byte[] buffer = new byte[1024];
        String OUTPUT_FOLDER = "D:\\temp\\soum";
        File xmlFile = null;
        boolean unzipX451 = false;
        
        try{
            File folder = new File(OUTPUT_FOLDER);
            if(!folder.exists())
                folder.mkdir();
            
            ZipInputStream zis = new ZipInputStream(new FileInputStream(input.toFile()));
            ZipEntry ze = zis.getNextEntry();
            while(ze!=null){
                String fileName = ze.getName();
               
                if(fileName.equals("x451.xml"))
                {
                    xmlFile = new File(OUTPUT_FOLDER + File.separator + fileName);
                    //File newFile = new File(OUTPUT_FOLDER + File.separator + UUID.randomUUID() + ".tmp");
                    FileOutputStream fos = new FileOutputStream(xmlFile);

                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                    
                    fos.close();
                    Log.msg(0, "Unzip x451.xml");                    
                }
                ze = zis.getNextEntry();
            }

            zis.closeEntry();
            zis.close();
            
            return xmlFile;
        }
        catch(IOException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur - unzip");
            alert.setHeaderText(null);
            alert.setContentText("Erreur unzip : " + ex.getMessage());
            alert.showAndWait(); 
            Log.msg(1, "Unzip x451.xml");
        }
        
        return xmlFile;
    }        
}
