import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class MusicalInstruments extends Application {

    private static final String PIANO_IMAGE_PATH = "Piano.jpg";
    private static final String GUITAR_IMAGE_PATH = "guitar.jpg";
    private static final String DRUMS_IMAGE_PATH = "Drums.png";

    private Button pianoButton;
    private Button guitarButton;
    private Button drumsButton;

    @Override
    public void start(Stage stage) {

        // Create buttons with images and text
        pianoButton = createButtonWithImageAndText(PIANO_IMAGE_PATH, "Piano");
        guitarButton = createButtonWithImageAndText(GUITAR_IMAGE_PATH, "Guitar");
        drumsButton = createButtonWithImageAndText(DRUMS_IMAGE_PATH, "Drums");

        // Set button colors
        pianoButton.setStyle("-fx-background-color: white;");
        guitarButton.setStyle("-fx-background-color: #d3d3d3;");
        drumsButton.setStyle("-fx-background-color: white;");

        // Add buttons to VBox
        VBox vbox = new VBox();
        vbox.setSpacing(20);
        vbox.setPadding(new Insets(20));
        vbox.getChildren().addAll(pianoButton, guitarButton, drumsButton);

        // Create region to fill extra space
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        // Add spacer to VBox
        vbox.getChildren().add(spacer);

        // Create scene and show stage
        Scene scene = new Scene(vbox, 500, 900);
        stage.setTitle("Musical Instruments");
        stage.setScene(scene);
        stage.show();

        // Bind button dimensions to VBox dimensions
        pianoButton.prefWidthProperty().bind(vbox.widthProperty());
        guitarButton.prefWidthProperty().bind(vbox.widthProperty());
        drumsButton.prefWidthProperty().bind(vbox.widthProperty());

        pianoButton.prefHeightProperty().bind(vbox.heightProperty().divide(4));
        guitarButton.prefHeightProperty().bind(vbox.heightProperty().divide(4));
        drumsButton.prefHeightProperty().bind(vbox.heightProperty().divide(4));
    }

    // Helper method to create a button with an image and text
    private Button createButtonWithImageAndText(String imagePath, String buttonText) {
        Button button = new Button();
        button.setPrefSize(100, 120);
        button.setAlignment(Pos.CENTER);
        Image image = new Image(getClass().getResourceAsStream(imagePath));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(80);
        imageView.setFitHeight(80);
        button.setGraphic(imageView);
        button.setText(buttonText);
        button.setTextAlignment(TextAlignment.CENTER);
        button.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        return button;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
