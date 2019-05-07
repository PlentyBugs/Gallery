package Gallery;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Image extends JPanel {

    private BufferedImage image;
    private int width, height;

    public Image(BufferedImage image){
        this.image = image;
        height = 100;
        width = 100;

        JLabel label = new JLabel(new ImageIcon(resizeImage(image, BufferedImage.TYPE_INT_ARGB)));

        add(label);
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int type){
        if(originalImage.getWidth() > width || originalImage.getHeight() > height){

            double koeff = Math.min(((double)width)/((double)originalImage.getWidth()), ((double)height)/((double)originalImage.getHeight()));

            int width = (int)(originalImage.getWidth()*koeff);
            int height = (int)(originalImage.getHeight()*koeff);

            BufferedImage resizedImage = new BufferedImage(width, height, type);
            Graphics2D g = resizedImage.createGraphics();
            g.drawImage(originalImage, 0, 0, width, height, null);
            g.dispose();

            return resizedImage;
        }
        return originalImage;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
