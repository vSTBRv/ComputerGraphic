package graphic;

import algorithms.*;
import entities.GradientFilter;
import entities.MaskFromFile;
import entities.StatisticFilter;
import enums.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;

public class MainFrame extends JFrame implements ActionListener {
    private static final String TITLE ="Computer graphic";
    private final FrameMenu menu = new FrameMenu();
    private final GraphicPanel leftPanel = new GraphicPanel();
    private final GraphicPanel rightPanel = new GraphicPanel();
    private String filePath;
    public MainFrame() throws HeadlessException{
        super(TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setLayout(
                new FlowLayout(FlowLayout.CENTER,2,2)
        );
        setJMenuBar(menu);
        add(leftPanel);
        add(rightPanel);
        setUpEventListener();
        matchTheContent();
        setVisible(true);
    }

    private void setUpEventListener() {
        menu.openFile.addActionListener(this);
        menu.saveFile.addActionListener(this);
        menu.closeFile.addActionListener(this);
        menu.copy.addActionListener(this);
        menu.clearLeft.addActionListener(this);
        menu.clearRight.addActionListener(this);
        menu.toGreyAvgScale.addActionListener(this);
        menu.toGreyRedScale.addActionListener(this);
        menu.toGreyGreenScale.addActionListener(this);
        menu.toGreyBlueScale.addActionListener(this);
        menu.toGreyYUVScale.addActionListener(this);
        menu.changeBrightness.addActionListener(this);
        menu.changeContrast.addActionListener(this);
        menu.changeBrightnessRange.addActionListener(this);
        menu.negation.addActionListener(this);
        menu.maskFromFile.addActionListener(this);
        menu.minValueFilter.addActionListener(this);
        menu.medianFiltering.addActionListener(this);
        menu.maxValueFilter.addActionListener(this);
        menu.simpleGradientAbs.addActionListener(this);
        menu.simpleGradientSqr.addActionListener(this);
        menu.robertsGradientAbs.addActionListener(this);
        menu.robertsGradientSqr.addActionListener(this);
        menu.whiteBackground.addActionListener(this);
        menu.blackEdge.addActionListener(this);
        menu.blackEdgesWithWhiteBackground.addActionListener(this);
    }

    private void matchTheContent() {
        pack();
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String label = e.getActionCommand();
        switch (label){
            case FrameMenu.OPEN_FILE_TEXT -> openFile();
            case FrameMenu.SAVE_FILE_TEXT -> saveFile();
            case FrameMenu.CLOSE_TEXT -> System.exit(0);
            case FrameMenu.COPY_TEXT -> copyRightToLeft();
            case FrameMenu.CLEAR_RIGHT_TEXT -> rightPanel.clearPanel();
            case FrameMenu.CLEAR_LEFT_TEXT -> leftPanel.clearPanel();
            case FrameMenu.TO_GREY_AVG_TEXT -> toGrey(GreyScaleType.Average);
            case FrameMenu.TO_GREY_RED_TEXT -> toGrey(GreyScaleType.Red);
            case FrameMenu.TO_GREY_GREEN_TEXT -> toGrey(GreyScaleType.Green);
            case FrameMenu.TO_GREY_BLUE_TEXT -> toGrey(GreyScaleType.Blue);
            case FrameMenu.TO_GREY_YUV_TEXT -> toGrey(GreyScaleType.YUV);
            case FrameMenu.CHANGE_BRIGHTNESS_TEXT -> setBrightness();
            case FrameMenu.CHANGE_CONTRAST_TEXT -> setContrast();
            case FrameMenu.CHANGE_BRIGHTNESS_RANGE_TEXT -> setBrightnessRange();
            case FrameMenu.NEGATION_TEXT -> negation();
            case FrameMenu.MASK_FROM_FILE -> useMaskFromFile();
            case FrameMenu.MIN_VALUE_FILTER -> useStatisticFilter(StatisticFilterOption.Minimum);
            case FrameMenu.MEDIAN_FILTER -> useStatisticFilter(StatisticFilterOption.Median);
            case FrameMenu.MAX_VALUE_FILTER -> useStatisticFilter(StatisticFilterOption.Maximum);
            case FrameMenu.SIMPLE_GRADIENT_ABSOLUT -> useGradientFilter(GradientType.simple, GradientCalculationType.absolut,null);
            case FrameMenu.SIMPLE_GRADIENT_SQR -> useGradientFilter(GradientType.simple, GradientCalculationType.sqrRoot,null);
            case FrameMenu.ROBERTS_GRADIENT_ABSOLUT -> useGradientFilter(GradientType.Roberts, GradientCalculationType.absolut,null);
            case FrameMenu.ROBERTS_GRADIENT_SQR -> useGradientFilter(GradientType.Roberts, GradientCalculationType.sqrRoot,null);
            case FrameMenu.WHITE_BACKGROUND -> useGradientFilter(GradientType.simple, GradientCalculationType.absolut,GradientFiltrationOptions.White_background);
            case FrameMenu.BLACK_EDGES -> useGradientFilter(GradientType.simple,GradientCalculationType.absolut,GradientFiltrationOptions.Black_edges);
            case FrameMenu.BLACK_EDGES_WHITE_BACKGROUND -> useGradientFilter(GradientType.simple, GradientCalculationType.absolut,GradientFiltrationOptions.Black_edges_white_background);
        }
    }

    private void useGradientFilter(GradientType gradientType, GradientCalculationType gradientCalculationType, GradientFiltrationOptions gradientFiltrationOptions) {
        GradientFilter gradientFilter = new GradientFilter();
        int with = rightPanel.canvas.getWidth();
        int height = rightPanel.canvas.getHeight();
        if(gradientFiltrationOptions != null){
            String value = JOptionPane.showInputDialog("Enter threshold value");
            if (value != null) {
                try {
                    int threshold = Integer.parseInt(value);
                    gradientFilter.setThreshold(threshold);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Threshold value must be an integer");
                }
            }
        }
        if(gradientFilter.getThreshold() > -1) {
            GradientFiltrationWithThreshold gradientFiltrationWithThreshold = new GradientFiltrationWithThreshold(leftPanel.canvas, gradientFilter, gradientType, gradientCalculationType,gradientFiltrationOptions);
            rightPanel.copy(gradientFiltrationWithThreshold.filterImage());
        }else {
            GradientFiltration gradientFiltration = new GradientFiltration(leftPanel.canvas, gradientFilter, gradientType, gradientCalculationType);
            rightPanel.copy(gradientFiltration.filterImage());
        }
        if (with != rightPanel.canvas.getWidth() || height != rightPanel.canvas.getHeight()) {
            matchTheContent();
        }
    }

    private void useStatisticFilter(StatisticFilterOption statisticFilterOption) {
        int with = rightPanel.canvas.getWidth();
        int height = rightPanel.canvas.getHeight();
        StatisticFiltration statisticFiltration = new StatisticFiltration(leftPanel.canvas, new StatisticFilter(3), statisticFilterOption);
        rightPanel.copy(statisticFiltration.filterImage());
        if (with != rightPanel.canvas.getWidth() || height != rightPanel.canvas.getHeight())
            matchTheContent();
    }

    private void useMaskFromFile() {
        int with = rightPanel.canvas.getWidth();
        int height = rightPanel.canvas.getHeight();
        String path;
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "TXT file",
                "txt"
        );
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            path = fileChooser.getSelectedFile().getPath();
            try {
                FiltrationFromFile filtrationFromFile = new FiltrationFromFile(leftPanel.canvas, new MaskFromFile(path));
                rightPanel.copy(filtrationFromFile.filterImage());
                if (with != rightPanel.canvas.getWidth() || height != rightPanel.canvas.getHeight())
                    matchTheContent();
            } catch (FileNotFoundException e){
                System.out.println(e.getMessage());
            }
        }
    }

    private void setBrightnessRange() {
        int with = rightPanel.canvas.getWidth();
        int height = rightPanel.canvas.getHeight();
        SinglePointProcessing.getINSTANCE().loadImage(leftPanel.canvas);
        BufferedImage greyImage = SinglePointProcessing.getINSTANCE().changeBrightnessRange();
        rightPanel.copy(greyImage);
        if (with != rightPanel.canvas.getWidth() || height != rightPanel.canvas.getHeight())
            matchTheContent();
    }

    private void toGrey(GreyScaleType type) {
        int with = rightPanel.canvas.getWidth();
        int height = rightPanel.canvas.getHeight();
        SinglePointProcessing.getINSTANCE().loadImage(leftPanel.canvas);
        BufferedImage greyImage = SinglePointProcessing.getINSTANCE().toGreyScale(type);
        rightPanel.copy(greyImage);
        if (with != rightPanel.canvas.getWidth() || height != rightPanel.canvas.getHeight())
            matchTheContent();
    }
    private void negation(){
        SinglePointProcessing.getINSTANCE().loadImage(leftPanel.canvas);
        BufferedImage image = SinglePointProcessing.getINSTANCE().negation();
        int with = rightPanel.canvas.getWidth();
        int height = rightPanel.canvas.getHeight();
        rightPanel.copy(image);
        if (with != rightPanel.canvas.getWidth() || height != rightPanel.canvas.getHeight()){
            matchTheContent();}
    }
    private void setContrast() {
        String value =  JOptionPane.showInputDialog("Enter a positive value");
        int with = rightPanel.canvas.getWidth();
        int height = rightPanel.canvas.getHeight();
        if (value != null) {
            try {
                double contrastValue = Double.parseDouble(value);
                if(contrastValue > 0) {
                    SinglePointProcessing.getINSTANCE().loadImage(leftPanel.canvas);
                    BufferedImage image = SinglePointProcessing.getINSTANCE().changeContrast(contrastValue);
                    rightPanel.copy(image);
                    if (with != rightPanel.canvas.getWidth() || height != rightPanel.canvas.getHeight()) {
                        matchTheContent();
                    }
                }else JOptionPane.showMessageDialog(null,"Entered invalid value");
            } catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null,"Invalid value");
            }
        }
    }
    private void setBrightness() {
        String value = JOptionPane.showInputDialog("Enter value");
        int with = rightPanel.canvas.getWidth();
        int height = rightPanel.canvas.getHeight();
        if(value != null){
            try{
                int brightnessValue = Integer.parseInt(value);
                if(-255 < brightnessValue && brightnessValue < 255){
                    SinglePointProcessing.getINSTANCE().loadImage(leftPanel.canvas);
                    BufferedImage image = SinglePointProcessing.getINSTANCE().changeBrightness(brightnessValue);
                    rightPanel.copy(image);
                    if (with != rightPanel.canvas.getWidth() || height != rightPanel.canvas.getHeight()){
                        matchTheContent();}
                } else JOptionPane.showMessageDialog(null,"Enter a value between -255 and 255");
            }catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null,"Invalid value");
            }
        }
    }

    private void copyRightToLeft() {
        int with = leftPanel.canvas.getWidth();
        int height = leftPanel.canvas.getHeight();
        leftPanel.copy(rightPanel.canvas);
        if (with != leftPanel.canvas.getWidth() || height != leftPanel.canvas.getHeight())
            matchTheContent();
    }

    private void saveFile() {
        JFileChooser fileChooser;
        if (filePath != null){
            fileChooser = new JFileChooser(filePath);
        }else {
            fileChooser = new JFileChooser();
        }
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG, BMP & PNG",
                "jpg", "bmp", "PNG"
        );
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION){
            filePath = fileChooser.getSelectedFile().getPath();
            rightPanel.saveFile(filePath);
        }
    }

    private void openFile(){
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG, BMP & PNG Images",
                "jpg", "bpm", "png"
        );
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION){
            filePath = fileChooser.getSelectedFile().getPath();
            int width = leftPanel.canvas.getWidth();
            int height = leftPanel.canvas.getHeight();
            leftPanel.loadFile(filePath);
            if (width!=leftPanel.canvas.getWidth() || height != leftPanel.canvas.getHeight())
                matchTheContent();
        }
    }
}
