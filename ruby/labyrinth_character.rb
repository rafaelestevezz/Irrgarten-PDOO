# encoding: UTF-8

module Irrgarten
  class LabyrinthCharacter

    INVALID_POS = -1

    def initialize(name, intelligence, strength, health)
      @name = name
      @intelligence = intelligence
      @strength = strength
      @health = health

      @row = INVALID_POS
      @col = INVALID_POS
    end

    def dead
      @health <= 0
    end

    def row
      @row
    end

    def col
      @col
    end

    def set_pos(row, col)
      @row = row
      @col = col
    end

    def to_s
      "#{@name} (I: #{@intelligence}, S: #{@strength}, " \
        "H: #{@health}, Pos: (#{@row}, #{@col}))"
    end

    def attack
      raise NotImplementedError
    end

    def defend(received_attack)
      raise NotImplementedError
    end

    protected

    attr_reader :name, :intelligence, :strength, :health

    def set_health(health)
      @health = health
    end

    def got_wounded
      @health -= 1
    end

    def copy_from(other)
      @name = other.name
      @intelligence = other.intelligence
      @strength = other.strength
      @health = other.health

      @row = other.row
      @col = other.col
    end

  end
end