package model;

import java.io.File;

public class PoolElements {
    public static final String IMG_FOLDER = System.getProperty("user.dir") +
            (System.getProperty("os.name").equalsIgnoreCase("Window") ? "\\src\\main\\resources\\img" : "/src/main/resources/img");
    private static PoolElements singleton_instant = null;
    private Element[] elements;
    private PoolElements(){
        // khoi tao 20 items randomly
        // set imgFIle and random weight to each element
        elements = new Element[Element.MAX_ELEMENTS];

        File[] listOfFiles = new File(IMG_FOLDER).listFiles();
        if(listOfFiles == null)
            listOfFiles = new File(IMG_FOLDER).listFiles();
        for (int i = 0; i < Element.MAX_ELEMENTS; i++) {
            double ranWeight = Math.random() * 3 + 1;
            ranWeight = (double)Math.round(ranWeight * 100) / 100;
            if (listOfFiles[i].isFile())
                elements[i] = new Element(ranWeight, listOfFiles[i].getName());
        }
    }

    public static Element[] getElements(){
        if(PoolElements.singleton_instant == null){
            PoolElements.singleton_instant = new PoolElements();
        }
        return singleton_instant.elements;
    }
}
