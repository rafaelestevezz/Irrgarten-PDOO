package irrgarten;

import java.util.ArrayList;

public class Player {
    private static final int MAX_WEAPONS = 2;
    private static final int MAX_SHIELDS = 3;
    private static final int INITIAL_HEALTH = 10;
    private static final int HITS2LOSE = 3;

    private String name;
    private char number;
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;
    private int consecutiveHits = 0;

    private ArrayList<Weapon> weapons;
    private ArrayList<Shield> shields;

    public Player(char number, float intelligence, float strength) {
        this.number = number;
        this.name = "Player #" + this.number;
        this.intelligence = intelligence;
        this.strength = strength;
        this.health = INITIAL_HEALTH;
        this.row = -1;
        this.col = -1;
        this.weapons = new ArrayList<>();
        this.shields = new ArrayList<>();
    }

    public void resurrect() {
        weapons.clear();
        shields.clear();
        this.health = INITIAL_HEALTH;
        this.consecutiveHits = 0;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public char getNumber() {
        return this.number;
    }

    public void setPos(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public boolean dead() {
        return this.health <= 0;
    }

    public Directions move(Directions direction, Directions[] validMoves) {
        throw new UnsupportedOperationException(); // Práctica 3
    }

    public float attack() {
        return this.strength + this.sumWeapons();
    }

    public boolean defend(float receivedAttack) {
        return this.manageHit(receivedAttack);
    }

    public void receiveReward() {
        throw new UnsupportedOperationException(); // Práctica 3
    }

    public String toString() {
        return this.name + " (I: " + this.intelligence + ", S: " + this.strength + ", H: " + this.health +
                ", Pos: (" + this.row + ", " + this.col + "), Weapons: " + this.weapons.size() +
                ", Shields: " + this.shields.size() + ")";
    }

    private void receiveWeapon(Weapon w) {
        throw new UnsupportedOperationException(); // Práctica 3

    }

    private void receiveShield(Shield s) {
        throw new UnsupportedOperationException(); // Práctica 3

    }

    private Weapon newWeapon() {
        return new Weapon(Dice.weaponPower(), Dice.usesLeft());
    }

    private Shield newShield() {
        return new Shield(Dice.shieldPower(), Dice.usesLeft());
    }

    private float sumWeapons() {
        float sum = 0.0f;

        for (int i = 0; i < weapons.size(); i++) {
            sum = sum + weapons.get(i).attack();
        }
        return sum;
    }

    private float sumShields() {
        float sum = 0.0f;

        for (int i = 0; i < shields.size(); i++) {
            sum = sum + shields.get(i).protect();
        }
        return sum;
    }

    private float defensiveEnergy() {
        return this.intelligence + this.sumShields();
    }

    private boolean manageHit(float receivedAttack) {
        throw new UnsupportedOperationException(); // Práctica 3
    }

    private void resetHits() {
        this.consecutiveHits = 0;
    }

    private void gotWounded() {
        this.health = this.health - 1;
    }

    private void incConsecutiveHits() {
        this.consecutiveHits = this.consecutiveHits + 1;
    }

}
