
        // TODO: Apply filter from Task 4

        // save completed image with all filters applied.
        pic.setPixels(pixels);
        pic.save("creative4.jpg");
    }

#! /bin/bash

javac *.java && java ImageManipulation && rm *.class
