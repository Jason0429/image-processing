package controller;

/**
 * An interaction with the user consists of some input to send the program
 * and some output to expect.  We represent it as an object that takes in two
 * {@code StringBuilder}s and produces the intended effects on them.
 */
interface Interaction {

  /**
   * Alters the input, output or both streams, for testing.
   *
   * @param in  the input
   * @param out the output
   */
  void apply(StringBuilder in, StringBuilder out);
}
