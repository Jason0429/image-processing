package view.gui;

import model.ImageInterface;
import model.Pixel;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class GUIView extends JFrame implements IView {
  private final Histogram histogram;
  private ImagePreview imagePreview;
  private JComboBox<String> commandDropdown;
  private JButton exportBtn;
  private JButton loadBtn;
  private JButton applyBtn;


  public GUIView() {
    this.setTitle("Image Processing");
    this.setSize(1000, 1000);

    Map<Color, Function<Pixel, Integer>> types = new HashMap<Color, Function<Pixel, Integer>>();
    types.put(Color.red, PixelReader::getRed);
    types.put(Color.green, PixelReader::getGreen);
    types.put(Color.blue, PixelReader::getBlue);
    types.put(Color.gray, PixelReader::getIntensity);
    this.histogram = new Histogram(types);
    this.imagePreview = new ImagePreview();
    this.setLayout(new GridLayout(1, 3));
    this.add(histogram);
    this.add(imagePreview);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.makeVisible();
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void update(ImageInterface img) {
    this.histogram.updateImage(img);
    this.imagePreview.updateImage(img);
  }

  @Override
  public String getSelectedQuery() {
    // TODO: should be getting the selected command
    return "";
  }

  @Override
  public void addFeatures(Features features) {

  }
}
