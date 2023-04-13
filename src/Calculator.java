
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Calculator extends Application {

    private TextField displayField;
    private double operand1;
    private double operand2;
    private String operator = "";
    private boolean isDecimal = false;

    @Override
    public void start(Stage primaryStage) {

        // Create the display field for the calculator
        displayField = new TextField();
        displayField.setAlignment(Pos.CENTER_RIGHT);
        displayField.setEditable(false);

        // Create the buttons for the calculator
        Button button0 = new Button("0");
        Button button1 = new Button("1");
        Button button2 = new Button("2");
        Button button3 = new Button("3");
        Button button4 = new Button("4");
        Button button5 = new Button("5");
        Button button6 = new Button("6");
        Button button7 = new Button("7");
        Button button8 = new Button("8");
        Button button9 = new Button("9");
        Button buttonDecimal = new Button(".");
        Button buttonEquals = new Button("=");
        Button buttonPlus = new Button("+");
        Button buttonMinus = new Button("-");
        Button buttonMultiply = new Button("*");
        Button buttonDivide = new Button("/");
        Button buttonClear = new Button("C");
        Button buttonSin = new Button("sin");
        Button buttonCos = new Button("cos");
        Button buttonTan = new Button("tan");
        Button buttonArcSin = new Button("sin⁻¹");
        Button buttonArcCos = new Button("cos⁻¹");
        Button buttonArcTan = new Button("tan⁻¹");
        Button buttonLog = new Button("log");
        Button buttonLn = new Button("ln");
        Button buttonRoot = new Button("√");

        // Set the properties of the buttons
        button0.setOnAction(event -> handleButtonPress("0"));
        button1.setOnAction(event -> handleButtonPress("1"));
        button2.setOnAction(event -> handleButtonPress("2"));
        button3.setOnAction(event -> handleButtonPress("3"));
        button4.setOnAction(event -> handleButtonPress("4"));
        button5.setOnAction(event -> handleButtonPress("5"));
        button6.setOnAction(event -> handleButtonPress("6"));
        button7.setOnAction(event -> handleButtonPress("7"));
        button8.setOnAction(event -> handleButtonPress("8"));
        button9.setOnAction(event -> handleButtonPress("9"));
        buttonDecimal.setOnAction(event -> handleButtonPress("."));
        buttonPlus.setOnAction(event -> handleOperatorPress("+"));
        buttonMinus.setOnAction(event -> handleOperatorPress("-"));
        buttonMultiply.setOnAction(event -> handleOperatorPress("*"));
        buttonDivide.setOnAction(event -> handleOperatorPress("/"));
        buttonEquals.setOnAction(event -> handleEqualsPress());
        buttonClear.setOnAction(event -> handleClearPress());
        buttonSin.setOnAction(event -> handleUnaryOperatorPress("sin"));
        buttonCos.setOnAction(event -> handleUnaryOperatorPress("cos"));
        buttonTan.setOnAction(event -> handleUnaryOperatorPress("tan"));
        buttonArcSin.setOnAction(event -> handleUnaryOperatorPress("asin"));
        buttonArcCos.setOnAction(event -> handleUnaryOperatorPress("acos"));
        buttonArcTan.setOnAction(event -> handleUnaryOperatorPress("atan"));
        buttonLog.setOnAction(event -> handleUnaryOperatorPress("log"));
        buttonLn.setOnAction(event -> handleUnaryOperatorPress("ln"));
        buttonRoot.setOnAction(event -> handleUnaryOperatorPress("sqrt"));

        // Create a grid pane to hold the buttons
        GridPane buttonGrid = new GridPane();
        buttonGrid.setHgap(5);
        buttonGrid.setVgap(5);
        buttonGrid.add(buttonClear, 0, 0);
        buttonGrid.add(buttonArcSin, 1, 0);
        buttonGrid.add(buttonArcCos, 2, 0);
        buttonGrid.add(buttonArcTan, 3, 0);
        buttonGrid.add(buttonSin, 0, 1);
        buttonGrid.add(buttonCos, 1, 1);
        buttonGrid.add(buttonTan, 2, 1);
        buttonGrid.add(buttonLog, 3, 1);
        buttonGrid.add(button7, 0, 2);
        buttonGrid.add(button8, 1, 2);
        buttonGrid.add(button9, 2, 2);
        buttonGrid.add(buttonRoot, 3, 2);
        buttonGrid.add(button4, 0, 3);
        buttonGrid.add(button5, 1, 3);
        buttonGrid.add(button6, 2, 3);
        buttonGrid.add(buttonLn, 3, 3);
        buttonGrid.add(button1, 0, 4);
        buttonGrid.add(button2, 1, 4);
        buttonGrid.add(button3, 2, 4);
        buttonGrid.add(buttonMultiply, 3, 4);
        buttonGrid.add(buttonDecimal, 0, 5);
        buttonGrid.add(button0, 1, 5);
        buttonGrid.add(buttonEquals, 2, 5);
        buttonGrid.add(buttonDivide, 3, 5);
        buttonGrid.add(buttonPlus, 4, 3, 1, 2);
        buttonGrid.add(buttonMinus, 4, 1, 1, 2);

        // Create an HBox to hold the display field and the button grid
        HBox displayBox = new HBox();
        displayBox.setAlignment(Pos.CENTER);
        displayBox.setSpacing(5);
        displayBox.getChildren().addAll(displayField, buttonGrid);

        // Create a VBox to hold the display box and the scene title
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);
        root.getChildren().addAll(displayBox);

        // Create the scene for the calculator
        Scene scene = new Scene(root, 500, 500);

        // Set the stage title and scene, and show the stage
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Handle the press of a digit or decimal button.
     *
     * @param value the value of the button pressed
     */
    private void handleButtonPress(String value) {
        if (value.equals(".") && isDecimal) {
            return;
        }

        if (value.equals(".")) {
            isDecimal = true;
        }

        displayField.setText(displayField.getText() + value);
    }

    /**
     * Handle the press of an operator button.
     *
     * @param operator the operator button pressed
     */
    private void handleOperatorPress(String operator) {
        if (!displayField.getText().isEmpty()) {
            this.operator = operator;
            operand1 = Double.parseDouble(displayField.getText());
            displayField.clear();
            isDecimal = false;
        }
    }

    /**
     * Handle the press of an unary operator button.
     *
     * @param operator the unary operator button pressed
     */
    private void handleUnaryOperatorPress(String operator) {
        if (!displayField.getText().isEmpty()) {
            double operand = Double.parseDouble(displayField.getText());

            switch (operator) {
                case "sin":
                    displayField.setText(String.format("%.8f", Math.sin(Math.toRadians(operand))));
                    break;
                case "cos":
                    displayField.setText(String.format("%.8f", Math.cos(Math.toRadians(operand))));
                    break;
                case "tan":
                    displayField.setText(String.format("%.8f", Math.tan(Math.toRadians(operand))));
                    break;
                case "arcsin":
                    displayField.setText(String.format("%.8f", Math.toDegrees(Math.asin(operand))));
                    break;
                case "arccos":
                    displayField.setText(String.format("%.8f", Math.toDegrees(Math.acos(operand))));
                    break;
                case "arctan":
                    displayField.setText(String.format("%.8f", Math.toDegrees(Math.atan(operand))));
                    break;
                case "log":
                    displayField.setText(String.format("%.8f", Math.log10(operand)));
                    break;
                case "ln":
                    displayField.setText(String.format("%.8f", Math.log(operand)));
                    break;
                case "sqrt":
                    displayField.setText(String.format("%.8f", Math.sqrt(operand)));
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Handle the press of the equals button.
     */
    private void handleEqualsPress() {

        if (!displayField.getText().isEmpty() && operator != null) {
            double operand2 = Double.parseDouble(displayField.getText());
            double result = 0.0;

            switch (operator) {
                case "+":
                    result = operand1 + operand2;
                    break;
                case "-":
                    result = operand1 - operand2;
                    break;
                case "*":
                    result = operand1 * operand2;
                    break;
                case "/":
                    if (operand2 != 0) {
                        result = operand1 / operand2;
                    } else {
                        displayField.setText("Error");
                        return;
                    }
                    break;
                case "^":
                    result = Math.pow(operand1, operand2);
                    break;
                case "nthRoot":
                    if (operand1 >= 0) {
                        result = Math.pow(operand2, 1.0 / operand1);
                    } else {
                        displayField.setText("Error");
                        return;
                    }
                    break;
                default:
                    break;
            }

            displayField.setText(String.format("%.8f", result));
            operator = null;
            operand1 = 0.0;
        }
    }

    /**
     * Handle the press of the clear button.
     */
    private void handleClearPress() {
        displayField.clear();
        operator = null;
        operand1 = (Double) null;
        isDecimal = false;
    }

    /**
     * Launch the calculator application.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }
}