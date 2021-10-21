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
public class cashInfo {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty  materialName; 
    private final SimpleStringProperty countAvailable;
    private final SimpleStringProperty priceall;
    private final SimpleStringProperty pricetotal;

    public cashInfo(int id,String materialName, String countAvailable, String priceall,String pricetotal) {
        this.id=new SimpleIntegerProperty(id);
        this.materialName = new SimpleStringProperty(materialName);
        this.countAvailable = new SimpleStringProperty(countAvailable);
        this.priceall = new SimpleStringProperty(priceall);
        this.pricetotal = new SimpleStringProperty(pricetotal);
    }

    public int getId() {
        return id.get();
    }

    
    public String getMaterialName() {
        return materialName.get();
    }

    public String getCountAvailable() {
        return countAvailable.get();
    }

    public String getPriceall() {
        return priceall.get();
    }

    public String getPricetotal() {
        return pricetotal.get();
    }
    
}
