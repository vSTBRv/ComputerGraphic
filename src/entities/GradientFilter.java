package entities;

import interfaces.GraphicalFilterInterface;

public class GradientFilter implements GraphicalFilterInterface {
    private final int size;
    private int[][] filter;
    public GradientFilter(){
        size = 2;
        filter = new int[size][size];
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
}
