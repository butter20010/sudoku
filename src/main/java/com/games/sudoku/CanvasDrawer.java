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
        
        int width = 46;
        context.setFill(Color.WHITE);

        //change for rounded effect
        context.fillRoundRect(position_x, position_y, width, width, 10, 10);
      }
    }
  }

  public static void fillCells(GraphicsContext context, int[][] initial, Color color) {
    for (int row = 0; row < initial.length; row++) {
      for (int col = 0; col < initial[row].length; col++) {

        int position_y = row * 50 + 30;
        int position_x = col * 50 + 20;

        context.setFill(color);
        context.setFont(new Font(20));

        if (initial[row][col] != 0) {
          context.fillText(String.valueOf(initial[row][col]), position_x, position_y);
        }
      }
    }
  }

  public static void drawCellBorder(GraphicsContext context, int col, int row) {
    context.setStroke(Color.RED);
    context.setLineWidth(5);
    context.strokeRoundRect(col * 50 + 2, row * 50 + 2, 46, 46, 10, 10);
  }

  public static void drawSuccess(GraphicsContext context) {
    context.clearRect(0, 0, 450, 450);
    context.setFill(Color.GREEN);
    context.setFont(new Font(36));
    context.fillText("SUCCESS!", 150, 250);
  }
}
