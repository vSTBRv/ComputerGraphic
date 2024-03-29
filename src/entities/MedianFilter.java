package entities;

import interfaces.GraphicalFilterInterface;

import java.util.Arrays;

public class MedianFilter implements GraphicalFilterInterface {
    private final int size;
    private int[][] filter;
    public MedianFilter(int size){
        this.size = size;
        filter = new int[size][size];
    }
    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getValue(int i, int j) {
        return filter[i][j];
    }

    public int findMedian(int[][] filter) {
        this.filter = filter;
        int index = 0;
        int[] tmp = new int[filter.length*filter[0].length];
        for (int i = 0; i < filter.length;i++){
            for (int j = 0; j < filter.length;j++){
                tmp[index++] = filter[i][j];
            }
        }
        Arrays.sort(tmp);
        return tmp[(tmp.length/2)+1];
    }
}