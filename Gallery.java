package Gallery;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Gallery extends JPanel {
    private ArrayList<Image> images = new ArrayList<>();
    private GridBagConstraints constraints = new GridBagConstraints();
    private JPanel galleryPanel = new JPanel(new GridBagLayout());
    private int width, height;
    private int countRows;
    private int countLines;
    private int point;
    private JLabel page;

    public Gallery(int width, int height, int countLines, int countRows, GalleryMode mode){
        this(width, height, mode);
        this.countLines = countLines;
        this.countRows = countRows;
    }

    public Gallery(int width, int height, GalleryMode mode){
        this.height = height;
        this.width = width;
        countLines = 2;
        countRows = 5;
        point = 0;
        setLayout(new BorderLayout());
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridy = 0;
        constraints.gridx = 0;
        constraints.insets = new Insets(2,2,2,2);

        page = new JLabel("Page 1 from " + (Math.max(images.size(), 1)/(countLines*countRows)+1));

        if(mode == GalleryMode.VERTICAL){
            JButton toTop = new JButton("↑");
            toTop.addActionListener(e -> {
                point = Math.max(0, point - countRows*countLines);
                update();
            });
            JButton toBottom = new JButton("↓");
            toBottom.addActionListener(e -> {
                if(point + countRows*countLines < images.size())
                    point = point + countRows*countLines;
                update();
            });

            toTop.setPreferredSize(new Dimension(width, (int)(height*0.05)));
            toTop.setMinimumSize(new Dimension(width, (int)(height*0.05)));
            toTop.setMaximumSize(new Dimension(width, (int)(height*0.05)));

            toBottom.setPreferredSize(new Dimension(width, (int)(height*0.05)));
            toBottom.setMinimumSize(new Dimension(width, (int)(height*0.05)));
            toBottom.setMaximumSize(new Dimension(width, (int)(height*0.05)));

            add(toTop, BorderLayout.NORTH);

            JPanel panel = new JPanel(new BorderLayout());
            panel.add(toBottom, BorderLayout.NORTH);
            panel.add(page, BorderLayout.SOUTH);

            add(panel, BorderLayout.SOUTH);
        }

        if(mode == GalleryMode.HORIZONTAL){
            JButton toRight = new JButton("→");
            toRight.addActionListener(e -> {
                if(point + countRows*countLines < images.size())
                    point = point + countRows*countLines;
                update();
            });
            JButton toLeft = new JButton("←");
            toLeft.addActionListener(e -> {
                point = Math.max(0, point - countRows*countLines);
                update();
            });

            toLeft.setPreferredSize(new Dimension((int)(width*0.1), (int)(height*0.9)/2));
            toLeft.setMinimumSize(new Dimension((int)(width*0.1), (int)(height*0.9)/2));
            toLeft.setMaximumSize(new Dimension((int)(width*0.1), (int)(height*0.9)/2));

            toRight.setPreferredSize(new Dimension((int)(width*0.1), (int)(height*0.9)/2));
            toRight.setMinimumSize(new Dimension((int)(width*0.1), (int)(height*0.9)/2));
            toRight.setMaximumSize(new Dimension((int)(width*0.1), (int)(height*0.9)/2));

            add(toRight, BorderLayout.EAST);
            add(toLeft, BorderLayout.WEST);
            add(page, BorderLayout.PAGE_END);
        }

        add(galleryPanel, BorderLayout.CENTER);
    }

    public void addImages(Image ... image){
        for(Image img : image){
            img.setHeight((int)(height*0.9)/2);
            img.setWidth((int)(width*0.9)/5);
            images.add(img);
        }
        update();
    }

    private void update(){
        galleryPanel.removeAll();
        int counter = 0;
        for(int i = point; i < countLines*countRows + point; i++){
            if(i < images.size()){
                constraints.gridy = counter/countRows;
                constraints.gridx = counter%countRows;
                galleryPanel.add(images.get(i), constraints);
            }
            counter ++;
        }
        repaint();revalidate();
        page.setText("Page " + (Math.max(point, 1)/(countLines*countRows)+1) + " from " + (Math.max(images.size(), 1)/(countLines*countRows)+1));
    }
}
