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
public class TotalMost {
    private final SimpleIntegerProperty id ;
    private final SimpleStringProperty  mostName;

    public TotalMost(int id, String mostName) {
        this.id = new SimpleIntegerProperty(id);
        this.mostName = new SimpleStringProperty(mostName);
    }

    public int getId() {
        return id.get();
    }

    public String getMostName() {
        return mostName.get();
    }
    
}
