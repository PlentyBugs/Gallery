package Gallery;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Window extends JFrame {

    private Gallery gallery;

    public Window(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        gallery = new Gallery();

        for(int i = 1; i < 7; i++){
            try {
                BufferedImage image = ImageIO.read(new File("./images/image" + i + ".png"));
                gallery.addImages(new Image(image));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        getContentPane().add(gallery);
        pack();
        setVisible(true);
    }
}
