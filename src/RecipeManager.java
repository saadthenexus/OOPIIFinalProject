package src;

import java.util.List;

class RecipeManager implements Manager<Recipe> {
    private static final String FILENAME = "recipes.dat";
    private List<Recipe> recipes = DataManager.loadData(FILENAME);
    private IngredientManager ingredientManager;
    
    public RecipeManager(IngredientManager ingredientManager) {
        this.ingredientManager = ingredientManager;
    }
    
    @Override
    public void add() {
        if (ingredientManager.getAll().isEmpty()) {
            System.out.println("No ingredients available. Please add ingredients first.");
            return;
        }
        String recipeName = InputHelper.getString("Enter recipe name: ");
        Recipe recipe = new Recipe(recipeName);
        while (true) {
            List<Ingredient> ingredients = ingredientManager.getAll();
            for (int i = 0; i < ingredients.size(); i++) {
                System.out.println((i+1) + ". " + ingredients.get(i).getName());
            }
            int choice = InputHelper.getInt("Enter ingredient number to add (0 to stop): ");
            if (choice == 0) break;
            if (choice > 0 && choice <= ingredients.size()) {
                double quantity = InputHelper.getDouble("Enter quantity (in grams): ");
                recipe.addIngredient(ingredients.get(choice-1), quantity);
                System.out.println("Ingredient added to recipe.");
            } else {
                System.out.println("Invalid choice.");
            }
        }
        recipes.add(recipe);
        save();
        System.out.println("Recipe created.");
    }
    
    @Override
    public void delete() {
        if (recipes.isEmpty()) { 
            System.out.println("No recipes to delete."); 
            return; 
        }
        for (int i = 0; i < recipes.size(); i++) {
            System.out.println((i+1) + ". " + recipes.get(i).getName());
        }
        int idx = InputHelper.getInt("Select a recipe to delete: ");
        if (idx > 0 && idx <= recipes.size()) {
            recipes.remove(idx-1);
            save();
            System.out.println("Recipe deleted.");
        } else {
            System.out.println("Invalid selection.");
        }
    }
    
    @Override
    public List<Recipe> getAll() { return recipes; }
    @Override
    public void save() { DataManager.saveData(FILENAME, recipes); }
}
