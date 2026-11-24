package irrgarten;
import java.util.Arrays;
import java.util.ArrayList;

public class Player extends LabyrinthCharacter{
    private static final int MAX_WEAPONS = 2;
    private static final int MAX_SHIELDS = 3;
    private static final int INITIAL_HEALTH = 10;
    private static final int HITS2LOSE = 3;

    private char number;
    private int consecutiveHits = 0;

    private ArrayList<Weapon> weapons;
    private ArrayList<Shield> shields;
    
    private WeaponCardDeck weaponCardDeck;
    private ShieldCardDeck shieldCardDeck;

    public Player(char number, float intelligence, float strength) {
        super("Player " + number, intelligence, strength, INITIAL_HEALTH);
        this.number = number;
        
        this.weapons = new ArrayList<>();
        this.shields = new ArrayList<>();
        
        this.weaponCardDeck = new WeaponCardDeck();
        this.shieldCardDeck = new ShieldCardDeck();
    }
    
    public Player(Player other){
        super(other);
        this.number = other.number;
        this.consecutiveHits = other.consecutiveHits;
        this.weapons = new ArrayList<>(other.weapons);
        this.shields = new ArrayList<>(other.shields);
        
        this.weaponCardDeck = other.weaponCardDeck;
        this.shieldCardDeck = other.shieldCardDeck;
    }

    public void resurrect() {
        weapons.clear();
        shields.clear();
        this.setHealth(INITIAL_HEALTH);
        this.consecutiveHits = 0;
    }

    public char getNumber() {
        return this.number;
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

    @Override
    public float attack() {
        return this.getStrength() + this.sumWeapons();
    }

    @Override
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
        
        setHealth(getHealth() + extraHealth);
    }

    @Override
    public String toString() {
        return super.toString() +
           ", Weapons: " + this.weapons.size() +
           ", Shields: " + this.shields.size();
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
        return weaponCardDeck.nextCard();
    }

    private Shield newShield() {
        return shieldCardDeck.nextCard();
    }

    protected float sumWeapons() {
        float sum = 0.0f;

        for (int i = 0; i < weapons.size(); i++) {
            sum = sum + weapons.get(i).attack();
        }
        return sum;
    }

    protected float sumShields() {
        float sum = 0.0f;

        for (int i = 0; i < shields.size(); i++) {
            sum = sum + shields.get(i).protect();
        }
        return sum;
    }

    protected float defensiveEnergy() {
        return this.getIntelligence() + this.sumShields();
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

    private void incConsecutiveHits() {
        this.consecutiveHits = this.consecutiveHits + 1;
    }

}
