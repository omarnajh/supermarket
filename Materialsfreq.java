/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marktingprograme.DataBase;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author omar
 */
public class Materialsfreq {
     private final SimpleStringProperty  materialName; 
     private final SimpleStringProperty  frequnt; 

    public Materialsfreq( String materialName,String frequnt) {
       this.materialName = new SimpleStringProperty(materialName);
       this.frequnt = new SimpleStringProperty(frequnt);
    }

  

    public String getMaterialName() {
        return materialName.get();
    }

    public String getFrequnt() {
        return frequnt.get();
    }

   
}
