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
public class TotalCostmer {
    private final SimpleIntegerProperty id ;
    private final SimpleStringProperty  costmerName;
    private final SimpleStringProperty costmerPhone;
    private final SimpleStringProperty costmerId;
    private final SimpleStringProperty costmerDate;
    private final SimpleStringProperty costmerTotalMony;
    private final SimpleStringProperty payType;
    private final SimpleStringProperty payNote;

    public TotalCostmer(int id, String costmerName, String costmerPhone, String costmerId, String costmerDate, String costmerTotalMony, String payType, String payNote) {
        this.id =new SimpleIntegerProperty(id);
        this.costmerName = new SimpleStringProperty(costmerName);
        this.costmerPhone = new SimpleStringProperty(costmerPhone);
        this.costmerId = new SimpleStringProperty(costmerId);
        this.costmerDate = new SimpleStringProperty(costmerDate);
        this.costmerTotalMony = new SimpleStringProperty(costmerTotalMony);
        this.payType = new SimpleStringProperty(payType);
        this.payNote = new SimpleStringProperty(payNote);
    }

    public int getId() {
        return id.get();
    }

    public String getCostmerName() {
        return costmerName.get();
    }

    public String getCostmerPhone() {
        return costmerPhone.get();
    }

    public String getCostmerId() {
        return costmerId.get();
    }

    public String getCostmerDate() {
        return costmerDate.get();
    }

    public String getCostmerTotalMony() {
        return costmerTotalMony.get();
    }

    public String getPayType() {
        return payType.get();
    }

    public String getPayNote() {
        return payNote.get();
    }
    
    
}
