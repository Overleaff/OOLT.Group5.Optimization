package model.individual;

public class State extends BackPack implements Individual{
    private Element[] elements;

    public State(Element[] elements){
        super(elements);
        this.elements = elements;
    }
}
