/*
 * CS310 Assignment 7 - Stacks and Image Manipulation
 */
package cs310datastructures;

import java.awt.Color;

/**
 * This class provides methods for applying various filters and transformations to images. It uses a ColorStack
 * to perform horizontal and vertical flips, and creates new image patterns
 * like tiled and abstract designs. 
 * 
 * @author Jeffrey LaMarche
 * @version 1.0 2020-Sept-4 Template Version
 * 
 * @author Selinde Tatum
 * @version 1.0 2024-May-1- Student Version 
 */
public class ImageManipulation
{
    /**
     * Restricted default constructor 
     */
    private ImageManipulation()
    {
        // Given private access to prevent someone from being able to 
        // instantiate an ImageManipulation object
    }
    
    /**
     * Applies the black an white filter to the original image
     * @param originalImage
     * @return a new image with black and white
     */
    public static Picture blackWhiteFilter(Picture originalImage)
    {
        int width = originalImage.width();
        int height = originalImage.height();
        Picture newImage = new Picture(width,height);
        
        for (int col = 0; col < width; col++){
           for (int row = 0; row < height; row++){
              //Gets the RGB values of the orginal image
           Color orginalColor = originalImage.get(col,row);
           
           //Computes the average RGB value 
           int averageRGB = (orginalColor.getRed() +orginalColor.getGreen()+ orginalColor.getBlue())/3;
           
           //Creates a new pixel color based on average 
          // Color newPixelColor = (averageRGB < 128)? new Color(0,0,0): new Color (255,255,255);
           Color newPixelColor = new Color(averageRGB, averageRGB, averageRGB);
           //Sets new pixel color in new image 
           newImage.set(col,row,newPixelColor);
        }
        }
        return newImage;
    }
    
    /**
     * Applies the red tone filter to the original image
     * @param originalImage
     * @return a new image with red tone
     */
    public static Picture redToneFilter(Picture originalImage)
    {
        int width = originalImage.width();
        int height = originalImage.height();
        Picture newImage = new Picture(width,height);
        for (int col = 0; col<width;col++){
           for (int row = 0; row<height; row++){
              //Gets the RGB values of the orginal image
           Color orginalColor = originalImage.get(col,row);
           
           int newRed = orginalColor.getRed();
           int newGreen = 0;
           int newBlue = 0;
           Color newPixelColor = new Color(newRed,newGreen,newBlue);
          //Sets new pixel color in new image 
           newImage.set(col,row,newPixelColor);
        }
        }
        return newImage; 
        
        
    }
    
    /**
     * Applies the green tone filter to the original image
     * @param originalImage
     * @return a new image with green tone
     */
    public static Picture greenToneFilter(Picture originalImage)
    {
       int width = originalImage.width();
        int height = originalImage.height();
        Picture newImage = new Picture(width,height);
        for (int col = 0; col<width;col++){
           for (int row = 0; row<height; row++){
              //Gets the RGB values of the orginal image
           Color orginalColor = originalImage.get(col,row);
           
           int newRed = 0;
           int newGreen = orginalColor.getGreen();
           int newBlue = 0;
           Color newPixelColor = new Color(newRed,newGreen,newBlue);
          //Sets new pixel color in new image 
           newImage.set(col,row,newPixelColor);
        }
        }
        return newImage;  
    }
    
    /**
     * Applies the blue tone filter to the original image
     * @param originalImage
     * @return a new image with blue tone
     */
    public static Picture blueToneFilter(Picture originalImage)
    {
        int width = originalImage.width();
        int height = originalImage.height();
        Picture newImage = new Picture(width,height);
        for (int col = 0; col<width;col++){
           for (int row = 0; row<height; row++){
              //Gets the RGB values of the orginal image
           Color orginalColor = originalImage.get(col,row);
           
           int newRed = 0;
           int newGreen = 0;
           int newBlue = orginalColor.getBlue();
           Color newPixelColor = new Color(newRed,newGreen,newBlue);
          //Sets new pixel color in new image 
           newImage.set(col,row,newPixelColor);
        }
        }
        return newImage;  
    }
    
