package src;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HealthLog implements Serializable {
    private static final long serialVersionUID = 1L;
    
    // Threshold constants
    private static final double WATER_THRESHOLD = 2.0; // liters
    private static final double SLEEP_THRESHOLD = 7.0;   // hours
    
    private LocalDate date;
    private List<Meal> meals = new ArrayList<>();
    private double waterIntake; // liters
    private double sleepHours;
    private List<Exercise> exercises = new ArrayList<>();

    public HealthLog(LocalDate date) { 
        this.date = date; 
    }
    public LocalDate getDate() { return date; }
    public void addMeal(Meal meal) { meals.add(meal); }
    public void addExercise(Exercise exercise) { exercises.add(exercise); }
    public void setWaterIntake(double waterIntake) { this.waterIntake = waterIntake; }
    public void setSleepHours(double sleepHours) { this.sleepHours = sleepHours; }
    public double getTotalMealCalories() { 
        return meals.stream().mapToDouble(Meal::getTotalCalories).sum(); 
    }
    public double getTotalExerciseCalories() { 
        return exercises.stream().mapToDouble(Exercise::getCaloriesBurned).sum(); 
    }
    public String generateDailySummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("Date: ").append(date).append("\n");
        summary.append("Total Meal Calories: ").append(String.format("%.2f", getTotalMealCalories())).append("\n");
        summary.append("Total Exercise Calories Burned: ").append(String.format("%.2f", getTotalExerciseCalories())).append("\n");
        summary.append("Water Intake: ").append(waterIntake).append(" liters\n");
        summary.append("Sleep Hours: ").append(sleepHours).append(" hours\n");
        summary.append(waterIntake < WATER_THRESHOLD ? "Suggestion: Increase your water intake.\n" : "Water intake is good.\n");
        summary.append(sleepHours < SLEEP_THRESHOLD ? "Suggestion: Try to get more sleep.\n" : "Sleep duration is adequate.\n");
        return summary.toString();
    }
}