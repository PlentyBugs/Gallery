package Gallery;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Gallery extends JPanel {
    private ArrayList<Image> images = new ArrayList<>();
    private GridBagConstraints constraints = new GridBagConstraints();

    public Gallery(){
        setLayout(new GridBagLayout());
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridy = 0;
        constraints.gridx = 0;
        constraints.insets = new Insets(5,5,5,5);
    }

    public void addImages(Image ... image){
        images.addAll(Arrays.asList(image));
        update();
    }

    private void update(){

        for(int i = 0; i < images.size(); i++){
            Image image = images.get(i);
            if (!Arrays.asList(getComponents()).contains(image)){
                constraints.gridy = i/5;
                constraints.gridx = i%5;
                add(image, constraints);
            }
        }
    }
}
