public class EducationalLoan extends Loan {
    public EducationalLoan(Borrower borrower, double loanAmount, int loanTerm) {
        super(borrower, loanAmount, loanTerm, "Educational Loan");
    }

    @Override
    public double calculateInterestRate() {
        return (borrower.getCreditScore() > 750) ? 4.5 : (borrower.getCreditScore() >= 650) ? 6.0 : 8.5;
    }
}
