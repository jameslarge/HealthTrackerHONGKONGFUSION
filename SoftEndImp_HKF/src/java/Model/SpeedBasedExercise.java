package Model;

public class SpeedBasedExercise extends ExerciseType{
    private double speed;
    private double calsPerSpeed;

    public SpeedBasedExercise(String exerciseName, 
            double caloriesBurned, double time, double speed, 
            double calsPerSpeed) {
        
        super(exerciseName, caloriesBurned, time);
        this.speed = speed;
        this.calsPerSpeed = calsPerSpeed;
    }
    
    /**
     * Method to calculate the amount of calories burnt by User
     * doing a Speed based exercise
     * @return Calories burnt
     */
    @Override
    public double calculateCalsBurnt(){
        //Will be amount * constant from Database that corresponds to this exercise
        return 0.0;
    }
    
    /**
     * Method to calculate the speed travelled by user
     * @param distance Distance travelled by user
     * @return 
     */
    public double calculateSpeed(double distance){
        double time = getTime();
        
        return (distance/time);
    }
}
