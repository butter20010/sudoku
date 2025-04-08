package com.games.sudoku;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class SudokuController implements Initializable {

  @FXML
  private Canvas canvas;
  @FXML
  private MenuItem easy;
  @FXML
  private MenuItem medium;
  @FXML
  private MenuItem hard;
  @FXML
  private MenuItem expert;
  @FXML
  private Button hint;
  @FXML
  private Button newSudoku;

  private SudokuGenerator generator;
  private int player_selected_row;
  private int player_selected_col;
  private Difficulty difficulty;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    generator = new SudokuGenerator();
    difficulty = Difficulty.EASY;
    generator.createNewSudoku(difficulty);
    drawOnCanvas();
  }

  public void drawOnCanvas() {
    GraphicsContext context = canvas.getGraphicsContext2D();
    context.clearRect(0, 0, 450, 450);
    CanvasDrawer.drawEmptyCells(context);
    CanvasDrawer.fillCells(context, generator.getSudoku(), Color.BLACK);
    CanvasDrawer.drawCellBorder(context, player_selected_col, player_selected_row);
    CanvasDrawer.drawBlockBorders(context);
    CanvasDrawer.fillCells(context, generator.getPlayer(), Color.PURPLE);
    if (generator.checkForSuccess()) {
      CanvasDrawer.drawSuccess(context);
    }
  }

  public void canvasMouseClicked() {
    canvas.setOnMouseClicked(event -> {
      int mouse_x = (int) event.getX();
      int mouse_y = (int) event.getY();
      player_selected_row = mouse_y / 50;
      player_selected_col = mouse_x / 50;
      drawOnCanvas();
    });
  }

  public void pressedKey(KeyEvent event) {
    if (event.getCode().isDigitKey()) {
      generator.modifyPlayer(Integer.parseInt(event.getCode().getName()), player_selected_row, player_selected_col);
      drawOnCanvas();
    }
  }

  public void onEasyClicked() {
    difficulty = Difficulty.EASY;
    generator.createNewSudoku(difficulty);
    drawOnCanvas();
  }

  public void onMediumClicked() {
    difficulty = Difficulty.MEDIUM;
    generator.createNewSudoku(difficulty);
    drawOnCanvas();
  }

  public void onHardClicked() {
    difficulty = Difficulty.HARD;
    generator.createNewSudoku(difficulty);
    drawOnCanvas();
  }

  public void onExpertClicked() {
    difficulty = Difficulty.EXPERT;
    generator.createNewSudoku(difficulty);
    drawOnCanvas();
  }

  public void onCreateNewClicked() {
    generator.createNewSudoku(difficulty);
    drawOnCanvas();
  }

}