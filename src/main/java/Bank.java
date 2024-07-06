import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Bank {

    private List<User> users;

    public Bank() {
        users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public User getUserByCardNumber(String cardNumber) {
        for (User user : users) {
            if (user.getCard().getCardNumber().equals(cardNumber)) {
                return user;
            }
        }
        return null;
    }

    public boolean validatePin(String cardNumber, String pin) {
        User user = getUserByCardNumber(cardNumber);
        if (user != null) {
            return user.getCard().getPin().equals(pin);
        }
        return false;
    }

    public void loadUsersFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 4) {
                    String name = parts[0];
                    String cardNumber = parts[1];
                    String pin = parts[2];
                    double balance = Double.parseDouble(parts[3]);
                    Card card = new Card(cardNumber, pin, balance);
                    User user = new User(name, card);
                    addUser(user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveUsersToFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (User user : users) {
                writer.write(user.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
