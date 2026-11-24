/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author rafae
 */
public abstract class CombatElement {

    private float effect;
    private int uses;

    public CombatElement(float effect, int uses) {
        this.effect = effect;
        this.uses = uses;
    }

    protected float produceEffect(){
        float potencia;
        
        if(uses > 0){
            potencia = effect; 
            uses = uses -1;
        }else{
            potencia = 0.0f; 
        }
        
        return potencia;
    }
    
    @Override
    public String toString() {
        return "[" + effect + ", " + uses + "]";
    }
    
    public boolean discard(){
        return Dice.discardElement(uses);
    }
}
