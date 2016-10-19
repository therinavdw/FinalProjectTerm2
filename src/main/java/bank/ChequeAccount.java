package bank;

import exception.NegativeAmountException;

import java.util.Random;

/**
 * Created by therina on 2016/05/16.
 */
public abstract class ChequeAccount extends BankAccount {


        //inherits methods and instances from bankAccount class
        public ChequeAccount (Client client, double balance) {
            super(client, balance);

        }

    @Override
    public void withdraw(double amount) throws NegativeAmountException {
        //see if there is enough funds
        if(amount < 0){
            throw new NegativeAmountException();
        } else if (amount > balance ) {
            System.err.println("Not enough funds,transaction must be cancelled");
        } else {
            balance = balance - amount;
        }
    }

    @Override
    public void deposit(double amount) throws NegativeAmountException {
        if (amount < 0){
            throw new NegativeAmountException();
        }else {
            balance += amount;
        }

    }

    public void transfer(BankAccount otherAccount) throws NegativeAmountException
    {
        transactionCount--;
        otherAccount.deposit(balance);
        balance = 0;
    }



    @Override
    public void closeAccount(BankAccount otherAccount) throws NegativeAmountException {
        transfer(otherAccount);

    }







}
