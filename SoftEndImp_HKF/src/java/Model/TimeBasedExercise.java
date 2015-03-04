package Model;

public class TimeBasedExercise extends ExerciseType{
    
    private double calsPerHour;

    public TimeBasedExercise(String exerciseName, 
            double caloriesBurned, double time, double calsPerHour) {
        
        super(exerciseName, caloriesBurned, time);
        this.calsPerHour = calsPerHour;
    }

    public double getCalsPerHour() {
        return calsPerHour;
    }

    public void setCalsPerHour(double calsPerHour) {
        this.calsPerHour = calsPerHour;
    }
    
    /**
     * Method to Calculate the Calories Burnt by User doing a Time based exercise
     * @return Calories Burnt
     */
    @Override
    public double calculateCalsBurnt(){
        //Will be amount * constant from Database that corresponds to this exercise
        return 0.0;
    }
}
