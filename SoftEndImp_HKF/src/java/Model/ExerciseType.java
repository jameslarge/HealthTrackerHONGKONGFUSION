
package Model;

public class ExerciseType {
    private String exerciseName;
    private double caloriesBurned;
    private double time;

    public ExerciseType(String exerciseName, double caloriesBurned, double time) {
        this.exerciseName = exerciseName;
        this.caloriesBurned = caloriesBurned;
        this.time = time;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public double getCaloriesBurned() {
        return caloriesBurned;
    }

    public double getTime() {
        return time;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public void setCaloriesBurned(double caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    public void setTime(double time) {
        this.time = time;
    }
    
    public double calculateCalsBurnt(){
        return 0.0;
    }
}
