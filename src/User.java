package src;
import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    
    // Constants for BMR calculation (Mifflin-St Jeor Equation)
    private static final double WEIGHT_FACTOR = 10.0;
    private static final double HEIGHT_FACTOR = 6.25;
    private static final double AGE_FACTOR = 5.0;
    private static final int MALE_OFFSET = 5;
    private static final int FEMALE_OFFSET = -161;
    
    private String name;
    private double weight; // kg
    private double height; // cm
    private int age;
    private String gender; // "male" or "female"

    public User(String name, double weight, double height, int age, String gender) {
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.gender = gender;
    }
    // BMI calculation: weight (kg) / (height in m)^2
    public double calculateBMI() {
        double heightInMeters = height / 100.0;
        return weight / (heightInMeters * heightInMeters);
    }
    // BMR calculation using the Mifflin-St Jeor Equation
    public double calculateBMR() {
        if (gender.equalsIgnoreCase("male"))
            return WEIGHT_FACTOR * weight + HEIGHT_FACTOR * height - AGE_FACTOR * age + MALE_OFFSET;
        else if (gender.equalsIgnoreCase("female"))
            return WEIGHT_FACTOR * weight + HEIGHT_FACTOR * height - AGE_FACTOR * age + FEMALE_OFFSET;
        else
            throw new IllegalArgumentException("Gender must be 'male' or 'female'");
    }
    public String getHealthInsight() {
        double bmi = calculateBMI();
        String insight = bmi < 18.5 ? "Underweight" : bmi < 25 ? "Normal weight" : bmi < 30 ? "Overweight" : "Obese";
        return "BMI: " + String.format("%.2f", bmi) + " (" + insight + "), BMR: " + String.format("%.2f", calculateBMR());
    }
}

