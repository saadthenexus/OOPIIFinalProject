package src;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static final String BASE_FOLDER = "classInfo";

    // Utility method to get full file path and ensure the directory exists
    private static String getFullPath(String filename) {
        File dir = new File(BASE_FOLDER);
        if (!dir.exists()) {
            dir.mkdirs(); // Create the folder if it doesn't exist.
        }
        return BASE_FOLDER + File.separator + filename;
    }
    
    public static <T> void saveData(String filename, List<T> list) {
        String fullPath = getFullPath(filename);
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fullPath))) {
            out.writeObject(list);
        } catch (IOException e) {
            System.err.println("Error saving " + fullPath + ": " + e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public static <T> List<T> loadData(String filename) {
        String fullPath = getFullPath(filename);
        File file = new File(fullPath);
        if (!file.exists()) return new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fullPath))) {
            return (List<T>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading " + fullPath + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }
}

