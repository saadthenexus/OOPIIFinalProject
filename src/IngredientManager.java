package src;

import java.util.List;


class IngredientManager implements Manager<Ingredient> {
    private static final String FILENAME = "ingredients.dat";
    private List<Ingredient> ingredients = DataManager.loadData(FILENAME);
    
    @Override
    public void add() {
        String name = InputHelper.getString("Enter ingredient name: ");
        double calories = InputHelper.getDouble("Enter calories per 100g: ");
        double protein = InputHelper.getDouble("Enter protein per 100g: ");
        double carbs = InputHelper.getDouble("Enter carbs per 100g: ");
        double fats = InputHelper.getDouble("Enter fats per 100g: ");
        ingredients.add(new Ingredient(name, calories, protein, carbs, fats));
        save();
        System.out.println("Ingredient added.");
    }
    
    @Override
    public void delete() {
        if (ingredients.isEmpty()) { 
            System.out.println("No ingredients to delete."); 
            return; 
        }
        for (int i = 0; i < ingredients.size(); i++) {
            System.out.println((i+1) + ". " + ingredients.get(i).getName());
        }
        int idx = InputHelper.getInt("Select an ingredient to delete: ");
        if (idx > 0 && idx <= ingredients.size()) {
            ingredients.remove(idx-1);
            save();
            System.out.println("Ingredient deleted.");
        } else {
            System.out.println("Invalid selection.");
        }
    }
    
    @Override
    public List<Ingredient> getAll() { return ingredients; }
    @Override
    public void save() { DataManager.saveData(FILENAME, ingredients); }
}

