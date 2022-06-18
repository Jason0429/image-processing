package view.gui;

import model.ImageInterface;
import model.Pixel;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.geom.Line2D;
import java.util.*;
import java.util.List;
import java.util.function.Function;

public class Histogram extends JPanel {
  private List<Function<Pixel, Integer>> types;
  private List<Color> colors;
  private ImageInterface img;

  public Histogram(ImageInterface img) {
    this.types = new ArrayList<Function<Pixel, Integer>>(Arrays.asList(PixelReader::getRed,
            PixelReader::getGreen, PixelReader::getBlue, PixelReader::getIntensity));
    this.colors = new ArrayList<Color>(Arrays.asList(Color.red, Color.green, Color.blue,
            Color.gray));
    this.img = img;
    this.setBackground(Color.darkGray);
  }

  @Override
  public void paint(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    int width = 1;
    g2.setStroke(new BasicStroke(width));

    for (int x = 0; x < this.types.size(); x++) {
      g2.setColor(this.colors.get(x));
      Function<Pixel, Integer> type = this.types.get(x);
      Map<Integer, Integer> frequency = new HashMap<Integer, Integer>();
      for (int row = 0; row < img.getHeight(); row++) {
        for (int col = 0; col < img.getWidth(); col++) {
          int value = type.apply(img.getPixelAt(row, col));
          if (frequency.containsKey(value)) {
            frequency.put(value, frequency.get(value) + 1);
          } else {
            frequency.put(value, 1);
          }
        }
      }
      for (int i = 0; i < 256; i++) {
        int value = frequency.getOrDefault(i, 0);
        g2.draw(new Line2D.Float(i * width, 200, i * width, Math.max(200 - value, 0)));
      }
    }
  }
}
