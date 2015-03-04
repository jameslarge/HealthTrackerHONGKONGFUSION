package Model;

public class Exercise {
    private ExerciseType exerciseType; //What exercise was done
    private double amount;  //How much of that exercise was done

    public Exercise(ExerciseType exerciseType, double amount) {
        this.exerciseType = exerciseType;
        this.amount = amount;
    }

    public ExerciseType getExerciseType() {
        return exerciseType;
    }

    public double getAmount() {
        return amount;
    }

    public void setExerciseType(ExerciseType exerciseType) {
        this.exerciseType = exerciseType;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    
}
