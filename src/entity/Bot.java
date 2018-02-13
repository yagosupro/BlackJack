package entity;

public class Bot extends Players {


    public Bot(String name) {
        this.name = name;
        this.strategyAi = 2;
    }

    @Override
    public boolean getAnswer() {
        getHandValue();
        if (total < 14)
            return true;
        else
            return false;
    }
}
