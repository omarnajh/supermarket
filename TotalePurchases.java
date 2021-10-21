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
public class TotalePurchases {
    private final SimpleIntegerProperty id ;
    private final SimpleIntegerProperty idMaterial;
    private final SimpleStringProperty  materialName;
    private final SimpleStringProperty dateProdect;
    private final SimpleStringProperty dateExp;
    private final SimpleStringProperty materialCount;
    private final SimpleStringProperty companyName;
    private final SimpleStringProperty senderName;
    private final SimpleStringProperty senderPhone;
    private final SimpleStringProperty datePurchases;
    private final SimpleIntegerProperty idInvoices;
    private final SimpleStringProperty invoicesAmont;
    private final SimpleStringProperty notePurcheses;

    public TotalePurchases(int id, int idMaterial, String materialName, String dateProdect, String dateExp, String materialCount, String companyName, String senderName, String senderPhone, String datePurchases, int idInvoices, String invoicesAmont, String notePurcheses) {
        this.id  =new SimpleIntegerProperty(id);
        this.idMaterial = new SimpleIntegerProperty(idMaterial);
        this.materialName =new SimpleStringProperty(materialName);
        this.dateProdect =new SimpleStringProperty( dateProdect);
        this.dateExp = new SimpleStringProperty(dateExp);
        this.materialCount =new SimpleStringProperty( materialCount);
        this.companyName = new SimpleStringProperty(companyName);
        this.senderName = new SimpleStringProperty(senderName);
        this.senderPhone =new SimpleStringProperty( senderPhone);
        this.datePurchases =new SimpleStringProperty( datePurchases);
        this.idInvoices =new SimpleIntegerProperty( idInvoices);
        this.invoicesAmont = new SimpleStringProperty(invoicesAmont);
        this.notePurcheses =new SimpleStringProperty( notePurcheses);
    }

    public int getId() {
        return id.get();
    }

    public int getIdMaterial() {
        return idMaterial.get();
    }

    public String getMaterialName() {
        return materialName.get();
    }

    public String getDateProdect() {
        return dateProdect.get();
    }

    public String getDateExp() {
        return dateExp.get();
    }

    public String getMaterialCount() {
        return materialCount.get();
    }

    public String getCompanyName() {
        return companyName.get();
    }

    public String getSenderName() {
        return senderName.get();
    }

    public String getSenderPhone() {
        return senderPhone.get();
    }

    public String getDatePurchases() {
        return datePurchases.get();
    }

    public int getIdInvoices() {
        return idInvoices.get();
    }

    public String getInvoicesAmont() {
        return invoicesAmont.get();
    }

    public String getNotePurcheses() {
        return notePurcheses.get();
    }
   
 }
