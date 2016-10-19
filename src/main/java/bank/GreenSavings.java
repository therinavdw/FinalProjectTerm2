package bank;

import exception.NegativeAmountException;

/**
 * Created by therina on 2016/05/16.
 */
public class GreenSavings extends SavingsAccount {
    protected double transactionFee;
    protected double interest;
    protected double interestAmount;
    protected double amount;
    protected int transactionsCount;



    public GreenSavings(Client client, double balance){
        super(client,balance);
        transactionFee=10;
        interest = 1.2/100;
        transactionsCount = 0;

    }
    @Override
    public void endMonth() throws NegativeAmountException {
        interestAmount = balance * (interest/12);
        deposit(interestAmount);
        transactionsCount = 0;
    }


        @Override
        public void closeAccount(BankAccount otherAccount) throws NegativeAmountException {
            transfer(otherAccount);

        }

    public void transfer(BankAccount otherAccount) throws NegativeAmountException
    {   transactionsCount--;
        otherAccount.deposit(balance);
        balance = 0;
    }



    public void deductFees() {
        balance = balance - transactionFee;
    }

    @Override
    public void withdraw(double amount) throws NegativeAmountException{
        super.withdraw(amount);
        transactionsCount++;
        deductFees();
    }

    @Override
    public void deposit(double amount)throws NegativeAmountException {
        super.deposit(amount);
        transactionsCount++;
        deductFees();
    }

    @Override
    public String toString() {
        return "Green Savings: " + accountNum ;
    }

    @Override
    public int getTransactionCount() {
        return super.getTransactionCount();
    }



    @Override
    public void setTransactionCount(int transactionCount) {
        super.setTransactionCount(transactionCount);
    }


}
