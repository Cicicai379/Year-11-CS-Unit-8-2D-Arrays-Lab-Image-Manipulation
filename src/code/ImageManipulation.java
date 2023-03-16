package code;

import image.Pixel;
import image.APImage;

import static java.lang.Math.abs;

public class ImageManipulation {

    /** CHALLENGE 0: Display Image
     *  Write a statement that will display the image in a window
     */
    public static void main(String[] args) {
        APImage a = new APImage("cyberpunk2077.jpg");
       // a.draw()
        //grayScale("cyberpunk2077.jpg");
// blackAndWhite("cyberpunk2077.jpg");
 //edgeDetection("cyberpunk2077.jpg",20);
//        reflectImage("cyberpunk2077.jpg");
        rotateImage("cyberpunk2077.jpg");

    }

    /** CHALLENGE ONE: Grayscale
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: a grayscale copy of the image
     *
     * To convert a colour image to grayscale, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * Set the red, green, and blue components to this average value. */
    public static void grayScale(String pathOfFile) {
        APImage a = new APImage(pathOfFile);

        int w=a.getWidth();
        int h=a.getHeight();
        for(int i=0; i<w; i++){
            for(int j=0; j<h; j++){
                Pixel p = a.getPixel(i,j);
                int av = (p.getBlue()+p.getBlue()+p.getGreen())/3;
                System.out.println(av);
                Pixel n = new Pixel(av,av,av);
                a.setPixel(i,j,n);
            }
        }
        a.draw();
    }

    /** A helper method that can be used to assist you in each challenge.
     * This method simply calculates the average of the RGB values of a single pixel.
     * @param pixel
     * @return the average RGB value
     */
    private static int getAverageColour(Pixel pixel) {
        return (pixel.getBlue()+pixel.getBlue()+pixel.getGreen())/3;

    }

    /** CHALLENGE TWO: Black and White
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: a black and white copy of the image
     *
     * To convert a colour image to black and white, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * If the average is less than 128, set the pixel to black
     * If the average is equal to or greater than 128, set the pixel to white */
    public static void blackAndWhite(String pathOfFile) {
        APImage a = new APImage(pathOfFile);
        int w=a.getWidth();
        int h=a.getHeight();

        for(int i=0; i<w; i++){
            for(int j=0; j<h; j++){
                Pixel p = a.getPixel(i,j);
                int av = getAverageColour(p);
                if(av<128){
                    av=0;
                }else{
                    av=255;
                }
                Pixel n = new Pixel(av,av,av);
                a.setPixel(i,j,n);
            }
        }
        a.draw();
    }

    /** CHALLENGE Three: Edge Detection
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: an outline of the image. The amount of information will correspond to the threshold.
     *
     * Edge detection is an image processing technique for finding the boundaries of objects within images.
     * It works by detecting discontinuities in brightness. Edge detection is used for image segmentation
     * and data extraction in areas such as image processing, computer vision, and machine vision.
     *
     * There are many different edge detection algorithms. We will use a basic edge detection technique
     * For each pixel, we will calculate ...
     * 1. The average colour value of the current pixel
     * 2. The average colour value of the pixel to the left of the current pixel
     * 3. The average colour value of the pixel below the current pixel
     * If the difference between 1. and 2. OR if the difference between 1. and 3. is greater than some threshold value,
     * we will set the current pixel to black. This is because an absolute difference that is greater than our threshold
     * value should indicate an edge and thus, we colour the pixel black.
     * Otherwise, we will set the current pixel to white
     * NOTE: We want to be able to apply edge detection using various thresholds
     * For example, we could apply edge detection to an image using a threshold of 20 OR we could apply
     * edge detection to an image using a threshold of 35
     *  */
    public static void edgeDetection(String pathToFile, int threshold) {
        APImage a = new APImage(pathToFile);
        int w=a.getWidth();
        int h=a.getHeight();
        Pixel[][] ans=new Pixel[w][h];

        for(int i=1; i<w-1; i++){
            for(int j=1; j<h-1; j++){
                Pixel p = a.getPixel(i,j);
                Pixel ll = a.getPixel(i-1,j);
                Pixel bb = a.getPixel(i,j+1);
                int av = getAverageColour(p);
                int l = getAverageColour(ll);
                int b = getAverageColour(bb);
                if(abs(av-b)>threshold || abs(av-l)>threshold){
                    av=0;
                }else{
                    av=255;
                }
                Pixel n = new Pixel(av,av,av);
                ans[i][j]=n;

            }
        }
        for(int i=1; i<w-1; i++){
            for(int j=1; j<h-1; j++){
                a.setPixel(i,j,ans[i][j]);
            }
        }


        a.draw();
    }

    /** CHALLENGE Four: Reflect Image
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: the image reflected about the y-axis
     *
     */
    public static void reflectImage(String pathToFile) {
        APImage a = new APImage(pathToFile);
        int w=a.getWidth();
        int h=a.getHeight();
        for(int i=0; i<w/2; i++){
            for(int j=0; j<h; j++){
                Pixel l = a.getPixel(i,j);
                Pixel r = a.getPixel(w-i-1,j);
                a.setPixel(i,j,r);
                a.setPixel(w-i-1,j,l);

//                System.out.println(a.getPixel(i,j)+" "+a.getPixel(w-i-1,j));
            }
        }
        a.draw();
    }

    /** CHALLENGE Five: Rotate Image
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: the image rotated 90 degrees CLOCKWISE
     *
     *  */
    public static void rotateImage(String pathToFile) {
        APImage a = new APImage(pathToFile);
        int w=a.getWidth();
        int h=a.getHeight();
        APImage b = new APImage(w,h);

        Pixel[][] ans=new Pixel[w][h];

        int y=0;

        for(int i=0; i<w/2; i++){
            for(int j=0; j<h/2; j++){
                b.setPixel(i,j,a.getPixel(j,h-i-1));

            }

        }

        b.draw();

    }

}
