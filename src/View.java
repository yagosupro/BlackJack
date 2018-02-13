import entity.Card;
import entity.Players;

import java.io.IOException;
import java.util.ArrayList;

public interface View {
    void showStartGame();

    int showChoiceNumberPlayers() throws IOException;

    String getNamePlayer() throws IOException;

    void showCardsRoundOne(ArrayList<Players> playersList);

    void showWinner(ArrayList<Players> winner, double money);

    void weHaveWinner();

    boolean wePlayNextQuestion(String playerName) throws IOException;

    void showGetCard(Card card, String name);

    void showCards(ArrayList<Card> handList, String name);

    void showTotal(int total, String name);

    void showYouLost(String name);

    void showYouWin(String name);

    void weHaveWinnerOnPoints();

    void showAllTotal(ArrayList<Players> playersList);

    boolean questionPlayNext() throws IOException;

}
