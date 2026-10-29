# encoding: UTF-8
require_relative 'dice'

module Irrgarten
  class Monster
    INITIAL_HEALTH = 5
    INVALID_POS = -1

    def initialize(name, intelligence, strength)
      @name = name
      @intelligence = intelligence
      @strength = strength
      @health = INITIAL_HEALTH
      @row = INVALID_POS
      @col = INVALID_POS
    end

    def dead
      @health <= 0
    end

    def attack
      Dice.intensity(@strength)
    end

    def defend(received_attack)
      raise NotImplementedError, 'Pendiente de la P3'
    end

    def set_pos(row, col)
      @row = row
      @col = col
    end

    def to_s
      "#{@name} (I: #{@intelligence}, S: #{@strength}, H: #{@health}, Pos: (#{@row}, #{@col}))"
    end

    def got_wounded
      @health -= 1
    end
  end
end
