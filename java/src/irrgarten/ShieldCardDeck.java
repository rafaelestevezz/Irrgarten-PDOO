/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author rafae
 */
public class ShieldCardDeck extends CardDeck<Shield>{
    @Override
    protected void addCards() {
        addCard(new Shield(4.0f, 2));
        addCard(new Shield(3.0f, 3));
        addCard(new Shield(1.0f, 6));
        addCard(new Shield(7.0f, 1));
        addCard(new Shield(0.0f, 15));
        addCard(new Shield(2.5f, 2));
        addCard(new Shield(2.0f, 4));
    }
}
