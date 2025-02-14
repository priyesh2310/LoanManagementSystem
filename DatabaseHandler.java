import com.loantype.Loan;
import java.sql.*;

public class DatabaseHandler {
    private static final String URL = "jdbc:mysql://localhost:3306/Loandb";
    private static final String USER = "root";
    private static final String PASSWORD = "Qwerty@123";

    public static void saveLoanToDB(Loan loan, String loanType) {
        String query = "INSERT INTO loans (borrowerName, loanType, loanAmount, interestRate, loanTerm, creditScore, salary, age) VALUES (?, ?, ?, ?, ?,?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, loan.borrower.getName());
            stmt.setString(2, loanType);
            stmt.setDouble(3, loan.loanAmount);
            stmt.setDouble(4, loan.interestRate);
            stmt.setInt(5, loan.loanTerm);
            stmt.setInt(6, loan.borrower.getCreditScore());
            stmt.setDouble(7, loan.borrower.getSalary());
            stmt.setInt(8, loan.borrower.getAge());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int generatedLoanID = rs.getInt(1);
                System.out.println("✅ Loan saved successfully with Loan ID: " + generatedLoanID);
            }

        } catch (SQLException e) {
            System.out.println("❌ Database Error: " + e.getMessage());
        }


}
    public static void fetchLoansFromDB() {
        String query = "SELECT * FROM loans";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("\n=== Stored Loan Records ===");
            if (!rs.isBeforeFirst()) {
                System.out.println("⚠ No records found.");
                return;
            }

            while (rs.next()) {
                System.out.println("\nLoan ID: " + rs.getInt("loanID"));
                System.out.println("Loan Type: " + rs.getString("loanType"));
                System.out.println("Borrower: " + rs.getString("borrowerName"));
                System.out.println("Age: " + rs.getInt("age"));
                System.out.println("Credit Score: " + rs.getInt("creditScore"));
                System.out.println("Salary: ₹" + rs.getDouble("salary"));
                System.out.println("Loan Amount: ₹" + rs.getDouble("loanAmount"));
                System.out.println("Interest Rate: " + rs.getDouble("interestRate") + "%");
                System.out.println("Loan Term: " + rs.getInt("loanTerm") + " years");
            }

        } catch (SQLException e) {
            System.out.println("❌ Database Error: " + e.getMessage());
        }
    }

    public static void searchLoanByName(String borrowerName) {
        String query = "SELECT * FROM loans WHERE borrowerName = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, borrowerName);
            ResultSet rs = stmt.executeQuery();

            if (!rs.isBeforeFirst()) {
                System.out.println("⚠ No records found for borrower: " + borrowerName);
                return;
            }

            while (rs.next()) {
                System.out.println("\nLoan ID: " + rs.getInt("loanID"));
                System.out.println("Loan Type: " + rs.getString("loanType"));
                System.out.println("Loan Amount: ₹" + rs.getDouble("loanAmount"));
                System.out.println("Interest Rate: " + rs.getDouble("interestRate") + "%");
                System.out.println("Loan Term: " + rs.getInt("loanTerm") + " years");
            }
        } catch (SQLException e) {
            System.out.println("❌ Database Error: " + e.getMessage());
        }
    }
    public static void searchLoanByID(int loanID) {
        String query = "SELECT * FROM loans WHERE loanID = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, loanID);
            ResultSet rs = stmt.executeQuery();

            if (!rs.isBeforeFirst()) {
                System.out.println("⚠ No loan found with ID: " + loanID);
                return;
            }

            while (rs.next()) {
                System.out.println("\nBorrower: " + rs.getString("borrowerName"));
                System.out.println("Loan Type: " + rs.getString("loanType"));
                System.out.println("Loan Amount: ₹" + rs.getDouble("loanAmount"));
                System.out.println("Interest Rate: " + rs.getDouble("interestRate") + "%");
                System.out.println("Loan Term: " + rs.getInt("loanTerm") + " years");
            }
        } catch (SQLException e) {
            System.out.println("❌ Database Error: " + e.getMessage());
        }
    }
    public static void deleteLoanByName(String borrowerName) {
        String query = "DELETE FROM loans WHERE borrowerName = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, borrowerName);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("✅ Loan record for " + borrowerName + " has been deleted.");
            } else {
                System.out.println("⚠ No record found for deletion.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Database Error: " + e.getMessage());
        }
    }

    public static void updateCreditScore(String borrowerName, int newCreditScore) {
        String query = "UPDATE loans SET creditScore = ?, interestRate = ? WHERE borrowerName = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Calculate new interest rate based on updated credit score
            double newInterestRate = Loan.calculateInterestRateForCreditScore(newCreditScore);

            stmt.setInt(1, newCreditScore);
            stmt.setDouble(2, newInterestRate);
            stmt.setString(3, borrowerName);

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("✅ Credit score updated successfully for " + borrowerName);
            } else {
                System.out.println("⚠ No records found for borrower: " + borrowerName);
            }

        } catch (SQLException e) {
            System.out.println("❌ Database Error: " + e.getMessage());
        }
    }
}


