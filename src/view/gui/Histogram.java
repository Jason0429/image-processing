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

  @Override
  public void paint(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;

    if (this.img == null) {
      g2.drawString("No image loaded!", 100, 100);
      return;
    }

    int strokeWidth = 1;
    g2.setStroke(new BasicStroke(strokeWidth));

    for (Map.Entry<Color, Function<Pixel, Integer>> entry : this.types.entrySet()) {
      Color color = entry.getKey();
      Function<Pixel, Integer> type = entry.getValue();
      g2.setColor(color);
      Map<Integer, Integer> frequency = this.getPixelTypeFrequency(type);
//      frequency.forEach((key, value) -> System.out.println(key + " " + value));
      int maxFreq = Collections.max(frequency.values());
      double scalar = maxFreq > this.HEIGHT ? 400.0 / maxFreq : 1;

//      System.out.println("Image width: " + this.img.getWidth());
//      System.out.println("Image height: " + this.img.getHeight());
//      System.out.println("Scalar: " + scalar);

      for (int i = 0; i < 256; i++) {
        int value = frequency.getOrDefault(i, 0);
        int x1 = i * strokeWidth;
        int x2 = i * strokeWidth;
//        int y2 = Math.max(this.HEIGHT - value, 0);
        int y2 = this.HEIGHT - value;
        g2.draw(new Line2D.Float(x1, this.HEIGHT, x2, y2));
      }
    }
  }

  private Map<Integer, Integer> getPixelTypeFrequency(Function<Pixel, Integer> type) {
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
    return frequency;
  }
}
