package com.games.sudoku;

import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.input.KeyCode.*;

public class SudokuController implements Initializable {

  public Canvas canvas;

  private SudokuFields fields;
  private int player_selected_row;
  private int player_selected_col;


  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    fields = new SudokuFields();
    GraphicsContext context = canvas.getGraphicsContext2D();
    drawOnCanvas(context);
  }

  public void drawOnCanvas(GraphicsContext context) {
    context.clearRect(0, 0, 450, 450);
    CanvasDrawer.drawEmptyCells(context);
    // draw highlight around selected cell
    // set stroke color to res
    context.setStroke(Color.RED);
    // set stroke width to 5px
    context.setLineWidth(5);
    // draw a strokeRoundRect using the same technique we used for drawing our board.
    context.strokeRoundRect(player_selected_col * 50 + 2, player_selected_row * 50 + 2, 46, 46, 10, 10);
    // draw the initial numbers from our GameBoard instance
    int[][] initial = fields.getInitial();

    // for loop is the same as before
    CanvasDrawer.fillCellsByInitial(context, initial);


    // draw the players numbers from our GameBoard instance
    int[][] player = fields.getPlayer();
    CanvasDrawer.fillCellsByPlayer(context, player);

    if (fields.checkForSuccess()) {

      context.clearRect(0, 0, 450, 450);
      context.setFill(Color.GREEN);
      context.setFont(new Font(36));
      context.fillText("SUCCESS!", 150, 250);
    }
  }

  public void canvasMouseClicked() {

    canvas.setOnMouseClicked(event -> {
      int mouse_x = (int) event.getX();
      int mouse_y = (int) event.getY();

      player_selected_row = mouse_y / 50;
      player_selected_col = mouse_x / 50;

      drawOnCanvas(canvas.getGraphicsContext2D());
    });
  }

  public void pressedKey(KeyEvent event) {
    if (event.getCode().isDigitKey()) {
      fields.modifyPlayer(Integer.parseInt(event.getCode().getName()), player_selected_row, player_selected_col);
      drawOnCanvas(canvas.getGraphicsContext2D());
    }
  }

}