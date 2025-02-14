import com.db.DatabaseHandler;
import com.exception.InvalidAgeException;
import com.exception.InvalidCreditScoreException;
import com.exception.InvalidSalaryException;
import com.loantype.*;

import java.util.Scanner;

public class LoanManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Loan Management System ===");
            System.out.println("1. Apply for a Loan");
            System.out.println("2. Search Loan by Borrower Name or Loan ID");
            System.out.println("3. Display All Loan Records");
            System.out.println("4. Delete Loan by Borrower Name");
            System.out.println("5. Update Credit Score and Recalculate EMI");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    applyForLoan(scanner);
                    break;
                case 2:
                    searchLoan(scanner);
                    break;
                case 3:
                    DatabaseHandler.fetchLoansFromDB();
                    break;
                case 4:
                    deleteLoan(scanner);
                    break;
                case 5:
                    updateCreditScore(scanner);
                    break;
                case 6:
                    System.out.println("Exiting application.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("❌ Invalid choice! Please select a valid option.");
            }
        }
    }

    private static void applyForLoan(Scanner scanner) {
        try {
            System.out.println("\n=== Apply for a Loan ===");
            System.out.println("Choose Loan Type: 1. Home Loan  2. Car Loan  3. Educational Loan");
            System.out.print("Enter your choice: ");
            int loanTypeChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            String loanType;
            switch (loanTypeChoice) {
                case 1: loanType = "Home Loan"; break;
                case 2: loanType = "Car Loan"; break;
                case 3: loanType = "Educational Loan"; break;
                default:
                    System.out.println("❌ Invalid loan type selection.");
                    return;
            }

            System.out.print("Enter Borrower Name: ");
            String borrowerName = scanner.nextLine();

            System.out.print("Enter Age: ");
            int age = scanner.nextInt();

            System.out.print("Enter Credit Score (400-900): ");
            int creditScore = scanner.nextInt();

            System.out.print("Enter Monthly Salary (₹25,000 - ₹25,00,000): ");
            double salary = scanner.nextDouble();

            Borrower borrower = new Borrower(borrowerName, age, creditScore, salary);

            System.out.print("Enter Loan Amount: ");
            double loanAmount = scanner.nextDouble();

            System.out.print("Enter Loan Term (years): ");
            int loanTerm = scanner.nextInt();

            Loan loan = null;
            switch (loanType) {
                case "Home Loan": loan = new HomeLoan(borrower, loanAmount, loanTerm); break;
                case "Car Loan": loan = new CarLoan(borrower, loanAmount, loanTerm); break;
                case "Educational Loan": loan = new EducationalLoan(borrower, loanAmount, loanTerm); break;
            }

            if (loan != null) {
                loan.showLoanDetails();
                DatabaseHandler.saveLoanToDB(loan, loanType);
            }
        } catch (InvalidAgeException | InvalidSalaryException | InvalidCreditScoreException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void searchLoan(Scanner scanner) {
        System.out.println("\n=== Search Loan ===");
        System.out.println("1. Search by Borrower Name");
        System.out.println("2. Search by Loan ID");
        System.out.print("Enter your choice: ");
        int searchChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (searchChoice == 1) {
            System.out.print("Enter Borrower Name: ");
            String borrowerName = scanner.nextLine();
            DatabaseHandler.searchLoanByName(borrowerName);
        } else if (searchChoice == 2) {
            System.out.print("Enter Loan ID: ");
            int loanID = scanner.nextInt();
            DatabaseHandler.searchLoanByID(loanID);
        } else {
            System.out.println("❌ Invalid search option.");
        }
    }

    private static void deleteLoan(Scanner scanner) {
        System.out.println("\n=== Delete Loan ===");
        System.out.print("Enter Borrower Name to delete their loan: ");
        String borrowerName = scanner.nextLine();
        DatabaseHandler.deleteLoanByName(borrowerName);
    }

    private static void updateCreditScore(Scanner scanner) {
        System.out.println("\n=== Update Credit Score ===");
        System.out.print("Enter Borrower Name: ");
        String borrowerName = scanner.nextLine();

        System.out.print("Enter New Credit Score (400-900): ");
        int newCreditScore = scanner.nextInt();

        DatabaseHandler.updateCreditScore(borrowerName, newCreditScore);
    }
}
