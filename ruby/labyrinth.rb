#encoding:utf-8

require_relative 'dice'
require_relative 'directions'
require_relative 'orientation'
require_relative 'monster'
require_relative 'player'

module Irrgarten
  class Labyrinth
    BLOCK_CHAR = 'X'
    EMPTY_CHAR = '-'
    MONSTER_CHAR = 'M'
    COMBAT_CHAR = 'C'
    EXIT_CHAR = 'E'

    @@ROW = 0
    @@COL = 1

    def initialize(n_rows, n_cols, exit_row, exit_col)
      @n_rows = n_rows
      @n_cols = n_cols
      @exit_row = exit_row
      @exit_col = exit_col

      @monsters = Array.new(n_rows) { Array.new(n_cols) }
      @players = Array.new(n_rows) { Array.new(n_cols) }
      @labyrinth = Array.new(n_rows) { Array.new(n_cols, EMPTY_CHAR) }

      @labyrinth[@exit_row][@exit_col] = EXIT_CHAR
    end

    def spread_players(players)
      raise NotImplementedError, 'Pendiente de la P3'
    end

    def have_a_winner
      !@players[@exit_row][@exit_col].nil?
    end

    def to_s
      resultado = ''

      @n_rows.times do |row|
        @n_cols.times do |col|
          resultado += @labyrinth[row][col]
        end
        resultado += "\n"
      end

      resultado
    end

    def add_monster(row, col, monster)
      if pos_ok(row, col) && empty_pos(row, col)
        @labyrinth[row][col] = MONSTER_CHAR
        @monsters[row][col] = monster
        monster.set_pos(row, col)
      end
    end

    def put_player(direction, player)
      raise NotImplementedError, 'Pendiente de la P3'
    end

    def add_block(orientation, start_row, start_col, length)
      raise NotImplementedError, 'Pendiente de la P3'
    end

    def valid_moves(row, col)
      raise NotImplementedError, 'Pendiente de la P3'
    end

    private

    def pos_ok(row, col)
      row >= 0 && row < @n_rows &&
        col >= 0 && col < @n_cols
    end

    def empty_pos(row, col)
      @labyrinth[row][col] == EMPTY_CHAR
    end

    def monster_pos(row, col)
      @labyrinth[row][col] == MONSTER_CHAR
    end

    def exit_pos(row, col)
      @labyrinth[row][col] == EXIT_CHAR
    end

    def combat_pos(row, col)
      @labyrinth[row][col] == COMBAT_CHAR
    end

    def can_step_on(row, col)
      pos_ok(row, col) &&
        (empty_pos(row, col) ||
         monster_pos(row, col) ||
         exit_pos(row, col))
    end

    def update_old_pos(row, col)
      if pos_ok(row, col)
        if combat_pos(row, col)
          @labyrinth[row][col] = MONSTER_CHAR
        else
          @labyrinth[row][col] = EMPTY_CHAR
        end
      end
    end

    def dir2pos(row, col, direction)
      case direction
      when Directions::UP
        row -= 1
      when Directions::DOWN
        row += 1
      when Directions::LEFT
        col -= 1
      when Directions::RIGHT
        col += 1
      end

      [row, col]
    end

    def random_empty_pos
      loop do
        row = Dice.random_pos(@n_rows)
        col = Dice.random_pos(@n_cols)

        return [row, col] if empty_pos(row, col)
      end
    end

    def put_player_2d(old_row, old_col, row, col, player)
      raise NotImplementedError, 'Pendiente de la P3'
    end
  end
end
