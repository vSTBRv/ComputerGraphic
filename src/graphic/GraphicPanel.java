package graphic;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GraphicPanel extends JPanel {
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 400;
    private static final Color DEFAULT_BACKGROUND = Color.gray;
    private static final Color DEFAULT_BORDER = Color.black;
    private static final String LOAD_FILE_ERROR = "Load file error";
    private static final String SAVE_FILE_ERROR = "save file error";

    BufferedImage canvas;

    public GraphicPanel(){
        super();
        setPanelSize(
                new Dimension(GraphicPanel.DEFAULT_WIDTH,GraphicPanel.DEFAULT_HEIGHT)
        );
        clearPanel();
    }

    public void clearPanel() {
        Graphics2D g = (Graphics2D) canvas.getGraphics();
        g.setColor(GraphicPanel.DEFAULT_BACKGROUND);
        g.fillRect(0,0,canvas.getWidth(), canvas.getHeight());
        setBorder(BorderFactory.createLineBorder(GraphicPanel.DEFAULT_BORDER));
        repaint();
    }

    public void setPanelSize(Dimension dimension) {
        createCanvas(dimension);
        packPanel(dimension);
    }

    public void packPanel(Dimension dimension) {
        setPreferredSize(dimension);
        setMaximumSize(dimension);
    }

    public void createCanvas(Dimension dimension) {
        canvas = new BufferedImage(
                (int) dimension.getWidth(),
                (int) dimension.getHeight(),
                BufferedImage.TYPE_INT_RGB
        );
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(canvas,0,0,this);
    }

    public void loadFile(String filePath){
        try {
            File file = new File(filePath);
            canvas = ImageIO.read(file);
            Dimension dimension = new Dimension(canvas.getWidth(), canvas.getHeight());
            packPanel(dimension);
            setBorder(BorderFactory.createLineBorder(GraphicPanel.DEFAULT_BORDER));
            repaint();
        }catch (IOException e){
            JOptionPane.showMessageDialog(null,GraphicPanel.LOAD_FILE_ERROR + filePath);
        }
    }

    public void saveFile(String filePath){
        try{
            File file = new File(filePath);
            if(canvas!=null){
                boolean result = ImageIO.write(
                        canvas,
                        filePath.substring(filePath.lastIndexOf('.')+1),
                        file
                );
                if (!result){
                    JOptionPane.showMessageDialog(null,GraphicPanel.SAVE_FILE_ERROR + filePath);
                }
            }else {
                JOptionPane.showMessageDialog(null,GraphicPanel.SAVE_FILE_ERROR + filePath);
            }
        }catch (IOException e){
            JOptionPane.showMessageDialog(null,GraphicPanel.SAVE_FILE_ERROR  + filePath);
        }
    }
    public void copy(BufferedImage input){
        setPanelSize(new Dimension(input.getWidth(),input.getHeight()));
        Color color;
        int r, g, b;

        for (int w = 0; w < input.getWidth(); w++){
            for (int h = 0; h < input.getHeight(); h++){
                color = new Color(input.getRGB(w,h));
                r = color.getRed();
                g = color.getGreen();
                b = color.getBlue();

                canvas.setRGB(
                        w, h, new Color(r, g, b).getRGB()
                );
            }
        }
        repaint();
    }
}