package model.individual;

public class BackPack implements Individual {
	private int numOfElement;
	private double weight;
	private Element[] elementsList = new Element[Element.MAX_ELEMENTS];
	private Element[] elements;

	public BackPack(Element[] elements){
		// mỗi khi tạo 1 backpack mới, thì elements trong cái backpack này được chọn ngẫu nhiên
		// lưu lại 10 items này vào 10 phần tử đầu của itemList for simple
		// element nào k có trong backpack thì để weight = 0, số lượng elements có weight != 0 nên là 10
		// nhớ tính toán weight của backpack và lưu lại
		this.elements = elements;
		for(int i = 0; i < Element.MAX_ELEMENTS/2; i++){
			elementsList[i] = getNewRandomElement();
			this.weight += elementsList[i].getWeight();
			numOfElement++;
		}
		for(int i = Element.MAX_ELEMENTS/2; i < Element.MAX_ELEMENTS; i++)
			elementsList[i] = new Element(0,"");
	}

	public Element getNewRandomElement(){
		int ran = (int)(Math.random()*Element.MAX_ELEMENTS);
		int maxLoop = 0;
		while(isContain(elements[ran]) && ++maxLoop < 10) {
			ran = (int) (Math.random() * Element.MAX_ELEMENTS);
		}
		return elements[ran];
	}

	public double getWeight() {
		return (double)Math.round(weight * 100) / 100;
	}

	public int getNumOfElement(){
		return this.numOfElement;
	}

	public Element[] getElements() {
		return elementsList;
	}

	public synchronized void updateElement(int i, double weight, String imgFile)
	{
		if(i >= 0 && i < Element.MAX_ELEMENTS){
			double oldWei = elementsList[i].getWeight();
			elementsList[i] = new Element(weight, imgFile);
			this.weight += weight - oldWei;
		}
	}

	public boolean isContain(Element e){
		if(numOfElement == 0)
			return false;
		for(int i = 0; i< numOfElement; i++){
			if(elementsList[i].equals(e))
				return true;
		}
		return false;
	}

	public String toString(){
		StringBuilder sB = new StringBuilder();
		for(int i = 0; i < numOfElement; i++){
			sB.append(elementsList[i].getImageFile());
			sB.append(", ");
		}
		return sB.toString();
	}

	public double fitness(){
		if(this.getWeight() > MAX_WEIGHT)
			return 0.0;
		return (double)Math.round(this.getWeight() * 100) / 100;
	}

	public Element[] getPoolElements(){
		return this.elements;
	}

	public Double[] extractWeightArray(){
		Double[] res = new Double[elements.length];
		for(int i = 0; i < elements.length; i++){
			res[i] = elements[i].getWeight();
		}
		return res;
	}

	public static void main(String[] args){
		Element[] PoolElements = new PoolElements().getElements();
		BackPack bp = new BackPack(PoolElements);
		Element[] elements = bp.getElements();
		for(int i = 0; i < bp.numOfElement; i++){
			System.out.println(elements[i].getWeight() + " " + elements[i].getImageFile());
		}
	}
}
