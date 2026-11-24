/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

import java.util.Arrays;
import java.util.ArrayList;
/**
 *
 * @author rafae
 */
public class FuzzyPlayer extends Player{
    
    public FuzzyPlayer(Player other){
        super(other);
    }
    
    @Override
    public Directions move(Directions direction, Directions[] validMoves){
        Directions preference = super.move(direction, validMoves);
        
        ArrayList<Directions> moves = new ArrayList<>(Arrays.asList(validMoves));

        return Dice.nextStep(preference, moves, getIntelligence());
    }
    
    @Override
    public float attack(){
        return Dice.intensity(getStrength()) + sumWeapons();
    }
    
    @Override
    protected float defensiveEnergy(){
        return Dice.intensity(getIntelligence()) + sumShields();
    }
    
    @Override
    public String toString(){
        return "Fuzzy" + super.toString();
    }
}
