#encoding:utf-8

require_relative 'directions'
require_relative 'orientation'
require_relative 'game_character'
require_relative 'weapon'
require_relative 'shield'
require_relative 'game_state'
require_relative 'dice'

module Irrgarten
  class TestP1

    def self.main
      puts "ENUMS:"
      puts Directions::LEFT
      puts Directions::RIGHT
      puts Directions::UP
      puts Directions::DOWN
      puts Orientation::VERTICAL
      puts Orientation::HORIZONTAL
      puts GameCharacter::PLAYER
      puts GameCharacter::MONSTER

      puts "\nWEAPONS:"

      weapon1 = Weapon.new(2.0, 3)
      weapon2 = Weapon.new(3.0, 1)

      puts weapon1
      puts weapon2
      puts "Attack: #{weapon1.attack}"
      puts "After attack: #{weapon1}"
      puts "Discard: #{weapon1.discard}"

      weapon2.attack
      puts "Attack no uses: #{weapon2.attack}"

      puts "\nSHIELDS:"

      shield1 = Shield.new(2.0, 3)
      shield2 = Shield.new(1.5, 1)

      puts shield1
      puts shield2
      puts "Protect: #{shield1.protect}"
      puts "After protect: #{shield1}"
      puts "Discard: #{shield1.discard}"

      shield2.protect
      puts "Protect no uses: #{shield2.protect}"

      puts "\nGAMESTATE:"

      state1 = GameState.new(
        "Labyrinth 1",
        "Players 1",
        "Monsters 1",
        0,
        false,
        "Game starts"
      )

      state2 = GameState.new(
        "Labyrinth 2",
        "Players 2",
        "Monsters 2",
        1,
        true,
        "Game finished"
      )

      puts state1.labyrinth
      puts state1.players
      puts state1.monsters
      puts state1.current_player
      puts state1.winner
      puts state1.log

      puts state2.labyrinth
      puts state2.winner

      puts "\nDICE: 100 TESTS"

      resurrections = 0
      discarded = Array.new(6, 0)

      100.times do |i|
        Dice.random_pos(10)
        Dice.who_starts(4)
        Dice.random_intelligence
        Dice.random_strength

        if Dice.resurrect_player
          resurrections += 1
        end

        Dice.weapons_reward
        Dice.shields_reward
        Dice.health_reward
        Dice.weapon_power
        Dice.shield_power
        Dice.uses_left
        Dice.intensity(8.0)

        uses = i % 6

        if Dice.discard_element(uses)
          discarded[uses] += 1
        end
      end

      puts "Resurrections: #{resurrections}/100"

      puts "\ndiscardElement:"
      6.times do |uses|
        puts "Uses #{uses}: #{discarded[uses]}"
      end
    end

  end
end

Irrgarten::TestP1.main