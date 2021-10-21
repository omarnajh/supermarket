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
public class Invoices {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty dateInvoices;
    private final SimpleStringProperty amontInvoices;
    private final SimpleStringProperty numberBerlter;
    private final SimpleStringProperty employName;
    private final SimpleStringProperty typePay;
    private final SimpleStringProperty stateIn;
    private final SimpleStringProperty dar;
    private final SimpleStringProperty dis;
    private final SimpleStringProperty finalTotal;
    private final SimpleStringProperty winTotal;

    public Invoices(int id, String dateInvoices, String amontInvoices, String numberBerlter, String employName, String typePay, String stateIn, String dar, String dis, String finalTotal, String winTotal) {
        this.id = new SimpleIntegerProperty(id);
        this.dateInvoices = new SimpleStringProperty(dateInvoices);
        this.amontInvoices = new SimpleStringProperty(amontInvoices);
        this.numberBerlter = new SimpleStringProperty(numberBerlter);
        this.employName = new SimpleStringProperty(employName);
        this.typePay = new SimpleStringProperty(typePay);
        this.stateIn = new SimpleStringProperty(stateIn);
        this.dar = new SimpleStringProperty(dar);
        this.dis = new SimpleStringProperty(dis);
        this.finalTotal = new SimpleStringProperty(finalTotal);
        this.winTotal = new SimpleStringProperty(winTotal);
    }

    public int getId() {
        return id.get();
    }

    public String getDateInvoices() {
        return dateInvoices.get();
    }

    public String getAmontInvoices() {
        return amontInvoices.get();
    }

    public String getNumberBerlter() {
        return numberBerlter.get();
    }

    public String getEmployName() {
        return employName.get();
    }

    public String getTypePay() {
        return typePay.get();
    }

    public String getStateIn() {
        return stateIn.get();
    }

    public String getDar() {
        return dar.get();
    }

    public String getDis() {
        return dis.get();
    }

    public String getFinalTotal() {
        return finalTotal.get();
    }

    public String getWinTotal() {
        return winTotal.get();
    }
    
}
