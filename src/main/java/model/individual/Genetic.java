package model.individual;

public class Genetic extends BackPack{

    public Genetic(Element[] elements){
        super(elements);
    };
    public double fitness(){
        if(this.getWeight() > MAX_WEIGHT)
            return 0.0;
        return (double)Math.round(this.getWeight() * 100) / 100;
    }

}
