package in.ac.adit.pwj.miniproject.library;

import java.io.*;
import java.util.HashMap;

class DataManager {
    @SuppressWarnings("unchecked")
    public static HashMap<String, Book> load(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("Books file not found. Initializing empty inventory.");
            return new HashMap<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (HashMap<String, Book>) ois.readObject();
        } catch (Exception e) {
            System.out.println("Failed to load books: " + e.getMessage());
            return new HashMap<>();
        }
    }

    public static void save(HashMap<String, Book> books, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(books);
        } catch (Exception e) {
            System.out.println("Failed to save file: " + e.getMessage());
        }
    }
}

