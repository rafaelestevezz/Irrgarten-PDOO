/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author rafae
 */
public class TestP1 {

    public static void main(String[] args) {

        System.out.println("ENUMS:");

        Directions direction1 = Directions.LEFT;
        Directions direction2 = Directions.RIGHT;
        Directions direction3 = Directions.UP;
        Directions direction4 = Directions.DOWN;

        System.out.println(direction1);
        System.out.println(direction2);
        System.out.println(direction3);
        System.out.println(direction4);

        Orientation orientation1 = Orientation.VERTICAL;
        Orientation orientation2 = Orientation.HORIZONTAL;

        System.out.println(orientation1);
        System.out.println(orientation2);

        GameCharacter character1 = GameCharacter.PLAYER;
        GameCharacter character2 = GameCharacter.MONSTER;

        System.out.println(character1);
        System.out.println(character2);

        System.out.println("\nWEAPONS: ");

        Weapon weapon1 = new Weapon(2.0f, 3);
        Weapon weapon2 = new Weapon(3.0f, 1);

        System.out.println("Weapon 1: " + weapon1);
        System.out.println("Weapon 2: " + weapon2);

        System.out.println("Attack weapon1: " + weapon1.attack());
        System.out.println("After attack: " + weapon1);

        System.out.println("Attack weapon2: " + weapon2.attack());
        System.out.println("Attack weapon2 no uses: " + weapon2.attack());

        System.out.println("Discard: " + weapon1.discard());
        System.out.println("Discard: " + weapon2.discard());

        System.out.println("\nSHIELD: ");

        Shield shield1 = new Shield(2.0f, 3);
        Shield shield2 = new Shield(1.5f, 1);

        System.out.println("Shield 1: " + shield1);
        System.out.println("Shield 2: " + shield2);

        System.out.println("Protect shield1: " + shield1.protect());
        System.out.println("After protect: " + shield1);

        System.out.println("Protect shield2: " + shield2.protect());
        System.out.println("Protect shield2 no uses: " + shield2.protect());

        System.out.println("Discard: " + shield1.discard());
        System.out.println("Discard: " + shield2.discard());

        System.out.println("\nGAMESTATE: ");

        GameState state1 = new GameState("Labyrinth 1", "Players 1", "Monsters 1", 0, false, "Game starts");

        GameState state2 = new GameState("Labyrinth 2", "Players 2", "Monsters 2", 1, true, "Game finished");

        System.out.println("State 1:");
        System.out.println("Labyrinth: " + state1.getLabyrinth());
        System.out.println("Players: " + state1.getPlayers());
        System.out.println("Monsters: " + state1.getMonsters());
        System.out.println("Current player: " + state1.getCurrentPlayer());
        System.out.println("Winner: " + state1.getWinner());
        System.out.println("Log: " + state1.getLog());

        System.out.println("\nState 2:");
        System.out.println("Labyrinth: " + state2.getLabyrinth());
        System.out.println("Players: " + state2.getPlayers());
        System.out.println("Monsters: " + state2.getMonsters());
        System.out.println("Current player: " + state2.getCurrentPlayer());
        System.out.println("Winner: " + state2.getWinner());
        System.out.println("Log: " + state2.getLog());

        System.out.println("\nDICE: 100 TESTS");

        final int NUM_TESTS = 100;

        boolean randomPosCorrect = true;
        boolean whoStartsCorrect = true;
        boolean intelligenceCorrect = true;
        boolean strengthCorrect = true;
        boolean weaponsRewardCorrect = true;
        boolean shieldsRewardCorrect = true;
        boolean healthRewardCorrect = true;
        boolean weaponPowerCorrect = true;
        boolean shieldPowerCorrect = true;
        boolean usesLeftCorrect = true;
        boolean intensityCorrect = true;

        int resurrections = 0;

        // Data used to test discardElement
        int[] discardTests = new int[6];
        int[] discarded = new int[6];

        for (int i = 0; i < NUM_TESTS; i++) {

            // randomPos: [0, 10[
            int pos = Dice.randomPos(10);

            if (pos < 0 || pos >= 10) {
                randomPosCorrect = false;
            }

            // whoStarts: assuming 4 players -> [0, 4[
            int player = Dice.whoStarts(4);

            if (player < 0 || player >= 4) {
                whoStartsCorrect = false;
            }

            // randomIntelligence: [0, 10[
            float intelligence = Dice.randomIntelligence();

            if (intelligence < 0.0f || intelligence >= 10.0f) {
                intelligenceCorrect = false;
            }

            // randomStrength: [0, 10[
            float strength = Dice.randomStrength();

            if (strength < 0.0f || strength >= 10.0f) {
                strengthCorrect = false;
            }

            // resurrectPlayer: approximately 30%
            if (Dice.resurrectPlayer()) {
                resurrections++;
            }

            // weaponsReward: [0, 2]
            int weapons = Dice.weaponsReward();

            if (weapons < 0 || weapons > 2) {
                weaponsRewardCorrect = false;
            }

            // shieldsReward: [0, 3]
            int shields = Dice.shieldsReward();

            if (shields < 0 || shields > 3) {
                shieldsRewardCorrect = false;
            }

            // healthReward: [0, 5]
            int health = Dice.healthReward();

            if (health < 0 || health > 5) {
                healthRewardCorrect = false;
            }

            // weaponPower: [0, 3[
            float weaponPower = Dice.weaponPower();

            if (weaponPower < 0.0f || weaponPower >= 3.0f) {
                weaponPowerCorrect = false;
            }

            // shieldPower: [0, 2[
            float shieldPower = Dice.shieldPower();

            if (shieldPower < 0.0f || shieldPower >= 2.0f) {
                shieldPowerCorrect = false;
            }

            // usesLeft: [0, 5]
            int uses = Dice.usesLeft();

            if (uses < 0 || uses > 5) {
                usesLeftCorrect = false;
            }

            // intensity: [0, competence[
            float competence = 8.0f;
            float intensity = Dice.intensity(competence);

            if (intensity < 0.0f || intensity >= competence) {
                intensityCorrect = false;
            }

            // discardElement
            // Tests values 0, 1, 2, 3, 4 and 5 repeatedly
            int usesToTest = i % 6;

            discardTests[usesToTest]++;

            if (Dice.discardElement(usesToTest)) {
                discarded[usesToTest]++;
            }
        }

        System.out.println("\nRange checks: ");

        System.out.println("randomPos correct: " + randomPosCorrect);
        System.out.println("whoStarts correct: " + whoStartsCorrect);
        System.out.println("randomIntelligence correct: " + intelligenceCorrect);
        System.out.println("randomStrength correct: " + strengthCorrect);
        System.out.println("weaponsReward correct: " + weaponsRewardCorrect);
        System.out.println("shieldsReward correct: " + shieldsRewardCorrect);
        System.out.println("healthReward correct: " + healthRewardCorrect);
        System.out.println("weaponPower correct: " + weaponPowerCorrect);
        System.out.println("shieldPower correct: " + shieldPowerCorrect);
        System.out.println("usesLeft correct: " + usesLeftCorrect);
        System.out.println("intensity correct: " + intensityCorrect);

        System.out.println("\nResurrection: ");

        System.out.println("Resurrections: " + resurrections + " out of " + NUM_TESTS);

        System.out.println("Approximate percentage: " + resurrections + "%");

        System.out.println("\ndiscardElement: ");

        for (int uses = 0; uses <= 5; uses++) {

            float percentage = (float) discarded[uses] / discardTests[uses] * 100.0f;

            System.out.println("Uses left: " + uses + " -> " + percentage + "% discarded");
        }
    }

}
