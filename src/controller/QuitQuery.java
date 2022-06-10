package controller;

public class QuitQuery implements QueryCommand {

  private final Runnable quit;

  public QuitQuery(Runnable quit) {
    this.quit = quit;
  }

  @Override
  public void execute(String[] query) {
    this.quit.run();
  }
}
