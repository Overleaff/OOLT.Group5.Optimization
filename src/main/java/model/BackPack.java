package model;

public class BackPack implements Individual {
	private int numOfElement;
	private double weight;
	private Element[] elements = new Element[Element.MAX_ELEMENTS];
	private Element[] poolElements = PoolElements.getElements();

	public BackPack(){
		// mỗi khi tạo 1 backpack mới, thì elements trong cái backpack này được chọn ngẫu nhiên
		// lưu lại 10 items này vào 10 phần tử đầu của itemList for simple
		// element nào k có trong backpack thì để weight = 0, số lượng elements có weight != 0 nên là 10
		// nhớ tính toán weight của backpack và lưu lại
		for(int i = 0; i < Element.MAX_ELEMENTS/2; i++){
			elements[i] = getNewRandomElement();
			this.weight += elements[i].getWeight();
			numOfElement++;
		}
		for(int i = Element.MAX_ELEMENTS/2; i < Element.MAX_ELEMENTS; i++)
			elements[i] = new Element(0,"");
	}
	
	//get new random element that not in Backpack yet
	public Element getNewRandomElement(){
		int ran = (int)(Math.random()*Element.MAX_ELEMENTS);
		int maxLoop = 0;
		while(isContain(poolElements[ran]) && ++maxLoop < 10) {
			ran = (int) (Math.random() * Element.MAX_ELEMENTS);
		}
		return poolElements[ran];
	}

	public double getWeight() {
		// round to 2 digit decimal
		return (double)Math.round(weight * 100) / 100;
	}

	public int getNumOfElement(){
		return this.numOfElement;
	}

	public Element[] getElements() {
		return elements;
	}

	public synchronized void updateElement(int i, double weight, String imgFile)
	{
		if(i >= 0 && i < Element.MAX_ELEMENTS){
			double oldWei = elements[i].getWeight();
			elements[i] = new Element(weight, imgFile);
			this.weight += weight - oldWei;
		}
	}

	public boolean isContain(Element e){
		if(numOfElement == 0)
			return false;
		for(int i = 0; i< numOfElement; i++){
			if(elements[i].equals(e))
				return true;
		}
		return false;
	}

	public String toString(){
		StringBuilder sB = new StringBuilder();
		for(int i = 0; i < numOfElement; i++){
			sB.append(elements[i].getImageFile());
			sB.append(", ");
		}
		sB.append(this.getWeight());
		return sB.toString();
	}

	public double fitness(){
		if(this.getWeight() > Individual.MAX_WEIGHT)
			return 0.0;
		return (double)Math.round(this.getWeight() * 100) / 100;
	}

	public static void main(String[] args){
		BackPack bp = new BackPack();
		Element[] elements = bp.getElements();
		for(int i = 0; i < bp.numOfElement; i++){
			System.out.println(elements[i].getWeight() + " " + elements[i].getImageFile());
		}
	}
}
