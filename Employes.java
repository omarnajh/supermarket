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
public class Employes {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty employesName;
    private final SimpleStringProperty employesPassword;
    private final SimpleStringProperty employesLevel;

    public Employes(int id, String employesName, String employesPassword, String employesLevel) {
        this.id = new SimpleIntegerProperty(id);
        this.employesName = new SimpleStringProperty(employesName);
        this.employesPassword = new SimpleStringProperty(employesPassword);
        this.employesLevel = new SimpleStringProperty(employesLevel);
    }

    public int getId() {
        return id.get();
    }

    public String getEmployesName() {
        return employesName.get();
    }

    public String getEmployesPassword() {
        return employesPassword.get();
    }

    public String getEmployesLevel() {
        return employesLevel.get();
    }
    
}
