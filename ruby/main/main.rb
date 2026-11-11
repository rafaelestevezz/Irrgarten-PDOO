# encoding: utf-8

require_relative '../game'
require_relative '../UI/textUI'
require_relative '../controller/controller'

module Main

  def self.run
    game = Irrgarten::Game.new(2)

    view = UI::TextUI.new

    controller = Control::Controller.new(game, view)

    controller.play
  end

end

Main.run if __FILE__ == $PROGRAM_NAME
