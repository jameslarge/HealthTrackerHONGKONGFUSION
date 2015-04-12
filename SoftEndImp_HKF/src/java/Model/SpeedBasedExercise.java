package Model;

public class SpeedBasedExercise extends ExerciseType{
    private int speed;
    private int calsPerSpeed;

    public SpeedBasedExercise(String exerciseName, 
            int caloriesBurned, int duration, int speed, 
            int calsPerSpeed) {
        
        super(exerciseName, caloriesBurned, duration);
        this.speed = speed;
        this.calsPerSpeed = calsPerSpeed;
    }
    
    /**
     * Method to calculate the amount of calories burnt by User
     * doing a Speed based exercise
     * @return Calories burnt
     */
    @Override
    public int calculateCalsBurnt(){
        //Will be amount * constant from Database that corresponds to this exercise
        return 0;
    }
    
    /**
     * Method to calculate the speed travelled by user
     * @param distance Distance travelled by user
     * @return 
     */
    public int calculateSpeed(double distance){
        //double time = getDuration();
        return 0;
        //return (distance/time);
    }
}
