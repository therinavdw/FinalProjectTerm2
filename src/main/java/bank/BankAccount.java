package bank;

import exception.NegativeAmountException;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by therina on 2016/05/15.
 */

public abstract class BankAccount implements Serializable{
    protected String accountNum;
    protected Client client;
    protected double balance;
    protected int transactionCount;




    public abstract void withdraw(double amount) throws NegativeAmountException;
    public abstract void deposit(double amount) throws NegativeAmountException;
    public abstract void endMonth() throws NegativeAmountException;
    public abstract void closeAccount(BankAccount account) throws NegativeAmountException;





    //instance of a bankAccount
    public BankAccount( Client client, double balance){
        this.client = client;
        this.balance = balance;
        this.accountNum = generateAccountNum();

        //accountNum = "123";

    }



    public String generateAccountNum() {
        Random rn = new Random();

        this.accountNum = "1";
        for (int i = 0; i < 5; i++) {
            Integer randomDigit = rn.nextInt(10)+1;
            this.accountNum += randomDigit.toString();
        }


     return accountNum;

    }




    //return balance
    public double getBalance() {
        return balance;
    }

    //get client name
    public Client getClient() {
        return client;
    }

    //get account number
    public String getAccountNum() {
        return accountNum;
    }



    @Override
    public  String toString()
    {
        return getClient().toString();
    }



    public int getTransactionCount() {
        return transactionCount;
    }

    public void setTransactionCount(int transactionCount) {
        this.transactionCount = transactionCount;
    }
}

