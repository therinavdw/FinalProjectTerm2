package main;

import bank.*;
import exception.NegativeAmountException;
import gui.HomeFrame;
import gui.MenuFrame;

/**
 * Created by therina on 2016/05/17.
 */
public class Main {
    public static void main(String[] args) throws NegativeAmountException {

        Client client = new Client("Therina", 1234567890L, "therina@openwindow.co.za");
        BankAccount myBankAccount = new DiamondCheque(client,2000 );
        BankAccount yourBankAccount = new InvestedSavings(client,1000);
        BankAccount greenBankAccount = new GreenSavings(client,3000);
        BankAccount goldBankAccount = new GoldCheque(client,2000);
        client.getBankAccounts().add(myBankAccount);
        client.getBankAccounts().add(yourBankAccount);
        client.getBankAccounts().add(greenBankAccount);
        client.getBankAccounts().add(goldBankAccount);

        //unhandled experience
        //main method can handle it(catch) or rethrow to method that handles it
        //rethrow by adding throw to main - not great
        //yourPokemon.gainExperience(-1000);
        try {
            myBankAccount.withdraw(-1000);
        } catch (NegativeAmountException ex){
            System.out.println("You cannot withdraw a negative amount");
            //ex.printStackTrace();
        }

        ProgramState programState = new ProgramState();
        programState.getBankAccounts().add(myBankAccount);
        programState.getBankAccounts().add(yourBankAccount);
        programState.getBankAccounts().add(greenBankAccount);
        programState.getBankAccounts().add(goldBankAccount);
        programState.getClientList().add(client);



        new HomeFrame(programState);


    }



}

