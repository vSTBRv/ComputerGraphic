package algorithms;

import enums.GreyScaleType;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SinglePointProcessing {

    BufferedImage canvas;
    BufferedImage originalImage;
    private static final SinglePointProcessing INSTANCE = new SinglePointProcessing();

    public static SinglePointProcessing getINSTANCE() {
        return INSTANCE;
    }
    public void loadImage(BufferedImage originalImage){
        this.originalImage = originalImage;
        canvas = new BufferedImage(originalImage.getWidth(),originalImage.getHeight(),BufferedImage.TYPE_INT_RGB);
    }

    private SinglePointProcessing(){
    }
    public BufferedImage toGreyScale(GreyScaleType type){
        Color color;
        int r, g, b;

        for (int w = 0; w < originalImage.getWidth(); w++){
            for (int h = 0; h < originalImage.getHeight(); h++){
                color = new Color(originalImage.getRGB(w,h));
                r = color.getRed();
                g = color.getGreen();
                b = color.getBlue();
                int gray;
                switch (type){
                    case Red -> gray = r;
                    case Green -> gray = g;
                    case Blue -> gray = b;
                    case Average -> gray = (r + g + b)/3;
                    case YUV -> gray = (int) (0.299 * r + 0.587 * g + 0.114 * b);
                    default -> throw new IllegalArgumentException();
                }
                canvas.setRGB(
                        w, h, new Color(gray, gray, gray).getRGB()
                );
            }
        }
        return canvas;
    }
    public BufferedImage changeBrightness(int amount){
        Color color;
        int r, g, b;
        for (int w = 0; w < originalImage.getWidth(); w++){
            for (int h = 0; h < originalImage.getHeight(); h++){
                color = new Color(originalImage.getRGB(w,h));
                r = color.getRed() + amount;
                g = color.getGreen() + amount;
                b = color.getBlue() + amount;


                canvas.setRGB(
                        w, h, new Color(trim(r), trim(g), trim(b)).getRGB()
                );
            }
        }
        return canvas;
    }
    public BufferedImage changeContrast(double amount){
        Color color;
        double r, g, b;
        for (int w = 0; w < originalImage.getWidth(); w++){
            for (int h = 0; h < originalImage.getHeight(); h++){
                color = new Color(originalImage.getRGB(w,h));
                r = color.getRed() * amount;
                g = color.getGreen() * amount;
                b = color.getBlue() * amount;


                canvas.setRGB(
                        w, h, new Color(trim((int)r), trim((int)g), trim((int)b)).getRGB()
                );
            }
        }
        return canvas;
    }
    public BufferedImage changeBrightnessRange(){
        Color color;
        int r,g,b;
        int maxRed = findMax(GreyScaleType.Red);
        int maxGreen = findMax(GreyScaleType.Green);
        int maxBlue = findMax(GreyScaleType.Blue);
        int minRed = findMin(GreyScaleType.Red);
        int minGreen = findMin(GreyScaleType.Green);
        int minBlue = findMin(GreyScaleType.Blue);
        for (int w = 0; w < originalImage.getWidth(); w++) {
            for (int h = 0; h < originalImage.getHeight(); h++) {
                color = new Color(originalImage.getRGB(w,h));
                r = 255*(color.getRed()-minRed)/(maxRed-minRed);
                g = 255*(color.getGreen()-minGreen)/(maxGreen-minGreen);
                b = 255*(color.getBlue()-minBlue)/(maxBlue-minBlue);
                canvas.setRGB(
                        w,h, new Color(r,g,b).getRGB()
                );
            }
        }
        return canvas;
    }
    public BufferedImage negation(){
        Color color;
        int r, g, b;
        for (int w = 0; w < originalImage.getWidth(); w++){
            for (int h = 0; h < originalImage.getHeight(); h++){
                color = new Color(originalImage.getRGB(w,h));
                r = 255 - color.getRed();
                g = 255 - color.getGreen();
                b = 255 - color.getBlue();
                canvas.setRGB(
                        w, h, new Color(r, g, b).getRGB()
                );
            }
        }
        return canvas;
    }

    private int trim(int value){
        if (value > 255){
            return 255;
        }else return Math.max(value, 0);
    }
    private int findMax(GreyScaleType type){
        Color color;
        int value = 0;
        for (int w = 0; w < originalImage.getWidth(); w++) {
            for (int h = 0; h < originalImage.getHeight(); h++) {
                color = new Color(originalImage.getRGB(w, h));
                switch (type){
                    case Red -> value = Math.max(value,color.getRed());
                    case Green -> value = Math.max(value,color.getGreen());
                    case Blue -> value = Math.max(value,color.getBlue());
                }
            }
        }
        return value;
    }
    private int findMin(GreyScaleType type){
        Color color;
        int value = 0;
        for (int w = 0; w < originalImage.getWidth(); w++) {
            for (int h = 0; h < originalImage.getHeight(); h++) {
                color = new Color(originalImage.getRGB(w, h));
                switch (type){
                    case Red -> value = Math.min(value,color.getRed());
                    case Green -> value = Math.min(value,color.getGreen());
                    case Blue -> value = Math.min(value,color.getBlue());
                }
            }
        }
        return value;
    }
}
