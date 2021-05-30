package model.individual;

public class State extends BackPack{
    private Element[] elements;

    public State(Element[] elements){
        super(elements);
        this.elements = elements;
    }

    public Double[] extractWeightArray(){
        Double[] res = new Double[elements.length];
        for(int i = 0; i < elements.length; i++){
            res[i] = elements[i].getWeight();
        }
        return res;
    }
}