    /**
     * Uses the ColorStack to flip an image horizontally 
     * @param originalImage
     * @return a new picture image
     */
    public static Picture flipHorizontally(Picture originalImage)
    {
        int width = originalImage.width();
        int height = originalImage.height();
        Picture newImage = new Picture(width,height);
        
        //Create ColorStack with original image width as size
        ColorStack colorStack = new ColorStack(width);
        
        //Loop through each of x &y value 
        for (int row = 0; row <height; row++){
           for (int col = 0; col <width; col++){
              //Push orginal image pixel color onto stack 
              Color pixelColor = originalImage.get(col,row);
              colorStack.pushColor(pixelColor);
           }
           //Pop the stack and set the new image pizel color values
           for (int col = 0; col < width; col++){
              Color newPixelColor = colorStack.popColor();
              newImage.set(col, row, newPixelColor);
           }
        }
        return newImage;
    }
    
    /**
     * Flips original image vertically 
     * @param originalImage
     * @return a new image vertically
     */
    public static Picture flipVertically(Picture originalImage)
    {
        int width = originalImage.width();
        int height = originalImage.height();
        Picture newImage = new Picture(width,height);
        
        //Create ColorStack with original image width as size
        ColorStack colorStack = new ColorStack(height);
        
        //Loop through each of x &y value 
        for (int col = 0; col <width; col++){
           for (int row = 0; row <height; row++){
              //Push orginal image pixel color onto stack 
              Color originalColor = originalImage.get(col,row);
              colorStack.pushColor(originalColor);
           }
           //Pop the stack and set the new image pizel color values
           for (int row = 0; row< height; row++){
              Color newPixelColor = colorStack.popColor();
              newImage.set(col, row, newPixelColor);
           }
        }
        return newImage;
    }
    
    /**
     * Creates new image with four quadrants where each contains versions of original image
     * @param originalImage
     * @return a new image 
     */
    public static Picture normalTilePattern(Picture originalImage)
    {   
       int width = originalImage.width();
       int height = originalImage.height();
       
       //Creae four quadrants using flipping methods 
       Picture topLeft = originalImage;
       Picture topRight = flipHorizontally(originalImage);
       Picture bottomLeft = flipVertically(originalImage);
       Picture bottomRight = flipVertically(flipHorizontally(originalImage));
       
       //Combine into a single image
       Picture combinedImage = combineImages(topLeft, topRight, bottomLeft, bottomRight);
       return combinedImage;
    }
    
    /**
     * Creates abstract art pattern from original image
     * @param originalImage
     * @return a new image 
     */
    public static Picture abstractTilePattern(Picture originalImage)
    {   
       int width = originalImage.width();
       int height = originalImage.height();
       
       Picture topLeft = blackWhiteFilter(originalImage);
       Picture topRight = redToneFilter(flipHorizontally(originalImage));
       Picture bottomLeft = blueToneFilter(flipVertically(originalImage));
       Picture bottomRight = greenToneFilter(flipVertically(flipHorizontally(originalImage)));
    
       Picture combinedImage = combineImages(topLeft, topRight, bottomLeft, bottomRight);
       return combinedImage;
    }
    
    /**
     * Combines four images into a single image
     * @param topLeft the top let image 
     * @param topRight the top right image
     * @param bottomLeft the bottom left image
     * @param bottomRight the bottom right image
     * @return a new image 
     */
    private static Picture combineImages(Picture topLeft, Picture topRight, 
                                         Picture bottomLeft, Picture bottomRight)
    {
        int width = topLeft.width();
        int height = topLeft.height();
        //Creates new picture thats 2*2 
        Picture combinedImage =  new Picture(2*width,2*height);
        
        //Loops thorugh the x and y values in the width and height 
        for (int x = 0; x < combinedImage.width(); x++){
           for (int y = 0; y < combinedImage.height(); y++){
              Color pixelColor;
              //If y and x values are in the top quarannt, correct top left color 
              if (x < width && y < height){
               pixelColor = topLeft.get(x, y);
              }else if( x< width && y>= height){
                 pixelColor = bottomLeft.get(x, y-height);
                 
              }else if (x >= width && y < height){
                 pixelColor = topRight.get(x-width,y);
                 
              }else{
                 pixelColor = bottomRight.get(x-width,y-height);
              }
              combinedImage.set(x,y,pixelColor);
           }
        }
        return combinedImage;
    }
}
