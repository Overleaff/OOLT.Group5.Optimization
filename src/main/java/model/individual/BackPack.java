package model.individual;

public class BackPack implements Individual {
	public static final int MAX_WEIGHT = 7; // 5kg

	private int numOfElement;
	private double weight;
	private Element[] elementsList = new Element[Element.MAX_ELEMENTS];
	private Element[] elements = (new PoolElements()).getElements();

	public BackPack(){
		// mỗi khi tạo 1 backpack mới, thì elements trong cái backpack này được chọn ngẫu nhiên
		// lưu lại 10 items này vào 10 phần tử đầu của itemList for simple
		// element nào k có trong backpack thì để weight = 0, số lượng elements có weight != 0 nên là 10
		// nhớ tính toán weight của backpack và lưu lại
		for(int i = 0; i < Element.MAX_ELEMENTS/2; i++){
			elementsList[i] = getNewRandomElement();
			this.weight += elementsList[i].getWeight();
			numOfElement++;
		}
	}

	public double fitness(){
		if(this.getWeight() > MAX_WEIGHT)
			return 0.0;
		return this.getWeight() - MAX_WEIGHT;
	}

	public double getWeight() {
		return weight;
	}

	public int getNumOfElement(){
		return this.numOfElement;
	}

	public Element[] getElements() {
		return elementsList;
	}

	public void updateElement(Element e, double weight, String imgFile)
	{
		e.setWeight(weight);
		e.setImageFile(imgFile);
	}

	public boolean isContain(Element e){
		if(numOfElement == 0)
			return false;
		for(int i = 0; i< numOfElement; i++){
			if(elementsList[i].getImageFile().equalsIgnoreCase(e.getImageFile()))
				return true;
		}
		return false;
	}
	public Element getNewRandomElement(){
		int ran = (int)(Math.random()*Element.MAX_ELEMENTS);
		while(isContain(elements[ran])) {
			ran = (int) (Math.random() * Element.MAX_ELEMENTS);
		}
		return elements[ran];
	}
}
