package irrgarten;

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
        throw new UnsupportedOperationException(); // Práctica 3
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
        throw new UnsupportedOperationException(); // Práctica 3
    }

    public void addBlock(Orientation orientation, int startRow, int startCol, int length) {
        throw new UnsupportedOperationException(); // Práctica 3
    }

    public Directions[] validMoves(int row, int col) {
        throw new UnsupportedOperationException(); // Práctica 3
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
        throw new UnsupportedOperationException(); // Práctica 3
    }
}
