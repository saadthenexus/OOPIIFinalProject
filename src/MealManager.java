package src;

import java.util.List;

class MealManager implements Manager<Meal> {
    private static final String FILENAME = "meals.dat";
    private List<Meal> meals = DataManager.loadData(FILENAME);
    private RecipeManager recipeManager;
    
    public MealManager(RecipeManager recipeManager) {
        this.recipeManager = recipeManager;
    }
    
    @Override
    public void add() {
        if (recipeManager.getAll().isEmpty()) {
            System.out.println("No recipes available. Please create a recipe first.");
            return;
        }
        String mealName = InputHelper.getString("Enter meal name: ");
        Meal meal = new Meal(mealName);
        while (true) {
            List<Recipe> recipes = recipeManager.getAll();
            for (int i = 0; i < recipes.size(); i++) {
                System.out.println((i+1) + ". " + recipes.get(i).getName());
            }
            int choice = InputHelper.getInt("Enter recipe number to add (0 to stop): ");
            if (choice == 0) break;
            if (choice > 0 && choice <= recipes.size()) {
                meal.addRecipe(recipes.get(choice-1));
                System.out.println("Recipe added to meal.");
            } else {
                System.out.println("Invalid choice.");
            }
        }
        meals.add(meal);
        save();
        System.out.println("Meal created.");
    }
    
    @Override
    public void delete() {
        if (meals.isEmpty()) { 
            System.out.println("No meals to delete."); 
            return; 
        }
        for (int i = 0; i < meals.size(); i++) {
            System.out.println((i+1) + ". " + meals.get(i).getName());
        }
        int idx = InputHelper.getInt("Select a meal to delete: ");
        if (idx > 0 && idx <= meals.size()) {
            meals.remove(idx-1);
            save();
            System.out.println("Meal deleted.");
        } else {
            System.out.println("Invalid selection.");
        }
    }
    
    @Override
    public List<Meal> getAll() { return meals; }
    @Override
    public void save() { DataManager.saveData(FILENAME, meals); }
}

