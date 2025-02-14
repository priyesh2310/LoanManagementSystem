public class CarLoan extends Loan {
    public CarLoan(Borrower borrower, double loanAmount, int loanTerm) {
        super(borrower, loanAmount, loanTerm, "Car Loan");
    }

    @Override
    public double calculateInterestRate() {
        return (borrower.getCreditScore() > 750) ? 3.0 : (borrower.getCreditScore() >= 650) ? 4.5 : 8.5;
    }
}
