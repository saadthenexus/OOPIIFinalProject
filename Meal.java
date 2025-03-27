import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Meal implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String name;
    private List<Recipe> recipes = new ArrayList<>();

    public Meal(String name) { 
        this.name = name; 
    }
    public void addRecipe(Recipe recipe) { 
        recipes.add(recipe); 
    }
    public double getTotalCalories() { 
        return recipes.stream().mapToDouble(Recipe::getTotalCalories).sum(); 
    }
    public double getTotalProtein() { 
        return recipes.stream().mapToDouble(Recipe::getTotalProtein).sum(); 
    }
    public double getTotalCarbs() { 
        return recipes.stream().mapToDouble(Recipe::getTotalCarbs).sum(); 
    }
    public double getTotalFats() { 
        return recipes.stream().mapToDouble(Recipe::getTotalFats).sum(); 
    }
    public String getName() { return name; }
}
