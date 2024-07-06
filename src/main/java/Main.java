public class Main {
    public static void main(String[] args) {

        Bank bank = new Bank();
        bank.loadUsersFromFile("data/accounts.txt");

        ATM atm = new ATM(bank);
        atm.start();

        bank.saveUsersToFile("data/accounts.txt");
        System.out.println("Данные успешно сохранены. Программа завершена.");
    }
}
