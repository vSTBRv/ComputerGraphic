package graphic;

import algorithms.SinglePointProcessing;
import enums.GreyScaleType;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
            case FrameMenu.COPY_TEXT -> copyLeftToRight();
            case FrameMenu.CLEAR_RIGHT_TEXT -> rightPanel.clearPanel();
            case FrameMenu.CLEAR_LEFT_TEXT -> leftPanel.clearPanel();
            case FrameMenu.TO_GREY_AVG_TEXT -> toGrey(GreyScaleType.Average);
            case FrameMenu.TO_GREY_RED_TEXT -> toGrey(GreyScaleType.Red);
            case FrameMenu.TO_GREY_GREEN_TEXT -> toGrey(GreyScaleType.Green);
            case FrameMenu.TO_GREY_BLUE_TEXT -> toGrey(GreyScaleType.Blue);
            case FrameMenu.TO_GREY_YUV_TEXT -> toGrey(GreyScaleType.YUV);
        }
    }

    private void toGrey(GreyScaleType type) {
        int with = rightPanel.canvas.getWidth();
        int height = rightPanel.canvas.getHeight();
        SinglePointProcessing toGreyAvg = new SinglePointProcessing(leftPanel.canvas);
        rightPanel.copy(toGreyAvg.toGreyScale(type));
        if (with != rightPanel.canvas.getWidth() || height != rightPanel.canvas.getHeight())
            matchTheContent();
    }

    private void copyLeftToRight() {
        int with = rightPanel.canvas.getWidth();
        int height = rightPanel.canvas.getHeight();
        rightPanel.copy(leftPanel.canvas);
        if (with != rightPanel.canvas.getWidth() || height != rightPanel.canvas.getHeight())
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
                "JPG, BMP & JPG",
                "jpg", "bmp", "jpg"
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
