package entities;

import interfaces.GraphicalFilterInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MaskFromFile implements GraphicalFilterInterface {
    private final int size;
    private final int[][] filter;
    public MaskFromFile(String path) throws FileNotFoundException{
        File file = new File(path);
        int count = 0;
        Scanner in = new Scanner(file);
        while (in.hasNextLine()){
            in.nextLine();
            count++;
        }
        size = count;
        filter = new int [size][size];
        in = new Scanner(file);
        for (int i = 0;i<size; i++){
            for (int j = 0;j<size; j++){
                filter[i][j] = in.nextInt();
            }
        }
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