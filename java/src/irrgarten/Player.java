package irrgarten;
import java.util.Arrays;
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
        int size = validMoves.length;
        boolean contained = Arrays.asList(validMoves).contains(direction);
        Directions firstElement;
        
        if((size > 0) && (!contained)){
            firstElement = validMoves[0];
            return firstElement;
        }
        
        return direction;
    }

    public float attack() {
        return this.strength + this.sumWeapons();
    }

    public boolean defend(float receivedAttack) {
        return this.manageHit(receivedAttack);
    }

    public void receiveReward() {
        int wReward = Dice.weaponsReward();
        int sReward = Dice.shieldsReward();
        int extraHealth = Dice.healthReward();
        
        for(int i = 0; i < wReward; i++){
            Weapon wnew = newWeapon();
            this.receiveWeapon(wnew);
        }
        
        for(int i = 0; i < sReward; i++){
            Shield snew = newShield();
            this.receiveShield(snew);
        }
        
        health = health + extraHealth;
    }

    public String toString() {
        return this.name + " (I: " + this.intelligence + ", S: " + this.strength + ", H: " + this.health +
                ", Pos: (" + this.row + ", " + this.col + "), Weapons: " + this.weapons.size() +
                ", Shields: " + this.shields.size() + ")";
    }

    private void receiveWeapon(Weapon w) {
        boolean discard;
        
        for(int i = weapons.size() - 1; i >= 0; i--) {
            Weapon wi = weapons.get(i);
            discard = wi.discard();
                    
            if(discard){
                weapons.remove(i);
            }
        }
        
        int size = weapons.size();
        
        if(size < MAX_WEAPONS){
            weapons.add(w);
        }
    }

    private void receiveShield(Shield s) {
        boolean discard;
        
        for(int i = shields.size() - 1; i >= 0; i--) {
            Shield si = shields.get(i);
            discard = si.discard();
                    
            if(discard){
                shields.remove(i);
            }
        }
        
        int size = shields.size();
        
        if(size < MAX_SHIELDS){
            shields.add(s);
        }
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
        float defense = defensiveEnergy();
        boolean lose;
        
        if(defense < receivedAttack){
            this.gotWounded();
            this.incConsecutiveHits();
        }else{
            this.resetHits();
        }
        
        if((consecutiveHits == HITS2LOSE)||(dead())){
            this.resetHits();
            lose = true;
        }else{
            lose = false;
        }
        
        return lose;
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
