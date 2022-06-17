package model.commands;

/**
 * Represents a class that holds transformation matrices for filtering.
 */
public class Transformation {

  /**
   * Returns a matrix for luma filter.
   *
   * @return a luma matrix.
   */
  public static double[][] luma() {
    double[][] transformation = new double[3][3];
    transformation[0] = new double[]{0.2126, 0.7152, 0.0722};
    transformation[1] = new double[]{0.2126, 0.7152, 0.0722};
    transformation[2] = new double[]{0.2126, 0.7152, 0.0722};
    return transformation;
  }

  /**
   * Returns a matrix for sepia filter.
   *
   * @return a sepia matrix.
   */
  public static double[][] sepia() {
    double[][] transformation = new double[3][3];
    transformation[0] = new double[]{0.393, 0.769, 0.189};
    transformation[1] = new double[]{0.349, 0.686, 0.168};
    transformation[2] = new double[]{0.272, 0.534, 0.131};
    return transformation;
  }
}
