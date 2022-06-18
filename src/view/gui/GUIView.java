package view.gui;

import model.ImageInterface;
import model.Pixel;

import javax.swing.*;
import java.awt.*;
import java.io.File;
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
    this.exportBtn = new JButton();
    this.exportBtn.setText("Export");
    this.loadBtn = new JButton();
    this.loadBtn.setText("Load");
    this.setLayout(new GridLayout(1, 4));
    this.add(histogram);
    this.add(imagePreview);
    this.add(exportBtn);
    this.add(loadBtn);
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
    this.exportBtn.addActionListener((event) -> {
      JFileChooser fileChooser = new JFileChooser();
      fileChooser.setDialogTitle("Choose a save location");
      int returnValue = fileChooser.showSaveDialog(null);
      if (returnValue == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();
        try {
          features.exportImage(selectedFile.getAbsolutePath());
        } catch (IllegalArgumentException e) {
          JOptionPane.showMessageDialog(null, e.getMessage());
        }
      }
    });
    this.loadBtn.addActionListener((event) -> {
      JFileChooser fileChooser = new JFileChooser();
      int returnValue = fileChooser.showOpenDialog(null);
      if (returnValue == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();
        features.update(selectedFile.getAbsolutePath());
      }
    });
  }
}
