# encoding: UTF-8

require_relative 'dice'

module Irrgarten
  class CombatElement

    def initialize(effect, uses)
      @effect = effect
      @uses = uses
    end

    def discard
      Dice.discard_element(@uses)
    end

    def to_s
      "[#{@effect}, #{@uses}]"
    end

    protected

    def produce_effect
      result = 0.0

      if @uses > 0
        result = @effect
        @uses -= 1
      end

      result
    end

  end
end