#encoding:utf-8

require_relative 'dice'
require_relative 'weapon'
require_relative 'shield'

module Irrgarten
  class Player
    MAX_WEAPONS = 2
    MAX_SHIELDS = 3
    INITIAL_HEALTH = 10
    HITS2LOSE = 3

    def initialize(number, intelligence, strength)
      @number = number
      @name = "Player ##{@number}"
      @intelligence = intelligence
      @strength = strength
      @health = INITIAL_HEALTH
      @row = -1
      @col = -1
      @consecutive_hits = 0

      @weapons = []
      @shields = []
    end

    def resurrect
      @weapons.clear
      @shields.clear
      @health = INITIAL_HEALTH
      @consecutive_hits = 0
    end

    def row
      @row
    end

    def col
      @col
    end

    def number
      @number
    end

    def set_pos(row, col)
      @row = row
      @col = col
    end

    def dead
      @health <= 0
    end

    def move(direction, valid_moves)
      raise NotImplementedError, 'Pendiente de la P3'
    end

    def attack
      @strength + sum_weapons
    end

    def defend(received_attack)
      manage_hit(received_attack)
    end

    def receive_reward
      raise NotImplementedError, 'Pendiente de la P3'
    end

    def to_s
      "#{@name} (I: #{@intelligence}, S: #{@strength}, H: #{@health}, " \
        "Pos: (#{@row}, #{@col}), Weapons: #{@weapons.size}, Shields: #{@shields.size})"
    end

    private

    def receive_weapon(w)
      raise NotImplementedError, 'Pendiente de la P3'
    end

    def receive_shield(s)
      raise NotImplementedError, 'Pendiente de la P3'
    end

    def new_weapon
      Weapon.new(Dice.weapon_power, Dice.uses_left)
    end

    def new_shield
      Shield.new(Dice.shield_power, Dice.uses_left)
    end

    def sum_weapons
      sum = 0.0
      @weapons.each do |weapon|
        sum += weapon.attack
      end
      sum
    end

    def sum_shields
      sum = 0.0
      @shields.each do |shield|
        sum += shield.protect
      end
      sum
    end

    def defensive_energy
      @intelligence + sum_shields
    end

    def manage_hit(received_attack)
      raise NotImplementedError, 'Pendiente de la P3'
    end

    def reset_hits
      @consecutive_hits = 0
    end

    def got_wounded
      @health -= 1
    end

    def inc_consecutive_hits
      @consecutive_hits += 1
    end
  end
end
