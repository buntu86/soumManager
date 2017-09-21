package com.soumManager.zdrash;

import com.soumManager.model.Projet_CatChapter;
import com.soumManager.zdrash.Position2;
import com.soumManager.model.Projet;
import com.soumManager.utils.Config;
import com.soumManager.utils.Log;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Xml {

    public static void xml(File sia451) {

        
    }
    
    /*public static void xml(Projet projet) {
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            	
        try {
            final DocumentBuilder builder = factory.newDocumentBuilder();
            Log.msg(0, Config.getTempX451().toString());
	    final Document document= builder.parse(Config.getTempX451().toFile());
								
	    final Element racine = document.getDocumentElement();
           
            // PROJET
            Element projetNode = (Element) racine.getElementsByTagName("X451Project").item(0);
            String projetNum = projetNode.getElementsByTagName("Name").item(0).getFirstChild().getNodeValue();
            String projetName = projetNode.getElementsByTagName("LongName").item(0).getFirstChild().getNodeValue();
            projet.setNumProjet(projetNum);
            projet.setNomProjet(projetName);
            
            // CAT CHAPTER                     
            NodeList catChapterNodes = racine.getElementsByTagName("X451CatChapter");
            for(int i=0; i<catChapterNodes.getLength(); i++)
            {
                Element element = (Element) catChapterNodes.item(i);
                Projet_CatChapter catChapter = new Projet_CatChapter();
                
                catChapter.setNumChapter(element.getElementsByTagName("ChapterNr").item(0).getFirstChild().getNodeValue());
                catChapter.setNomChapter(element.getElementsByTagName("ChapterName").item(0).getFirstChild().getNodeValue());
                catChapter.setVersChapter(element.getElementsByTagName("VersionYear").item(0).getFirstChild().getNodeValue());
                catChapter.setRefChapter(element.getAttribute("id"));
                Log.msg(0, catChapter.getRefChapter() + "|" + catChapter.getNumChapter() + " - " + catChapter.getNomChapter());
                projet.addCatChapter(catChapter);
                
                // POSITION
                NodeList positionNodes = racine.getElementsByTagName("X451CatPos");
                String lastIdChapt_posNum = "";
                for(int j=0; j<positionNodes.getLength(); j++)
                {
                    String posIdChapter = "";
                    String posNumPos = "";
                    Element elementPosition = (Element) positionNodes.item(j);
                    
                    if(elementPosition.getAttribute("ref").equals(catChapter.getRefChapter()))
                    {
                        posIdChapter = catChapter.getRefChapter();
                        posNumPos = elementPosition.getElementsByTagName("LPosNr").item(0).getFirstChild().getNodeValue();

                        if(!(posIdChapter + posNumPos).equals(lastIdChapt_posNum))
                        {
                            Position position = new Position(posIdChapter, posNumPos);
                            //Log.msg(0, posNumPos);
                        }
                        
                        lastIdChapt_posNum = posIdChapter + posNumPos;
                    }
                }
            }	
        }
        catch (final ParserConfigurationException e) {
            e.printStackTrace();
        }
        catch (final SAXException e) {
            e.printStackTrace();
        }
        catch (final IOException e) {
            e.printStackTrace();
        }		
        
    }*/
}
