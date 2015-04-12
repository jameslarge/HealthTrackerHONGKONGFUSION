package Model;

public class TimeBasedExercise extends ExerciseType{
    
    private int calsPerHour;

    public TimeBasedExercise(String exerciseName, 
            int caloriesBurned, int calsPerHour) {
        
        super(exerciseName, caloriesBurned);
        this.calsPerHour = calsPerHour;
    }

    public int getCalsPerHour() {
        return calsPerHour;
    }

    public void setCalsPerHour(int calsPerHour) {
        this.calsPerHour = calsPerHour;
    }
    
    /**
     * Method to Calculate the Calories Burnt by User doing a Time based exercise
     * @return Calories Burnt
     */
    @Override
    public int calculateCalsBurnt(){
        //Will be amount * constant from Database that corresponds to this exercise
        return 0;
    }
}
