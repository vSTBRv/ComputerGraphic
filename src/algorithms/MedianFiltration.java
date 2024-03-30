package algorithms;

import entities.MedianFilter;

import java.awt.image.BufferedImage;

public class MedianFiltration extends Filtration{

    protected MedianFiltration(BufferedImage originalImage, MedianFilter medianFilter) {
        super(originalImage, medianFilter);
    }

    @Override
    protected int filterValue(int[][] originalColorValue) {
        ((MedianFilter)graphicalFilterInterface).setFilter(originalColorValue);
        return ((MedianFilter)graphicalFilterInterface).getMedian();
    }
}
