package src;

public class NutritionApp {
    private IngredientManager ingredientManager = new IngredientManager();
    private RecipeManager recipeManager = new RecipeManager(ingredientManager);
    private MealManager mealManager = new MealManager(recipeManager);
    private ExerciseManager exerciseManager = new ExerciseManager();
    private HealthLogManager healthLogManager = new HealthLogManager(mealManager, exerciseManager);
    
    // Menu Option Constants
    private static final int MANAGE_INGREDIENTS = 1;
    private static final int MANAGE_RECIPES     = 2;
    private static final int MANAGE_MEALS       = 3;
    private static final int MANAGE_EXERCISES   = 4;
    private static final int CALCULATE_BMI_BMR  = 5;
    private static final int LOG_HEALTH_JOURNAL = 6;
    private static final int VIEW_HEALTH_JOURNAL= 7;
    private static final int EXIT               = 8;
       
    public void start() {
        while (true) {
            displayMenu();
            int choice = InputHelper.getInt("Enter your choice: ");
            switch (choice) {
                case MANAGE_INGREDIENTS -> manageIngredients();
                case MANAGE_RECIPES     -> manageRecipes();
                case MANAGE_MEALS       -> manageMeals();
                case MANAGE_EXERCISES   -> manageExercises();
                case CALCULATE_BMI_BMR  -> calculateBMIAndBMR();
                case LOG_HEALTH_JOURNAL -> healthLogManager.add();
                case VIEW_HEALTH_JOURNAL-> healthLogManager.view();
                case EXIT               -> { System.out.println("Exiting..."); return; }
                default                 -> System.out.println("Invalid choice.");
            }
        }
    }
    
    private void displayMenu(){
        System.out.println("\n--- Nutrition & Health App Menu ---");
        System.out.println(MANAGE_INGREDIENTS + ". Manage Ingredients");
        System.out.println(MANAGE_RECIPES     + ". Manage Recipes");
        System.out.println(MANAGE_MEALS       + ". Manage Meals");
        System.out.println(MANAGE_EXERCISES   + ". Manage Exercises");
        System.out.println(CALCULATE_BMI_BMR  + ". Calculate BMI & BMR");
        System.out.println(LOG_HEALTH_JOURNAL + ". Log Daily Health Journal");
        System.out.println(VIEW_HEALTH_JOURNAL + ". View Health Journal");
        System.out.println(EXIT               + ". Exit");
    }

    private void manageIngredients() {
        System.out.println("\n--- Manage Ingredients ---");
        System.out.println("1. Add Ingredient");
        System.out.println("2. Delete Ingredient");
        int c = InputHelper.getInt("Choice: ");
        if (c == 1) ingredientManager.add();
        else if (c == 2) ingredientManager.delete();
        else System.out.println("Invalid option.");
    }
    
    private void manageRecipes() {
        System.out.println("\n--- Manage Recipes ---");
        System.out.println("1. Add Recipe");
        System.out.println("2. Delete Recipe");
        int c = InputHelper.getInt("Choice: ");
        if (c == 1) recipeManager.add();
        else if (c == 2) recipeManager.delete();
        else System.out.println("Invalid option.");
    }
    
    private void manageMeals() {
        System.out.println("\n--- Manage Meals ---");
        System.out.println("1. Add Meal");
        System.out.println("2. Delete Meal");
        int c = InputHelper.getInt("Choice: ");
        if (c == 1) mealManager.add();
        else if (c == 2) mealManager.delete();
        else System.out.println("Invalid option.");
    }
    
    private void manageExercises() {
        System.out.println("\n--- Manage Exercises ---");
        System.out.println("1. Log Exercise");
        System.out.println("2. Delete Exercise");
        int c = InputHelper.getInt("Choice: ");
        if (c == 1) exerciseManager.add();
        else if (c == 2) exerciseManager.delete();
        else System.out.println("Invalid option.");
    }
    
    private void calculateBMIAndBMR() {
        double weight = InputHelper.getDouble("Enter your weight in kg: ");
        double height = InputHelper.getDouble("Enter your height in cm: ");
        int age = InputHelper.getInt("Enter your age: ");
        String gender = InputHelper.getString("Enter your gender (male/female): ");
        try {
            User user = new User(weight, height, age, gender);
            System.out.println("Health Insights: " + user.getHealthInsight());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        new NutritionApp().start();
    }
}