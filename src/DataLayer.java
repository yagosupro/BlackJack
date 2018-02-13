import entity.*;

import java.util.ArrayList;

public class DataLayer implements ModelLayer {


    ArrayList<Players> players;
    Deck deck;
    ArrayList<Players> playersWhoPlay = new ArrayList<>();
    ArrayList<Card> cards;
    ArrayList<Players> winnersList = new ArrayList<>();

    int numberPlayers = 0;


    @Override
    public void setOutPlayer(int id) {
        players.get(id).setStatus("Loose");
    }

    @Override
    public String getPlayerNameById(int id) {
        return players.get(id).getName();
    }

    @Override
    public int getTotal(int id) {
        return players.get(id).getHandValue();
    }

    @Override
    public void setWinner(int id) {
        players.get(id).setStatus("Winner");
    }


    @Override
    public void wager() {
        for (int i = 0; i < players.size(); i++) {
            players.get(i).wager();
        }
    }

    @Override
    public void inicializePlayer() {
        playersWhoPlay = players;
    }

    @Override
    public Card getCard(int userId) {
        players.get(userId).addCardToHand((deck.giveCard()));
        return players.get(userId).getLastCard();

    }

    @Override
    public void getCardRoundOne() {
        for (int i = 0; i < players.size(); i++) {
            players.get(i).addCardToHand((deck.giveCard()));
            players.get(i).addCardToHand(deck.giveCard());
        }
    }

    @Override
    public ArrayList<Players> getPlayersList() {
        return players;
    }


    @Override
    public Boolean checkWinner() {
        boolean winner = false;
        for (int i = 0; i < players.size(); i++) {
            players.get(i).getHandValue();

        }
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getStatus() == "Winner") {
                winnersList.add(players.get(i));
                winner = true;
            }
        }
        return winner;
    }


    @Override
    public ArrayList<Players> getWinnerRoundOne() {
        if (winnersList.size() > 1) {
            for (int i = 0; i < winnersList.size(); i++) {
                if (winnersList.get(i).getName() == "Dealer") {
                    winnersList.remove(i);
                }
            }
        }
        return winnersList;
    }

    @Override
    public void addWinnings() {
        double winningCash = players.size() * players.get(0).getWager() / winnersList.size();

        for (int i = 0; i < players.size(); i++) {
            for (int j = 0; j < winnersList.size(); j++) {
                if (players.get(i).getName() == winnersList.get(j).getName()) {
                    players.get(i).addWinnings(winningCash);
                }
            }
        }

    }

    @Override
    public int getWinnerListSize() {
        return winnersList.size();
    }

    @Override
    public ArrayList<Players> getWinner() {

        return winnersList;
    }

    @Override
    public void checkWinnerOnPoints() {
        int winPoints = 0;

        for (int i = 0; i < players.size(); i++) {
            players.get(i).getHandValue();
        }
        for (int i = 0; i < players.size(); i++) {
            if (winPoints < players.get(i).getTotal() && players.get(i).getTotal() < 21) {
                winPoints = players.get(i).getTotal();

            }
        }

        for (int i = 0; i < players.size(); i++) {
            if (winPoints == players.get(i).getTotal()) {
                winnersList.add(players.get(i));
                players.get(i).setStatus("Winner");
            }
        }
        if (winnersList.size() == 0) {
            winnersList.add(players.get(4));
        }


    }

    @Override
    public void inicializeNewRound() {
        winnersList = new ArrayList<>();
        playersWhoPlay = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            players.get(i).clearCardIsHand();
            players.get(i).setStatus("");
        }

    }

    @Override
    public void createBotAlco(String name) {
        players.add(new BotAlco(name));
    }


    @Override
    public Players createPlayer(String name) {
        players.add(new Player(name));
        numberPlayers++;
        return null;
    }


    @Override
    public Players createDealer() {
        players.add(new Dealer());
        return null;
    }

    @Override
    public void createBot(String name) {
        players.add(new Bot(name));
    }

    @Override
    public void createNewDeck() {
        deck = new Deck();
        deck.populate();
        deck.shuffleDeck();

    }

    @Override
    public void clearListPlayers() {
        players = new ArrayList<>();
    }

    @Override
    public int getDeckSize() {
        return deck.getDeckSize();
    }


}
