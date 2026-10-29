package irrgarten;

import java.util.ArrayList;

public class Game {
    private static final int MAX_ROUNDS = 10;

    private int currentPlayerIndex;
    private String log;

    private ArrayList<Player> players;
    private Player currentPlayer;
    private ArrayList<Monster> monsters;
    private Labyrinth labyrinth;

    public Game(int nplayers) {
        this.log = "";
        this.players = new ArrayList<>();
        this.monsters = new ArrayList<>();

        for (int i = 0; i < nplayers; i++) {
            Player player = new Player((char) ('0' + i), Dice.randomIntelligence(), Dice.randomStrength());
            players.add(player);
        }

        this.currentPlayerIndex = Dice.whoStarts(nplayers);
        this.currentPlayer = players.get(currentPlayerIndex);

        this.labyrinth = new Labyrinth(10, 10, 7, 9);

        configureLabyrinth();
        labyrinth.spreadPlayers(players.toArray(new Player[0]));
    }

    public boolean finished() {
        return labyrinth.haveAWinner();
    }

    public boolean nextStep(Directions preferredDirection) {
        throw new UnsupportedOperationException(); // Práctica 3
    }

    public GameState getGameState() {
        return new GameState(labyrinth.toString(), players.toString(), monsters.toString(),
                currentPlayerIndex, finished(), log);
    }

    public void configureLabyrinth() {
        labyrinth.addBlock(Orientation.HORIZONTAL, 2, 2, 6);

        Monster m1 = new Monster("Payaso", Dice.randomIntelligence(), Dice.randomStrength());
        monsters.add(m1);
        labyrinth.addMonster(1, 1, m1);

        Monster m2 = new Monster("Asesino", Dice.randomIntelligence(), Dice.randomStrength());
        monsters.add(m2);
        labyrinth.addMonster(6, 6, m2);
    }

    public void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        currentPlayer = players.get(currentPlayerIndex);
    }

    public Directions actualDirection(Directions preferredDirection) {
        throw new UnsupportedOperationException(); // Práctica 3
    }

    public GameCharacter combat(Monster monster) {
        throw new UnsupportedOperationException(); // Práctica 3
    }

    public void manageReward(GameCharacter winner) {
        throw new UnsupportedOperationException(); // Práctica 3

    }

    public void manageResurrection() {
        throw new UnsupportedOperationException(); // Práctica 3

    }

    public void logPlayerWon() {
        log += "El jugador ha ganado el combate.\n";

    }

    public void logMonsterWon() {
        log += "El monstruo ha ganado el combate.\n";

    }

    public void logResurrected() {
        log += "El jugador ha resucitado.\n";

    }

    public void logPlayerSkipTurn() {
        log += "El jugador ha perdido el turno por estar muerto.\n";

    }

    public void logPlayerNoOrders() {
        log += "El jugador no ha podido seguir las instrucciones.\n";

    }

    public void logNoMonster() {
        log += "El jugador se ha movido a una casilla vacía o no ha podido moverse.\n";

    }

    public void logRounds(int rounds, int max) {
        log += "Se han producido " + rounds + " de " + max + " rondas de combate.\n";
    }
}
