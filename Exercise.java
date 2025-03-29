import java.io.Serializable;

public class Exercise implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String activityName;
    private int duration; // minutes
    private String intensity; // low, moderate, high
    private double caloriesBurned; // estimated

    public Exercise(String activityName, int duration, String intensity, double caloriesBurned) {
        this.activityName = activityName;
        this.duration = duration;
        this.intensity = intensity;
        this.caloriesBurned = caloriesBurned;
    }
    public String getActivityName() { return activityName; }
    public int getDuration() { return duration; }
    public String getIntensity() { return intensity; }
    public double getCaloriesBurned() { return caloriesBurned; }
}