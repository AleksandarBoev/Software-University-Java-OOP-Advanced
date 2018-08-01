package myDemo;

public class BankAccount {
    private double balance;
    private AccountManager accountManager;

    public BankAccount() {
    }

    public BankAccount(double balance, AccountManager accountManager) {
        this.balance = balance;
        this.accountManager = accountManager;
    }

    public void addMoney(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("You can't add a negative amount!");
        }
        this.balance += amount;
    }

    public void withdrawMoney(double amount) {
        if (amount > this.balance) {
            throw new IllegalArgumentException("Nod enough cash!");
        }

        this.balance -= amount;
    }

    public void printWord(String word) {
        System.out.println(word);
    }

    public void payAccountManager() {
        this.balance -= this.accountManager.getSalary();
        this.accountManager.getPaid(this.accountManager.getSalary());
    }
}
