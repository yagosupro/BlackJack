import java.io.IOException;

public class Controller {

    ModelLayer modelLayer = new DataLayer();
    View view = new ConsoleView();

    void run() throws IOException {
        startGame();
        game();
        endGame();


    }

    private void game() throws IOException {
        do {
            roundOne();
            if (modelLayer.getWinner().isEmpty()) {
                roundMidle();

            }
            showAllTotal();
            inicializeNewRound();
        } while (view.questionPlayNext());

    }

    private void inicializeNewRound() {
        modelLayer.inicializeNewRound();
    }


    private void showAllTotal() {
        view.showAllTotal(modelLayer.getPlayersList());
    }


    private void checkWinner() {

        double moneyForEachWinner = modelLayer.getPlayersList().size();

        //если мы имеем победителя по стандартным правилам ==21
        if (modelLayer.checkWinner()) {
            view.weHaveWinner();
            moneyForEachWinner = (moneyForEachWinner) / modelLayer.getWinnerListSize();

            view.showWinner(modelLayer.getWinner(), moneyForEachWinner);
            modelLayer.addWinnings();


        } else {
            view.weHaveWinnerOnPoints();

            modelLayer.checkWinnerOnPoints();
            moneyForEachWinner = (moneyForEachWinner) / modelLayer.getWinnerListSize();
            view.showWinner(modelLayer.getWinner(), moneyForEachWinner);
            modelLayer.addWinnings();


        }

    }


    void roundMidle() throws IOException {
        if (modelLayer.getDeckSize() < 18) {
            modelLayer.createNewDeck();
        }
        getNextCard();
        checkWinner();

    }


    boolean answer(int i) throws IOException {
        if (modelLayer.getPlayersList().get(i).getStrategyAi() == 0) {
            return view.wePlayNextQuestion(modelLayer.getPlayerNameById(i));
        } else
            return modelLayer.getPlayersList().get(i).getAnswer();

    }


    void getNextCard() throws IOException {
        for (int i = 0; i < modelLayer.getPlayersList().size(); i++) {

            boolean loop = true;
            boolean s;


            s = answer(i);
            if (s) {
                while (loop) {

                    view.showGetCard(modelLayer.getCard(i), modelLayer.getPlayersList().get(i).getName());
                    view.showCards(modelLayer.getPlayersList().get(i).getHandList(), modelLayer.getPlayersList().get(i).getName());
                    view.showTotal(modelLayer.getTotal(i), modelLayer.getPlayersList().get(i).getName());

                    if (modelLayer.getTotal(i) == 21) {
                        view.showYouWin(modelLayer.getPlayersList().get(i).getName());
                        modelLayer.setWinner(i);

                        break;

                    }

                    if (modelLayer.getTotal(i) > 21) {
                        view.showYouLost(modelLayer.getPlayersList().get(i).getName());
                        modelLayer.setOutPlayer(i);

                        break;
                    }
                    loop = answer(i);
                }
            }
            if (!(s)) {
                view.showCards(modelLayer.getPlayersList().get(i).getHandList(), modelLayer.getPlayersList().get(i).getName());
                view.showTotal(modelLayer.getTotal(i), modelLayer.getPlayersList().get(i).getName());
            }

        }
    }

    void roundOne() {
        modelLayer.inicializePlayer();
        modelLayer.wager();
        modelLayer.getCardRoundOne();
        view.showCardsRoundOne(modelLayer.getPlayersList());
        checkWinnerRoundOne();

    }

    void checkWinnerRoundOne() {
        if (modelLayer.checkWinner()) {
            view.weHaveWinner();
            double moneyForEachWinner = modelLayer.getPlayersList().size();
            moneyForEachWinner = (moneyForEachWinner) / modelLayer.getWinnerListSize();

            view.showWinner(modelLayer.getWinnerRoundOne(), moneyForEachWinner);
            modelLayer.addWinnings();
        }

    }

    void startGame() throws IOException {
        modelLayer.clearListPlayers();
        view.showStartGame();

        if (choiceNumberPlayers() == 2) {
            inicializePlayers(view.getNamePlayer(), view.getNamePlayer());
        } else
            inicializePlayers(view.getNamePlayer());
        modelLayer.createNewDeck();


    }

    private void inicializePlayers(String name) {
        modelLayer.createPlayer(name);
        modelLayer.createBotAlco("botAlco");
        modelLayer.createBot("bot2");
        modelLayer.createBot("bot3");
        modelLayer.createDealer();
    }

    void inicializePlayers(String name, String name2) {
        modelLayer.createPlayer(name);
        modelLayer.createPlayer(name2);
        modelLayer.createBot("bot1");
        modelLayer.createBot("bot2");
        modelLayer.createDealer();
    }

    int choiceNumberPlayers() throws IOException {
        return view.showChoiceNumberPlayers();
    }

    private void endGame() {
        System.out.println("Конец игры");
    }


}
