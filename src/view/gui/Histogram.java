package view.gui;

import model.Pixel;

import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.HashMap;
import java.util.function.Function;

public class Histogram extends JScrollPane {
  private final Map<Color, Function<Pixel, Integer>> types;
  private BufferedImage img;
  private final int HEIGHT = 400;

  public Histogram(Map<Color, Function<Pixel, Integer>> types) {
    this.types = types;
    this.img = null;
  }

  public void updateImage(BufferedImage img) {
    this.img = img;
    this.invalidate();
    this.repaint();
  }

  private void thinking() {
    int histogramHeight = 400; // Represents pure height of histogram.
    int histogramWidth = 256; // Represents rgb values 0-255

    Map<String, Integer> values = new HashMap<>();

    // Find max Integer in values.
    // max : 400px
    // scalar: max / 400
  }

  @Override
  public void paint(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    if (this.img != null) {
      int width = 1;
      g2.setStroke(new BasicStroke(width));

      for (Color c : this.types.keySet()) {
        g2.setColor(c);
        Function<Pixel, Integer> type = this.types.get(c);
        Map<Integer, Integer> frequency = new HashMap<Integer, Integer>();
        for (int row = 0; row < img.getHeight(); row++) {
          for (int col = 0; col < img.getWidth(); col++) {
            Color rgb = new Color(img.getRGB(col, row), true);
            Pixel pixel = new Pixel(255, rgb.getRed(), rgb.getGreen(),
                    rgb.getBlue(), rgb.getAlpha());
            int value = type.apply(pixel);
            if (frequency.containsKey(value)) {
              frequency.put(value, frequency.get(value) + 1);
            } else {
              frequency.put(value, 1);
            }
          }
        }
        for (int i = 0; i < 256; i++) {
          int value = frequency.getOrDefault(i, 0);
          g2.draw(new Line2D.Float(i * width, this.HEIGHT, i * width,
                  Math.max(this.HEIGHT - value, 0)
          ));
        }
      }
    } else {
      g2.drawString("No image loaded!", 100, 100);
    }
  }
}
