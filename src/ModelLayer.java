import entity.Card;
import entity.Players;

import java.util.ArrayList;

public interface ModelLayer {

    Players createPlayer(String name);

    Players createDealer();

    void createBot(String name);

    void createNewDeck();

    void clearListPlayers();

    int getDeckSize();

    void wager();

    void inicializePlayer();

    Card getCard(int userId);

    void getCardRoundOne();

    ArrayList<Players> getPlayersList();

    Boolean checkWinner();

    ArrayList<Players> getWinnerRoundOne();

    void addWinnings();

    int getWinnerListSize();

    void setOutPlayer(int id);

    String getPlayerNameById(int id);

    int getTotal(int id);

    void setWinner(int id);

    ArrayList<Players> getWinner();

    void checkWinnerOnPoints();

    void inicializeNewRound();

    void createBotAlco(String name);
}
