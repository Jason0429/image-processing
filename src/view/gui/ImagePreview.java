package view.gui;

import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

import java.awt.image.BufferedImage;

/**
 * Represents a component for the image preview.
 */
public class ImagePreview extends JScrollPane {
  private final JLabel label;

  /**
   * Construct a new image preview.
   */
  public ImagePreview() {
    ImageIcon icon = new ImageIcon();
    this.label = new JLabel(icon);
    this.setViewportView(this.label);
  }

  public void updateImage(BufferedImage image) {
    this.label.setIcon(new ImageIcon(image));
  }
}