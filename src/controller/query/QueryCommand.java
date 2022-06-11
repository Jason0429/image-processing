package controller.query;

/**
 * This interface contains the methods for query commands that handle user interactions.
 */
public interface QueryCommand {

  /**
   * Executes the query.
   */
  void execute(String[] query);
}