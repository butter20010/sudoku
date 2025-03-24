package com.games.sudoku;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class CanvasDrawer {

  public static void drawEmptyCells(GraphicsContext context) {
    for (int row = 0; row < 9; row++) {
      for (int col = 0; col < 9; col++) {
        int position_y = row * 50 + 2;
        int position_x = col * 50 + 2;

        //width and height of cells
        int width = 46;

        //color of cells
        context.setFill(Color.WHITE);

        //change for rounded effect
        context.fillRoundRect(position_x, position_y, width, width, 10, 10);
      }
    }
  }

  public static void fillCellsByInitial(GraphicsContext context, int[][] initial) {
    for (int row = 0; row < 9; row++) {
      for (int col = 0; col < 9; col++) {

        int position_y = row * 50 + 30;
        int position_x = col * 50 + 20;

        //color of numbers
        context.setFill(Color.BLACK);

        //font of numbers
        context.setFont(new Font(20));

        if (initial[row][col] != 0) {
          context.fillText(String.valueOf(initial[row][col]), position_x, position_y);
        }
      }
    }
  }

  public static void fillCellsByPlayer(GraphicsContext context, int[][] player) {
    for (int row = 0; row < 9; row++) {
      for (int col = 0; col < 9; col++) {
        int position_y = row * 50 + 30;
        int position_x = col * 50 + 20;

        //color of players numbers
        context.setFill(Color.PURPLE);

        //font of players numbers
        context.setFont(new Font(20));

        if (player[row][col] != 0) {
          context.fillText(String.valueOf(player[row][col]), position_x, position_y);
        }
      }
    }
  }
}
