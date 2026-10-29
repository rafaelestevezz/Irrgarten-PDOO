/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author rafae
 */
public class Shield {

    private float protection;
    private int uses;

    public Shield(float protection, int uses) {
        this.protection = protection;
        this.uses = uses;
    }

    public float protect() {
        float damage = 0.0f;

        if (uses > 0) {
            uses--;
            damage = this.protection;
        }
        return damage;
    }

    @Override
    public String toString() {
        return "S[" + protection + ", " + uses + "]";
    }
    
    public boolean discard(){
        return Dice.discardElement(uses);
    }
}
