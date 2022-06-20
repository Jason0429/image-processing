package view.gui;

import controller.gui.Features;
import model.CommandType;
import model.Pixel;
import model.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
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
    this.setResizable(true);
    Map<Color, Function<Pixel, Integer>> types = new HashMap<Color, Function<Pixel, Integer>>();
    types.put(new Color(255, 0, 0, 50), PixelReader::getRed);
    types.put(new Color(0, 255, 0, 50), PixelReader::getGreen);
    types.put(new Color(0, 0, 255, 50), PixelReader::getBlue);
    types.put(new Color(50, 50, 50, 50), PixelReader::getIntensity);
    this.histogram = new Histogram(types);
    this.imagePreview = new ImagePreview();
    this.commandDropdown = new JComboBox<String>(
            Arrays.stream(Utils.getCommands()).map(CommandType::toString).toArray(String[]::new));
    this.exportBtn = new JButton();
    this.exportBtn.setText("Export");
    this.loadBtn = new JButton();
    this.loadBtn.setText("Load");
    this.applyBtn = new JButton();
    this.applyBtn.setText("Apply");
    this.setLayout(new GridLayout(2, 4));
    this.addComponents();
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  private void addComponents() {
    this.add(histogram);
    this.add(imagePreview);
    this.add(this.commandDropdown);
    this.add(exportBtn);
    this.add(loadBtn);
    this.add(applyBtn);
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
      try {
        if (command.equals("brighten")) {
          SpinnerModel spinnerModel = new SpinnerNumberModel(10, -255, 255, 1);
          JSpinner spinner = new JSpinner(spinnerModel);
          JScrollPane scrollPane = new JScrollPane(spinner);
          scrollPane.requestFocusInWindow();
          spinner.requestFocusInWindow();
          scrollPane.setPreferredSize(new Dimension(50, 50));
          JOptionPane.showMessageDialog(this, scrollPane,
                  "Value", JOptionPane.PLAIN_MESSAGE);
          spinner.getValue();
          features.processImage(command, (int) spinnerModel.getValue());
        }
        features.processImage(command);
      } catch (IllegalArgumentException e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
      }
    });
  }
}
