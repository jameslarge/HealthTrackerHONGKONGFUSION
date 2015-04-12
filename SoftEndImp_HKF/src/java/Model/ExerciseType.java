
package Model;

public class ExerciseType {
    private String exerciseName;
    private int caloriesBurned;

    public ExerciseType(String exerciseName, int caloriesBurned) {
        this.exerciseName = exerciseName;
        this.caloriesBurned = caloriesBurned;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public int getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public void setCaloriesBurned(int caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }
    
    public int calculateCalsBurnt(){
        return 0;
    }
}
