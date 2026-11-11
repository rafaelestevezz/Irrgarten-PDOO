/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten.UI;

/**
 *
 * @author rafae
 */

import irrgarten.Directions;
import irrgarten.GameState;
import java.util.Scanner;


public class TextUI {
    
    private static Scanner in = new Scanner(System.in);
    
    private char readChar() {
        String s = in.nextLine();     
        return s.charAt(0);
    }
    

    public Directions nextMove() {
        System.out.print("Where? ");
        
        Directions direction = Directions.DOWN;
        boolean gotInput = false;
        
        while (!gotInput) {
            char c = readChar();
            switch(c) {
                case 'w':
                    System.out.print(" UP\n");
                    direction = Directions.UP;
                    gotInput = true;
                    break;
                case 's':
                    System.out.print(" DOWN\n");
                    direction = Directions.DOWN;
                    gotInput = true;
                    break;
                case 'd':
                    System.out.print("RIGHT\n");
                    direction = Directions.RIGHT;
                    gotInput = true;
                    break;
                case 'a':
                    System.out.print(" LEFT\n");
                    direction = Directions.LEFT;
                    gotInput = true;    
                    break;
            }
        }    
        return direction;
    }
    
    public void showGame(GameState gameState) {
        System.out.println("\nIRRGARTEN");

        System.out.println("\nLABERINTO:");
        System.out.println(gameState.getLabyrinth());

        System.out.println("\nJUGADORES:");
        System.out.println(gameState.getPlayers());

        System.out.println("\nMONSTRUOS:");
        System.out.println(gameState.getMonsters());

        System.out.println("\nJUGADOR ACTUAL:");
        System.out.println(gameState.getCurrentPlayer());

        System.out.println("\nHAY GANADOR?");
        System.out.println(gameState.getWinner());

        System.out.println("\nREGISTRO:");
        System.out.println(gameState.getLog());

        System.out.println("\n");
    }
}

