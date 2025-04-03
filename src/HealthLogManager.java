package src;

import java.time.LocalDate;
import java.util.List;

class HealthLogManager implements Manager<HealthLog> {
    private static final String FILENAME = "healthLogs.dat";
    private List<HealthLog> healthLogs = DataManager.loadData(FILENAME);
    private MealManager mealManager;
    private ExerciseManager exerciseManager;
    
    public HealthLogManager(MealManager mealManager, ExerciseManager exerciseManager) {
        this.mealManager = mealManager;
        this.exerciseManager = exerciseManager;
    }
    
    @Override
    public void add() {
        LocalDate today = LocalDate.now();
        HealthLog log = new HealthLog(today);
        
        int mealCount = InputHelper.getInt("How many meals to log for today? ");
        for (int i = 0; i < mealCount; i++) {
            if (mealManager.getAll().isEmpty()) {
                System.out.println("No meals available. Create a meal first.");
                break;
            }
            List<Meal> meals = mealManager.getAll();
            for (int j = 0; j < meals.size(); j++) {
                System.out.println((j+1) + ". " + meals.get(j).getName());
            }
            int mealChoice = InputHelper.getInt("Select a meal to add: ");
            if (mealChoice > 0 && mealChoice <= meals.size()) {
                log.addMeal(meals.get(mealChoice-1));
                System.out.println("Meal logged.");
            } else {
                System.out.println("Invalid choice.");
            }
        }
        
        double water = InputHelper.getDouble("Enter water intake (liters): ");
        log.setWaterIntake(water);
        
        double sleep = InputHelper.getDouble("Enter sleep hours: ");
        log.setSleepHours(sleep);
        
        int exCount = InputHelper.getInt("How many exercises to log for today? ");
        for (int i = 0; i < exCount; i++) {
            if (exerciseManager.getAll().isEmpty()) {
                System.out.println("No exercises available. Log an exercise first.");
                break;
            }
            List<Exercise> exercises = exerciseManager.getAll();
            for (int j = 0; j < exercises.size(); j++) {
                System.out.println((j+1) + ". " + exercises.get(j).getActivityName());
            }
            int exChoice = InputHelper.getInt("Select an exercise to add: ");
            if (exChoice > 0 && exChoice <= exercises.size()) {
                log.addExercise(exercises.get(exChoice-1));
                System.out.println("Exercise added.");
            } else {
                System.out.println("Invalid choice.");
            }
        }
        healthLogs.add(log);
        save();
        System.out.println("Daily Health Log for " + today + " recorded.");
    }
    
    @Override
    public void delete() {
        if (healthLogs.isEmpty()) { 
            System.out.println("No health logs to delete."); 
            return; 
        }
        for (int i = 0; i < healthLogs.size(); i++) {
            System.out.println((i+1) + ". " + healthLogs.get(i).getDate());
        }
        int idx = InputHelper.getInt("Select a health log to delete: ");
        if (idx > 0 && idx <= healthLogs.size()) {
            healthLogs.remove(idx-1);
            save();
            System.out.println("Health log deleted.");
        } else {
            System.out.println("Invalid selection.");
        }
    }
    
    @Override
    public List<HealthLog> getAll() { return healthLogs; }
    @Override
    public void save() { DataManager.saveData(FILENAME, healthLogs); }
    
    public void view() {
        if (healthLogs.isEmpty()) {
            System.out.println("No health logs recorded.");
            return;
        }
        System.out.println("--- Daily Health Journal ---");
        for (HealthLog log : healthLogs) {
            System.out.println(log.generateDailySummary());
            System.out.println("--------------------------");
        }
    }
}