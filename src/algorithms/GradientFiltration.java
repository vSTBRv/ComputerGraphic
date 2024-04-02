package algorithms;

import entities.GradientFilter;
import enums.GradientCalculationType;
import enums.GradientType;

import java.awt.image.BufferedImage;

public class GradientFiltration extends Filtration{
    private GradientType gradientType;
    private GradientCalculationType gradientCalculationType;
    protected GradientFiltration(BufferedImage originalImage, GradientFilter gradientFilter) {
        super(originalImage, gradientFilter);
    }

    @Override
    protected int filterValue(int[][] originalColorValue) {
        return switch (gradientType) {
            case simple -> simpleGradientValue();
            case Roberts -> robertsGradientValue();
        };
    }

    public void setGradientType(GradientType gradientType) {
        this.gradientType = gradientType;
    }

    public void setGradientCalculationType(GradientCalculationType gradientCalculationType) {
        this.gradientCalculationType = gradientCalculationType;
    }
    private int simpleGradientValue(){
        return switch (gradientCalculationType) {
            case absolut -> Math.abs(
                    graphicalFilterInterface.getValue(0, 0) - graphicalFilterInterface.getValue(1, 0)
            ) + Math.abs(
                    graphicalFilterInterface.getValue(0, 0) - graphicalFilterInterface.getValue(0, 1)
            );
            case sqrRoot -> (int) Math.sqrt(
                    Math.pow(
                            graphicalFilterInterface.getValue(0, 0) - graphicalFilterInterface.getValue(1, 0), 2
                    ) + Math.pow(
                            graphicalFilterInterface.getValue(0, 0) - graphicalFilterInterface.getValue(0, 1), 2
                    )
            );
        };
    }
    private int robertsGradientValue() {
        return switch (gradientCalculationType) {
            case absolut -> Math.abs(
                    graphicalFilterInterface.getValue(0,0) - graphicalFilterInterface.getValue(1,1)
            ) + Math.abs(
                    graphicalFilterInterface.getValue(0,1) - graphicalFilterInterface.getValue(1,0)
            );
            case sqrRoot -> (int) Math.sqrt(
                    Math.pow(
                            graphicalFilterInterface.getValue(0,0) - graphicalFilterInterface.getValue(1,1),2
                    ) + Math.pow(
                            graphicalFilterInterface.getValue(0,1) - graphicalFilterInterface.getValue(1,0),2
                    )
            );
        };
    }
}