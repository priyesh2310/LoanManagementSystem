import com.exception.InvalidAgeException;
import com.exception.InvalidCreditScoreException;
import com.exception.InvalidSalaryException;


public class Borrower {
    private String name;
    private int age;
    private int creditScore;
    private double salary;

    public Borrower(String name, int age, int creditScore, double salary) throws InvalidAgeException, InvalidSalaryException, InvalidCreditScoreException {
        if (age < 0) throw new InvalidAgeException("❌ Age cannot be negative!");
        if (age < 21) throw new InvalidAgeException("❌ Loan application denied. Minimum age is 21.");
        if (salary < 25000 || salary > 2500000) throw new InvalidSalaryException("❌ Loan denied. Salary must be between ₹25,000 and ₹25,00,000.");
        if (creditScore < 400 || creditScore > 900) throw new InvalidCreditScoreException("❌ Invalid Credit Score. It must be between 400 and 900.");

        this.name = name;
        this.age = age;
        this.creditScore = creditScore;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public int getCreditScore() {
        return creditScore;
    }
    public double getSalary() {
        return salary;
    }

    public void updateCreditScore(int newCreditScore) throws InvalidCreditScoreException {
        if (newCreditScore < 400 || newCreditScore > 900) throw new InvalidCreditScoreException("❌ Invalid Credit Score.");
        this.creditScore = newCreditScore;
    }


}
