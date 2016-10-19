package bank;

import exception.NegativeAmountException;

/**
 * Created by therina on 2016/05/16.
 */
public class InvestedSavings extends SavingsAccount {
    protected double transactionFee;
    protected double interest;
    protected double amount;
    protected int transactionsCount;


    public InvestedSavings(Client client, double balance){
        super(client,balance);
        transactionFee = 50;
        interest = 1.4/100;
        transactionsCount = 0;

    }
    @Override
    public void endMonth() throws NegativeAmountException {
        amount = balance * (interest/12);
        deposit(amount);
        transactionsCount = 0;
    }

    public void deductFees()
    {
        balance = balance - transactionFee;
    }

    @Override
    public void withdraw(double amount) throws NegativeAmountException{
        super.withdraw(amount);
        transactionsCount++;
        deductFees();
    }

    @Override
    public void deposit(double amount)throws NegativeAmountException{
        super.deposit(amount);
        transactionsCount++;
        deductFees();
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

    @Override
    public String toString() {
        return "Invested Savings: " +
              accountNum ;
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




