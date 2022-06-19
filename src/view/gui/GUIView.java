package view.gui;

import controller.query.*;
import model.ImageInterface;
import model.Pixel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class GUIView extends JFrame implements IView {
  private final Histogram histogram;
  private final ImagePreview imagePreview;
  private final JComboBox<String> commandDropdown;
  private final JButton exportBtn;
  private final JButton loadBtn;
  private final JButton applyBtn;


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
    this.commandDropdown = new JComboBox<String>(new String[]{"red-component", "green-component",
            "blue-component", "value-component", "luma-component", "intensity-component", "horizontal" +
            "-flip", "vertical-flip", "brighten", "gaussian-blur", "sharpen", "sepia"});
    this.exportBtn = new JButton();
    this.exportBtn.setText("Export");
    this.loadBtn = new JButton();
    this.loadBtn.setText("Load");
    this.applyBtn = new JButton();
    this.applyBtn.setText("Apply");
    this.setLayout(new GridLayout(2, 4));
    this.add(histogram);
    this.add(imagePreview);
    this.add(this.commandDropdown);
    this.add(exportBtn);
    this.add(loadBtn);
    this.add(applyBtn);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.makeVisible();
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void update(BufferedImage img) {
    this.histogram.updateImage(img);
    this.imagePreview.updateImage(img);
  }

  @Override
  public String getSelectedQuery() {
    return String.valueOf(this.commandDropdown.getSelectedItem());
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
        try {
          features.update(selectedFile.getAbsolutePath());
        } catch (IllegalArgumentException e) {
          JOptionPane.showMessageDialog(null, e.getMessage());
        }
      }
    });
    this.applyBtn.addActionListener((event) -> {
      String command = this.getSelectedQuery();
      features.processImage(command);
    });
  }
}
