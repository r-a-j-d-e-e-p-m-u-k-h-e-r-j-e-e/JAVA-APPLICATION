import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
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
        imageView.setFitHeight(100); // increase the height to make the button bigger
        imageView.setFitWidth(100); // increase the width to make the button bigger
        button1.setGraphic(imageView);
        button1.setMaxSize(100, 100);
        // button1.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        button1.setStyle("-fx-border-color: white; -fx-border-width: 0;");
        button1.setStyle("-fx-background-color: transparent;");
        // button1.setStyle("-fx-background-radius: 30px;");

        Label label = new Label("YouTube");
        label.setStyle("-fx-text-fill: white;"); // Set text color to white using CSS
        VBox vbox = new VBox();
        vbox.getChildren().addAll(imageView, label);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(5);
        button1.setGraphic(vbox);

        Button button2 = new Button("Button 2");
        Image buttonImage_2 = new Image("paint.png");
        ImageView imageView_2 = new ImageView(buttonImage_2);
        imageView_2.setPreserveRatio(true);
        imageView_2.setFitHeight(100); // increase the height to make the button bigger
        imageView_2.setFitWidth(100); // increase the width to make the button bigger
        button2.setGraphic(imageView_2);
        button2.setMaxSize(100, 100);
        // button2.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        button2.setStyle("-fx-border-color: white; -fx-border-width: 0;");
        button2.setStyle("-fx-background-color: transparent;");

        Label label1 = new Label("PixelArt");
        label1.setStyle("-fx-text-fill: white;"); // Set text color to white using CSS
        VBox vbox1 = new VBox();
        vbox1.getChildren().addAll(imageView_2, label1);
        vbox1.setAlignment(Pos.CENTER);
        vbox1.setSpacing(5);
        button2.setGraphic(vbox1);

        Button button3 = new Button("Button 3");
        Image buttonImage_3 = new Image("Calculator-icon.png");
        ImageView imageView_3 = new ImageView(buttonImage_3);
        imageView_3.setPreserveRatio(true);
        imageView_3.setFitHeight(100); // increase the height to make the button bigger
        imageView_3.setFitWidth(100); // increase the width to make the button bigger
        button3.setGraphic(imageView_3);
        button3.setMaxSize(100, 100);
        // button3.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        button3.setStyle("-fx-border-color: white; -fx-border-width: 0;");
        button3.setStyle("-fx-background-color: transparent;");

        Label label2 = new Label("Calculator");
        label2.setStyle("-fx-text-fill: white;"); // Set text color to white using CSS
        VBox vbox2 = new VBox();
        vbox2.getChildren().addAll(imageView_3, label2);
        vbox2.setSpacing(5);
        button3.setGraphic(vbox2);

        Button button4 = new Button("Button 4");
        Image buttonImage_4 = new Image("music.png");
        ImageView imageView_4 = new ImageView(buttonImage_4);
        imageView_4.setPreserveRatio(true);
        imageView_4.setFitHeight(100); // increase the height to make the button bigger
        imageView_4.setFitWidth(100); // increase the width to make the button bigger
        button4.setGraphic(imageView_4);
        button4.setMaxSize(100, 100);
        // button4.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        button4.setStyle("-fx-border-color: white; -fx-border-width: 0;");
        button4.setStyle("-fx-background-color: transparent");

        Label label3 = new Label("Music Generator");
        label3.setStyle("-fx-text-fill: white;"); // Set text color to white using CSS
        VBox vbox3 = new VBox();
        vbox3.getChildren().addAll(imageView_4, label3);
        vbox3.setAlignment(Pos.CENTER);
        vbox3.setSpacing(5);
        button4.setGraphic(vbox3);

        Button button5 = new Button("Button 5");
        Image buttonImage_5 = new Image("travel_planner.png");
        ImageView imageView_5 = new ImageView(buttonImage_5);
        imageView_5.setPreserveRatio(true);
        imageView_5.setFitHeight(100); // increase the height to make the button bigger
        imageView_5.setFitWidth(100); // increase the width to make the button bigger
        button5.setGraphic(imageView_5);
        button5.setMaxSize(100, 100); // increase the maximum size of the button
        button5.setStyle("-fx-border-color: white; -fx-border-width: 0;");
        button5.setStyle("-fx-background-color: transparent; -fx-background-radius: 30 30 30 30;");

        Label label4 = new Label("Travel Planner");
        label4.setStyle("-fx-text-fill: white;"); // Set text color to white using CSS
        VBox vbox4 = new VBox();
        vbox4.getChildren().addAll(imageView_5, label4);
        vbox4.setAlignment(Pos.CENTER);
        vbox4.setSpacing(5);
        button5.setGraphic(vbox4);

        // Create a layout for the buttons
        GridPane layout = new GridPane();
        layout.setHgap(95);
        layout.setVgap(70);
        layout.add(button1, 2, 2);
        layout.add(button2, 2, 4);
        layout.add(button3, 1, 4);
        layout.add(button4, 2, 5);
        layout.add(button5, 1, 5);

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
        double screenAspectRatio = Screen.getPrimary().getBounds().getWidth()
                / Screen.getPrimary().getBounds().getHeight();
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
