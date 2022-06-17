# Image Processing by Jason & Elvin

This is a program that processes .ppm files and has the ability to load, manipulate, and export
images.

## <u>Updates</u>

### Model

* `Commands`
    * Added new abstract classes `FilterProcessingCommand` and `TransformationProcessingCommand`
      to handle kernel image filtering and image matrix processing.
    * Created `Kernel` class with static methods that produce specific kernels
    * Created `Transformation` class with static methods that produce specific 3x3 matrices for
      image transformations
    * Added support for gaussian blur, sepia, sharpen
* `Pixel`
    * Added support for aRGB to support transparency in .png images

### Controller

* `Exporter`
    * Created an `ImageExporterInterface` interface and abstracted the image exporter to allow for
      other formats of image export
    * Added new `ImageIOExporter` to handle .png, .bmp, .jpeg/jpg files
    * Created static export method in new final `ImageExporter`
* `Loader`
    * Created an `ImageLoaderInterface` interface and abstracted the image importer to allow for
      other formats of image imports
    * Added new `ImageIOLoader` to handle .png, .bmp, .jpeg/jpg files
    * Created static import method in new final `ImageLoader`
* `Query`
    * Created new `QueryCommand` interface to handle queries from the controller that executes
      actions given arguments and abstracted it in `AbstractQueryCommand`
    * Created queries for each type of query a user can provide to the program
* `ImageFilter`
    * Created new class `ImageFilter` with static filter method to handle image kernel processing
      with support for kernels of varying odd dimensions.
* `ImageProcessingControllerImpl`
    * Rewrote the `start` method to take advantage of new `QueryCommands` and utilizes a command
      pattern with a mapping of queries. This allows the controller to have more flexibility to
      add more command support in the future.

## <u>A Note About Our Images</u>

* We created and processed our own test .ppm files.
* https://freepngimg.com/png/20698-mario-transparent-background
* https://unsplash.com/photos/H1cuGOjk8d8
* https://ichef.bbci.co.uk/news/976/cpsprodpb/17638/production/_124800859_gettyimages-817514614.jpg
* https://images.unsplash.com/photo-1623063921261-5a9914c19548?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8Ym1wJTIwcGhvdG98ZW58MHx8MHx8&w=1000&q=80

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
* `brighten test test-brighten 10`
* `brighten test test-darken -10`
* `gaussian-blur test test-gaussian`
* `sepia test test-sepia`
* `sharpen test test-sharpen`
* `greyscale test test-greyscale`
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
* `Kernel`: represents an image processing kernel.
* `Transformation`: represents a transformation to be applied on an image
* `ImageProcessingCommand`: an interface that represents different types of processing that can be
  performed on images
    * `BrightenCommand`: takes in an `Image` and produces a new brightened/darkened `Image`.
    * `PixelInPlaceProcessingCommand`: an abstract class that represents image processing commands
      that do not alter the location of pixels.
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
        * `IntensityComponentGreyscaleCommand`: takes in an `Image` and produces creates greyscale
          versions of images based on the intensity of each pixel.
        * `ValueComponentGreyscaleCommand`: takes in an `Image` and produces a new greyscale `Image`
          based on the value component of the pixels.
    * `FilterProcessingCommand`: an abstract class that represents image processing commands
      that apply a filter
        * `GaussianBlurCommand`: takes in an `Image` and produces a version with gaussian blur
        * `SharpenCommand`: takes in an `Image` and produces a sharpened version
    * `TransformationProcessingCommand`: an abstract class that represents image processing
      commands that apply a transformation
        * `SepiaProcessingCommand`: takes in an `Image` and produces a sepia version
        * `LumaComponentCommand`: takes in an `Image` and produces a new greyscale `Image` based on
          the luma component of the pixels.

### View

* `ImageProcessingView`: an interface that represents a view for the image processing program.
    * `ImageProcessingTextView`: text-based implementation that can handle rendering messages.

### Controller

* `ImageLoader`: loads images of type PPM, PNG, BMP, JPEG, and JPG as `Image` objects.
* `ImageExporter`: converts `Image` objects to their `String` PPM representation and saves them as
  type PPM, PNG, BMP, JPEG, or JPG files.
* `ImageProcessingController`: represents a controller for the image processing program.
    * `ImageProcessingControllerImpl`: implementation of the controller that interacts with a
      text-based view.
* `QueryCommand`: interface that represents a potential query a user might make
    * `AbstractQueryCommand`: an abstract class that represents the queries used for the
      text-based controller
        * `BlueComponentQuery`: handles queries for blue-component processing
        * `BrightenQuery`: handles queries for brightening/darkening processing
        * `FlipHorizontalQuery`: handles queries for horizontal flip
        * `FlipVerticalQuery`: handles queries for vertical flip
        * `GaussianBlurQuery`: handles queries for gaussian blur
        * `GreenComponentQuery`: handles queries for green-component processing
        * `IntensityQuery`: handles queries for intensity processing
        * `ListQuery`: handles queries to list all images
        * `LoadQuery`: handles queries to load an image
        * `LumaQuery`: handles queries for luma component processing
        * `MenuQuery`: handles queries to return the menu
        * `QuitQuery`: handles queries to quit
        * `RedComponentQuery`: handles queries for red-component processing
        * `SaveQuery`: handles queries to save images
        * `SepiaQuery`: handles queries to apply sepia
        * `SharpenQuery`: handles queries to sharpen images
        * `ValueQuery`: handles queries for value component processing
* `ImageFilter`: handles applying filters to images

### Other

* `ExceptionMessage`: enumeration for common exception messages throughout our program. Helps us not
  have to remember the exact message to write.

## <u>Decision Making</u>

* We tried to make all components as separate and independent as possible, and tried to create our
  implementation for our models as immutable as possible.
* We did not want our command implementations to take in models or `Scanner`s and wanted it to
  solely handle manipulating a given `Image` and producing a new processed version.
* We had to rewrite a majority of our controller to make it more versatile for more
  functionality down the line.