package model;

public class BackPack implements Individual {
    private int numOfElement;
    private double weight;
    private Element[] elements = new Element[Element.MAX_ELEMENTS];
    public static final int MAX = 10;
    public static final int MIN = 3;

    public BackPack() {
        // mỗi khi tạo 1 backpack mới, thì elements trong cái backpack này được chọn ngẫu nhiên
        // lưu lại 10 items này vào 10 phần tử đầu của itemList for simple
        // element nào k có trong backpack thì để weight = 0, số lượng elements có weight != 0 nên là 10
        // nhớ tính toán weight của backpack và lưu lại
        this.numOfElement = ranRange(MIN, MAX);
        for (int i = 0; i < numOfElement; i++) {
            elements[i] = getNewRandomElement();
            this.weight += elements[i].getWeight();
        }
        for (int i = numOfElement; i < Element.MAX_ELEMENTS; i++)
            elements[i] = new Element(0, "");
    }

    public BackPack(BackPack bp) {
        this.numOfElement = bp.getNumOfElement();
        for (int i = 0; i < numOfElement; i++) {
            this.elements[i] = bp.getElements()[i];
            this.weight += this.elements[i].getWeight();
        }
        for (int i = numOfElement; i < Element.MAX_ELEMENTS; i++)
            elements[i] = new Element(0, "");
    }

    public static int ranRange(int lowerRange, int upperRange){
        return (int)(Math.random()*(upperRange - lowerRange)) + lowerRange;
    }

    //get new random element that not in Backpack yet
    public Element getNewRandomElement() {
        int ran = (int) (Math.random() * Element.MAX_ELEMENTS);
        int maxLoop = 0;
        while (isContain(PoolElements.getElements()[ran]) && ++maxLoop < 10) {
            ran = (int) (Math.random() * Element.MAX_ELEMENTS);
        }
        return PoolElements.getElements()[ran];
    }

    public double getWeight() {
        // round to 2 digit decimal
        return (double) Math.round(weight * 100) / 100;
    }

    public int getNumOfElement() {
        return this.numOfElement;
    }

    public Element[] getElements() {
        return elements;
    }

    public synchronized void updateElement(int i, double weight, String imgFile) {
        if (i >= 0 && i < Element.MAX_ELEMENTS) {
            double oldWei = elements[i].getWeight();
            elements[i] = new Element(weight, imgFile);
            this.weight += weight - oldWei;
        }
    }

    public boolean isContain(Element e) {
        if (numOfElement == 0)
            return false;
        for (int i = 0; i < numOfElement; i++) {
            if (elements[i] != null && elements[i].equals(e))
                return true;
        }
        return false;
    }

    public String toString() {
        StringBuilder sB = new StringBuilder();
        for (int i = 0; i < numOfElement; i++) {
            sB.append(elements[i].getImageFile());
            sB.append(", ");
        }
        sB.append(this.getWeight());
        return sB.toString();
    }

    public double fitness() {
        if (this.getWeight() > Individual.MAX_WEIGHT)
            return 0.0;
        return (double) Math.round(this.getWeight() * 100) / 100;
    }

    public static Element diffItem(BackPack b1, BackPack b2){
        for (Element e: b1.getElements()){
            if(!b2.isContain(e)){
                return e;
            }
        }
        return null;
    }

    public BackPack clone(){
        return new BackPack(this);
    }
}
