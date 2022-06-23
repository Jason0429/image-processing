package view.gui;

import model.Pixel;

import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.function.Function;

public class Histogram extends JScrollPane {
  private final Map<Color, Function<Pixel, Integer>> types;
  private BufferedImage img;

  public Histogram(Map<Color, Function<Pixel, Integer>> types) {
    this.types = types;
    this.img = null;
  }

  public void updateImage(BufferedImage img) {
    this.img = img;
    this.invalidate();
    this.repaint();
  }

  @Override
  public void paint(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;

    if (this.img == null) {
      g2.drawString("No image loaded!", 100, 100);
      return;
    }

    int strokeWidth = 1;
    int height = 400;
    g2.setStroke(new BasicStroke(strokeWidth));

    for (Map.Entry<Color, Function<Pixel, Integer>> entry : this.types.entrySet()) {
      Color color = entry.getKey();
      Function<Pixel, Integer> type = entry.getValue();

      g2.setColor(color);

      Map<Integer, Integer> frequency = this.getPixelTypeFrequency(type);
      int maxFreq = Collections.max(frequency.values());
      int minFreq = Collections.min(frequency.values());

      for (int i = 0; i < 256; i++) {
        int value = frequency.getOrDefault(i, 0);
        int x1 = i * strokeWidth;
        int x2 = i * strokeWidth;
        int normalizedValue = (int) (((double) (value - minFreq) / (maxFreq - minFreq)) * height);
        int y2 = Math.max(height - normalizedValue, 0);
        g2.draw(new Line2D.Float(x1, height, x2, y2));
      }
    }
  }

  private Map<Integer, Integer> getPixelTypeFrequency(Function<Pixel, Integer> type) {
    Map<Integer, Integer> frequency = new HashMap<Integer, Integer>();
    for (int row = 0; row < img.getHeight(); row++) {
      for (int col = 0; col < img.getWidth(); col++) {
        Color rgb = new Color(img.getRGB(col, row), true);
        // Ignore transparent pixels.
        if (rgb.getAlpha() == 0) {
          continue;
        }
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
    return frequency;
  }
}
