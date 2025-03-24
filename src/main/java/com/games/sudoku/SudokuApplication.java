package com.games.sudoku;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SudokuApplication extends Application {
  @Override
  public void start(Stage primaryStage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(SudokuApplication.class.getResource("sudoku-view.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 480, 480);
    primaryStage.setTitle("Sudoku!");
    primaryStage.setScene(scene);
    SudokuController controller = fxmlLoader.getController();
    scene.setOnKeyPressed(controller::pressedKey);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch();
  }
}