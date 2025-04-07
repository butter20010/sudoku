package com.games.sudoku;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class SudokuController implements Initializable {

  public Canvas canvas;

  @FXML
  private GridPane gridPane;

  private Label[][] cells = new Label[9][9];

  private SudokuFields fields;
  private int player_selected_row;
  private int player_selected_col;


  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    fields = new SudokuFields();
    GraphicsContext context = canvas.getGraphicsContext2D();
    fields.createNewSudoku();
    drawOnCanvas(context);
  }

  public void drawOnCanvas(GraphicsContext context) {
    context.clearRect(0, 0, 450, 450);
    CanvasDrawer.drawEmptyCells(context);
    CanvasDrawer.fillCells(context, fields.getSudoku(), Color.BLACK);
    CanvasDrawer.drawCellBorder(context, player_selected_col, player_selected_row);
    CanvasDrawer.fillCells(context, fields.getPlayer(), Color.PURPLE);
    if (fields.checkForSuccess()) {
      CanvasDrawer.drawSuccess(context);
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