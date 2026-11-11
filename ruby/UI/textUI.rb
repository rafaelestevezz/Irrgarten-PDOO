require 'io/console'
require_relative '../directions'

module UI

  class TextUI

    def read_char
      STDIN.echo = false
      STDIN.raw!

      input = STDIN.getc.chr
      if input == "\e"
        input << STDIN.read_nonblock(3) rescue nil
        input << STDIN.read_nonblock(2) rescue nil
      end
    ensure
      STDIN.echo = true
      STDIN.cooked!

      return input
    end

    def next_move
      print "Where? "
      got_input = false
      while (!got_input)
        c = read_char
        case c
          when "\e[A"
            puts "UP ARROW"
            output = Irrgarten::Directions::UP
            got_input = true
          when "\e[B"
            puts "DOWN ARROW"
            output = Irrgarten::Directions::DOWN
            got_input = true
          when "\e[C"
            puts "RIGHT ARROW"
            output = Irrgarten::Directions::RIGHT
            got_input = true
          when "\e[D"
            puts "LEFT ARROW"
            output = Irrgarten::Directions::LEFT
            got_input = true
          when "\u0003"
            puts "CONTROL-C"
            got_input = true
            exit(1)
          else
            #Error
        end
      end
      output
    end

    def show_game(game_state)

    def show_game(game_state)
      puts "\nIRRGARTEN"

      puts "\nLABERINTO:"
      puts game_state.labyrinth

      puts "\nJUGADORES:"
      puts game_state.players

      puts "\nMONSTRUOS:"
      puts game_state.monsters

      puts "\nJUGADOR ACTUAL:"
      puts game_state.current_player

      puts "\nHAY GANADOR?"
      puts game_state.winner

      puts "\nREGISTRO:"
      puts game_state.log

      puts "\n"
    end

    end

  end # class

end # module


