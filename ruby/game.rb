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
      @log = ''
      is_dead = @current_player.dead

      unless is_dead
        direction = actual_direction(preferred_direction)

        if direction != preferred_direction
          log_player_no_orders
        end

        monster = @labyrinth.put_player(direction, @current_player)

        if monster.nil?
          log_no_monster
        else
          winner = combat(monster)
          manage_reward(winner)
        end
      else
        manage_resurrection
      end

      end_game = finished

      unless end_game
        next_player
      end

      end_game
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
      current_row = @current_player.row
      current_col = @current_player.col

      valid_moves = @labyrinth.valid_moves(current_row, current_col)

      output = @current_player.move(preferred_direction, valid_moves)

      output
    end

    def combat(monster)
      rounds = 0
      winner = GameCharacter::PLAYER

      player_attack = @current_player.attack
      lose = monster.defend(player_attack)

      while !lose && rounds < MAX_ROUNDS
        winner = GameCharacter::MONSTER
        rounds += 1

        monster_attack = monster.attack
        lose = @current_player.defend(monster_attack)

        unless lose
          player_attack = @current_player.attack
          winner = GameCharacter::PLAYER
          lose = monster.defend(player_attack)
        end
      end

      log_rounds(rounds, MAX_ROUNDS)

      winner
    end

    def manage_reward(winner)
      if winner == GameCharacter::PLAYER
        @current_player.receive_reward
        log_player_won
      else
        log_monster_won
      end
    end

    def manage_resurrection
      resurrect = Dice.resurrect_player

      if resurrect
        @current_player.resurrect
        log_resurrected
      else
        log_player_skip_turn
      end
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
