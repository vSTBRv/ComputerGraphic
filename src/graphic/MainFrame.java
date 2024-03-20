package graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {
    private static final String TITLE ="Computer graphic";
    private final FrameMenu menu = new FrameMenu();
    public MainFrame() throws HeadlessException{
        super(TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(
                new FlowLayout(FlowLayout.CENTER,2,2)
        );
        setJMenuBar(menu);
        matchTheContent();
        setVisible(true);
    }

    private void matchTheContent() {
        pack();
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
