# Text-Based User Interface

* Run the jar program with a `-text` option OR
* Run the jar program with a `-file` [`file-path`] option to run a script of commands.

`menu` (loads this menu)

`list` (lists all loaded image names)

`load` [`image-path`] [`image-name`]

`save` [`image-path`] [`image-name`]

`red-component` [`image-name`] [`dest-image-name`]

`green-component` [`image-name`] [`dest-image-name`]

`blue-component` [`image-name`] [`dest-image-name`]

`value-component` [`image-name`] [`dest-image-name`]

`luma-component` [`image-name`] [`dest-image-name`]

`intensity-component` [`image-name`] [`dest-image-name`]

`horizontal-flip` [`image-name`] [`dest-image-name`]

`vertical-flip` [`image-name`] [`dest-image-name`]

`brighten` [`image-name`] [`dest-image-name`] [`increment`]

`gaussian-blur` [`image-name`] [`dest-image-name`]

`sharpen` [`image-name`] [`dest-image-name`]

`*` [`source-image-name`] [`mask-image-name`] [`dest-image-name`] [`command-args, if any`] where `*`
is any command except flipping.

`quit`/`q` (quit the program)

# GUI-Based User Interface

* Run the jar program with no arguments.
* A GUI window should appear with 6 rectangular panels.
* Below is a brief description of each panel and its purpose and usage.

`Histogram`: Located on the top left panel. Shows pixel data visualizations of the current image
displayed.

`Image Preview`: Located at the top center panel. Displays the current image with image processing
modifications, if any.

`Command Dropdown`: Located at the top right panel. A dropdown consisting of available image
processing commands that can be applied to the loaded image.

`Load`: Located at the bottom center panel. When pressed, a file explorer will appear to choose an
image file to load and display for
image processing.

`Save`: Located at the bottom left panel. When pressed, a file explorer will appear to choose a
directory as well as a given name to
save the processed image.

`Apply`: Located at the bottom right panel. When pressed, will apply the command selected in the
dropdown to the current image
displayed.

