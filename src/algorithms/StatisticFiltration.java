package algorithms;

import entities.StatisticFilter;
import enums.StatisticFilterOption;

import java.awt.image.BufferedImage;

public class StatisticFiltration extends Filtration{
    private  final StatisticFilterOption statisticFilterOption;

    public StatisticFiltration(BufferedImage originalImage, StatisticFilter statisticFilter, StatisticFilterOption statisticFilterOption) {
        super(originalImage, statisticFilter);
        this.statisticFilterOption = statisticFilterOption;
    }
    @Override
    protected int filterValue(int[][] originalColorValue) {
        ((StatisticFilter)graphicalFilterInterface).setFilter(originalColorValue);
        return switch (statisticFilterOption){
            case  Median ->((StatisticFilter) graphicalFilterInterface).getMedian();
            case Maximum -> ((StatisticFilter) graphicalFilterInterface).getMaximum();
            case Minimum -> ((StatisticFilter) graphicalFilterInterface).getMinimum();
        };
    }
}
