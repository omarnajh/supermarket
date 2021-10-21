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
public class TotalPremiums {
    private final SimpleIntegerProperty id ;
    private final SimpleIntegerProperty  idIvoices; 
    private final SimpleStringProperty costmerName;
    private final SimpleStringProperty premiumsDate;
    private final SimpleStringProperty premiumsposh;
    private final SimpleStringProperty settingsystem;

    public TotalPremiums(int id, int idIvoices, String costmerName, String premiumsDate, String premiumsposh,String settingsystem) {
        this.id = new SimpleIntegerProperty(id);
        this.idIvoices = new SimpleIntegerProperty(idIvoices);
        this.costmerName = new SimpleStringProperty(costmerName);
        this.premiumsDate = new SimpleStringProperty(premiumsDate);
        this.premiumsposh = new SimpleStringProperty(premiumsposh);
        this.settingsystem = new SimpleStringProperty(settingsystem);
    }

    public int getId() {
        return id.get();
    }

    public int getIdIvoices() {
        return idIvoices.get();
    }

    public String getCostmerName() {
        return costmerName.get();
    }

    public String getPremiumsDate() {
        return premiumsDate.get();
    }

    public String getPremiumsposh() {
        return premiumsposh.get();
    }

    public String getSettingsystem() {
        return settingsystem.get();
    }
    
}
