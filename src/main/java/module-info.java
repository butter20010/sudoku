module com.games.sudoku {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.games.sudoku to javafx.fxml;
    exports com.games.sudoku;
}