package entities;

import interfaces.GraphicalFilterInterface;

import java.util.Arrays;

public class StatisticFilter implements GraphicalFilterInterface {
    private final int size;
    private int[][] filter;
    public StatisticFilter(int size){
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

    public void setFilter(int[][] filter) {
        this.filter = filter;
    }

    public int getMedian() {
        int[] tmp = sorted2Dto1D();
        return tmp[(tmp.length/2)+1];
    }
    public int getMaximum(){
        int[] tmp = sorted2Dto1D();
        return tmp[tmp.length-1];
    }
    public int getMinimum(){
        int[] tmp = sorted2Dto1D();
        return tmp[0];
    }
    private int[] sorted2Dto1D(){
        int index = 0;
        int [] tmp = new int[size*size];
        for (int i = 0; i < filter.length;i++){
            for (int j = 0; j < filter.length;j++){
                tmp[index++] = getValue(i,j);
            }
        }
        Arrays.sort(tmp);
        return tmp;
    }
}
