package entities;

import interfaces.GraphicalFilterInterface;

public class GradientFilter implements GraphicalFilterInterface {
    private final int size;
    private int[][] filter;
    private int threshold;
    public GradientFilter(){
        size = 2;
        filter = new int[size][size];
        threshold = -1;
    }
    public void setFilter(int[][] filterValues){
        this.filter = filterValues;
    }
    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getValue(int i, int j) {
        return filter[i][j];
    }
    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }
}
