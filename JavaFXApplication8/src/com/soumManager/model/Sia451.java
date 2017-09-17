package com.soumManager.model;

import com.soumManager.data.Unzip;
import com.soumManager.utils.Config;
import com.soumManager.utils.Log;
import java.io.File;
import java.nio.file.Path;
import javafx.scene.control.Alert;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Sia451 {
    private File xmlFile = null;
    private Projet projet;
    private String projetNum, projetName;
    final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    
    public Sia451(Path fileSia451) {
        xmlFile = Unzip.unzip(fileSia451);
        
        if(xmlFile!=null){
            xml();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur - fichier sia451");
            alert.setHeaderText(null);
            alert.setContentText("Erreur lors de l'ouverture du fichier sia451. Le fichier n'existe pas.");
            alert.showAndWait();         
        }
    }
    
    public Projet getProjet(){
        return this.projet;
    }
    
    private void xml(){
        try {
            final DocumentBuilder builder = factory.newDocumentBuilder();
            Log.msg(0, Config.getTempX451().toString());
            final Document document= builder.parse(xmlFile);
            final Element racine = document.getDocumentElement();
            
            // PROJET
            Element projetNode = (Element) racine.getElementsByTagName("X451Project").item(0);
            projet = new Projet(
                    projetNode.getElementsByTagName("Name").item(0).getFirstChild().getNodeValue(), 
                    projetNode.getElementsByTagName("LongName").item(0).getFirstChild().getNodeValue());

            // CAT CHAPTER
            NodeList catChapterNodes = racine.getElementsByTagName("X451CatChapter");
            for(int i=0; i<catChapterNodes.getLength(); i++)
            {
                Element element = (Element) catChapterNodes.item(i);
                int tmp = Integer.parseInt(element.getElementsByTagName("ChapterNr").item(0).getFirstChild().getNodeValue());
                Catalog catChapter = new Catalog(
                        i, 
                        tmp, 
                        element.getElementsByTagName("ChapterName").item(0).getFirstChild().getNodeValue(), 
                        element.getAttribute("id"));

                projet.addCatChapter(catChapter);
            }

            // POSITION
            NodeList positionNodes = racine.getElementsByTagName("X451CatPos");
            for(int j=0; j<positionNodes.getLength(); j++)
            {
                Element elementPosition = (Element) positionNodes.item(j);
                if(elementPosition.getElementsByTagName("PosType").item(0).getFirstChild().getNodeValue().equals("2"))
                {    
                    projet.addPosition(new Position(
                            elementPosition.getAttribute("id"), //id
                            elementPosition.getAttribute("ref"), //ref catalog
                            0.00f, //prix
                            elementPosition.getElementsByTagName("PosTxt").item(0).getFirstChild().getNodeValue(), //text
                            elementPosition.getElementsByTagName("LPosNr").item(0).getFirstChild().getNodeValue(), //num position
                            1, //type de prix
                            0)); //quantité
                }
            }
            
            // QUANTITE
            NodeList quantiteNodes = racine.getElementsByTagName("X451Quantity");
            String oldRef="", quantite="", unit="", price="";
            for(int j=0; j<quantiteNodes.getLength(); j++)
            {
                Element elementQuantite = (Element) quantiteNodes.item(j);
                
                if(elementQuantite.hasAttribute("ref") && elementQuantite.hasChildNodes())
                {
                    String tmpRef = elementQuantite.getAttribute("ref");
                    if(!tmpRef.equals(oldRef))
                    {
                        quantite = elementQuantite.getElementsByTagName("Quantity").item(0).getFirstChild().getNodeValue();
                        unit = elementQuantite.getElementsByTagName("QuantityUnit").item(0).getFirstChild().getNodeValue();
                        price = elementQuantite.getElementsByTagName("Price").item(0).getFirstChild().getNodeValue();
                        projet.addQuantite(tmpRef, quantite);
                        projet.addPrice(tmpRef, price);
                        projet.addUnit(tmpRef, unit);
                        
                        oldRef = tmpRef;
                    }
                }
            }
        }   
        catch (Exception ex) {
            Log.msg(1, "Erreur xml " + ex);
        }
    }
}