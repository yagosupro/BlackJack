import entity.Card;
import entity.Players;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ConsoleView implements View {
    String strelki = "----------------------";

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


    @Override
    public void showStartGame() {
        System.out.println("Начало Новой игры!");
    }


    @Override
    public int showChoiceNumberPlayers() throws IOException {
        int numberPlayer = 0;
        do {
            System.out.println("1 игрок или 2?");
            numberPlayer = Integer.parseInt(reader.readLine());

        } while (!(numberPlayer == 1 || numberPlayer == 2));

        return numberPlayer;
    }


    public Boolean getAnswer() throws IOException {
        String answer;
        do {
            answer = reader.readLine();
        } while (!(answer.equals("y") || answer.equals("n")));

        if (answer.equals("y")) {
            return true;
        }
        if (answer.equals("n")) {
            return false;
        }
        return false;

    }

    @Override
    public String getNamePlayer() throws IOException {
        String name;
        do {
            System.out.println("введите имя:");
            name = reader.readLine();

        } while (name.isEmpty());

        return name;
    }


    @Override
    public void showCardsRoundOne(ArrayList<Players> playersList) {
        ArrayList<Card> cardIsHand;
        System.out.println(strelki);
        for (int i = 0; i < playersList.size(); i++) {
            System.out.println("Игрок " + playersList.get(i).getName() + " получил карты:");
            cardIsHand = playersList.get(i).getHandList();

            for (int j = 0; j < cardIsHand.size(); j++) {
                if (playersList.get(i).getName() == "Dealer") {
                    System.out.println("*****");
                    j++;
                    System.out.println(cardIsHand.get(j).getFace() + " " + cardIsHand.get(j).getSuit());
                } else {

                    System.out.println(cardIsHand.get(j).getFace() + " " + cardIsHand.get(j).getSuit());
                }


            }
        }
        System.out.println(strelki);

    }

    @Override
    public void showWinner(ArrayList<Players> winner, double money) {
        for (int i = 0; i < winner.size(); i++) {
            System.out.println(winner.get(i).getName() + " выйграл сумму: " + money);
            System.out.println("Количество очков: " + winner.get(i).getTotal());
        }
        System.out.println(strelki);

    }

    @Override
    public void weHaveWinner() {
        System.out.println("Кажется у нас победитель!");
    }

    @Override
    public boolean wePlayNextQuestion(String playerName) throws IOException {
        System.out.println(strelki);
        System.out.println("Игрок " + playerName + " берет карту?");
        System.out.println("Введите y(Да), n(Нет)");
        if (getAnswer()) {
            return true;
        } else return false;

    }

    @Override
    public void showGetCard(Card card, String name) {

        System.out.println(strelki);
        System.out.println("Игрок " + name + " достает карту: ");
        System.out.println(card.getFace() + " " + card.getSuit());
        System.out.println(strelki);

    }

    @Override
    public void showCards(ArrayList<Card> handList, String name) {
        System.out.println("Карты игрока " + name);
        System.out.println("");
        for (int j = 0; j < handList.size(); j++) {
            System.out.println(handList.get(j).getFace() + " " + handList.get(j).getSuit());
        }
        System.out.println();

    }

    @Override
    public void showTotal(int total, String name) {
        System.out.println("Очки ирока: " + total);
        System.out.println(strelki);

    }

    @Override
    public void showYouLost(String name) {
        System.out.println("Игрок " + name + " проиграл");
        System.out.println(strelki);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showYouWin(String name) {
        System.out.println(strelki);
        System.out.println("Игрок " + name + " набрал 21!");
        System.out.println(strelki);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void weHaveWinnerOnPoints() {
        System.out.println("По очкам победил:");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showAllTotal(ArrayList<Players> playersList) {
        System.out.println(strelki);
        System.out.println("Общий счет:");
        for (int i = 0; i < playersList.size(); i++) {
            System.out.println(playersList.get(i).getName() + "  " + playersList.get(i).getCash());
        }

        System.out.println(strelki);
    }

    @Override
    public boolean questionPlayNext() throws IOException {
        System.out.println("Продолжаем играть?");
        boolean s = getAnswer();
        return s;
    }

}
