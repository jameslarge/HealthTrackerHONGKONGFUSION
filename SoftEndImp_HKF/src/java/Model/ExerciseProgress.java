package Model;

import java.util.Date;

public class ExerciseProgress {
    
    private Exercise exercise;
    private Date date;
    private int duration;
    private int amount;
    
    public int getAmount() {
        return amount;
    }
    
    public void setAmount(int amount) {
        this.amount = amount;
    }
}
