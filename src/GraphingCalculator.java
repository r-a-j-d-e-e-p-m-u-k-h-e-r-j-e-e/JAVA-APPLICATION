import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GraphingCalculator extends Application {

    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private final int CHART_WIDTH = 600;
    private final int CHART_HEIGHT = 400;

    private LineChart<Number, Number> chart;
    private XYChart.Series<Number, Number> series;
    private TextField textFieldXMin;
    private TextField textFieldXMax;
    private TextField textFieldYMin;
    private TextField textFieldYMax;
    private TextField textFieldExpression;
    private Label labelError;

    @Override
    public void start(Stage primaryStage) {

        // Create the chart
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        chart = new LineChart<>(xAxis, yAxis);
        chart.setPrefSize(CHART_WIDTH, CHART_HEIGHT);
        series = new XYChart.Series<>();

        // Create the input fields and buttons
        textFieldXMin = new TextField("-10");
        textFieldXMax = new TextField("10");
        textFieldYMin = new TextField("-10");
        textFieldYMax = new TextField("10");
        textFieldExpression = new TextField("x^2");
        Button buttonGraph = new Button("Graph");
        buttonGraph.setOnAction(new GraphHandler());

        // Create the error label
        labelError = new Label();
        labelError.setStyle("-fx-text-fill: red;");

        // Create the input field boxes
        HBox hboxX = new HBox(10, new Label("X-min:"), textFieldXMin, new Label("X-max:"), textFieldXMax);
        HBox hboxY = new HBox(10, new Label("Y-min:"), textFieldYMin, new Label("Y-max:"), textFieldYMax);
        HBox hboxExpression = new HBox(10, new Label("Expression:"), textFieldExpression, buttonGraph);
        VBox vboxInputs = new VBox(10, hboxX, hboxY, hboxExpression, labelError);

        // Create the root node and add the chart and input field boxes
        VBox root = new VBox(10, chart, vboxInputs);

        // Create the scene and show the stage
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class GraphHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            // Clear the chart
            chart.getData().clear();
            series = new XYChart.Series<>();

            // Parse the input fields
            double xMin, xMax, yMin, yMax;

            try {
                xMin = Double.parseDouble(textFieldXMin.getText());
                xMax = Double.parseDouble(textFieldXMax.getText());
                yMin = Double.parseDouble(textFieldYMin.getText());
                yMax = Double.parseDouble(textFieldYMax.getText());

            } catch (NumberFormatException e) {
                labelError.setText("Invalid input.");
                return;
            }

            String expression = textFieldExpression.getText();

            // Evaluate the expression for each x value and add it to the series
            for (double x = xMin; x <= xMax; x += 0.1) {
                try {
                    double y = evaluate(expression, x);
                    if (y >= yMin && y <= yMax) {
                        series.getData().add(new XYChart.Data<>(x, y));
                    }
                } catch (Exception e) {
                    labelError.setText("Invalid expression.");
                    return;
                }
            }

            // Add the series to the chart
            chart.getData().add(series);
            labelError.setText("");
        }

        private double evaluate(String expression, double x) {
            // Evaluate the expression using a math library or parser
            // For this example, we will simply use Java's built-in ScriptEngine
            javax.script.ScriptEngine engine = new javax.script.ScriptEngineManager().getEngineByName("JavaScript");
            try {
                engine.put("x", x);
                return (double) engine.eval(expression);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}