package model.individual;

import java.io.File;
import java.util.ResourceBundle;

public class PoolElements {
    public static final String projectDirectory = System.getProperty("user.dir");
    //sua lai directory image file later
    public static final String imgFolder = "/home/sherlock/IdeaProjects/OOLT.Group5.Optimization/src/main/resources/img";
    private static Element[] elements;
    private PoolElements(){
        // khoi tao 20 items theo y mink
        // set imgFIle and random weight to each element
        elements = new Element[Element.MAX_ELEMENTS];
        File[] listOfFiles = new File(imgFolder).listFiles();
        for (int i = 0; i < Element.MAX_ELEMENTS; i++) {
            double ranWeight = Math.random() * 3 + 1;
            ranWeight = (double)Math.round(ranWeight * 100) / 100;
            if (listOfFiles[i].isFile())
                elements[i] = new Element(ranWeight, listOfFiles[i].getName());
        }
    }

    public static Element[] getElements(){
        return elements;
    }

    public static void main(String[] args){
        Element[] elements = new PoolElements().getElements();
        for(int i = 0 ; i < Element.MAX_ELEMENTS; i++){
            System.out.println(elements[i].getWeight()+ " " + elements[i].getImageFile());
        }
    }
}
