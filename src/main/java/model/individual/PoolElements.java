package model.individual;

import java.io.File;
import java.util.ResourceBundle;

public class PoolElements {
    public static final String projectDirectory = System.getProperty("user.dir");
    //sua lai directory image file later
    public static final String imgFolder = "/home/sherlock/IdeaProjects/OOLT.Group5.Optimization/src/main/resources/img";
    private Element[] elements = new Element[Element.MAX_ELEMENTS];
    public PoolElements(){
        // khoi tao 20 items theo y mink
        // set imgFIle and random weight to each element
        File[] listOfFiles = new File(imgFolder).listFiles();
        System.out.println(imgFolder);
        for (int i = 0; i < Element.MAX_ELEMENTS; i++) {
            double ranWeight = Math.random() * 3 + 1;
            ranWeight = (double)Math.round(ranWeight * 100) / 100;
            if (listOfFiles[i].isFile())
                elements[i] = new Item(ranWeight, listOfFiles[i].getName());
        }
    }

    public Element[] getElements(){
        return elements;
    }

    public Double[] extractWeightArray(){
        Double[] res = new Double[elements.length];
        for(int i = 0; i < elements.length; i++){
            res[i] = elements[i].getWeight();
        }
        return res;
    }

    public static void main(String[] args){
        Element[] elements = new PoolElements().getElements();
        for(int i = 0 ; i < Element.MAX_ELEMENTS; i++){
            System.out.println(elements[i].getWeight()+ " " + elements[i].getImageFile());
        }
    }
}
