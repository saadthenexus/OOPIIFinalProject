import java.io.Serializable;

public class Ingredient implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String name;
    private double calories;
    private double protein;
    private double carbs;
    private double fats;

    public Ingredient(String name, double calories, double protein, double carbs, double fats) {
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fats = fats;
    }
    public String getName() { return name; }
    public double getCalories() { return calories; }
    public double getProtein() { return protein; }
    public double getCarbs() { return carbs; }
    public double getFats() { return fats; }
}