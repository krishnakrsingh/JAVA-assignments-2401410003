import java.util.Scanner;

class Account {
    private int accountNumber;
    private String accountHolderName;
    private double balance;
    private String email;
    private String phoneNumber;

    public Account(int accountNumber, String accountHolderName, double balance, String email, String phoneNumber) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited: " + amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Successfully withdrawn: " + amount);
        } else if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void displayAccountDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Balance: " + balance);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phoneNumber);
    }

    public void updateContactDetails(String email, String phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        System.out.println("Contact details updated successfully.");
    }

    public int getAccountNumber() {
        return accountNumber;
    }
}

class UserInterface {
    private Account[] accounts;
    private int accountCount;
    private Scanner scanner;

    public UserInterface() {
        accounts = new Account[100]; // can store 100 accounts
        accountCount = 0;
        scanner = new Scanner(System.in);
    }

    public void createAccount() {
        System.out.print("Enter account holder name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter initial deposit amount: ");
        double initialBalance = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        
        System.out.print("Enter email address: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();

        int accountNumber = 10000 + accountCount + 1;
        accounts[accountCount] = new Account(accountNumber, name, initialBalance, email, phone);
        accountCount++;

        System.out.println("Account created successfully with Account Number: " + accountNumber);
    }

    private Account findAccount(int accountNumber) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].getAccountNumber() == accountNumber) {
                return accounts[i];
            }
        }
        return null;
    }

    public void performDeposit() {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        Account account = findAccount(accountNumber);
        if (account != null) {
            account.deposit(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    public void performWithdrawal() {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        Account account = findAccount(accountNumber);
        if (account != null) {
            account.withdraw(amount);
        } else {
            System.out.println("Error: Account not found.");
        }
    }

    public void showAccountDetails() {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        scanner.nextLine();

        Account account = findAccount(accountNumber);
        if (account != null) {
            account.displayAccountDetails();
        } else {
            System.out.println("Error: Account not found.");
        }
    }

    public void updateContact() {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Enter new email: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter new phone number: ");
        String phone = scanner.nextLine();

        Account account = findAccount(accountNumber);
        if (account != null) {
            account.updateContactDetails(email, phone);
        } else {
            System.out.println("Error: Account not found.");
        }
    }

    public void mainMenu() {
        while (true) {
            System.out.println("\n--- Welcome to the Banking Application ---");
            System.out.println("1. Create a new account");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. View account details");
            System.out.println("5. Update contact details");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    performDeposit();
                    break;
                case 3:
                    performWithdrawal();
                    break;
                case 4:
                    showAccountDetails();
                    break;
                case 5:
                    updateContact();
                    break;
                case 6:
                    System.out.println("Thank you for using the Banking Application.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}

public class BankingApplication {
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ui.mainMenu();
    }
}