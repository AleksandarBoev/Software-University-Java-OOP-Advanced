package myDemo;

import org.mockito.Mockito;

public class Main {
    public static void main(String[] args) {
        AccountManager fakeAccountManager = Mockito.mock(AccountManager.class);
        Mockito.when(fakeAccountManager.getSalary()).thenReturn(200.0);
        BankAccount bankAccount = new BankAccount(200000.0, fakeAccountManager);
        System.out.println("Hello?");
        System.out.println(fakeAccountManager.getSalary());

        BankAccount bankAccount1 = Mockito.mock(BankAccount.class);
        Mockito.verify(bankAccount1);
//        BankAccount bankAccount = new BankAccount()
    }
}
