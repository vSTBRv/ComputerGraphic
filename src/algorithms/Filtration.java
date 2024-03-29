package algorithms;

import interfaces.GraphicalFilterInterface;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Filtration {
    private BufferedImage canvas;
    private final BufferedImage originalImage;
    protected GraphicalFilterInterface graphicalFilterInterface;
    private int[][] originalColorValueRed;
    private int[][] originalColorValueGreen;
    private int[][] originalColorValueBlue;
    protected final int normalization;

    protected Filtration(BufferedImage originalImage, GraphicalFilterInterface graphicalFilterInterface){
        this.originalImage = originalImage;
        canvas = new BufferedImage(originalImage.getWidth(),originalImage.getHeight(),BufferedImage.TYPE_INT_RGB);
        this.graphicalFilterInterface = graphicalFilterInterface;

        originalColorValueRed = new int[graphicalFilterInterface.getSize()][graphicalFilterInterface.getSize()];
        originalColorValueGreen = new int[graphicalFilterInterface.getSize()][graphicalFilterInterface.getSize()];
        originalColorValueBlue = new int[graphicalFilterInterface.getSize()][graphicalFilterInterface.getSize()];
        normalization = calculateNormalization();
    }

    protected BufferedImage filterImage(){
        Color color;
        int r, g, b;
        for (int w = graphicalFilterInterface.getSize()/2; w < originalImage.getWidth(); w++){
            for (int h = graphicalFilterInterface.getSize()/2; h < originalImage.getHeight(); h++){
                for(int i = 0; i < graphicalFilterInterface.getSize(); i++){
                    for (int j = 0; j < graphicalFilterInterface.getSize(); j++){
                        color = new Color(originalImage.getRGB(w-(graphicalFilterInterface.getSize()/2)+i,h-(graphicalFilterInterface.getSize()/2)+j));
                        originalColorValueRed[i][j] = color.getRed();
                        originalColorValueGreen[i][j] = color.getGreen();
                        originalColorValueBlue[i][j] = color.getBlue();
                    }
                }
                r = filterValue(originalColorValueRed);
                g = filterValue(originalColorValueGreen);
                b = filterValue(originalColorValueBlue);

                canvas.setRGB(
                        w, h, new Color(r, g, b).getRGB()
                );
            }
        }
        return canvas;
    }
    protected abstract int filterValue(int[][] originalColorValue);

    private int calculateNormalization(){
        int normalization = 0;
        for (int w = 0; w< graphicalFilterInterface.getSize(); w++){
            for (int h = 0; h< graphicalFilterInterface.getSize(); h++){
                normalization += graphicalFilterInterface.getValue(w,h);
            }
        }
        return normalization;
    }

}
