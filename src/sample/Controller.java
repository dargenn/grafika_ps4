package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Controller {
    @FXML
    public Slider redSlider;
    @FXML
    public Slider greenSlider;
    @FXML
    public Slider blueSlider;
    @FXML
    private Label fileNameLabel;
    @FXML
    private ImageView imageView;

    public void loadPicture(ActionEvent event) throws FileNotFoundException {
        Node node = (Node) event.getSource();
        Scene scene = node.getScene();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg")
        );
        File file = fileChooser.showOpenDialog(scene.getWindow());
        fileNameLabel.setText(file.getName());
        imageView.setImage(new Image(new FileInputStream(file)));
    }

    public void add() {
        Image image = imageView.getImage();
        PixelReader pixelReader = image.getPixelReader();
        WritableImage wImage = new WritableImage(
                (int) image.getWidth(),
                (int) image.getHeight());
        PixelWriter pixelWriter = wImage.getPixelWriter();

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color color = pixelReader.getColor(x, y);
                double red = color.getRed() + redSlider.getValue();
                double green = color.getGreen() + greenSlider.getValue();
                double blue = color.getBlue() + blueSlider.getValue();
                pixelWriter.setColor(x, y, new Color(red % 1, green % 1, blue % 1, 1));
            }
        }

        imageView.setImage(wImage);
    }

    public void subtract() {
        Image image = imageView.getImage();
        PixelReader pixelReader = image.getPixelReader();
        WritableImage wImage = new WritableImage(
                (int) image.getWidth(),
                (int) image.getHeight());
        PixelWriter pixelWriter = wImage.getPixelWriter();

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color color = pixelReader.getColor(x, y);
                double red = Math.abs(color.getRed() - redSlider.getValue());
                double green = Math.abs(color.getGreen() - greenSlider.getValue());
                double blue = Math.abs(color.getBlue() - blueSlider.getValue());
                pixelWriter.setColor(x, y, new Color(red % 1, green % 1, blue % 1, 1));
            }
        }

        imageView.setImage(wImage);
    }

    public void multiply() {
        Image image = imageView.getImage();
        PixelReader pixelReader = image.getPixelReader();
        WritableImage wImage = new WritableImage(
                (int) image.getWidth(),
                (int) image.getHeight());
        PixelWriter pixelWriter = wImage.getPixelWriter();

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color color = pixelReader.getColor(x, y);
                double red = color.getRed() * redSlider.getValue();
                double green = color.getGreen() * greenSlider.getValue();
                double blue = color.getBlue() * blueSlider.getValue();
                pixelWriter.setColor(x, y, new Color(red % 1, green % 1, blue % 1, 1));
            }
        }

        imageView.setImage(wImage);
    }

    public void divide() {
        Image image = imageView.getImage();
        PixelReader pixelReader = image.getPixelReader();
        WritableImage wImage = new WritableImage(
                (int) image.getWidth(),
                (int) image.getHeight());
        PixelWriter pixelWriter = wImage.getPixelWriter();

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color color = pixelReader.getColor(x, y);
                double red = color.getRed() / redSlider.getValue();
                double green = color.getGreen() / greenSlider.getValue();
                double blue = color.getBlue() / blueSlider.getValue();
                pixelWriter.setColor(x, y, new Color(red % 1, green % 1, blue % 1, 1));
            }
        }

        imageView.setImage(wImage);
    }

    public void changeBrightness() {
        Image image = imageView.getImage();
        PixelReader pixelReader = image.getPixelReader();
        WritableImage wImage = new WritableImage(
                (int) image.getWidth(),
                (int) image.getHeight());
        PixelWriter pixelWriter = wImage.getPixelWriter();

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color color = pixelReader.getColor(x, y);
                color = color.brighter();
                pixelWriter.setColor(x, y, color);
            }
        }

        imageView.setImage(wImage);
    }

    public void toGrayScale1() {
        Image image = imageView.getImage();
        PixelReader pixelReader = image.getPixelReader();
        WritableImage wImage = new WritableImage(
                (int) image.getWidth(),
                (int) image.getHeight());
        PixelWriter pixelWriter = wImage.getPixelWriter();

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color color = pixelReader.getColor(x, y);
                double red = color.getRed();
                double green = color.getGreen();
                double blue = color.getBlue();
                double gray = 0.21 * red + 0.71 * green + 0.07 * blue;
                pixelWriter.setColor(x, y, new Color(gray, gray, gray, 1));
            }
        }

        imageView.setImage(wImage);
    }

    public void toGrayScale2() {
        Image image = imageView.getImage();
        PixelReader pixelReader = image.getPixelReader();
        WritableImage wImage = new WritableImage(
                (int) image.getWidth(),
                (int) image.getHeight());
        PixelWriter pixelWriter = wImage.getPixelWriter();

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int pixel = pixelReader.getArgb(x, y);

                int red = ((pixel >> 16) & 0xff);
                int green = ((pixel >> 8) & 0xff);
                int blue = (pixel & 0xff);

                int grayLevel = (int) (0.299 * (double) red + 0.587 * (double) green + 0.114 * (double) blue) / 3;
                int gray = (grayLevel << 16) + (grayLevel << 8) + grayLevel;

                pixelWriter.setArgb(x, y, -gray);
            }
        }

        imageView.setImage(wImage);
    }
}
