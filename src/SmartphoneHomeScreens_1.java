import javafx.application.Application;
//import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
//import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class SmartphoneHomeScreens_1 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create six buttons
        Button button1 = new Button();
        Image buttonImage = new Image("youtube_icon.png");
        ImageView imageView = new ImageView(buttonImage);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(70);
        button1.setGraphic(imageView);
        button1.setMaxSize(50, 50);
        button1.setStyle("-fx-border-color: white; -fx-border-width: 0;");
        button1.setStyle("-fx-background-color: transparent;");
       // button1.setStyle("-fx-background-radius: 30px;");
        
        Button button2 = new Button("Button 2");
        Button button3 = new Button("Button 3");
        Button button4 = new Button("Button 4");
        Button button5 = new Button("Button 5");
        Button button6 = new Button("Button 6");

        // Create a layout for the buttons
        GridPane layout = new GridPane();
        layout.setHgap(20);
        layout.setVgap(20);
        layout.add(button1, 0, 0);
        layout.add(button2, 1, 0);
        layout.add(button3, 2, 0);
        layout.add(button4, 0, 1);
        layout.add(button5, 1, 1);
        layout.add(button6, 2, 1);

        // Set the background image
        Image backgroundImage = new Image("background_1.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);

        // Create a pane for the layout and add the background image
        Pane pane = new Pane();
        pane.getChildren().add(backgroundImageView);
        pane.getChildren().add(layout);

        // Create a scene with the pane and set the resolution
        Scene scene = new Scene(new StackPane(pane), 500, 900);

        // Set the stage title and show the scene
        primaryStage.setTitle("Smartphone Home Screen");
        primaryStage.setScene(scene);

        // Maintain aspect ratio of 9:16
        double screenAspectRatio = Screen.getPrimary().getBounds().getWidth() / Screen.getPrimary().getBounds().getHeight();
        double targetWidth = scene.getHeight() * 9 / 16;
        double targetHeight = scene.getWidth() / 9 * 16;

        if (screenAspectRatio > 9 / 16) {
            // Portrait orientation
            double scaleFactor = scene.getWidth() / targetWidth;
            backgroundImageView.setFitWidth(scene.getWidth());
            backgroundImageView.setFitHeight(targetHeight * scaleFactor);
            primaryStage.setWidth(scene.getWidth());
            primaryStage.setHeight(targetHeight * scaleFactor);
        } else {
            // Landscape orientation
            double scaleFactor = scene.getHeight() / targetHeight;
            backgroundImageView.setFitHeight(scene.getHeight());
            backgroundImageView.setFitWidth(targetWidth * scaleFactor);
            primaryStage.setWidth(targetWidth * scaleFactor);
            primaryStage.setHeight(scene.getHeight());
        }

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
