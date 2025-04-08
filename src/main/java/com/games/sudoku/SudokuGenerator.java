package com.games.sudoku;

import java.util.Arrays;
import java.util.Random;

public class SudokuGenerator {

  private int[][] sudoku;
  private int[][] solution;
  private int[][] player;

  private int solutions;

  private final Random random = new Random();

  public void createNewSudoku(Difficulty difficulty) {
    this.solution = new int[9][9];
    createSolution(this.solution);
    this.sudoku = emptyCellsByDifficult(difficulty);
    this.player = new int[9][9];
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
          return false;
        }
      }
    }
    return true;
  }

  private static boolean isValid(int[][] board, int row, int col, int num) {
    for (int i = 0; i < 9; i++) {
      if (board[row][i] == num || board[i][col] == num ||
              board[row - row % 3 + i / 3][col - col % 3 + i % 3] == num) {
        return false;
      }
    }
    return true;
  }

  private void solveSudoku(int[][] solvedSudoku) {
    if (solutions > 1) {
      return;
    }
    for (int row = 0; row < 9; row++) {
      for (int col = 0; col < 9; col++) {
        if (solvedSudoku[row][col] == 0) {
          for (int num = 1; num <= 9; num++) {
            if (isValid(solvedSudoku, row, col, num)) {
              solvedSudoku[row][col] = num;
              solveSudoku(solvedSudoku);
              solvedSudoku[row][col] = 0;
            }
          }
          return;
        }
      }
    }
    this.solutions++;
  }

  private int[][] emptyCellsByDifficult(Difficulty difficulty) {
    int[][] newSudoku = deepCopy(solution);
    int cellsToRemove = switch (difficulty) {
      case EASY -> random.nextInt(36, 41);
      case MEDIUM -> random.nextInt(41, 50);
      case HARD -> random.nextInt(50, 55);
      case EXPERT -> random.nextInt(55, 64);
    };
    for (int i = 0; i < cellsToRemove; i++) {
      int row = random.nextInt(0, 9);
      int col = random.nextInt(0, 9);
      if (newSudoku[row][col] == 0) {
        i--;
        continue;
      }
      newSudoku[row][col] = 0;
      solutions = 0;
      solveSudoku(deepCopy(newSudoku));
      if (solutions > 1) {
        newSudoku[row][col] = this.solution[row][col];
        i--;
      }
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

  public int[][] getPlayer() {
    return player;
  }

  public void modifyPlayer(int val, int row, int col) {
    if (sudoku[row][col] != 0) {
      return;
    }
    if (val >= 0 && val <= 9)
      player[row][col] = val;
    else
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