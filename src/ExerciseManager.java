package src;

import java.util.List;

class ExerciseManager implements Manager<Exercise> {
    private static final String FILENAME = "exercises.dat";
    private List<Exercise> exercises = DataManager.loadData(FILENAME);
    
    @Override
    public void add() {
        String activityName = InputHelper.getString("Enter exercise activity name: ");
        int duration = InputHelper.getInt("Enter duration in minutes: ");
        String intensity = InputHelper.getString("Enter intensity (low/moderate/high): ");
        double caloriesBurned = InputHelper.getDouble("Enter estimated calories burned: ");
        exercises.add(new Exercise(activityName, duration, intensity, caloriesBurned));
        save();
        System.out.println("Exercise logged.");
    }
    
    @Override
    public void delete() {
        if (exercises.isEmpty()) { 
            System.out.println("No exercises to delete."); 
            return; 
        }
        for (int i = 0; i < exercises.size(); i++) {
            System.out.println((i+1) + ". " + exercises.get(i).getActivityName());
        }
        int idx = InputHelper.getInt("Select an exercise to delete: ");
        if (idx > 0 && idx <= exercises.size()) {
            exercises.remove(idx-1);
            save();
            System.out.println("Exercise deleted.");
        } else {
            System.out.println("Invalid selection.");
        }
    }
    
    @Override
    public List<Exercise> getAll() { return exercises; }
    @Override
    public void save() { DataManager.saveData(FILENAME, exercises); }
}