package controller.query;

/**
 * This interface contains the methods for query commands that handle user interactions.
 */
public interface QueryCommand {

  /**
   * Executes the command given a query.
   *
   * @param query the query to be processed.
   */
  void execute(String[] query);
}
