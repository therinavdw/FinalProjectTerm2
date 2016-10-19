import bank.Client;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

/**
 * Created by therina on 2016/05/19.
 */
public class TestClients {

    @Test
    public void testAddNewClient()
    {
        Map<String, Client> clients;
        //pokemon is abstract superclass and charmander concrete class
        Client client = new Client("Therina",0,"therina@yahoo.com");
        System.out.println(client.toString());
        Assert.assertNotNull("This is Therina",client.toString());
    }



}
