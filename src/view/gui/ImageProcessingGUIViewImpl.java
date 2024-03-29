package view.gui;

import controller.gui.Features;
import model.CommandType;
import model.Utils;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JScrollPane;
import javax.swing.SpinnerModel;

import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.prefs.Preferences;

/**
 * Implementation of the GUI view for the Image Processing Program.
 */
public class ImageProcessingGUIViewImpl extends JFrame implements ImageProcessingGUIView {
  private final Histogram histogram;
  private final ImagePreview imagePreview;
  private final JComboBox<String> commandDropdown;
  private final JButton exportBtn;
  private final JButton loadBtn;
  private final JButton applyBtn;

  /**
   * Constructs a new ImageProcessingGUIView.
   */
  public ImageProcessingGUIViewImpl() {
    super("Image Processing");
    this.setSize(1000, 1000);
    this.setLayout(new GridLayout(2, 4));
    this.setResizable(true);

    this.histogram = new Histogram(Utils.getTypes());
    this.imagePreview = new ImagePreview();
    this.commandDropdown = new JComboBox<String>(Arrays.stream(CommandType.values())
            .map(CommandType::toString).toArray(String[]::new));
    this.exportBtn = new JButton();
    this.loadBtn = new JButton();
    this.applyBtn = new JButton();

    this.setButtonTexts();
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
  public String getSelectedQuery() {
    return String.valueOf(this.commandDropdown.getSelectedItem());
  }

  @Override
  public String chooseExportLocation() {
    Preferences prefs = Preferences.userRoot().node(getClass().getName());
    JFileChooser fileChooser = new JFileChooser(prefs.get("LAST_USED_FOLDER",
            new File(".").getAbsolutePath()));
    fileChooser.setDialogTitle("Choose a save location");
    int returnValue = fileChooser.showSaveDialog(null);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      prefs.put("LAST_USED_FOLDER", fileChooser.getSelectedFile().getParent());
      return fileChooser.getSelectedFile().getAbsolutePath();
    }
    return null;
  }

  @Override
  public String chooseLoadLocation() {
    Preferences prefs = Preferences.userRoot().node(getClass().getName());
    JFileChooser fileChooser = new JFileChooser(prefs.get("LAST_USED_FOLDER",
            new File(".").getAbsolutePath()));
    fileChooser.setDialogTitle("Choose an image to load");
    int returnValue = fileChooser.showOpenDialog(null);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      prefs.put("LAST_USED_FOLDER", fileChooser.getSelectedFile().getParent());
      return fileChooser.getSelectedFile().getAbsolutePath();
    }
    return null;
  }

  @Override
  public void showError(String message) {
    JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public int askForIntegerValue(String valueName, int defaultValue, int min, int max,
                                int increment) {
    SpinnerModel spinnerModel = new SpinnerNumberModel(defaultValue, min, max, increment);
    JSpinner spinner = new JSpinner(spinnerModel);
    JScrollPane scrollPane = new JScrollPane(spinner);
    scrollPane.requestFocusInWindow();
    spinner.requestFocusInWindow();
    scrollPane.setPreferredSize(new Dimension(50, 50));
    JOptionPane.showMessageDialog(this, scrollPane, valueName, JOptionPane.PLAIN_MESSAGE);
    spinner.getValue();
    return (int) spinnerModel.getValue();
  }

  @Override
  public void addFeatures(Features features) {
    this.loadBtn.addActionListener((event) -> features.load());
    this.exportBtn.addActionListener((event) -> features.save());
    this.applyBtn.addActionListener((event) -> features.apply());
  }
}
