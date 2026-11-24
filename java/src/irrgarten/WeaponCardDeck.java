/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author rafae
 */
public class WeaponCardDeck extends CardDeck<Weapon>{
    @Override
    protected void addCards() {
        addCard(new Weapon(4.0f, 2));
        addCard(new Weapon(3.0f, 3));
        addCard(new Weapon(1.0f, 6));
        addCard(new Weapon(7.0f, 1));
        addCard(new Weapon(0.0f, 15));
        addCard(new Weapon(2.5f, 2));
        addCard(new Weapon(2.0f, 4));
    }
}
