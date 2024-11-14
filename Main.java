import java.util.Scanner;

public class Main {
    private static double balance = 0.0; 

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Welcome to the Simple Banking Application!");

        while (running) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    withdraw(withdrawAmount);
                    break;
                case 4:
                    System.out.println("Thank you for using the Simple Banking Application!");
                    running = false; 
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }

        scanner.close();
    }
    private static void checkBalance() {
        System.out.printf("Your current balance is: $%.2f\n", balance);
    }

    private static void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("$%.2f deposited successfully.\n", amount);
            checkBalance();
        } else {
            System.out.println("Invalid deposit amount. Please enter a positive value.");
        }
    }
    private static void withdraw(double amount) {
        if (amount > 0) {
            if (amount <= balance) {
                balance -= amount;
                System.out.printf("$%.2f withdrawn successfully.\n", amount);
                checkBalance();
            } else {
                System.out.println("Insufficient balance. Transaction failed.");
            }
        } else {
            System.out.println("Invalid withdrawal amount. Please enter a positive value.");
        }
    }
}
