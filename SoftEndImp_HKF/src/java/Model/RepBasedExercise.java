package Model;

public class RepBasedExercise extends ExerciseType{
    private int calsPerRep;

    public RepBasedExercise(String exerciseName, 
            int caloriesBurned, int duration, int calsPerRep) {
        
        super(exerciseName, caloriesBurned, duration);
        this.calsPerRep = calsPerRep;
    }

    public int getCalsPerRep() {
        return calsPerRep;
    }

    public void setCalsPerRep(int calsPerRep) {
        this.calsPerRep = calsPerRep;
    }
    
    /**
     * Method to Calculate the Calories Burnt by User doing a Rep based exercise
     * @return Calories Burnt
     */
    @Override
    public int calculateCalsBurnt(){
        //Will be amount * constant from Database that corresponds to this exercise
        return 0;
    }
    
}
