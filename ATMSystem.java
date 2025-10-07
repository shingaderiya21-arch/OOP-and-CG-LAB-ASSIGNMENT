
import java.util.Scanner;

class ATM {

    private double balance;
    private int pin;

    // Constructor
    public ATM(int pin, double initialBalance) {
        this.pin = pin;
        this.balance = initialBalance;
    }

    // Verify PIN
    public boolean verifyPin(int enteredPin) {
        return this.pin == enteredPin;
    }

    // Check balance
    public void checkBalance() {
        System.out.println("Your current balance: ₹" + balance);
    }

    // Deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("₹" + amount + " deposited successfully.");
            checkBalance();
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    // Withdraw money
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("₹" + amount + " withdrawn successfully.");
            checkBalance();
        } else {
            System.out.println("Insufficient balance or invalid amount!");
        }
    }
}

public class ATMSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Create ATM object with default PIN 1234 and balance 5000
        ATM atm = new ATM(1234, 5000);

        System.out.print("Enter your PIN: ");
        int enteredPin = sc.nextInt();

        if (atm.verifyPin(enteredPin)) {
            int choice;
            do {
                System.out.println("\n===== ATM MENU =====");
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit Money");
                System.out.println("3. Withdraw Money");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        atm.checkBalance();
                        break;
                    case 2:
                        System.out.print("Enter deposit amount: ");
                        double depositAmount = sc.nextDouble();
                        atm.deposit(depositAmount);
                        break;
                    case 3:
                        System.out.print("Enter withdrawal amount: ");
                        double withdrawAmount = sc.nextDouble();
                        atm.withdraw(withdrawAmount);
                        break;
                    case 4:
                        System.out.println("Thank you for using ATM. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice! Try again.");
                }
            } while (choice != 4);

        } else {
            System.out.println("Incorrect PIN! Access Denied.");
        }

        sc.close();
    }
}
