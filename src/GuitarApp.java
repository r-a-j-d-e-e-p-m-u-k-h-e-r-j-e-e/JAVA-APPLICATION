import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class GuitarApp extends Application {

    private static final int WINDOW_WIDTH = 1006;
    private static final int WINDOW_HEIGHT = 450;
    private static final String TITLE = "Guitar App";

    private static final String[] STRING_NOTES = {"E", "A", "D", "G", "B", "E"};
    private static final String[] SOUND_FILES = {"e.wav", "a.wav", "d.wav", "g.wav", "b.wav", "e_high.wav"};

    @Override
    public void start(Stage stage) {
        // Create buttons for guitar strings
        Button[][] fretButtons = new Button[6][12];
        for (int i = 0; i < fretButtons.length; i++) {
            for (int j = 0; j < fretButtons[i].length; j++) {
                final int stringIndex = i;
                final int fretIndex = j;
                fretButtons[i][j] = new Button();
                fretButtons[i][j].setPrefSize(1006 / 12, 307 / 6); // Set the size of each button to match the grid
                //fretButtons[i][j].setStyle("-fx-background-color: brown");
                fretButtons[i][j].setStyle("-fx-background-color: transparent");
                fretButtons[i][j].setOnAction(event -> playSound(SOUND_FILES[stringIndex]));
            }
        }

        // Create layout for guitar fretboard
        GridPane fretboardLayout = new GridPane();
        fretboardLayout.setHgap(1);
        fretboardLayout.setVgap(1);
        fretboardLayout.setPadding(new Insets(60, 0, 0, 0)); // Adjust padding to move fretboard up
        for (int i = 0; i < fretButtons.length; i++) {
            for (int j = 0; j < fretButtons[i].length; j++) {
                fretboardLayout.add(fretButtons[i][j], j, i);
            }
        }

        // Create layout for guitar strings
        VBox stringLayout = new VBox(10);
        for (int i = 0; i < 12; i++) {
            HBox column = new HBox(10);
            for (int j = 0; j < STRING_NOTES.length; j++) {
                Button stringButton = new Button(STRING_NOTES[j]);
                stringButton.setPrefSize(1006 / 12, 30); // Set the size of each button to match the grid
                column.getChildren().add(stringButton);
            }
            stringLayout.getChildren().add(column);
        }

        // Create layout for guitar image and fretboard
        StackPane guitarLayout = new StackPane();
        ImageView guitarImage = new ImageView(new Image(getClass().getResourceAsStream("guitar.png")));
        guitarLayout.getChildren().addAll(guitarImage, fretboardLayout);
        guitarLayout.setPadding(new Insets(20, 0, 0, 0)); // Adjust padding to move guitar image down

        // Create main layout for window
        HBox mainLayout = new HBox(20);
        mainLayout.getChildren().addAll(guitarLayout,stringLayout);

        // Create layout for guitar strings buttons
        VBox stringButtonsLayout = new VBox(10);
        for (int i = 0; i < 12; i++) {
        for (int j = 0; j < STRING_NOTES.length; j++) {
        Button stringButton = new Button(STRING_NOTES[j] + " " + i);
        stringButton.setPrefSize(1006 / 12, 25); // Set the size of each button to match the grid
        stringButtonsLayout.getChildren().add(stringButton);
        }
        }
        
        // Add the guitar strings buttons layout to the main layout
        mainLayout.getChildren().add(stringButtonsLayout);
        
        Scene scene = new Scene(mainLayout, WINDOW_WIDTH, WINDOW_HEIGHT);
        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.show();
        }
        
        private void playSound(String soundFile) {
        Media sound = new Media(getClass().getResource(soundFile).toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
        }
        
        public static void main(String[] args) {
        launch(args);
        }
    }        
