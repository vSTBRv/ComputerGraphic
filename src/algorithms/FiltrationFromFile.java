package algorithms;

import entities.MaskFromFile;

import java.awt.image.BufferedImage;

public class FiltrationFromFile extends Filtration{
    public FiltrationFromFile(BufferedImage originalImage, MaskFromFile maskFromFile) {
        super(originalImage, maskFromFile );
    }

    @Override
    protected int filterValue(int[][] originalColorValue) {
        int filteredValue = 0;
        for (int i = 0; i < graphicalFilterInterface.getSize(); i++){
            for (int j = 0; j < graphicalFilterInterface.getSize(); j++){
                filteredValue += originalColorValue[i][j] * graphicalFilterInterface.getValue(i,j);
                if (normalization != 0) filteredValue /= normalization;
            }
        }
        return filteredValue;
    }

}
