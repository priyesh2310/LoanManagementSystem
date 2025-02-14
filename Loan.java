public abstract class Loan {
    public com.loantype.Borrower borrower;
    public double loanAmount;
    public double interestRate;
    public int loanTerm;
    protected String loanType;

    public Loan(com.loantype.Borrower borrower, double loanAmount, int loanTerm, String loanType) {
        this.borrower = borrower;
        this.loanAmount = loanAmount;
        this.loanTerm = loanTerm;
        this.loanType = loanType;
        this.interestRate = calculateInterestRate();
    }

    public abstract double calculateInterestRate();

    public double calculateEMI() {
        double monthlyRate = interestRate / 1200;
        return (loanAmount * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -loanTerm * 12));
    }

    public void showLoanDetails() {
        System.out.println("\n✅ Loan Approved!");
        System.out.println("Loan Type: " + loanType);
        System.out.println("Borrower: " + borrower.getName());
        System.out.println("Age: " + borrower.getAge());
        System.out.println("Credit Score: " + borrower.getCreditScore());
        System.out.println("Salary: ₹" + borrower.getSalary());
        System.out.println("Loan Amount: ₹" + loanAmount);
        System.out.println("Interest Rate: " + interestRate + "%");
        System.out.println("Loan Term: " + loanTerm + " years");
        System.out.println("EMI: ₹" + String.format("%.2f", calculateEMI()));
    }

    public static double calculateInterestRateForCreditScore(int creditScore) {
        if (creditScore < 400 || creditScore > 900) {
            throw new IllegalArgumentException("❌ Credit Score must be between 400-900.");
        }
        if (creditScore > 750) return 3.5;  // Best interest rate for high credit score
        else if (creditScore >= 650) return 4.0;
        else return 8.5;  // Higher interest rate for low credit score
    }
}
