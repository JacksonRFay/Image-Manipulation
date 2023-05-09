//Jackson Fay
//TA: Ambika Shastry
//April 27th, 2023
//This program has four different methods to change the image in various ways.
//It can either increase the purple in the image, make the image grainy, make a pink rectagnle, 
//or mirror the right side of the image.

import java.util.*;
import java.awt.*;

public class ImageManipulation {
    public static void main(String[] args) {
        Picture pic = new Picture("suzzallo.jpg");
        Color[][] pixels = pic.getPixels();

        increasePurple(pixels);
        pic.setPixels(pixels);
        pic.save("creative1.jpg");

        grainy(pixels);
        pic.setPixels(pixels);
        pic.save("creative2.jpg");

        negativeRectangleOfPink(pixels, 400, 200, 500, 180);
        pic.setPixels(pixels);
        pic.save("creative3.jpg");

        mirrorRight(pixels);
        pic.setPixels(pixels);
        pic.save("creative4.jpg");
    }

    //Task 1
    //This method increases the amount of purple in a given photo.
    // It increases red by 63, green by 13, and blue by 94.
    //  Color[][] pixels = a 2d array of pixels from the given image.
    public static void increasePurple(Color[][] pixels) {
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                Color originalColor = pixels[i][j];
                int red = originalColor.getRed();
                int blue = originalColor.getBlue();
                int green = originalColor.getGreen();

                Color newColor = new Color(Math.min(red + 63, 255), Math.min(green + 13, 255), Math.min(blue + 94, 255)); 
                pixels[i][j] = newColor;
            }
        }
    }

    //Task 2
    //This method will add noise to a photo by randomly increasing red,
    //green, or blue by 100. Uses helper method "addNoise" which is 
    //acctually adding the noise based on the color.
    //  Color[][] pixels = a 2d array of pixels from the given image. 
    public static void grainy(Color[][] pixels) {
        Random rand = new Random(2);
        Map<Integer, String> colorCounts = new HashMap<>();
        colorCounts.put(0, "red");
        colorCounts.put(1, "green");
        colorCounts.put(2, "blue");

        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                Color originalColor = pixels[i][j];
                int red = originalColor.getRed();
                int blue = originalColor.getBlue();
                int green = originalColor.getGreen();

                String randomColor = colorCounts.get(rand.nextInt(3));

                if (randomColor.equals("red")) {
                    int newRed = addNoise(red);
                    Color newColor = new Color(newRed, green, blue);
                    pixels[i][j] = newColor;
                } else if (randomColor.equals("green")) {
                    int newGreen = addNoise(green);
                    Color newColor = new Color(red, newGreen, blue);
                    pixels[i][j] = newColor;
                } else {
                    int newBlue = addNoise(blue);
                    Color newColor = new Color(red, green, newBlue);
                    pixels[i][j] = newColor;
                }
            }
        }
    }

    //Task 2 helper method
    //This method will add 100 to the color (int colorValue) that is being passed 
    //into the method.
    //It returns this number through "newColorValue".
    public static int addNoise(int colorValue) {
        int newColorValue = Math.min(255, Math.max(0, colorValue + 100)); 
        return newColorValue;
    }

    //Task 3
    //This method increases the amount of pink in a given photo, BUT
    // WILL ONLY BE APPLIED ON A RECTANGLE SPECIFIED BY USER.
    // It increases red by 163, green by 113, and blue by 194.
    // Throws IllegalArgumentException if nums for rectangle are below 0.
    //  Color[][] pixels = a 2d array of pixels from the given image.
    public static void negativeRectangleOfPink(Color[][] pixels, int x, int y, int width, int height) {
        if (x < 0 || y < 0 || width < 0 || height < 0) {
            throw new IllegalArgumentException("x, y, width, and height all must be positive or 0");
        }
        for (int row = y; row < y + height; row++) {
            for (int col = x; col < x + width; col++) {
                Color originalColor = pixels[row][col];
                int red = originalColor.getRed();
                int blue = originalColor.getBlue();
                int green = originalColor.getGreen();

                Color newColor = new Color(Math.min(red + 163, 255), Math.min(green + 113, 255), Math.min(blue + 194, 255)); 
                pixels[row][col] = newColor;
            }
        }
    }

    //Task 4
    // This method mirrors the right half of the image over to the left half.
    //  Color[][] pixels = a 2d array of pixels from the given image.
    public static void mirrorRight(Color[][] pixels) {
        int height = pixels.length;
        int width = pixels[0].length;

        for (int i = 0; i < height; i++) {
            for (int j = width / 2; j < width; j++) {
                pixels[i][width - j - 1] = pixels[i][j];

                
            }
        }
    }
}
