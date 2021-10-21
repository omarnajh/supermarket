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
public class SellingHistory {

    private final SimpleIntegerProperty id;
    private final SimpleIntegerProperty idInvoice;
    private final SimpleIntegerProperty idMaterial;
    private final SimpleStringProperty materialName;
    private final SimpleStringProperty sellingPrice;
    private final SimpleStringProperty countOfSelling;
    private final SimpleStringProperty sellingTotalPrice;
    private final SimpleStringProperty note;
    private final SimpleStringProperty winTotalPrice;
    private final SimpleStringProperty dateOfSelling;

    public SellingHistory(int id, int idInvoice, int idMaterial, String materialName, String sellingPrice, String countOfSelling, String sellingTotalPrice, String note, String winTotalPrice, String dateOfSelling) {
        this.id = new SimpleIntegerProperty(id);
        this.idInvoice = new SimpleIntegerProperty(idInvoice);
        this.idMaterial = new SimpleIntegerProperty(idMaterial);
        this.materialName = new SimpleStringProperty(materialName);
        this.sellingPrice = new SimpleStringProperty(sellingPrice);
        this.countOfSelling = new SimpleStringProperty(countOfSelling);
        this.sellingTotalPrice = new SimpleStringProperty(sellingTotalPrice);
        this.note = new SimpleStringProperty(note);
        this.winTotalPrice = new SimpleStringProperty(winTotalPrice);
        this.dateOfSelling = new SimpleStringProperty(dateOfSelling);
    }

    public int getId() {
        return id.get();
    }

    public int getIdInvoice() {
        return idInvoice.get();
    }

    public int getIdMaterial() {
        return idMaterial.get();
    }

  

    public String getMaterialName() {
        return materialName.get();
    }

    public String getSellingPrice() {
        return sellingPrice.get();
    }

    public String getCountOfSelling() {
        return countOfSelling.get();
    }

    public String getSellingTotalPrice() {
        return sellingTotalPrice.get();
    }

    public String getNote() {
        return note.get();
    }

    public String getWinTotalPrice() {
        return winTotalPrice.get();
    }

    public String getDateOfSelling() {
        return dateOfSelling.get();
    }

}
