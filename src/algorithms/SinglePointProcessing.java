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
    public BufferedImage changeBrightnessRange(int min, int max){
        Color color;
        int r, g, b;
        for (int w = 0; w < originalImage.getWidth(); w++){
            for (int h = 0; h < originalImage.getHeight(); h++){
                color = new Color(originalImage.getRGB(w,h));
                r = (255 * (color.getRed() - min))/(max - min);
                g = (255 * (color.getGreen() - min))/(max - min);
                b = (255 * (color.getBlue() - min))/(max - min);


                canvas.setRGB(
                        w, h, new Color(r, g, b).getRGB()
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
}
