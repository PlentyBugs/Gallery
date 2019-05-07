package Gallery;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Image extends JPanel {

    private BufferedImage image;

    public Image(BufferedImage image){
        this.image = image;

        setPreferredSize(new Dimension(100,100));
        setMaximumSize(new Dimension(100,100));
        setMinimumSize(new Dimension(100,100));

        add(new JLabel(new ImageIcon(image)));
    }

    public BufferedImage getImage() {
        return image;
    }
}
