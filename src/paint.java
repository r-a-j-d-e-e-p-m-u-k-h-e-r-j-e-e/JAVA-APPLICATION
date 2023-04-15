import java.io.File;
import java.io.IOException;
import java.util.Optional;
import javax.imageio.ImageIO;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class paint extends Application {
    private static final int change = 1;
    String slectedShape = "";
    String slectedDesign = "";
    int shapeLength = 25;
    private static final int changedShepeLength = 25;
    private static final int shapeWidth = 5;
    private static final int CANVAS_SIZE = 450;
    private static final int PIXEL_SIZE = 6 / change;
    private static final int NUM_PIXELS = (CANVAS_SIZE + 200) / PIXEL_SIZE;
    private Color currentColor = Color.BLACK;
    private int brushRadius = 1 * change;
    Color previousColor = currentColor;
    int eraserOnOrOff = 1;

    @Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(CANVAS_SIZE, CANVAS_SIZE + 200);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, CANVAS_SIZE, CANVAS_SIZE + 200);

        canvas.setOnMousePressed(event -> handleMousePress(event, gc));
        canvas.setOnMouseDragged(event -> handleMouseDrag(event, gc));

        ComboBox<String> colorComboBox = new ComboBox<>(FXCollections.observableArrayList("Color", "BLACK", "BLUE",
                "GRAY", "GREEN", "MAGENTA", "ORANGE", "RED", "VIOLET", "WHITE", "YELLOW"));
        ComboBox<String> sizeComboBox = new ComboBox<>(
                FXCollections.observableArrayList("Size", "Small", "Medium", "Large"));
        ComboBox<String> shapeComboBox = new ComboBox<>(
                FXCollections.observableArrayList("Draw", "Square", "Circle", "Triangle", "Star"));
        ComboBox<String> designComboBox = new ComboBox<>(
                FXCollections.observableArrayList("Design", "Regular", "Grid", "Speckled"));

        sizeComboBox.setOnAction(event -> {
            String selectedSize = sizeComboBox.getValue();
            sizeComboBox.getItems().remove("Size");
            if ("Small".equals(selectedSize)) {
                brushRadius = 1 * change;
                shapeLength = changedShepeLength;
            } else if ("Medium".equals(selectedSize)) {
                brushRadius = 2 * change;
                shapeLength = changedShepeLength * 2;
            } else if ("Large".equals(selectedSize)) {
                brushRadius = 4 * change;
                shapeLength = changedShepeLength * 4;
            }
        });
        colorComboBox.setOnAction(event -> {
            colorComboBox.getItems().remove("Color");
            currentColor = Color.valueOf(colorComboBox.getValue());
        });

        shapeComboBox.setOnAction(event -> {
            designComboBox.getSelectionModel().selectFirst();
            slectedShape = shapeComboBox.getValue();
            designComboBox.getSelectionModel().select("Regular");

        });

        designComboBox.setOnAction(event -> {
            designComboBox.getItems().remove("Design");
            slectedDesign = designComboBox.getValue();
            // shapeComboBox.getSelectionModel().select("Regular");

        });
        colorComboBox.getSelectionModel().selectFirst();
        sizeComboBox.getSelectionModel().selectFirst();
        shapeComboBox.getSelectionModel().selectFirst();
        designComboBox.getSelectionModel().selectFirst();
        Button clearButton = new Button("Clear");
        Button eraserButton = new Button("Eraser");

        clearButton.setOnAction(event -> {
            gc.setFill(Color.WHITE);
            gc.fillRect(0, 0, CANVAS_SIZE, CANVAS_SIZE + 200);
        });

        eraserButton.setOnAction(event -> {
            if ((eraserOnOrOff % 2) == 1) {
                previousColor = currentColor;
                currentColor = Color.WHITE;
                colorComboBox.getSelectionModel().select("WHITE");
                shapeComboBox.getSelectionModel().select("Draw");
                designComboBox.getSelectionModel().select("Regular");
            }
            if ((eraserOnOrOff % 2) == 0) {
                currentColor = previousColor;
                colorComboBox.getSelectionModel().selectFirst();
                shapeComboBox.getSelectionModel().select("Draw");
            }
            eraserOnOrOff++;
        });

        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> {
            String folderName = "PaintSaves";
            File folder = new File(folderName);

            if (!folder.exists()) {
                folder.mkdir();
            }
            FileChooser fileChooser = new FileChooser();

            String currentDirectory = System.getProperty("user.dir");
            File initialDirectory = new File(currentDirectory, folderName);
            fileChooser.setInitialDirectory(initialDirectory);

            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Paint");
            dialog.setHeaderText("Enter a name for the image");
            dialog.setContentText("File Name:");
            Optional<String> result = dialog.showAndWait();

            result.ifPresent(input -> {
                String fileName = input + ".png";
                File file = new File(initialDirectory, fileName);
                if (file != null) {
                    try {
                        ImageIO.write(SwingFXUtils.fromFXImage(canvas.snapshot(null, null), null), "png", file);
                        System.out.println("Image saved: " + file.getAbsolutePath());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        });

        BorderPane root = new BorderPane(canvas);
        HBox buttonBox1 = new HBox(7);
        HBox buttonBox2 = new HBox(7);

        buttonBox1.setAlignment(Pos.CENTER);
        buttonBox2.setAlignment(Pos.CENTER);
        buttonBox1.getChildren().addAll(colorComboBox, sizeComboBox, shapeComboBox, designComboBox);
        buttonBox2.getChildren().addAll(eraserButton, clearButton, saveButton);
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(buttonBox1, buttonBox2);
        root.setBottom(vbox);

        Scene scene = new Scene(root, 500, 850);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Pixel Art Editor");
        primaryStage.show();
    }

    private void handleMousePress(MouseEvent event, GraphicsContext gc) {
        int row = (int) (event.getY() / PIXEL_SIZE);
        int column = (int) (event.getX() / PIXEL_SIZE);
        if ("Draw".equals(slectedShape)) {
            drawPixels(gc, row, column);
        } else if ("Circle".equals(slectedShape)) {
            drawCircle(gc, row, column);
            // java.util.concurrent.TimeUnit.SECONDS.sleep((long) 0.002);
        } else if ("Square".equals(slectedShape)) {
            drawSquare(gc, row, column);
            // java.util.concurrent.TimeUnit.SECONDS.sleep((long) 0.002);
        } else if ("Triangle".equals(slectedShape)) {
            drawTriangle(gc, row, column);
            // java.util.concurrent.TimeUnit.SECONDS.sleep((long) 0.002);
        } else if ("Star".equals(slectedShape)) {
            drawStar(gc, row, column);
            // java.util.concurrent.TimeUnit.SECONDS.sleep((long) 0.002);
        } else {
            drawPixels(gc, row, column);
        }
    }

    private void drawStar(GraphicsContext gc, int row, int column) {
        int x = column * PIXEL_SIZE;
        int y = row * PIXEL_SIZE;
        double outerCircleRadius = shapeLength;
        double innerCircleRadius = shapeLength / 3;
        double[] yPoints = { y - outerCircleRadius, y - (innerCircleRadius * Math.cos(0.2 * Math.PI)),
                y - (outerCircleRadius * Math.cos(0.4 * Math.PI)), y - (innerCircleRadius * Math.cos(0.6 * Math.PI)),
                y - (outerCircleRadius * Math.cos(0.8 * Math.PI)), y - (innerCircleRadius * Math.cos(Math.PI)),
                y - (outerCircleRadius * Math.cos(1.2 * Math.PI)), y - (innerCircleRadius * Math.cos(1.4 * Math.PI)),
                y - (outerCircleRadius * Math.cos(1.6 * Math.PI)), y - (innerCircleRadius * Math.cos(1.8 * Math.PI)) };
        double[] xPoints = { x, x - (innerCircleRadius * Math.sin(0.2 * Math.PI)),
                x - (outerCircleRadius * Math.sin(0.4 * Math.PI)), x - (innerCircleRadius * Math.sin(0.6 * Math.PI)),
                x - (outerCircleRadius * Math.sin(0.8 * Math.PI)), x - (innerCircleRadius * Math.sin(Math.PI)),
                x - (outerCircleRadius * Math.sin(1.2 * Math.PI)), x - (innerCircleRadius * Math.sin(1.4 * Math.PI)),
                x - (outerCircleRadius * Math.sin(1.6 * Math.PI)), x - (innerCircleRadius * Math.sin(1.8 * Math.PI)) };
        // gc.setStroke(currentColor);
        gc.setLineWidth(shapeWidth);
        gc.setStroke(currentColor);
        // gc.strokePolygon(xPoints, yPoints, 3);
        gc.strokePolygon(xPoints, yPoints, 10);

    }

    private void drawSquare(GraphicsContext gc, int row, int column) {
        int x = column * PIXEL_SIZE;
        int y = row * PIXEL_SIZE;
        double[] yPoints = { y, y, y - shapeLength, y - shapeLength };
        double[] xPoints = { x, x + shapeLength, x + shapeLength, x };
        // gc.setStroke(currentColor);
        gc.setLineWidth(shapeWidth);
        gc.setStroke(currentColor);
        // gc.strokePolygon(xPoints, yPoints, 3);
        gc.strokePolygon(xPoints, yPoints, 4);
    }

    private void drawTriangle(GraphicsContext gc, int row, int column) {
        int x = column * PIXEL_SIZE;
        int y = row * PIXEL_SIZE;
        double[] yPoints = { y, y, y - (shapeLength * Math.sqrt(3) * 0.6) };
        double[] xPoints = { x, x + shapeLength, x + (shapeLength / 2) };
        gc.setLineWidth(shapeWidth);
        gc.setStroke(currentColor);
        gc.strokePolygon(xPoints, yPoints, 3);
    }

    private void drawCircle(GraphicsContext gc, int row, int column) {
        int xCenter = column * PIXEL_SIZE + PIXEL_SIZE / 2;
        int yCenter = row * PIXEL_SIZE + PIXEL_SIZE / 2;
        gc.setStroke(currentColor);
        gc.setLineWidth(shapeWidth);
        gc.strokeOval(xCenter - shapeLength, yCenter - shapeLength, shapeLength * 2, shapeLength * 2);
    }

    private void handleMouseDrag(MouseEvent event, GraphicsContext gc) {
        // if ("Draw".equals(slectedShape)){
        int row = (int) (event.getY() / PIXEL_SIZE);
        int column = (int) (event.getX() / PIXEL_SIZE);
        // if (row % (PIXEL_SIZE * brushRadius) == 0 || column % (PIXEL_SIZE *
        // brushRadius) == 0){
        drawPixels(gc, row, column);
        // }
        // }
    }

    private void drawPixels(GraphicsContext gc, int row, int column) {
        // int k = 0;

        for (int i = row - brushRadius; i <= row + brushRadius; i++) {
            for (int j = column - brushRadius; j <= column + brushRadius; j++) {
                if (i >= 0 && i < NUM_PIXELS && j >= 0 && j < NUM_PIXELS) {
                    double x = j * PIXEL_SIZE;
                    double y = i * PIXEL_SIZE;
                    gc.setFill(currentColor);
                    if ("Grid".equals(slectedDesign)) {
                        if (x % (PIXEL_SIZE * brushRadius * 3) == 0 || y % (PIXEL_SIZE * brushRadius * 3) == 0) {
                            gc.fillRect(x, y, PIXEL_SIZE, PIXEL_SIZE);
                        }
                    } else if ("Speckled".equals(slectedDesign)) {
                        if (i < NUM_PIXELS / 5 || j < NUM_PIXELS / 5 || NUM_PIXELS * 0.9 < j || NUM_PIXELS * 0.9 < i) {
                            if (Math.pow(x, y) % (PIXEL_SIZE * brushRadius) == 0
                                    || Math.pow(y, x) % (PIXEL_SIZE * brushRadius) == 0) {
                                gc.fillRect(x, y, PIXEL_SIZE, PIXEL_SIZE);
                            }
                        } else {
                            if (Math.pow(x / 100, y) % (PIXEL_SIZE * brushRadius) == 0
                                    || Math.pow(y / 100, x) % (PIXEL_SIZE * brushRadius) == 0) {
                                gc.fillRect(x, y, PIXEL_SIZE, PIXEL_SIZE);
                            }
                        }
                    } else {
                        gc.fillRect(x, y, PIXEL_SIZE, PIXEL_SIZE);
                    }
                    // k++;

                }
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}