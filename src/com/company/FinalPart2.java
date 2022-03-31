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

import java.util.Arrays;
import java.util.Random;

public class FinalPart2 extends Application {
    GridPane gridPane = new GridPane();
    Button button = new Button("Solve");
    int[][] values = new int[6][7];

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new Pane();
        Label label = new Label();
        Scene scene = new Scene(pane, 225, 250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Consecutive Four");
        primaryStage.show();
        //Creating grid using function and adding to pane
        pane.getChildren().add(createGrid());
        //Setting grid pane layout
        gridPane.setLayoutX(5);
        gridPane.setLayoutY(20);
        //Adding button and label to pane
        pane.getChildren().add(button);
        pane.getChildren().add(label);
        //Setting label and button layout
        label.setLayoutX(50);
        button.setLayoutX(85);
        button.setLayoutY(210);
        //Solve button event
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //printing array out for debugging purpose
                for (int i = 0; i < 6; i++) {
                    System.out.println(Arrays.toString(values[i]));
                }
                //Checking for consecutive values and setting label
                if (isConsecutiveFour(values)) {
                    label.setText("A consecutive four found");
                    System.out.println("Does the array Contain Four Consecutive Numbers of the Same Value: " + isConsecutiveFour(values));
                } else label.setText("No consecutive four found");

            }
        });
    }

    /**
     * Function to construct gridPane and create TextField with random numbers
     *
     * @return
     */
    private Pane createGrid() {
        Random random = new Random();
        //Creating grid of 6 rows and 7 columns
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                //Generating random number
                int rand = random.nextInt(10);
                //Creating TextField
                TextField text = new TextField();
                text.setPrefSize(30, 30);
                //Adding random number to TextField
                text.setText(Integer.toString(rand));
                //adding values to array
                values[row][col] = rand;
                //adding values to pane
                addPane(text, row, col);
            }
        }
        setBorders();
        return gridPane;
    }

    /**
     * Function add TextField to pane and change edited numbers
     *
     * @param text
     * @param row
     * @param col
     */
    private void addPane(TextField text, int row, int col) {
        //Listener for if TextField lost focus
        text.focusedProperty().addListener((ov, oldValue, newValue) -> {
            if (!newValue) {
                //print out what cell mouse is in for debug purpose
                System.out.printf("Mouse Entered Cell: %d,%d", row, col);
                //initialize nodeValue to it's value before the change
                int nodeValue = values[row][col];

                //Could be an exception parsing the int (i.e. when it is left blank)
                try {
                    //Get text of node in focused cell
                    nodeValue = Integer.parseInt(getNode(gridPane, row, col).getText());
                } catch (NumberFormatException exception) {
                    //leaves the text as what it was before changes were made if an exception was thrown
                    text.setText(Integer.toString(nodeValue));
                }

                //Print out NodeValue for debug
                System.out.println(", value: " + nodeValue);
                //Add changed NodeValue to Array
                values[row][col] = nodeValue;
            }
        });
        //Add to gridPane
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
        //Gets column and row for each node
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                //Returns TextField value of node
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
