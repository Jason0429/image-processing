package view.gui;

import model.Pixel;

import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.*;
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

    Map<Color, Map<Integer, Integer>> mapLists = new HashMap<>();
    int minFreq = 0;
    int maxFreq = 0;


    for (Map.Entry<Color, Function<Pixel, Integer>> entry : this.types.entrySet()) {
      Color color = entry.getKey();
      Function<Pixel, Integer> type = entry.getValue();
      Map<Integer, Integer> frequency = this.getPixelTypeFrequency(type);
      mapLists.put(color, frequency);
      maxFreq = Collections.max(frequency.values());
    }

    for (Map.Entry<Color, Map<Integer, Integer>> colorMap : mapLists.entrySet()) {
      Color color = colorMap.getKey();
      g2.setColor(color);
      for (int i = 1; i < 256; i++) {
        int valuePrev = colorMap.getValue().getOrDefault(i - 1, 0);
        int value = colorMap.getValue().getOrDefault(i, 0);
        int x1 = i * strokeWidth;
        int normalizedValuePrev = (int) (((double) valuePrev / maxFreq) * this.HEIGHT);
        int normalizedValue = (int) (((double) value / maxFreq) * this.HEIGHT);
        int y2Prev = Math.max(this.HEIGHT - normalizedValuePrev, 0);
        int y2 = Math.max(this.HEIGHT - normalizedValue, 0);
        g2.draw(new Line2D.Float(x1 - 1, y2Prev, x1, y2));
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
