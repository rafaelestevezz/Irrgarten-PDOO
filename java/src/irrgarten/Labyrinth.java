package irrgarten;
import java.util.ArrayList;

public class Labyrinth {
    private static final char BLOCK_CHAR = 'X';
    private static final char EMPTY_CHAR = '-';
    private static final char MONSTER_CHAR = 'M';
    private static final char COMBAT_CHAR = 'C';
    private static final char EXIT_CHAR = 'E';
    private static final int ROW = 0;
    private static final int COL = 1;

    private int nRows;
    private int nCols;
    private int exitRow;
    private int exitCol;

    private Monster[][] monsters;
    private Player[][] players;
    private char[][] labyrinth;

    public Labyrinth(int nRows, int nCols, int exitRow, int exitCol) {
        this.nRows = nRows;
        this.nCols = nCols;
        this.exitRow = exitRow;
        this.exitCol = exitCol;
        this.monsters = new Monster[nRows][nCols];
        this.players = new Player[nRows][nCols];
        this.labyrinth = new char[nRows][nCols];

        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                labyrinth[i][j] = EMPTY_CHAR;
            }
        }
        labyrinth[exitRow][exitCol] = EXIT_CHAR;
    }

    public void spreadPlayers(Player[] players) {
        int[] pos;
        
        for(int i = 0; i < players.length; i++){
            Player p = players[i];
            pos = (randomEmptyPos());
            putPlayer2D(-1,-1,pos[ROW],pos[COL],p);
        }
    }

    public boolean haveAWinner() {
        return players[exitRow][exitCol] != null;
    }

    public String toString() {
        String resultado = "";

        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                resultado += labyrinth[i][j];
            }
            resultado += "\n";
        }

        return resultado;
    }

    public void addMonster(int row, int col, Monster monster) {
        if (posOK(row, col) && emptyPos(row, col)) {
            labyrinth[row][col] = MONSTER_CHAR;
            monsters[row][col] = monster;
            monster.setPos(row, col);
        }
    }

    public Monster putPlayer(Directions direction, Player player) {
        int oldRow;
        int oldCol;
        int[] newPos;
        Monster monster;

        oldRow = player.getRow();
        oldCol = player.getCol();
        
        newPos = dir2Pos(oldRow,oldCol,direction);
        monster = putPlayer2D(oldRow,oldCol,newPos[ROW],newPos[COL],player);
        
        return monster;
    }

    public void addBlock(Orientation orientation, int startRow, int startCol, int length) {
        int incRow;
        int incCol;
        
        if(orientation == Orientation.VERTICAL){
            incRow = 1;
            incCol = 0;
        }else{
            incRow = 0;
            incCol = 1;
        }
        
        int row = startRow;
        int col = startCol;
        
        while((posOK(row, col)) && (emptyPos(row, col)) && (length > 0)){
            labyrinth[row][col] = BLOCK_CHAR;            
            length = length - 1;
            row = row + incRow;
            col = col + incCol;
        }
    }

    public Directions[] validMoves(int row, int col) {
        ArrayList<Directions> output = new ArrayList<>(); 
                
        if(canStepOn(row+1, col)){
            output.add(Directions.DOWN);
        }
        
        if(canStepOn(row-1, col)){
            output.add(Directions.UP);
        }
        
        if(canStepOn(row, col+1)){
            output.add(Directions.RIGHT);
        }
        
        if(canStepOn(row, col-1)){
            output.add(Directions.LEFT);
        }
        
        return output.toArray(new Directions[0]);
    }

    private boolean posOK(int row, int col) {
        return row >= 0 && row < nRows && col >= 0 && col < nCols;
    }

    private boolean emptyPos(int row, int col) {
        return labyrinth[row][col] == EMPTY_CHAR;
    }

    private boolean monsterPos(int row, int col) {
        return labyrinth[row][col] == MONSTER_CHAR;
    }

    private boolean exitPos(int row, int col) {
        return labyrinth[row][col] == EXIT_CHAR;
    }

    private boolean combatPos(int row, int col) {
        return labyrinth[row][col] == COMBAT_CHAR;

    }

    private boolean canStepOn(int row, int col) {
        if (posOK(row, col)) {
            return emptyPos(row, col) || monsterPos(row, col) || exitPos(row, col);
        }
        return false;
    }

    private void updateOldPos(int row, int col) {
        if (posOK(row, col)) {
            if (labyrinth[row][col] == COMBAT_CHAR) {
                labyrinth[row][col] = MONSTER_CHAR;
            } else {
                labyrinth[row][col] = EMPTY_CHAR;
            }
        }
    }

    private int[] dir2Pos(int row, int col, Directions direction) {
        switch (direction) {
            case UP:
                row--;
                break;

            case DOWN:
                row++;
                break;

            case LEFT:
                col--;
                break;

            case RIGHT:
                col++;
                break;
        }

        return new int[] { row, col };
    }

    private int[] randomEmptyPos() {
        int row;
        int col;

        do {
            row = Dice.randomPos(nRows);
            col = Dice.randomPos(nCols);
        } while (!emptyPos(row, col));

        return new int[] { row, col };
    }

    private Monster putPlayer2D(int oldRow, int oldCol, int row, int col, Player player) {
        Monster output = null;
        boolean monsterPos;
        char number;
        
        if(canStepOn(row, col)){
            
            if(posOK(oldRow, oldCol)){
                Player p = players[oldRow][oldCol];
            
                if(p == player){
                    updateOldPos(oldRow, oldCol);
                    players[oldRow][oldCol] = null;   
                }
            }
            
            monsterPos = monsterPos(row, col);
            
            if(monsterPos){
                labyrinth[row][col] = COMBAT_CHAR;
                output = monsters[row][col];
            }else{
                number = player.getNumber();
                labyrinth[row][col] = number;
            }
            
            players[row][col] = player;
            
            player.setPos(row,col);
        }
        
        return output;
    }
}
