# Image Processing by Jason & Elvin

This is a program that processes .ppm files and has the ability to load, manipulate, and export
images.

## <u>A Note About Our Images</u>

* We created and processed our own .ppm files.

## <u>Type each of these lines followed by `Enter`</u>

* You can call `menu` to get a list of all possible actions to perform.
* You can call `list` to get a list of all the names of current images loaded/processed in the
  program.

* `load res/test3x4.ppm test`
* `red-component test test-red`
* `green-component test test-green`
* `blue-component test test-blue`
* `value-component test test-value`
* `luma-component test test-luma`
* `intensity-component test test-intensity`
* `horizontal-flip test test-horizontal`
* `vertical-flip test test-vertical`
* `brighten 10 test test-brighten`
* `brighten -10 test test-darken`
* `save res/test-red.ppm test-red`
* `save res/test-green.ppm test-green`
* `save res/test-blue.ppm test-blue`
* `save res/test-value.ppm test-value`
* `save res/test-intensity.ppm test-intensity`
* `save res/test-horizontal.ppm test-horizontal`
* `save res/test-vertical.ppm test-vertical`
* `save res/test-brighten.ppm test-brighten`
* `save res/test-darken.ppm test-darken`
* `quit`

## <u>Design</u>

### Model

* `ImageInterface`: an interface that represents an RGB representation of an image.
    * `Image`: holds a 2D array of pixels that represents the image, and specifies the maximum RGB
      value.
* `ImageProcessingModel`: an interface that represents a model that handles storing/loading images.
    * `ImageProcessingModelImpl`: holds a map of images corresponding to their names.
* `Pixel`: represents a pixel with RGB values.
* `ImageProcessingCommand`: an interface that represents different types of processing that can be
  performed on images
    * `BrightenCommand`: takes in an `Image` and produces a new brightened/darkened `Image`.
    * `AbstractImageProcessingCommand`: an abstract class that represents image processing commands
      that do not alter the
      location of pixels.
        * `BlueComponentGreyscaleCommand`: takes in an `Image` and produces a new greyscale `Image`
          based on the blue component of the pixels.
        * `GreenComponentGreyscaleCommand`: takes in an `Image` and produces a new greyscale `Image`
          based on the green component of the pixels.
        * `RedComponentGreyscaleCommand`: takes in an `Image` and produces a new greyscale `Image`
          based on the red component of the pixels.
        * `FlipHorizontalCommand`: takes in an `Image` and produces a new horizontally flipped
          version of the image.
        * `FlipVerticalCommand`: takes in an `Image` and produces a new vertically flipped version
          of the image.
        * `LumaComponentCommand`: takes in an `Image` and produces a new greyscale `Image` based on
          the luma component of the pixels.
        * `IntensityComponentGreyscaleCommand`: takes in an `Image` and produces creates greyscale
          versions of images based on the intensity of each pixel.
        * `ValueComponentGreyscaleCommand`: takes in an `Image` and produces a new greyscale `Image`
          based on the value component of the pixels.

### View

* `ImageProcessingView`: an interface that represents a view for the image processing program.
    * `ImageProcessingTextView`: text-based implementation that can handle rendering messages.

### Controller

* `ImageLoader`: loads .ppm images as `Image` objects.
* `ImageExporter`: converts `Image` objects to their `String` PPM representation and saves them as
  .ppm files.
* `ImageProcessingController`: represents a controller for the image processing program.
    * `ImageProcessingControllerImpl`: implementation of the controller that interacts with a
      text-based view.

### Other

* `ExceptionMessage`: enumeration for common exception messages throughout our program. Helps us not
  have to remember the exact message to write.

## <u>Decision Making</u>

* We tried to make all components as separate and independent as possible, and tried to create our
  implementation for our models as immutable as possible.
* We did not want our command implementations to take in models or `Scanner`s and wanted it to
  solely handle manipulating a given `Image` and producing a new processed version.