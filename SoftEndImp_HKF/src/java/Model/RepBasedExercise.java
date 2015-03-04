package Model;

public class RepBasedExercise extends ExerciseType{
    private double calsPerRep;

    public RepBasedExercise(String exerciseName, 
            double caloriesBurned, double time, double calsPerRep) {
        
        super(exerciseName, caloriesBurned, time);
        this.calsPerRep = calsPerRep;
    }

    public double getCalsPerRep() {
        return calsPerRep;
    }

    public void setCalsPerRep(double calsPerRep) {
        this.calsPerRep = calsPerRep;
    }
    
    /**
     * Method to Calculate the Calories Burnt by User doing a Rep based exercise
     * @return Calories Burnt
     */
    @Override
    public double calculateCalsBurnt(){
        //Will be amount * constant from Database that corresponds to this exercise
        return 0.0;
    }
    
}
