package model.commands;

public class Transformation {

  public static double[][] sepia() {
    double[][] transformation = new double[3][3];
    transformation[0] = new double[]{0.393, 0.769, 0.189};
    transformation[1] = new double[]{0.349, 0.686, 0.168};
    transformation[2] = new double[]{0.272, 0.534, 0.131};
    return transformation;
  }
}
