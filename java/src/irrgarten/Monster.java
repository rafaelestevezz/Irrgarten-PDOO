package irrgarten;

public class Monster {
    private static final int INITIAL_HEALTH = 5;

    private String name;
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;

    public Monster(String name, float intelligence, float strength) {
        this.name = name;
        this.intelligence = intelligence;
        this.strength = strength;
        this.health = INITIAL_HEALTH;
        this.row = -1;
        this.col = -1;
    }

    public boolean dead() {
        return this.health <= 0;
    }

    public float attack() {
        return Dice.intensity(this.strength);
    }

    public boolean defend(float receivedAttack) {
        throw new UnsupportedOperationException(); // Práctica 3
    }

    public void setPos(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public String toString() {
        return this.name + " (I: " + this.intelligence + ", S: " + this.strength + ", H: " + this.health +
                ", Pos: (" + this.row + ", " + this.col + "))";
    }

    public void gotWounded() {
        this.health = this.health - 1;
    }

}
