package Model.PhysicalHealth;

import java.util.Date;
import java.util.Calendar;

public class WeightProgress {
    
    private Weight weight;
    private Date date;
    
    public WeightProgress(Weight weight, Date date) {
        this.weight = weight;
        this.date = date;
    }

    public Weight getWeight() {
        return weight;
    }

    public Date getDate() {
        return date;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
    }

    public void setDate(Date date) {
        this.date = date;
    }     
    
}
