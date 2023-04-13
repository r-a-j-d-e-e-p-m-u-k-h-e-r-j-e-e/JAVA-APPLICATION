import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class YoutubeViewer extends Application {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 900;
    private static final String URL = "https://www.youtube.com/";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.load(URL);

        //webView.setZoom(getZoomFactor(WIDTH, HEIGHT));

        Scene scene = new Scene(webView, WIDTH, HEIGHT);

        primaryStage.setTitle("YouTube");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

     private double getZoomFactor(double width, double height) {
         double aspectRatio = width / height;
         double defaultAspectRatio = 16.0 / 9.0;
         return aspectRatio / defaultAspectRatio;
     }

    public void run() {
    }
}
