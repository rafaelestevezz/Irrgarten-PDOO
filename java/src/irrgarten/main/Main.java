/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten.main;

/**
 *
 * @author rafae
 */

import irrgarten.Game;
import irrgarten.UI.TextUI;
import irrgarten.controller.Controller;

public class Main {

    public static void main(String[] args) {
        Game game = new Game(2);
        TextUI view = new TextUI();
        Controller controller = new Controller(game, view);

        controller.play();
    }
}

