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

    JMenu file = new JMenu(FrameMenu.FILE_TEXT);
    JMenuItem openFile = new JMenuItem(FrameMenu.OPEN_FILE_TEXT);
    JMenuItem saveFile = new JMenuItem(FrameMenu.SAVE_FILE_TEXT);
    JMenuItem closeFile = new JMenuItem(FrameMenu.CLOSE_TEXT);
    JMenu leftPanel = new JMenu(FrameMenu.LEFT_PANEL_TEXT);
    JMenuItem clearLeft = new JMenuItem(FrameMenu.CLEAR_LEFT_TEXT);
    JMenuItem copy = new JMenuItem(FrameMenu.COPY_TEXT);
    JMenu rightPanel = new JMenu(FrameMenu.RIGHT_PANEL_TEXT);
    JMenuItem clearRight = new JMenuItem(FrameMenu.CLEAR_RIGHT_TEXT);

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
    }
}
