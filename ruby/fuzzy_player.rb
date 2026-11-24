# encoding: UTF-8

require_relative 'player'
require_relative 'dice'

module Irrgarten
  class FuzzyPlayer < Player

    def initialize(other)
      copy_from(other)
    end

    def move(direction, valid_moves)
      preference = super(direction, valid_moves)

      Dice.next_step(preference, valid_moves, intelligence)
    end

    def attack
      Dice.intensity(strength) + sum_weapons
    end

    def to_s
      "Fuzzy" + super
    end

    protected

    def defensive_energy
      Dice.intensity(intelligence) + sum_shields
    end

  end
end