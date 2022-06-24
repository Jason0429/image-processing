package controller.query;

import model.ExceptionMessage;
import model.ImageProcessingModel;
import view.text.ImageProcessingTextView;

public class CheckMaskDecorator implements QueryCommand {
  private final QueryCommand delegate;
  private final ImageProcessingModel model;
  private final ImageProcessingTextView view;
  public CheckMaskDecorator(QueryCommand delegate, ImageProcessingModel model,
                            ImageProcessingTextView view) throws IllegalArgumentException {
    if (delegate == null || model == null || view == null) {
      throw new IllegalArgumentException(ExceptionMessage.NULL_VALUES.toString());
    }
    this.delegate = delegate;
    this.model = model;
    this.view = view;
  }

  @Override
  public void execute(String[] query) {
    if (query.length == this.getProperQueryLength()) {
      new ImageMaskQuery(this.model, this.view).execute(query);
    } else {
      this.delegate.execute(query);
    }
  }

  @Override
  public int getProperQueryLength() {
    return this.delegate.getProperQueryLength() + 1;
  }
}
