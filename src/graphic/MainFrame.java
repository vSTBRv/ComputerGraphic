package graphic;

import algorithms.SinglePointProcessing;
import enums.GreyScaleType;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class MainFrame extends JFrame implements ActionListener {
    private static final String TITLE ="Computer graphic";
    private final FrameMenu menu = new FrameMenu();
    private final GraphicPanel leftPanel = new GraphicPanel();
    private final GraphicPanel rightPanel = new GraphicPanel();
    private String filePath;
    public MainFrame() throws HeadlessException{
        super(TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
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
        }
    }

    private void setBrightnessRange() {
        //TODO
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
        rightPanel.copy(image);
        int with = rightPanel.canvas.getWidth();
        int height = rightPanel.canvas.getHeight();
        if (with != rightPanel.canvas.getWidth() || height != rightPanel.canvas.getHeight()){
            matchTheContent();}
    }
    private void setContrast() {
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 200, 100);
        slider.setMajorTickSpacing(25);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        int result = JOptionPane.showConfirmDialog(null, slider, "Change contrast",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            double contrastValue = slider.getValue() / 100.0;
            SinglePointProcessing.getINSTANCE().loadImage(leftPanel.canvas);
            BufferedImage image = SinglePointProcessing.getINSTANCE().changeContrast(contrastValue);
            rightPanel.copy(image);
            int with = rightPanel.canvas.getWidth();
            int height = rightPanel.canvas.getHeight();
            if (with != rightPanel.canvas.getWidth() || height != rightPanel.canvas.getHeight()){
                matchTheContent();}
        }
    }
    private void setBrightness() {
        JSlider slider = new JSlider(JSlider.HORIZONTAL, -255, 255, 10);
        slider.setMajorTickSpacing(100);
        slider.setMinorTickSpacing(100);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        int result = JOptionPane.showConfirmDialog(null, slider, "Change brightness",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            int brightnessValue = slider.getValue();
            SinglePointProcessing.getINSTANCE().loadImage(leftPanel.canvas);
            BufferedImage image = SinglePointProcessing.getINSTANCE().changeBrightness(brightnessValue);
            rightPanel.copy(image);
            int with = rightPanel.canvas.getWidth();
            int height = rightPanel.canvas.getHeight();
            if (with != rightPanel.canvas.getWidth() || height != rightPanel.canvas.getHeight()){
            matchTheContent();}
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
