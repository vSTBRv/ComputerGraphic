package graphic;

import algorithms.FiltrationFromFile;
import algorithms.GradientFiltration;
import algorithms.MedianFiltration;
import algorithms.SinglePointProcessing;
import entities.GradientFilter;
import entities.MaskFromFile;
import entities.MedianFilter;
import enums.GradientCalculationType;
import enums.GradientType;
import enums.GreyScaleType;

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
        menu.medianFiltering.addActionListener(this);
        menu.simpleGradientAbs.addActionListener(this);
        menu.simpleGradientSqr.addActionListener(this);
        menu.robertsGradientAbs.addActionListener(this);
        menu.robertsGradientSqr.addActionListener(this);
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
            case FrameMenu.MEDIAN_FILTERING -> useMedianFilter();
            case FrameMenu.SIMPLE_GRADIENT_ABSOLUT -> useGradientFilter(GradientType.simple, GradientCalculationType.absolut);
            case FrameMenu.SIMPLE_GRADIENT_SQR -> useGradientFilter(GradientType.simple, GradientCalculationType.sqrRoot);
            case FrameMenu.ROBERTS_GRADIENT_ABSOLUT -> useGradientFilter(GradientType.Roberts, GradientCalculationType.absolut);
            case FrameMenu.ROBERTS_GRADIENT_SQR -> useGradientFilter(GradientType.Roberts, GradientCalculationType.sqrRoot);
        }
    }

    private void useGradientFilter(GradientType gradientType, GradientCalculationType gradientCalculationType) {
        int with = rightPanel.canvas.getWidth();
        int height = rightPanel.canvas.getHeight();
        GradientFiltration gradientFiltration = new GradientFiltration(leftPanel.canvas,new GradientFilter(),gradientType,gradientCalculationType);
        rightPanel.copy(gradientFiltration.filterImage());
        if (with != rightPanel.canvas.getWidth() || height != rightPanel.canvas.getHeight())
            matchTheContent();
    }

    private void useMedianFilter() {
        int with = rightPanel.canvas.getWidth();
        int height = rightPanel.canvas.getHeight();
        MedianFiltration medianFiltration = new MedianFiltration(leftPanel.canvas, new MedianFilter(3));
        rightPanel.copy(medianFiltration.filterImage());
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
