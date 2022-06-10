package controller;

import model.ImageProcessingModel;

public class MenuQuery implements QueryCommand {
  private final Runnable displayMenu;

  public MenuQuery(Runnable displayMenu) {
    this.displayMenu = displayMenu;
  }

  @Override
  public void execute(String[] query) {
    this.displayMenu.run();
  }
}
