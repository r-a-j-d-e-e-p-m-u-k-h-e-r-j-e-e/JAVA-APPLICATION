import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WeatherApp extends Application {

    private static final String API_KEY = "31a997a2f608bf8168b5c158dfdbb54b";
    private static final String WEATHER_API_URL = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=%s";

    private TextField locationTextField;
    private Button searchButton;
    private Label temperatureLabel;
    private Label humidityLabel;
    private Label windLabel;
    private Label locationLabel;
    private ImageView weatherIconView;
    private Label timeLabel;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        // Top section: search bar and search button
        HBox searchBox = new HBox();
        searchBox.setAlignment(Pos.CENTER);
        searchBox.setSpacing(10);
        searchBox.setPadding(new Insets(10));
        locationTextField = new TextField();
        locationTextField.setPromptText("Enter location");
        searchButton = new Button("Search");
        searchButton.setOnAction(event -> searchWeather());
        searchBox.getChildren().addAll(locationTextField, searchButton);
        root.setTop(searchBox);

        // Center section: weather details and icon
        VBox weatherBox = new VBox();
        weatherBox.setAlignment(Pos.CENTER);
        weatherBox.setSpacing(10);
        temperatureLabel = new Label();
        humidityLabel = new Label();
        windLabel = new Label();
        locationLabel = new Label();
        weatherIconView = new ImageView();
        weatherBox.getChildren().addAll(weatherIconView, temperatureLabel, humidityLabel, windLabel, locationLabel);
        root.setCenter(weatherBox);

        // Bottom section: live time
        HBox timeBox = new HBox();
        timeBox.setAlignment(Pos.CENTER);
        timeBox.setSpacing(10);
        timeBox.setPadding(new Insets(10));
        timeLabel = new Label();
        timeBox.getChildren().add(timeLabel);
        root.setBottom(timeBox);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void searchWeather() {
        String location = locationTextField.getText();
        String units = "metric"; // or "imperial" for Fahrenheit
        String weatherUrl = String.format(WEATHER_API_URL, location, API_KEY, units);
        try {
            URL url = new URL(weatherUrl);
            URLConnection connection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            parseWeatherJson(response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseWeatherJson(String json) {
        // TODO: parse the JSON response and update the UI elements with weather data
    }

    public static void main(String[] args) {
        launch(args);
    }
}
