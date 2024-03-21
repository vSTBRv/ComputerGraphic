package algorithms;

import enums.GreyScaleType;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SinglePointProcessing {

    BufferedImage canvas;
    BufferedImage originalImage;
    public SinglePointProcessing(BufferedImage originalImage){
        this.originalImage = originalImage;
        canvas = new BufferedImage(originalImage.getWidth(),originalImage.getHeight(),BufferedImage.TYPE_INT_RGB);
    }
    public BufferedImage toGreyScale(GreyScaleType type){
        //setPanelSize(new Dimension(input.getWidth(),input.getHeight()));
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
        //repaint();
        return canvas;
    }
}
