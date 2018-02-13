package entity;

public class BotAlco extends Players {
    public BotAlco(String name) {
        this.name = name;
        this.strategyAi = 2;
    }

    @Override
    public boolean getAnswer() {
        getHandValue();
        if (total < 20)
            return true;
        else
            return false;
    }
}
