package algorithms;

import entities.GradientFilter;
import enums.GradientCalculationType;
import enums.GradientFiltrationOptions;
import enums.GradientType;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GradientFiltrationWithThreshold extends GradientFiltration{
    private final GradientFiltrationOptions gradientFiltrationOptions;
    public GradientFiltrationWithThreshold(BufferedImage originalImage, GradientFilter gradientFilter, GradientType gradientType,
                                           GradientCalculationType gradientCalculationType, GradientFiltrationOptions gradientFiltrationOptions) {
        super(originalImage, gradientFilter, gradientType, gradientCalculationType);
        this.gradientFiltrationOptions = gradientFiltrationOptions;
    }
    @Override
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
                    r = g = b = 0;
                    switch (gradientFiltrationOptions){
                        case White_background ->{
                            if (filterValue(originalColorValueRed) > ((GradientFilter) graphicalFilterInterface).getThreshold() ||
                                    filterValue(originalColorValueGreen) > ((GradientFilter) graphicalFilterInterface).getThreshold() ||
                                    filterValue(originalColorValueBlue) > ((GradientFilter) graphicalFilterInterface).getThreshold()){
                                color = new Color(originalImage.getRGB(w,h));
                                r = color.getRed();
                                g = color.getGreen();
                                b = color.getBlue();
                            } else {
                                r = g = b = 255;
                            }
                        }
                        case Black_edges ->{
                            if (filterValue(originalColorValueRed) > ((GradientFilter) graphicalFilterInterface).getThreshold() ||
                                    filterValue(originalColorValueGreen) > ((GradientFilter) graphicalFilterInterface).getThreshold() ||
                                    filterValue(originalColorValueBlue) > ((GradientFilter) graphicalFilterInterface).getThreshold()){
                                r = g = b = 0;
                            } else {
                                color = new Color(originalImage.getRGB(w,h));
                                r = color.getRed();
                                g = color.getGreen();
                                b = color.getBlue();
                            }
                        }
                        case Black_edges_white_background ->{
                            if (filterValue(originalColorValueRed) > ((GradientFilter) graphicalFilterInterface).getThreshold() ||
                                    filterValue(originalColorValueGreen) > ((GradientFilter) graphicalFilterInterface).getThreshold() ||
                                    filterValue(originalColorValueBlue) > ((GradientFilter) graphicalFilterInterface).getThreshold()){
                                r = g = b = 0;
                            } else {
                                r = g = b = 255;
                            }
                        }
                    }
                }else {
                    color = new Color(originalImage.getRGB(w,h));
                    r = color.getRed();
                    g = color.getGreen();
                    b = color.getBlue();
                }
                canvas.setRGB(
                        w, h, new Color(r,g,b).getRGB()
                );
            }
        }
        return canvas;
    }
}
