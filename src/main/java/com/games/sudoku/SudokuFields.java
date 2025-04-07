package com.games.sudoku;

import java.util.Arrays;
import java.util.Random;

public class SudokuFields {

  private int[][] sudoku = new int[9][9];
  private int[][] solution = new int[9][9];
  private int[][] player = new int[9][9];

  private Random random = new Random();

  public void createNewSudoku() {
    createSolution(solution);
    sudoku = emptyCellsByDifficult("easy");
  }

  public int[][] getSudoku() {
    return sudoku;
  }

  private boolean createSolution(int[][] board) {
    for (int row = 0; row < 9; row++) {
      for (int col = 0; col < 9; col++) {
        if (board[row][col] == 0) {
          for (int i = 1; i <= 9; i++) {
            int num = random.nextInt(1, 10);
            if (isValid(board, row, col, num)) {
              board[row][col] = num;
              if (createSolution(board)) {
                return true;
              }
              board[row][col] = 0;
            }
          }
          return false; // Если ни одно число не подошло, откатываемся
        }
      }
    }
    return true; // Если пустых клеток нет — Судоку решено
  }

  private static boolean isValid(int[][] board, int row, int col, int num) {
    for (int i = 0; i < 9; i++) {
      if (board[row][i] == num || board[i][col] == num ||
              board[row - row % 3 + i / 3][col - col % 3 + i % 3] == num) {
        return false; // Проверка строки, столбца и блока 3×3
      }
    }
    return true;
  }

  private int[][] emptyCellsByDifficult(String difficulty) {
    int[][] newSudoku = deepCopy(solution);
    int cellsToRemove = switch (difficulty) {
      case "easy" -> random.nextInt(36, 41);
      case "medium" -> random.nextInt(41, 50);
      case "hard" -> random.nextInt(50, 55);
      case "expert" -> random.nextInt(55, 64);
      default -> 36;
    };
    for (int i = 0; i < cellsToRemove; i++) {
      newSudoku[random.nextInt(0, 9)][random.nextInt(0, 9)] = 0;
    }
    return newSudoku;
  }

  private int[][] deepCopy(int[][] original) {
    int[][] copy = new int[original.length][];
    for (int i = 0; i < original.length; i++) {
      copy[i] = Arrays.copyOf(original[i], original[i].length);
    }
    return copy;
  }


  // returns the player array
  public int[][] getPlayer() {
    return player;
  }

  // modifies a value in the player array
  public void modifyPlayer(int val, int row, int col) {
    if (val >= 0 && val <= 9) // only values from 0 to 9 inclusive are permitted
      player[row][col] = val;
    else // print out an error message
      System.out.println("Value passed to player falls out of range");
  }

  public boolean checkForSuccess() {
    for (int row = 0; row < 9; row++) {
      for (int col = 0; col < 9; col++) {
        if (sudoku[row][col] == 0) {
          if (player[row][col] != solution[row][col]) {
            return false;
          }
        }
      }
    }
    return true;
  }

}