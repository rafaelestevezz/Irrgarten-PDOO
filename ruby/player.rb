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
      size = valid_moves.size
      contained = valid_moves.include?(direction)

      if size > 0 && !contained
        first_element = valid_moves.first
        return first_element
      end

      direction
    end


    def attack
      @strength + sum_weapons
    end

    def defend(received_attack)
      manage_hit(received_attack)
    end

    def receive_reward
      w_reward = Dice.weapons_reward
      s_reward = Dice.shields_reward

      w_reward.times do
        wnew = new_weapon
        receive_weapon(wnew)
      end

      s_reward.times do
        snew = new_shield
        receive_shield(snew)
      end

      extra_health = Dice.health_reward
      @health += extra_health
    end

    def to_s
      "#{@name} (I: #{@intelligence}, S: #{@strength}, H: #{@health}, " \
        "Pos: (#{@row}, #{@col}), Weapons: #{@weapons.size}, Shields: #{@shields.size})"
    end

    private

    def receive_weapon(w)
      @weapons.delete_if do |wi|
        wi.discard
      end

      size = @weapons.size

      if size < MAX_WEAPONS
        @weapons << w
      end
    end

    def receive_shield(s)
      @shields.delete_if do |si|
        si.discard
      end

      size = @shields.size

      if size < MAX_SHIELDS
        @shields << s
      end
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
      defense = defensive_energy

      if defense < received_attack
        got_wounded
        inc_consecutive_hits
      else
        reset_hits
      end

      if @consecutive_hits == HITS2LOSE || dead
        reset_hits
        lose = true
      else
        lose = false
      end

      lose
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
