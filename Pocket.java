public class Pocket {
    private int coin = 0;

    public void deposit(int coin) {
        this.coin += coin;
    }

    public void withdraw(int coin) {
        this.coin -= coin;
    }

    public int getcoin() {
        return this.coin;
    }
}
