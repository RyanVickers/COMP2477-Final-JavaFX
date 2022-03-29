package com.company;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.awt.*;
import java.awt.print.Pageable;
import java.util.Arrays;
import java.util.Random;

public class Main extends Application {
    GridPane gridPane = new GridPane();
    Button button = new Button("Solve");
    int[][] values = new int[6][7];

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane grid = new Pane();
        Label label = new Label();
        Scene scene = new Scene(grid, 225, 250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Consecutive Four");
        primaryStage.show();
        grid.getChildren().add(makeGrid());
        gridPane.setLayoutX(5);
        gridPane.setLayoutY(20);
        grid.getChildren().add(button);
        grid.getChildren().add(label);
        label.setLayoutX(50);
        button.setLayoutX(85);
        button.setLayoutY(210);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for (int i = 0; i < 6; i++) {
                    System.out.println(Arrays.toString(values[i]));
                }
                if (isConsecutiveFour(values)) {
                    label.setText("A consecutive four found");
                    System.out.println("Does the consecutive four pattern exist: " + isConsecutiveFour(values));
                } else label.setText("No consecutive four found");

            }
        });
    }

    /**
     * Function to construct gridPane
     *
     * @return
     */
    private Pane makeGrid() {
        Random random = new Random();
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                int rand = random.nextInt(10);
                TextField text = new TextField();
                text.setPrefSize(30, 30);
                text.setText(Integer.toString(rand));
                values[row][col] = rand;
                addPane(text, row, col);
            }
        }
        setBorders();
        return gridPane;
    }

    /**
     * Function add TextField Pane with random numbers to grid
     *
     * @param text
     * @param row
     * @param col
     */
    private void addPane(TextField text, int row, int col) {
        text.focusedProperty().addListener((ov, oldV, newV) -> {
            if (!newV) {
                System.out.printf("Mouse enetered cell [%d, %d]%n", row, col);
                int nodeValue = Integer.parseInt(getNode(gridPane, row, col).getText());
                System.out.println(nodeValue);
                values[row][col] = nodeValue;
            }
        });
        gridPane.add(text, col, row);

    }

    /**
     * Function to get individual nodes from gridPane and return TextField Value
     *
     * @param gridPane
     * @param row
     * @param col
     * @return
     */
    private TextField getNode(GridPane gridPane, int row, int col) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return (TextField) node;
            }
        }
        return null;
    }

    /**
     * Function to reset TextField border color
     */
    private void setBorders() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                getNode(gridPane, i, j).setStyle("-fx-border-color: lightgrey;");
            }
        }
    }

    /**
     * Function to check for consecutive four numbers and highlight boxes
     *
     * @param values
     * @return
     */
    public boolean isConsecutiveFour(int[][] values) {
        int rows = values.length;
        int columns = values[0].length;

        //Checking Horizontally
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns - 3; j++) {
                if (values[i][j] == values[i][j + 1] &&
                        values[i][j] == values[i][j + 2] &&
                        values[i][j] == values[i][j + 3]) {
                    //Checking for numbers in gridPane
                    getNode(gridPane, i, j).setStyle("-fx-border-color: Blue;");
                    getNode(gridPane, i, j + 1).setStyle("-fx-border-color: Blue;");
                    getNode(gridPane, i, j + 2).setStyle("-fx-border-color: Blue;");
                    getNode(gridPane, i, j + 3).setStyle("-fx-border-color: Blue;");
                    return true;
                }
            }
        }

        //Checking vertical
        for (int i = 0; i < rows - 3; i++) {
            for (int j = 0; j < columns; j++) {
                if (values[i][j] == values[i + 1][j] &&
                        values[i][j] == values[i + 2][j] &&
                        values[i][j] == values[i + 3][j]) {
                    getNode(gridPane, i, j).setStyle("-fx-border-color: Blue;");
                    getNode(gridPane, i + 1, j).setStyle("-fx-border-color: Blue;");
                    getNode(gridPane, i + 2, j).setStyle("-fx-border-color: Blue;");
                    getNode(gridPane, i + 3, j).setStyle("-fx-border-color: Blue;");
                    return true;
                }
            }
        }

        //Checking diagonal top left to right
        for (int i = 0; i < rows - 3; i++) {
            for (int j = 0; j < columns - 3; j++) {
                if (values[i][j] == values[i + 1][j + 1] &&
                        values[i][j] == values[i + 2][j + 2] &&
                        values[i][j] == values[i + 3][j + 3]) {
                    getNode(gridPane, i, j).setStyle("-fx-border-color: Blue;");
                    getNode(gridPane, i + 1, j + 1).setStyle("-fx-border-color: Blue;");
                    getNode(gridPane, i + 2, j + 2).setStyle("-fx-border-color: Blue;");
                    getNode(gridPane, i + 3, j + 3).setStyle("-fx-border-color: Blue;");
                    return true;
                }
            }

        }

        //checking diagonal top right to left
        for (int i = 0; i < rows - 3; i++) {
            for (int j = columns - 4; j < columns; j++) {
                if (values[i][j] == values[i + 1][j - 1] &&
                        values[i][j] == values[i + 2][j - 2] &&
                        values[i][j] == values[i + 3][j - 3]) {
                    getNode(gridPane, i, j).setStyle("-fx-border-color: Blue;");
                    getNode(gridPane, i + 1, j - 1).setStyle("-fx-border-color: Blue;");
                    getNode(gridPane, i + 2, j - 2).setStyle("-fx-border-color: Blue;");
                    getNode(gridPane, i + 3, j - 3).setStyle("-fx-border-color: Blue;");
                    return true;
                }
            }
        }
        setBorders();
        return false;
    }
}
