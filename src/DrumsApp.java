import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DrumsApp extends Application {

    @Override
    public void start(Stage stage) {
        // Load the drum image
        Image image = new Image("https://help.apple.com/assets/626AF41951D72B702C4A6AE1/626AF41C51D72B702C4A6AF7/en_US/8b6048994107944c8fc6929cda642c36.png");

        // Create an image view for the drum image
        ImageView imageView = new ImageView(image);

        // Create a grid pane to hold the drum buttons
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Create the drum buttons
        Button bassButton = createDrumButton(Color.RED, "Bass");
        Button snareButton = createDrumButton(Color.BLUE, "Snare");
        Button hiHatButton = createDrumButton(Color.YELLOW, "Hi-Hat");
        Button tom1Button = createDrumButton(Color.GREEN, "Tom 1");
        Button tom2Button = createDrumButton(Color.ORANGE, "Tom 2");
        Button crashButton = createDrumButton(Color.PURPLE, "Crash");
        Button rideButton = createDrumButton(Color.WHITE, "Ride");

        // Add the drum buttons to the grid pane
        gridPane.add(bassButton, 1, 2);
        gridPane.add(snareButton, 2, 2);
        gridPane.add(hiHatButton, 3, 2);
        gridPane.add(tom1Button, 1, 1);
        gridPane.add(tom2Button, 3, 1);
        gridPane.add(crashButton, 1, 3);
        gridPane.add(rideButton, 3, 3);

        // Add the image view and grid pane to a stack pane
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(imageView, gridPane);

        // Adjust the positions of the image view and grid pane
        StackPane.setAlignment(imageView, Pos.CENTER);
        StackPane.setAlignment(gridPane, Pos.CENTER);
        StackPane.setMargin(gridPane, new Insets(200, 0, 0, 0));

        // Create a scene with the stack pane as the root node
        Scene scene = new Scene(stackPane);
        scene.setFill(null); // make the background transparent
        stage.setScene(scene);
        stage.setTitle("Acoustic Drum Set");

        // Set the window size to the dimensions of the image
        stage.setWidth(image.getWidth());
        stage.setHeight(image.getHeight());

        stage.show();

            // Load the drum sound files
            // AudioClip bassSound = new AudioClip(getClass().getResource("/bass.wav").toString());
            // AudioClip snareSound = new AudioClip(getClass().getResource("/snare.wav").toString());
            // AudioClip hiHatSound = new AudioClip(getClass().getResource("/hihat.wav").toString());
            // AudioClip tom1Sound = new AudioClip(getClass().getResource("/tom1.wav").toString());
            // AudioClip tom2Sound = new AudioClip(getClass().getResource("/tom2.wav").toString());
            // AudioClip crashSound = new AudioClip(getClass().getResource("/crash.wav").toString());
            // AudioClip rideSound = new AudioClip(getClass().getResource("/ride.wav").toString());

            // // Associate each drum button with its corresponding sound file
            // bassButton.setOnAction(event -> bassSound.play());
            // snareButton.setOnAction(event -> snareSound.play());
            // hiHatButton.setOnAction(event -> hiHatSound.play());
            // tom1Button.setOnAction(event -> tom1Sound.play());
            // tom2Button.setOnAction(event -> tom2Sound.play());
            // crashButton.setOnAction(event -> crashSound.play());
            // rideButton.setOnAction(event -> rideSound.play());
}

// Helper method to create a drum button with the given color and text
private Button createDrumButton(Color color, String text) {
    Button button = new Button(text);
    button.setStyle("-fx-background-color: " + color.toString().replace("0x", "#") + ";");
    button.setPrefSize(100, 100);
    return button;
}

public static void main(String[] args) {
    launch(args);
}
}
