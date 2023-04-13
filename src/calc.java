import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.ValueAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class calc extends Application {

    private TextField equationInput;
    private TextField xMinInput;
    private TextField xMaxInput;
    private TextField yMinInput;
    private TextField yMaxInput;
    private ScatterChart<Number, Number> chart;

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Create UI controls
        Label equationLabel = new Label("Equation:");
        equationInput = new TextField();
        Label xMinLabel = new Label("X Min:");
        xMinInput = new TextField("-10");
        Label xMaxLabel = new Label("X Max:");
        xMaxInput = new TextField("1000");
        Label yMinLabel = new Label("Y Min:");
        yMinInput = new TextField("-10");
        Label yMaxLabel = new Label("Y Max:");
        yMaxInput = new TextField("1000");
        Button plotButton = new Button("Plot");
        plotButton.setOnAction(e -> plotEquation());

        // Add controls to a layout
        HBox inputLayout = new HBox(10);
        inputLayout.setPadding(new Insets(10));
        inputLayout.getChildren().addAll(
                equationLabel, equationInput,
                xMinLabel, xMinInput,
                xMaxLabel, xMaxInput,
                yMinLabel, yMinInput,
                yMaxLabel, yMaxInput,
                plotButton);

        // Create chart
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        chart = new ScatterChart<>(xAxis, yAxis);
        chart.setAnimated(false);

        // Add chart to layout
        BorderPane root = new BorderPane();
        root.setTop(inputLayout);
        root.setCenter(chart);

        // Create scene and show stage
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void plotEquation() {
        // Clear existing data from chart
        chart.getData().clear();

        // Parse input values
        double xMin = Double.parseDouble(xMinInput.getText());
        double xMax = Double.parseDouble(xMaxInput.getText());
        double yMin = Double.parseDouble(yMinInput.getText());
        double yMax = Double.parseDouble(yMaxInput.getText());

        // Set axis ranges
        chart.getXAxis().setAutoRanging(false);
        ((ValueAxis<Number>) chart.getXAxis()).setLowerBound(xMin);
        ((ValueAxis<Number>) chart.getXAxis()).setUpperBound(xMax);
        chart.getYAxis().setAutoRanging(false);
        ((ValueAxis<Number>) chart.getYAxis()).setLowerBound(yMin);
        ((ValueAxis<Number>) chart.getYAxis()).setUpperBound(yMax);

        // Parse equation input
        String equation = equationInput.getText().trim();

        // Plot equation on chart
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        for (double x = xMin; x <= xMax; x += 0.1) {
            double y = evaluateEquation(equation, x);
            series.getData().add(new XYChart.Data<>(x, y));
        }
        series.getNode().setStyle("-fx-stroke: blue;");
        chart.getData().add(series);
    }

    private double evaluateEquation(String equation, double x) {
        // TODO: Implement equation parser and
        // We can use the built-in Java Script engine to evaluate the equation
        // First, we create a new instance of the ScriptEngineManager
        javax.script.ScriptEngineManager mgr = new javax.script.ScriptEngineManager();
        // Next, we create a new JavaScript engine
        javax.script.ScriptEngine engine = mgr.getEngineByName("JavaScript");
        double y = Double.NaN;
        try {
            // We use the engine to evaluate the equation for the given x value
            engine.eval("var x = " + x + ";");
            Object result = engine.eval(equation);
            if (result instanceof Number) {
                y = ((Number) result).doubleValue();
            }
        } catch (javax.script.ScriptException ex) {
            // If there's an error evaluating the equation, we'll just return NaN
            System.err.println("Error evaluating equation: " + equation);
            System.err.println(ex);
        }
        return y;
    }

    public static void main(String[] args) {
        launch(args);
    }
}