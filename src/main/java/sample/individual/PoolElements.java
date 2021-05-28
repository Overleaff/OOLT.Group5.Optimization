package sample.individual;

public class PoolElements {
    private Element[] elements;
    public PoolElements(){
        // khoi tao 20 items theo y mink
        this.elements = new Element[Element.MAX_ELEMENTS];
        elements[0].setImageFile("radio.png");
        elements[0].setWeight(1.23);
        //...
    }

    public Element[] getElements(){
        return elements;
    }
}
