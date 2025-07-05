package in.ac.adit.pwj.miniproject.library;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

class UserDataManager {
    @SuppressWarnings("unchecked")
    public static List<User> loadUsers(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<User>) ois.readObject();
        } catch (Exception e) {
            System.out.println("Failed to load users: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void saveUsers(List<User> users, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(users);
        } catch (Exception e) {
            System.out.println("Failed to save users: " + e.getMessage());
        }
    }
}