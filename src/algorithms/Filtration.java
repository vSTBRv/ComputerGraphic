package algorithms;

import interfaces.GraphicalFilterInterface;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Filtration {
    protected BufferedImage canvas;
    protected final BufferedImage originalImage;
    protected GraphicalFilterInterface graphicalFilterInterface;
    protected int[][] originalColorValueRed;
    protected int[][] originalColorValueGreen;
    protected int[][] originalColorValueBlue;

    protected Filtration(BufferedImage originalImage, GraphicalFilterInterface graphicalFilterInterface){
        this.originalImage = originalImage;
        canvas = new BufferedImage(originalImage.getWidth(),originalImage.getHeight(),BufferedImage.TYPE_INT_RGB);
        this.graphicalFilterInterface = graphicalFilterInterface;

        originalColorValueRed = new int[graphicalFilterInterface.getSize()][graphicalFilterInterface.getSize()];
        originalColorValueGreen = new int[graphicalFilterInterface.getSize()][graphicalFilterInterface.getSize()];
        originalColorValueBlue = new int[graphicalFilterInterface.getSize()][graphicalFilterInterface.getSize()];
    }

    public BufferedImage filterImage(){
        Color color;
        int r, g, b;
        for (int w = 0; w < originalImage.getWidth(); w++){
            for (int h = 0; h < originalImage.getHeight(); h++){
                if (!isBoundaryPixel(w,h)) {
                    for (int i = 0; i < graphicalFilterInterface.getSize(); i++) {
                        for (int j = 0; j < graphicalFilterInterface.getSize(); j++) {
                            color = new Color(originalImage.getRGB(w - (graphicalFilterInterface.getSize() / 2) + i, h - (graphicalFilterInterface.getSize() / 2) + j));
                            originalColorValueRed[i][j] = color.getRed();
                            originalColorValueGreen[i][j] = color.getGreen();
                            originalColorValueBlue[i][j] = color.getBlue();
                        }
                    }
                    r = filterValue(originalColorValueRed);
                    g = filterValue(originalColorValueGreen);
                    b = filterValue(originalColorValueBlue);
                }else {
                    color = new Color(originalImage.getRGB(w,h));
                    r = color.getRed();
                    g = color.getGreen();
                    b = color.getBlue();
                }
                canvas.setRGB(
                        w, h, new Color(trim(r), trim(g), trim(b)).getRGB()
                );
            }
        }
        return canvas;
    }
    protected abstract int filterValue(int[][] originalColorValue);
    private int trim(int value){
        if (value > 255){
            return 255;
        }else return Math.max(value, 0);
    }
    protected boolean isBoundaryPixel(int w,int h){
        if (w < (graphicalFilterInterface.getSize()/2)) return true;
        if (h < graphicalFilterInterface.getSize()/2) return true;
        if (w >= originalImage.getWidth() - graphicalFilterInterface.getSize()/2) return true;
        if (h >= originalImage.getHeight() - graphicalFilterInterface.getSize()/2) return true;
        return false;
    }

}
