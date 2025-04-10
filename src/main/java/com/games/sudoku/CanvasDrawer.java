package com.games.sudoku;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CanvasDrawer {

  public static void drawEmptyCells(GraphicsContext context) {
    for (int row = 0; row < 9; row++) {
      for (int col = 0; col < 9; col++) {
        int position_y = row * 50 + 2;
        int position_x = col * 50 + 2;
        int width = 46;
        context.setFill(Color.WHITE);
        context.fillRoundRect(position_x, position_y, width, width, 10, 10);
      }
    }
  }

  public static void fillCells(GraphicsContext context, int[][] board, Color color, boolean bold) {
    context.setFont(Font.font(bold ? "System" : "Italic", bold ? FontWeight.BOLD : FontWeight.THIN, 25));
    context.setFill(color);

    for (int row = 0; row < 9; row++) {
      for (int col = 0; col < 9; col++) {
        if (board[row][col] != 0) {
          int position_y = row * 50 + 30;
          int position_x = col * 50 + 20;
          context.fillText(String.valueOf(board[row][col]), position_x, position_y);
        }
      }
    }
  }


  public static void drawCellBorder(GraphicsContext context, int col, int row) {
    context.setStroke(Paint.valueOf("#388e3c"));
    context.setLineWidth(4);
    context.setLineDashes(10, 5);
    context.strokeRoundRect(col * 50 + 2, row * 50 + 2, 46, 46, 10, 10);
  }

  public static void drawBlockBorders(GraphicsContext context) {
    context.setLineDashes();
    context.setStroke(Paint.valueOf("#a3bfa3"));
    context.setLineWidth(3);
    context.setLineCap(StrokeLineCap.ROUND);
    for (int col = 0; col <= 9; col = col + 3) {
      double x = col * 50;
      context.strokeLine(x, 0, x, 450);
    }
    for (int row = 0; row <= 9; row = row + 3) {
      double y = row * 50;
      context.strokeLine(0, y, 450, y);
    }
  }


  public static void drawSuccess(GraphicsContext context) {
    context.clearRect(0, 0, 450, 450);
    context.setFill(Color.GREEN);
    context.setFont(new Font(36));
    context.fillText("SUCCESS!", 150, 250);
  }
}
