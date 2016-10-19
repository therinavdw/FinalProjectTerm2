import bank.*;
import exception.NegativeAmountException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by therina on 2016/05/23.
 */
public class TestAccounts {
    @Test
    public  void  testDeductFees()throws NegativeAmountException{
        Client client = new Client("Therina", 1234567890L, "therina@openwindow.co.za");

        BankAccount myBankAccount = new DiamondCheque(client,2000);
        myBankAccount.withdraw(10);
        myBankAccount.withdraw(10);
        myBankAccount.withdraw(10);
        myBankAccount.withdraw(10);
        myBankAccount.withdraw(10);
        myBankAccount.withdraw(10);
        myBankAccount.withdraw(10);
        myBankAccount.withdraw(10);
        myBankAccount.withdraw(10);
        myBankAccount.withdraw(10);
        myBankAccount.withdraw(10);
        myBankAccount.withdraw(10);
        myBankAccount.withdraw(10);
        myBankAccount.withdraw(10);
        myBankAccount.withdraw(10);
        myBankAccount.withdraw(10);
        myBankAccount.withdraw(10);
        myBankAccount.withdraw(10);
        myBankAccount.withdraw(10);
        myBankAccount.withdraw(10);
        myBankAccount.withdraw(10);
        myBankAccount.withdraw(10);
        myBankAccount.withdraw(10);
        myBankAccount.withdraw(10);
        myBankAccount.withdraw(10);
        myBankAccount.withdraw(10);
        myBankAccount.withdraw(10);


        double epsilon = 0.0001;

        System.out.println(myBankAccount.getBalance());
        Assert.assertEquals(true, myBankAccount.getBalance() < 1681 + epsilon && myBankAccount.getBalance() > 1681 - epsilon);
    }
    @Test
    public void testInterest() throws NegativeAmountException {
        Client client = new Client("Cara",1234569L,"cara@gamil.com");
        BankAccount yourBankAccount = new InvestedSavings(client,3000);
        yourBankAccount.endMonth();


        double epsilon = 0.0001;

        System.out.println(yourBankAccount.getBalance());
        Assert.assertEquals(true, yourBankAccount.getBalance() < 2953.5 + epsilon && yourBankAccount.getBalance() > 2953.5  - epsilon);
    }

}
