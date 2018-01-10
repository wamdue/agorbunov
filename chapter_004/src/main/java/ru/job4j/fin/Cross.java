package ru.job4j.fin;

import java.util.Scanner;

/**
 * Created on 08.01.18.
 * Crosses and zeros the game.
 * @author Wamdue
 * @version 1.0
 */
public class Cross {
    /**
     * Main game board.
     */
    private final char[][] board;
    /**
     * Character cross.
     */
    private static final char CROSS = 'X';
    /**
     * Character zero.
     */
    private static final char ZERO = 'O';
    /**
     * Free cell.
     */
    public static final char FREE = '-';
    /**
     * Board size.
     */
    private final int size;
    /**
     * Number of player wins to win the game.
     */
    private final int winNumbers;
    /**
     * First player.
     */
    private Player player;
    /**
     * Second player.
     */
    private Player player2;


    /**
     * Default constructor with board size 3.
      */
    public Cross() {
        this(3, 1);
    }

    /**
     * Main constructor.
     * @param size - board size.
     * @param winNumbers - number of player wins to win the game.
     */
    public Cross(int size, int winNumbers) {
        this.board = new char[size][size];
        this.size = size;
        this.newGame();
        this.winNumbers = winNumbers;
    }

    /**
     * Fill board with free cells.
     */
    private void newGame() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.board[i][j] = FREE;
            }
        }
    }

    /**
     * Draw board.
     */
    private void drawGame() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                System.out.printf("%s ", this.board[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Check if have all figures on horizontal lines.
     * @return true or false if not.
     */
    private boolean isHorizontalWinner() {
        boolean result = false;
            for (int i = 0; i < this.size; i++) {
                char temp = this.board[i][0];
                if (temp == FREE) {
                    continue;
                }

                boolean check = true;
                for (int j = 0; j < this.size; j++) {
                    if (this.board[i][j] != temp) {
                        check = false;
                        break;
                    }
                }
                if (check) {
                    result = true;
                    break;
                }
            }
        return result;
    }
    /**
     * Check if have all figures on diagonal lines.
     * @return true or false if not.
     */
    private boolean isVerticalWinner() {
        boolean result = false;
        for (int i = 0; i < this.size; i++) {
            char temp = this.board[0][i];
            if (temp == FREE) {
                continue;
            }
            boolean check = true;
            for (int j = 0; j < this.size; j++) {
                if (this.board[j][i] != temp) {
                    check = false;
                    break;
                }
            }
            if (check) {
                result = true;
                break;
            }
        }

        return result;
    }
    /**
     * Check if have all figures on right diagonal.
     * @return true or false if not.
     */
    private boolean isDiagonalRightWinner() {
        boolean result = false;
        char temp = this.board[0][0];
        if (temp != FREE) {
            result = true;
            for (int i = 0; i < this.size; i++) {
                if (this.board[i][i] != temp) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }
    /**
     * Check if have all figures on left diagonal.
     * @return true or false if not.
     */
    private boolean isDiagonalLeftWinner() {
        boolean result = false;
        int val = this.size - 1;
        char temp = this.board[0][val];
        if (temp != FREE) {
            result = true;
            for (int i = 0; i < this.size; i++) {
                if (this.board[i][val - i] != temp) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Checks, if turn can be done on this coords.
     * @param x - coord x.
     * @param y - coord y.
     * @return - false if cannot, true if can.
     */
    private boolean canMove(int x, int y) {
        boolean result = false;
        if (x > 0 && y > 0 && x <= this.size && y <= this.size) {
            result = true;
        }
        return result;
    }

    /**
     * Checks if have possible moves on the board.
     * @return true if have, false if not.
     */
    private boolean haveMoves() {
        boolean result = false;
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (this.board[i][j] == FREE) {
                    result = true;
                }
            }
        }

        return result;
    }

    /**
     * Checks all possible directions for winners.
     * @return true if has winner.
     */
    private boolean isWinner() {
        boolean result = isHorizontalWinner();
        if (!result) {
            result = isVerticalWinner();
        }

        if (!result) {
            result = isDiagonalLeftWinner();
        }

        if (!result) {
            result = isDiagonalRightWinner();
        }

        return result;
    }

    /**
     * Create one player.
     * @param scanner input method.
     * @param line - welcome string.
     * @return new player.
     */
    private Player createPlayer(Scanner scanner, String line) {
        System.out.println(line);
        boolean flag = false;
        Player p = null;
        while (!flag) {
            String[] temp = scanner.nextLine().split(" ");
            flag = temp.length == 2;
            if (!flag) {
                continue;
            }
            char figure;
            Side s = "c".equals(temp[0]) ? Side.COMPUTER : Side.PLAYER;
            if (player != null) {
                figure = player.getFigure() == CROSS ? ZERO : CROSS;
            } else {
                figure = temp[1].toUpperCase().charAt(0);
            }
            p = new Player(s, figure, this.board);
        }

        return p;
    }

    /**
     * Welcome string to create players.
     * @param scanner - input method.
     */
    private void createPlayers(Scanner scanner) {
        System.out.println("Choose sides c - computer, p - player, figure x or o. "
                + "Example string c x - computer control, figure x");
        player = createPlayer(scanner, "Player 1:");
        player2 = createPlayer(scanner, "Player 2:");
    }

    /**
     * Checks, if player wins the game.
     * @param p - player.
     * @return wins or not.
     */
    private boolean checkGame(Player p) {
        boolean result = false;
        if (this.isWinner()) {
            p.win();
            this.drawGame();
            if (this.winNumbers == p.getWins()) {
                System.out.printf("Player %s is winner!\n", p.getFigure());
            }
            result = true;
        }
        return result;
    }

    /**
     * Starting game.
     */
    public void game() {
        Scanner scanner = new Scanner(System.in);
        boolean game = false;
        this.createPlayers(scanner);
        Player turn = player;
        while (!game) {
            this.drawGame();
            if (haveMoves()) {
                if (turn.getSide() ==  Side.COMPUTER) {
                    turn.makeMove();
                    game = this.checkGame(turn);
                } else {
                    System.out.printf("%s make move: ", turn.getFigure());
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();
                    if (x == -1) {
                        game = true;
                        continue;
                    }
                    if (canMove(x, y)) {
                        turn.makeMove(x - 1, y - 1);
                        game = this.checkGame(turn);
                    } else {
                        System.out.println("Coords is out of range. Enter one more time.");
                        continue;
                    }
                }
                turn = turn == player ? player2 : player;
            } else {
                System.out.println("No more moves.");
                this.newGame();
            }
        }
    }
}