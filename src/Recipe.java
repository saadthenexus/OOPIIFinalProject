package src;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Recipe implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String name;
    private Map<Ingredient, Double> ingredients = new HashMap<>();

    public Recipe(String name) { 
        this.name = name; 
    }
    public void addIngredient(Ingredient ingredient, double quantity) { 
        ingredients.put(ingredient, quantity); 
    }
    public void removeIngredient(String ingredientName) {
        ingredients.entrySet().removeIf(entry -> entry.getKey().getName().equalsIgnoreCase(ingredientName));
    }
    public double getTotalCalories() {
        return ingredients.entrySet().stream()
                .mapToDouble(entry -> (entry.getKey().getCalories() * entry.getValue()) / 100)
                .sum();
    }
    public double getTotalProtein() {
        return ingredients.entrySet().stream()
                .mapToDouble(entry -> (entry.getKey().getProtein() * entry.getValue()) / 100)
                .sum();
    }
    public double getTotalCarbs() {
        return ingredients.entrySet().stream()
                .mapToDouble(entry -> (entry.getKey().getCarbs() * entry.getValue()) / 100)
                .sum();
    }
    public double getTotalFats() {
        return ingredients.entrySet().stream()
                .mapToDouble(entry -> (entry.getKey().getFats() * entry.getValue()) / 100)
                .sum();
    }
    public String getName() { return name; }
}

