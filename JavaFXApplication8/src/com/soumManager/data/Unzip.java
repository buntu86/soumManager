package com.soumManager.data;

import com.soumManager.utils.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javafx.scene.control.Alert;

public class Unzip {
    public static File unzip(Path input) {
        byte[] buffer = new byte[1024];
        File tmp = new File("C:\\soumManager\\" + UUID.randomUUID() + ".tmp");
        try {
            tmp = File.createTempFile("soumManager-", ".tmp");
            tmp.deleteOnExit();
            Log.msg(0, tmp.getAbsolutePath());
        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur - unzip");
            alert.setHeaderText(null);
            alert.setContentText("Erreur unzip : impossible de créer le fichier temporaire : " + ex.getMessage());
            alert.showAndWait(); 
            Log.msg(1, "Unzip x451.xml");
        }
        
        try{
            ZipInputStream zis = new ZipInputStream(new FileInputStream(input.toFile()));
            ZipEntry ze = zis.getNextEntry();
            while(ze!=null){
                String fileName = ze.getName();
               
                if(fileName.equals("x451.xml"))
                {
                    FileOutputStream fos = new FileOutputStream(tmp);

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
            
            return tmp;
        }
        catch(IOException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur - unzip");
            alert.setHeaderText(null);
            alert.setContentText("Erreur unzip : " + ex.getMessage());
            alert.showAndWait(); 
            Log.msg(1, "Unzip x451.xml");
        }
        
        return tmp;
    }        
}
