package view.gui;

import controller.exporter.ImageExporter;
import model.ImageInterface;

import javax.swing.*;

public class ImagePreview extends JPanel {
  private JLabel label;

  public ImagePreview() {
    ImageIcon icon = new ImageIcon();
    this.label = new JLabel(icon);
    this.add(this.label);
  }

  public void updateImage(ImageInterface img) {
    this.label.setIcon(new ImageIcon(ImageExporter.convertBuffered(img)));
  }
}
