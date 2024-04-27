package graphic;

import javax.swing.*;

public class FrameMenu extends JMenuBar {
    public static final String FILE_TEXT = "File";
    public static final String OPEN_FILE_TEXT = "Open file";
    public static final String CLOSE_TEXT = "Close";
    public static final String SAVE_FILE_TEXT = "Save file";
    public static final String COPY_TEXT = "Copy";
    public static final String CLEAR_LEFT_TEXT = "Clear left panel";
    public static final String CLEAR_RIGHT_TEXT = "Clear right panel";
    public static final String SINGLE_POINT_PROCESSING_TEXT = "Single point processing";
    public static final String TO_GREY_AVG_TEXT = "by average scale";
    public static final String TO_GREY_RED_TEXT = "by red scale";
    public static final String TO_GREY_GREEN_TEXT = "by green scale";
    public static final String TO_GREY_BLUE_TEXT = "by blue scale";
    public static final String TO_GREY_YUV_TEXT = "by YUV scale";
    public static final String CHANGE_BRIGHTNESS_TEXT = "Change brightness";
    public static final String CHANGE_CONTRAST_TEXT = "Change contrast";
    public static final String NEGATION_TEXT = "Negation";
    public static final String CHANGE_BRIGHTNESS_RANGE_TEXT = "Change brightness range";
    public static final String FILTERING = "Filtering";
    public static final String MEDIAN_FILTERING = "Median filter";
    public static final String MASK_FROM_FILE = "Mask from file";
    public static final String GRADIENT_FILTER = "Gradient filter";
    public static final String SIMPLE_GRADIENT_ABSOLUT = "Simple gradient (abs)";
    public static final String ROBERTS_GRADIENT_ABSOLUT = "Robert's gradient (abs)";
    public static final String SIMPLE_GRADIENT_SQR = "Simple gradient (sqr)";
    public static final String ROBERTS_GRADIENT_SQR = "Robert's gradient (sqr)";
    public static final String TO_GREY_SCALE = "To grey scale";
    public static final String WHITE_BACKGROUND = "White background";
    public static final String BLACK_EDGES = "Black edges";
    public static final String BLACK_EDGES_WHITE_BACKGROUND = "Black edges with white background";

    JMenu file = new JMenu(FrameMenu.FILE_TEXT);
    JMenuItem openFile = new JMenuItem(FrameMenu.OPEN_FILE_TEXT);
    JMenuItem saveFile = new JMenuItem(FrameMenu.SAVE_FILE_TEXT);
    JMenuItem closeFile = new JMenuItem(FrameMenu.CLOSE_TEXT);
    JMenuItem clearLeft = new JMenuItem(FrameMenu.CLEAR_LEFT_TEXT);
    JMenuItem copy = new JMenuItem(FrameMenu.COPY_TEXT);
    JMenuItem clearRight = new JMenuItem(FrameMenu.CLEAR_RIGHT_TEXT);
    JMenu singlePointProcessing = new JMenu(FrameMenu.SINGLE_POINT_PROCESSING_TEXT);
    JMenuItem toGreyAvgScale = new JMenuItem(FrameMenu.TO_GREY_AVG_TEXT);
    JMenuItem toGreyRedScale = new JMenuItem(FrameMenu.TO_GREY_RED_TEXT);
    JMenuItem toGreyGreenScale = new JMenuItem(FrameMenu.TO_GREY_GREEN_TEXT);
    JMenuItem toGreyBlueScale = new JMenuItem(FrameMenu.TO_GREY_BLUE_TEXT);
    JMenuItem toGreyYUVScale = new JMenuItem(FrameMenu.TO_GREY_YUV_TEXT);
    JMenuItem changeBrightness = new JMenuItem(FrameMenu.CHANGE_BRIGHTNESS_TEXT);
    JMenuItem changeContrast = new JMenuItem(FrameMenu.CHANGE_CONTRAST_TEXT);
    JMenuItem negation = new JMenuItem(FrameMenu.NEGATION_TEXT);
    JMenuItem changeBrightnessRange = new JMenuItem(FrameMenu.CHANGE_BRIGHTNESS_RANGE_TEXT);
    JMenu filtering = new JMenu(FrameMenu.FILTERING);
    JMenu gradientFiltering = new JMenu(FrameMenu.GRADIENT_FILTER);
    JMenuItem simpleGradientAbs = new JMenuItem(FrameMenu.SIMPLE_GRADIENT_ABSOLUT);
    JMenuItem robertsGradientAbs = new JMenuItem(FrameMenu.ROBERTS_GRADIENT_ABSOLUT);
    JMenuItem simpleGradientSqr = new JMenuItem(FrameMenu.SIMPLE_GRADIENT_SQR);
    JMenuItem robertsGradientSqr = new JMenuItem(FrameMenu.ROBERTS_GRADIENT_SQR);
    JMenuItem medianFiltering = new JMenuItem(FrameMenu.MEDIAN_FILTERING);
    JMenuItem maskFromFile = new JMenuItem(FrameMenu.MASK_FROM_FILE);
    JMenu toGreyScale = new JMenu(FrameMenu.TO_GREY_SCALE);
    JMenuItem whiteBackground = new JMenuItem(FrameMenu.WHITE_BACKGROUND);
    JMenuItem blackEdge = new JMenuItem(FrameMenu.BLACK_EDGES);
    JMenuItem blackEdgesWithWhiteBackground = new JMenuItem(FrameMenu.BLACK_EDGES_WHITE_BACKGROUND);

    public FrameMenu(){
        file.add(openFile);
        file.add(saveFile);
        file.add(new JSeparator());
        file.add(copy);
        file.add(clearLeft);
        file.add(clearRight);
        file.add(new JSeparator());
        file.add(closeFile);
        this.add(file);


        toGreyScale.add(toGreyAvgScale);
        toGreyScale.add(toGreyRedScale);
        toGreyScale.add(toGreyGreenScale);
        toGreyScale.add(toGreyBlueScale);
        toGreyScale.add(toGreyYUVScale);
        singlePointProcessing.add(toGreyScale);
        singlePointProcessing.add(changeBrightness);
        singlePointProcessing.add(changeContrast);
        singlePointProcessing.add(negation);
        singlePointProcessing.add(changeBrightnessRange);
        this.add(singlePointProcessing);

        filtering.add(maskFromFile);
        filtering.add(medianFiltering);
        gradientFiltering.add(simpleGradientAbs);
        gradientFiltering.add(simpleGradientSqr);
        gradientFiltering.add(robertsGradientAbs);
        gradientFiltering.add(robertsGradientSqr);
        filtering.add(gradientFiltering);
        filtering.add(whiteBackground);
        filtering.add(blackEdge);
        filtering.add(blackEdgesWithWhiteBackground);
        this.add(filtering);
    }
}
