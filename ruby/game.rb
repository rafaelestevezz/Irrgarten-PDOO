#encoding:utf-8

require_relative 'dice'
require_relative 'directions'
require_relative 'game_character'
require_relative 'game_state'
require_relative 'labyrinth'
require_relative 'monster'
require_relative 'orientation'
require_relative 'player'

module Irrgarten
  class Game
    MAX_ROUNDS = 10

    def initialize(nplayers)
      @log = ''
      @players = []
      @monsters = []

      nplayers.times do |i|
        player = Player.new(i.to_s, Dice.random_intelligence, Dice.random_strength)
        @players << player
      end

      @current_player_index = Dice.who_starts(nplayers)
      @current_player = @players[@current_player_index]

      @labyrinth = Labyrinth.new(10, 10, 7, 9)

      configure_labyrinth
      @labyrinth.spread_players(@players)
    end

    def finished
      @labyrinth.have_a_winner
    end

    def next_step(preferred_direction)
      raise NotImplementedError, 'Pendiente de la P3'
    end

    def get_game_state
      GameState.new(
        @labyrinth.to_s,
        @players.map(&:to_s).join("\n"),
        @monsters.map(&:to_s).join("\n"),
        @current_player_index,
        finished,
        @log
      )
    end

    def configure_labyrinth
      @labyrinth.add_block(Orientation::HORIZONTAL, 2, 2, 6)

      m1 = Monster.new('Payaso', Dice.random_intelligence, Dice.random_strength)
      @monsters << m1
      @labyrinth.add_monster(1, 1, m1)

      m2 = Monster.new('Asesino', Dice.random_intelligence, Dice.random_strength)
      @monsters << m2
      @labyrinth.add_monster(6, 6, m2)
    end

    def next_player
      @current_player_index = (@current_player_index + 1) % @players.size
      @current_player = @players[@current_player_index]
    end

    def actual_direction(preferred_direction)
      raise NotImplementedError, 'Pendiente de la P3'
    end

    def combat(monster)
      raise NotImplementedError, 'Pendiente de la P3'
    end

    def manage_reward(winner)
      raise NotImplementedError, 'Pendiente de la P3'
    end

    def manage_resurrection
      raise NotImplementedError, 'Pendiente de la P3'
    end

    def log_player_won
      @log += "El jugador ha ganado el combate.\n"
    end

    def log_monster_won
      @log += "El monstruo ha ganado el combate.\n"
    end

    def log_resurrected
      @log += "El jugador ha resucitado.\n"
    end

    def log_player_skip_turn
      @log += "El jugador ha perdido el turno por estar muerto.\n"
    end

    def log_player_no_orders
      @log += "El jugador no ha podido seguir las instrucciones.\n"
    end

    def log_no_monster
      @log += "El jugador se ha movido a una casilla vacía o no ha podido moverse.\n"
    end

    def log_rounds(rounds, max)
      @log += "Se han producido #{rounds} de #{max} rondas de combate.\n"
    end
  end
end