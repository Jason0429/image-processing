package controller;

/**
 * This interface contains the methods for query commands that handle user interactions.
 */
interface QueryCommand {

  /**
   * Executes the query.
   */
  void execute(String[] query);
}
