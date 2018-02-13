package entity;

import java.util.ArrayList;

public abstract class Players {
    String name;
    double cash = 100;
    double wager = 1;
    ArrayList<Card> cardIsHand = new ArrayList<>();
    int total = 0;
    int acesCount = 0;
    String status;
    int strategyAi = 0;

    public double getCash() {
        return cash;
    }

    public int getStrategyAi() {
        return strategyAi;
    }

    public boolean getAnswer() {
        return true;
    }

    public double getWager() {
        return wager;
    }

    public int getTotal() {
        return total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getName() {
        return name;
    }

    public void wager() {
        cash -= wager;
    }

    public void addCardToHand(Card newCard) {
        cardIsHand.add(newCard);
    }

    public int getHandValue() {
        total = 0;
        acesCount = 0;

        for (Card card : cardIsHand) {
            if (card.getRankValue() == 1) {
                acesCount++;
                total += card.getRankValue();
            } else total += card.getRankValue();
        }
        if (total > 21) {
            while (acesCount > 0) {
                total -= 10;
                acesCount--;
            }
        }
        if (total == 21) {
            status = "Winner";
        }
        if (total > 21) {
            status = "Looser";
        }


        return total;
    }

    public ArrayList<Card> getHandList() {
        return cardIsHand;
    }

    public void clearCardIsHand() {
        cardIsHand = new ArrayList<>();
    }

    public void addWinnings(double winnings) {
        cash += winnings;
    }

    public Card getLastCard() {

        return cardIsHand.get(cardIsHand.size() - 1);
    }
}
