package bank;

import exception.NegativeAmountException;

import java.util.Random;

/**
 * Created by therina on 2016/05/16.
 */
public  class DiamondCheque extends ChequeAccount{
    protected double transactionFee;
    protected int freeTransactions;
    protected int transactionsCount;




    public DiamondCheque(Client client, double balance)
    {
        super(client,balance);
        transactionFee = 7;
        freeTransactions = 20;
        transactionsCount = 0;

    }
    @Override
    public void endMonth() throws NegativeAmountException{
        transactionsCount = 0;


    }

    @Override
    public void closeAccount(BankAccount otherAccount) throws NegativeAmountException {
        transfer(otherAccount);

    }


    public void deductFees() {
        if (transactionsCount > freeTransactions) {
            balance = balance - transactionFee;
        }
    }

    @Override
    public void withdraw(double amount) throws NegativeAmountException {
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

    public void transfer(BankAccount otherAccount) throws NegativeAmountException
    {   transactionsCount--;
        otherAccount.deposit(balance);
        balance = 0;
    }




    @Override
    public String toString() {
        return "Diamond Cheque: " + accountNum;
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
