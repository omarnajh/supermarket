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
public class TotalPosh {
  private final SimpleIntegerProperty id ;
    private final SimpleStringProperty  name;
    private final SimpleStringProperty total;
    private final SimpleStringProperty idinvoi;
    private final SimpleStringProperty date;
    private final SimpleStringProperty posh;
    private final SimpleStringProperty res;

    public TotalPosh(int id, String name, String total, String idinvoi, String date ,String posh ,String res) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.total = new SimpleStringProperty(total);
        this.idinvoi = new SimpleStringProperty(idinvoi);
        this.date = new SimpleStringProperty(date);
        this.posh = new SimpleStringProperty(posh);
        this.res = new SimpleStringProperty(res);
    }

    public int getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public String getTotal() {
        return total.get();
    }

    public String getIdinvoi() {
        return idinvoi.get();
    }

    public String getDate() {
        return date.get();
    }

    public String getPosh() {
        return posh.get();
    }

    public String getRes() {
        return res.get();
    }

      
}
