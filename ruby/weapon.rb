#encoding:utf-8

require_relative 'dice'

module Irrgarten

  class Weapon

    def initialize(power, uses)
      @power = power
      @uses = uses
    end

    def attack
      damage = 0.0

        if @uses > 0
          @uses -= 1
          damage = @power
        end

      damage
    end

    def discard
      Dice.discard_element(@uses)
    end

    def to_s
      "W[#{@power}, #{@uses}]"
    end

  end

end