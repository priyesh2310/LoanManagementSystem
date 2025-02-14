public class HomeLoan extends com.loantype.Loan {
    public HomeLoan(Borrower borrower, double loanAmount, int loanTerm) {
        super(borrower, loanAmount, loanTerm, "Home Loan");
    }

    @Override
    public double calculateInterestRate() {
        return (borrower.getCreditScore() > 750) ? 3.0 : (borrower.getCreditScore() >= 650) ? 5.5 : 7.0;
    }
}
