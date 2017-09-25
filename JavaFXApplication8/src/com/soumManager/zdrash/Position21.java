package com.soumManager.model;

import com.soumManager.utils.Log;
import java.text.DecimalFormat;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Position21 {
    private IntegerProperty id, numCatalog, yearCatalog, numPos, idTypePos;
    private FloatProperty prix, quantite, total;
    private StringProperty text, idXml, refCatalog, unit;
    
    //Create from reference
    public Position21(int id, int numCatalog, int yearCatalog, float prix, String text, int numPos, int idTypePos){
        this.numCatalog = new SimpleIntegerProperty(numCatalog);
        this.yearCatalog = new SimpleIntegerProperty(yearCatalog);
        this.numPos = new SimpleIntegerProperty(numPos);
        this.idTypePos = new SimpleIntegerProperty(idTypePos);
        this.prix = new SimpleFloatProperty(prix);
        this.text = new SimpleStringProperty(text);
        this.idXml = new SimpleStringProperty("0");
        this.refCatalog = new SimpleStringProperty("");
        this.unit = new SimpleStringProperty("");
        this.id = new SimpleIntegerProperty(id);        
        this.quantite = new SimpleFloatProperty(0.00f);
        this.total = new SimpleFloatProperty(quantite.get() * prix);
    }
    
    //Create from xml
    public Position21(String idXml, String refCatalog, float prix, String text, String numPos, int idTypePos, int quantite){
        this.idXml =  new SimpleStringProperty(idXml);
        this.refCatalog = new SimpleStringProperty(refCatalog);
        this.id = new SimpleIntegerProperty(0);
        this.numCatalog = new SimpleIntegerProperty(0);
        this.yearCatalog = new SimpleIntegerProperty(0);
        
        numPos = numPos.replaceAll("[^\\d]", "");
        if(numPos.isEmpty())
            numPos = "0";     
        this.numPos = new SimpleIntegerProperty(Integer.parseInt(numPos));
        this.idTypePos = new SimpleIntegerProperty(idTypePos);
        this.prix = new SimpleFloatProperty(prix);
        this.text = new SimpleStringProperty(text);
        this.quantite = new SimpleFloatProperty(quantite);
        this.total = new SimpleFloatProperty(quantite * prix);
    }    

    // GET
    public int getId() {
        return id.get();
    }
    public int getNumCatalog() {
        return numCatalog.get();
    }
    public int getYearCatalog() {
        return yearCatalog.get();
    }
    public int getNumPos() {
        return numPos.get();
    }
    public int getIdTypePos() {
        return idTypePos.get();
    }
    public float getPrix() {
        return prix.get();
    }
    public String getUnit(){
        return unit.get();
    }
    public String getIdXml(){
        return idXml.get();
    }    
    public String getText() {
        return text.get();
    }
    public float getQuantite() {
        return quantite.get();
    }
    public String getRefCatalog() {
        return refCatalog.get();
    }
    public float getTotal(){
        return total.get();
    }
    
    // SET
    public void setId(int id) {
        this.id = new SimpleIntegerProperty(id);
    }
    public void setNumCatalog(int numCatalog) {
        this.numCatalog = new SimpleIntegerProperty(numCatalog);
    }
    public void setYearCatalog(int yearCatalog) {
        this.yearCatalog = new SimpleIntegerProperty(yearCatalog);
    }
    public void setNumPos(int numPos) {
        this.numPos = new SimpleIntegerProperty(numPos);
    }
    public void setIdTypePos(int idTypePos) {
        this.idTypePos = new SimpleIntegerProperty(idTypePos);
    }
    public void setPrix(float prix) {
        this.prix = new SimpleFloatProperty(prix);
        this.total.set(prix * quantite.get());
    }
    public void setUnit(String unit){
        String unitStr="";
        switch(unit){
            case "AAA": unitStr = "m2";
                        break;
            case "BBB": unitStr = "m3";
                        break;                 
            default: unitStr = unit;
        }
        this.unit = new SimpleStringProperty(unitStr);
    }
    public void setIdXml(String idXml){
        this.idXml.set(idXml);
    }
    public void setText(String text) {
        this.text = new SimpleStringProperty(text);
    }
    public void setQuantite(float quantite){
        this.quantite = new SimpleFloatProperty(quantite);
        this.total.set(quantite * prix.get());
    }
    
    // PROPERTY
    public IntegerProperty idProperty(){
        return id;
    }
    public StringProperty unitProperty(){
        return unit;
    }
    public StringProperty idXmlProperty(){
        return idXml;
    }
    public StringProperty numPosProperty(){
        DecimalFormat myFormatter = new DecimalFormat("000.000");
        String tmp = myFormatter.format((float)numPos.get()/1000);
        return new SimpleStringProperty(tmp);
    }
    public StringProperty textProperty(){
        return text;
    }
    public StringProperty prixProperty(){
        DecimalFormat myFormatter = new DecimalFormat("0.00");
        String tmp = myFormatter.format((float)prix.get());        
        return new SimpleStringProperty(tmp);
    }
    public StringProperty yearCatalogProperty(){
        return new SimpleStringProperty(String.valueOf(yearCatalog.get()));
    }
    public StringProperty quantiteProperty(){
        DecimalFormat myFormatter = new DecimalFormat("0.00");
        String tmp = myFormatter.format((float)quantite.get());        
        return new SimpleStringProperty(tmp);
    }
    public StringProperty totalProperty(){
        DecimalFormat myFormatter = new DecimalFormat("0.00");
        String tmp = myFormatter.format((float)total.get());        
        return new SimpleStringProperty(tmp);    
    }
}
