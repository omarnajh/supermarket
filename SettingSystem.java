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
public class SettingSystem {
    private final SimpleIntegerProperty id ;
    private final SimpleStringProperty  centerName;
    private final SimpleStringProperty mangerName;
    private final SimpleStringProperty centerPhone;
    private final SimpleStringProperty systemPassword;
    private final SimpleStringProperty natureMaterial;
    private final SimpleStringProperty currenyName;
    private final SimpleStringProperty adsress;

    public SettingSystem(int id, String centerName, String mangerName, String centerPhone, String systemPassword, String natureMaterial, String currenyName,String adsress) {
        this.id = new SimpleIntegerProperty(id);
        this.centerName = new SimpleStringProperty(centerName);
        this.mangerName = new SimpleStringProperty(mangerName);
        this.centerPhone = new SimpleStringProperty(centerPhone);
        this.systemPassword = new SimpleStringProperty(systemPassword);
        this.natureMaterial = new SimpleStringProperty(natureMaterial);
        this.currenyName = new SimpleStringProperty(currenyName);
        this.adsress =new SimpleStringProperty(adsress);
    }

    public int getId() {
        return id.get();
    }

    public String getCenterName() {
        return centerName.get();
    }

    public String getMangerName() {
        return mangerName.get();
    }

    public String getCenterPhone() {
        return centerPhone.get();
    }

    public String getSystemPassword() {
        return systemPassword.get();
    }

    public String getNatureMaterial() {
        return natureMaterial.get();
    }

    public String getCurrenyName() {
        return currenyName.get();
    }

    public String getAdsress() {
        return adsress.get();
    }
    
}
