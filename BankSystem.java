
import java.util.Scanner;

class BankAccount {

    String accountHolderName;
    String accountNumber;
    double balance;

    // Constructor
    public BankAccount(String name, String accNumber, double initialDeposit) {
        this.accountHolderName = name;
        this.accountNumber = accNumber;
        this.balance = initialDeposit;
    }

    // Deposit method
    void deposit(double amount) {
        balance += amount;
        System.out.println("₹" + amount + " deposited. New balance: ₹" + balance);
    }

    // Withdraw method
    void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("₹" + amount + " withdrawn. New balance: ₹" + balance);
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    // Display account details
    void displayDetails() {
        System.out.println("\nAccount Holder: " + accountHolderName);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: ₹" + balance);
    }
}

public class BankSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Taking input from user
        System.out.print("Enter account holder name: ");
        String name = scanner.nextLine();

        System.out.print("Enter account number: ");
        String accNumber = scanner.nextLine();

        System.out.print("Enter initial deposit: ");
        double deposit = scanner.nextDouble();

        // Creating account
        BankAccount account = new BankAccount(name, accNumber, deposit);

        account.displayDetails();

        // Example deposit and withdrawal
        account.deposit(1000);
        account.withdraw(500);

        account.displayDetails();
    }
}
