package view.gui;

import controller.gui.Features;
import model.CommandType;
import model.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;

public class ImageProcessingGUIViewImpl extends JFrame implements ImageProcessingGUIView {
  private final Histogram histogram;
  private final ImagePreview imagePreview;
  private final JComboBox<String> commandDropdown;
  private final JButton exportBtn;
  private final JButton loadBtn;
  private final JButton applyBtn;


  public ImageProcessingGUIViewImpl() {
    super("Image Processing");
    this.setSize(1000, 1000);
    this.setLayout(new GridLayout(2, 4));
    this.setResizable(true);

    this.histogram = new Histogram(Utils.getTypes());
    this.imagePreview = new ImagePreview();
    this.commandDropdown = new JComboBox<String>(Arrays.stream(Utils.getCommands())
            .map(CommandType::toString).toArray(String[]::new));
    this.exportBtn = new JButton();
    this.loadBtn = new JButton();
    this.applyBtn = new JButton();

    this.setButtonTexts();
    this.setActionCommands();
    this.addComponents();

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  private void addComponents() {
    this.add(this.histogram);
    this.add(this.imagePreview);
    this.add(this.commandDropdown);
    this.add(this.exportBtn);
    this.add(this.loadBtn);
    this.add(this.applyBtn);
  }

  private void setButtonTexts() {
    this.exportBtn.setText("Export");
    this.loadBtn.setText("Load");
    this.applyBtn.setText("Apply");
  }

  private void setActionCommands() {
    this.exportBtn.setActionCommand("save");
    this.loadBtn.setActionCommand("load");
    this.applyBtn.setActionCommand("apply");
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void updateImagePreview(BufferedImage img) {
    this.histogram.updateImage(img);
    this.imagePreview.updateImage(img);
  }

  @Override
  public void setListener(ActionListener listener) {
    this.exportBtn.addActionListener(listener);
    this.loadBtn.addActionListener(listener);
    this.applyBtn.addActionListener(listener);
  }

  @Override
  public String getSelectedQuery() {
    return String.valueOf(this.commandDropdown.getSelectedItem());
  }

  @Override
  public String chooseExportLocation() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Choose a save location");
    int returnValue = fileChooser.showSaveDialog(null);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      return fileChooser.getSelectedFile().getAbsolutePath();
    }
    return null;
  }

  @Override
  public String chooseLoadLocation() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Choose an image to load");
    int returnValue = fileChooser.showOpenDialog(null);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      return fileChooser.getSelectedFile().getAbsolutePath();
    }
    return null;
  }

  @Override
  public void showError(String message) {
    JOptionPane.showMessageDialog(null, message);
  }

//  @Override
//  public void addFeatures(Features features) {
//    this.exportBtn.addActionListener((event) -> {
//      JFileChooser fileChooser = new JFileChooser();
//      fileChooser.setDialogTitle("Choose a save location");
//      int returnValue = fileChooser.showSaveDialog(null);
//      if (returnValue == JFileChooser.APPROVE_OPTION) {
//        File selectedFile = fileChooser.getSelectedFile();
//        try {
//          features.exportImage(selectedFile.getAbsolutePath());
//        } catch (IllegalArgumentException e) {
//          JOptionPane.showMessageDialog(null, e.getMessage());
//        }
//      }
//    });
//    this.loadBtn.addActionListener((event) -> {
//      JFileChooser fileChooser = new JFileChooser();
//      int returnValue = fileChooser.showOpenDialog(null);
//      if (returnValue == JFileChooser.APPROVE_OPTION) {
//        File selectedFile = fileChooser.getSelectedFile();
//        try {
//          features.update(selectedFile.getAbsolutePath());
//        } catch (IllegalArgumentException e) {
//          JOptionPane.showMessageDialog(null, e.getMessage());
//        }
//      }
//    });
//    this.applyBtn.addActionListener((event) -> {
//      String command = this.getSelectedQuery();
//      try {
//        if (command.equals("brighten")) {
//          SpinnerModel spinnerModel = new SpinnerNumberModel(10, -255, 255, 1);
//          JSpinner spinner = new JSpinner(spinnerModel);
//          JScrollPane scrollPane = new JScrollPane(spinner);
//          scrollPane.requestFocusInWindow();
//          spinner.requestFocusInWindow();
//          scrollPane.setPreferredSize(new Dimension(50, 50));
//          JOptionPane.showMessageDialog(this, scrollPane,
//                  "Value", JOptionPane.PLAIN_MESSAGE);
//          spinner.getValue();
//          features.processImage(command, (int) spinnerModel.getValue());
//        }
//        features.processImage(command);
//      } catch (IllegalArgumentException e) {
//        JOptionPane.showMessageDialog(null, e.getMessage());
//      }
//    });
//  }

  @Override
  public int askForIntegerValue(int defaultValue, int min, int max, int increment) {
    SpinnerModel spinnerModel = new SpinnerNumberModel(defaultValue, min, max, increment);
    JSpinner spinner = new JSpinner(spinnerModel);
    JScrollPane scrollPane = new JScrollPane(spinner);
    scrollPane.requestFocusInWindow();
    spinner.requestFocusInWindow();
    scrollPane.setPreferredSize(new Dimension(50, 50));
    JOptionPane.showMessageDialog(this, scrollPane, "Enter Value", JOptionPane.PLAIN_MESSAGE);
    spinner.getValue();
    return (int) spinnerModel.getValue();
  }
}
