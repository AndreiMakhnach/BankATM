import java.util.Scanner;

public class ATM {

    private Bank bank;
    private Scanner scanner;
    private boolean isRunning;

    public ATM(Bank bank) {
        this.bank = bank;
        this.scanner = new Scanner(System.in);
        this.isRunning = true;
    }

    public void start() {
        System.out.println("Добро пожаловать в банкомат!");

        while (isRunning) {
            System.out.print("Введите номер карты: ");
            String cardNumber = scanner.nextLine();

            System.out.print("Введите ПИН-код: ");
            String pin = scanner.nextLine();

            if (bank.validatePin(cardNumber, pin)) {
                User user = bank.getUserByCardNumber(cardNumber);
                if (user != null) {
                    System.out.println("Здравствуйте, " + user.getName() + "!");
                    showMenu(user);
                }
            } else {
                System.out.println("Неверный номер карты или ПИН-код.");
            }
        }
    }

    private void showMenu(User user) {
        while (true) {
            System.out.println("1. Проверить баланс");
            System.out.println("2. Снять средства");
            System.out.println("3. Пополнить баланс");
            System.out.println("4. Выйти");

            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Считываем оставшийся символ новой строки

            switch (choice) {
                case 1:
                    checkBalance(user);
                    break;
                case 2:
                    withdrawFunds(user);
                    break;
                case 3:
                    depositFunds(user);
                    break;
                case 4:
                    System.out.println("Выход.");
                    isRunning = false;
                    return;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private void checkBalance(User user) {
        System.out.println("Ваш баланс: " + user.getCard().getBalance());
    }

    private void withdrawFunds(User user) {
        System.out.print("Введите сумму для снятия: ");
        double amount = scanner.nextDouble();

        if (amount > 0 && amount <= user.getCard().getBalance()) {
            user.getCard().setBalance(user.getCard().getBalance() - amount);
            System.out.println("Операция выполнена. Новый баланс: " + user.getCard().getBalance());
        } else {
            System.out.println("Недостаточно средств или неверная сумма.");
        }
    }

    private void depositFunds(User user) {
        System.out.print("Введите сумму для пополнения: ");
        double amount = scanner.nextDouble();

        if (amount > 0 && amount <= 1000000) {
            user.getCard().setBalance(user.getCard().getBalance() + amount);
            System.out.println("Операция выполнена. Новый баланс: " + user.getCard().getBalance());
        } else {
            System.out.println("Неверная сумма.");
        }
    }
}
