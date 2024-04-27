package algorithms;

import entities.MaskFromFile;

import java.awt.image.BufferedImage;

public class FiltrationFromFile extends Filtration{
    private final int normalization;
    public FiltrationFromFile(BufferedImage originalImage, MaskFromFile maskFromFile) {
        super(originalImage, maskFromFile );
        this.normalization = calculateNormalization();
    }

    @Override
    protected int filterValue(int[][] originalColorValue) {
        int filteredValue = 0;
        for (int i = 0; i < graphicalFilterInterface.getSize(); i++){
            for (int j = 0; j < graphicalFilterInterface.getSize(); j++){
                filteredValue += originalColorValue[i][j] * graphicalFilterInterface.getValue(i,j);
            }
        }
        if (normalization != 0) filteredValue /= normalization;
        return filteredValue;
    }
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
