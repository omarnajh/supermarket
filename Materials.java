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
public class Materials {
    private final SimpleIntegerProperty id ;
    private final SimpleStringProperty  barcode; 
    private final SimpleStringProperty  materialName; 
    private final SimpleIntegerProperty countAvailable;
    private final SimpleIntegerProperty priceGet;
    private final SimpleIntegerProperty priceSelling;
    private final SimpleStringProperty expDate;
    private final SimpleStringProperty mosName;
    private final SimpleIntegerProperty colgetprice;

    public Materials(int id, String materialName, int countAvailable, int priceGet, int priceSelling, String expDate, String mosName, int colgetprice, String barcode) {
        this.id = new SimpleIntegerProperty(id);
       
        this.materialName = new SimpleStringProperty(materialName);
        this.countAvailable = new SimpleIntegerProperty(countAvailable);
        this.priceGet = new SimpleIntegerProperty(priceGet);
        this.priceSelling = new SimpleIntegerProperty(priceSelling);
        this.expDate = new SimpleStringProperty(expDate);
        this.mosName = new SimpleStringProperty(mosName);
        this.colgetprice= new SimpleIntegerProperty(colgetprice);
         this.barcode = new SimpleStringProperty(barcode);
    }

    public int getId() {
        return id.get();
    }

    public String  getBarcode() {
        return barcode.get();
    }

    public String getMaterialName() {
        return materialName.get();
    }

    public int getCountAvailable() {
        return countAvailable.get();
    }

    public int getPriceGet() {
        return priceGet.get();
    }

    public int getPriceSelling() {
        return priceSelling.get();
    }

    public String getExpDate() {
        return expDate.get();
    }

    public String getMosName() {
        return mosName.get();
    }

    public int getColgetprice() {
        return colgetprice.get();
    }
    
}
