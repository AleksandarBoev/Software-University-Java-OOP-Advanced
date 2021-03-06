package myDemoTests;

import myDemo.AccountManager;
import myDemo.BankAccount;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;

public class BankAccountTest {
    @Test(expected = IllegalArgumentException.class)
    public void addMoneyTest() throws NoSuchFieldException, IllegalAccessException {
        BankAccount bankAccount = new BankAccount();
        bankAccount.addMoney(-50.0);

        Field balanceField = BankAccount.class.getDeclaredField("balance");
        balanceField.setAccessible(true);
        double currentBalance = (Double)balanceField.get(bankAccount);
        balanceField.setAccessible(false);

        Assert.assertTrue(50.0 == currentBalance);
    }

    @Test
    public void testPayingAccountManager() {
        AccountManager fakeAccountManager = Mockito.mock(AccountManager.class);

        fakeAccountManager.getPaid(100);
    }
}
