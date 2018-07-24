package myDemo;

public class BankAccount {
    private double balance;

    public BankAccount() {
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
}
