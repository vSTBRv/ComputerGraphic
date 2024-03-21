package graphic;

import javax.swing.*;

public class FrameMenu extends JMenuBar {
    public static final String FILE_TEXT = "File";
    public static final String OPEN_FILE_TEXT = "Open file";
    public static final String CLOSE_TEXT = "Close";
    public static final String SAVE_FILE_TEXT = "Save file";
    public static final String COPY_TEXT = "Copy";
    public static final String LEFT_PANEL_TEXT = "Left panel";
    public static final String CLEAR_LEFT_TEXT = "Clear left panel";
    public static final String RIGHT_PANEL_TEXT = "Right panel";
    public static final String CLEAR_RIGHT_TEXT = "Clear right panel";
    public static final String SINGLE_POINT_PROCESSING_TEXT = "Single point processing";
    public static final String TO_GREY_AVG_TEXT = "To grey(avg.) scale";
    public static final String TO_GREY_RED_TEXT = "To grey(RED) scale";
    public static final String TO_GREY_GREEN_TEXT = "To grey(GREEN.) scale";
    public static final String TO_GREY_BLUE_TEXT = "To grey(BLUE) scale";
    public static final String TO_GREY_YUV_TEXT = "To grey(YUV) scale";

    JMenu file = new JMenu(FrameMenu.FILE_TEXT);
    JMenuItem openFile = new JMenuItem(FrameMenu.OPEN_FILE_TEXT);
    JMenuItem saveFile = new JMenuItem(FrameMenu.SAVE_FILE_TEXT);
    JMenuItem closeFile = new JMenuItem(FrameMenu.CLOSE_TEXT);
    JMenu leftPanel = new JMenu(FrameMenu.LEFT_PANEL_TEXT);
    JMenuItem clearLeft = new JMenuItem(FrameMenu.CLEAR_LEFT_TEXT);
    JMenuItem copy = new JMenuItem(FrameMenu.COPY_TEXT);
    JMenu rightPanel = new JMenu(FrameMenu.RIGHT_PANEL_TEXT);
    JMenuItem clearRight = new JMenuItem(FrameMenu.CLEAR_RIGHT_TEXT);
    JMenu singlePointProcessing = new JMenu(FrameMenu.SINGLE_POINT_PROCESSING_TEXT);
    JMenuItem toGreyAvgScale = new JMenuItem(FrameMenu.TO_GREY_AVG_TEXT);
    JMenuItem toGreyRedScale = new JMenuItem(FrameMenu.TO_GREY_RED_TEXT);
    JMenuItem toGreyGreenScale = new JMenuItem(FrameMenu.TO_GREY_GREEN_TEXT);
    JMenuItem toGreyBlueScale = new JMenuItem(FrameMenu.TO_GREY_BLUE_TEXT);
    JMenuItem toGreyYUVScale = new JMenuItem(FrameMenu.TO_GREY_YUV_TEXT);

    public FrameMenu(){
        file.add(openFile);
        file.add(saveFile);
        file.add(new JSeparator());
        file.add(closeFile);
        this.add(file);

        leftPanel.add(clearLeft);
        leftPanel.add(copy);
        this.add(leftPanel);

        rightPanel.add(clearRight);
        this.add(rightPanel);

        singlePointProcessing.add(toGreyAvgScale);
        singlePointProcessing.add(toGreyRedScale);
        singlePointProcessing.add(toGreyGreenScale);
        singlePointProcessing.add(toGreyBlueScale);
        singlePointProcessing.add(toGreyYUVScale);
        this.add(singlePointProcessing);
    }
}
