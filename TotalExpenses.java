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
public class TotalExpenses {
    private final SimpleIntegerProperty id ;
    private final SimpleStringProperty  dateExpenses;
    private final SimpleStringProperty subjectExpenses;
    private final SimpleStringProperty amountTotal;
    private final SimpleStringProperty note;

    public TotalExpenses(int id, String dateExpenses, String subjectExpenses, String amountTotal, String note) {
        this.id = new SimpleIntegerProperty(id);
        this.dateExpenses = new SimpleStringProperty(dateExpenses);
        this.subjectExpenses = new SimpleStringProperty(subjectExpenses);
        this.amountTotal = new SimpleStringProperty(amountTotal);
        this.note = new SimpleStringProperty(note);
    }

    public int getId() {
        return id.get();
    }

    public String getDateExpenses() {
        return dateExpenses.get();
    }

    public String getSubjectExpenses() {
        return subjectExpenses.get();
    }

    public String getAmountTotal() {
        return amountTotal.get();
    }

    public String getNote() {
        return note.get();
    }
    
}
