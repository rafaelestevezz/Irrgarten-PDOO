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
        log = "";
        boolean dead = currentPlayer.dead();
        
        if(!dead){
            Directions direction = this.actualDirection(preferredDirection);
            
            if(direction != preferredDirection){
                this.logPlayerNoOrders();
            }
            
            Monster monster = labyrinth.putPlayer(direction, currentPlayer);
                    
            if(monster == null){
                this.logNoMonster();
            }else{
                GameCharacter winner = this.combat(monster);
                this.manageReward(winner);
            }
        }else{
            this.manageResurrection();
        }
        
        boolean endGame = this.finished();
        
        if(!endGame){
            this.nextPlayer();
        }
        
        return endGame;
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
        int currentRow = currentPlayer.getRow();
        int currentCol = currentPlayer.getCol();
        Directions[] validMoves;
        
        validMoves = labyrinth.validMoves(currentRow, currentCol);
        Directions output = currentPlayer.move(preferredDirection, validMoves);
        
        return output;
    }

    public GameCharacter combat(Monster monster) {
        int rounds = 0;
        GameCharacter winner = GameCharacter.PLAYER;
        float playerAttack = currentPlayer.attack();
        boolean lose = monster.defend(playerAttack);
        
        while(!lose && rounds < MAX_ROUNDS){
            winner = GameCharacter.MONSTER;
            rounds++;
            
            float monsterAttack = monster.attack();
            lose = currentPlayer.defend(monsterAttack);
            
            if (!lose){
                playerAttack = currentPlayer.attack();
                winner = GameCharacter.PLAYER;
                lose = monster.defend(playerAttack);
            }
        }
        
        this.logRounds(rounds, MAX_ROUNDS);
        
        return winner;
    }

    public void manageReward(GameCharacter winner) {
        if(winner == GameCharacter.PLAYER){
            currentPlayer.receiveReward();
            this.logPlayerWon();
        }else{
            this.logMonsterWon();
        }
    }

    public void manageResurrection() {
        boolean resurrect = Dice.resurrectPlayer();
        
        if(resurrect){
            currentPlayer.resurrect();
            this.logResurrected();
        }else{
            this.logPlayerSkipTurn();
        }
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
        log += "El jugador se ha movido a una casilla vacia o no ha podido moverse.\n";

    }

    public void logRounds(int rounds, int max) {
        log += "Se han producido " + rounds + " de " + max + " rondas de combate.\n";
    }
}
