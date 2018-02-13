package entity;

public class Dealer extends Players {


    public Dealer() {
        this.name = "Dealer";
        this.strategyAi = 1;
    }

    @Override
    public boolean getAnswer() {
        getHandValue();
        if (total < 17) {
            return true;
        } else
            return false;
    }
}
