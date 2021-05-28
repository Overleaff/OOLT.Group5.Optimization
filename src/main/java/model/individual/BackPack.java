package model.individual;

public class BackPack implements Individual {
	public static final int MAX_WEIGHT = 7; // 5kg
	public static final int MIN_NUM_ITEMS = 2;

	private int numOfElement;
	private double weight;
	private Element[] itemsList;

	public BackPack(){
		// mỗi khi tạo 1 backpack mới, thì elements trong cái backpack này được chọn ngẫu nhiên
		// element nào k có trong backpack thì để weight = 0, số lượng elements nên lớn hơn MIN_NUM_ITEMS, và nhỏ hơn tổng số elements có
		// nhớ tính toán weight của backpack và lưu lại
	}

	public double getWeight() {
		return weight;
	}

	public int getNumOfElement(){
		return this.numOfElement;
	}

	public Element[] getElements() {
		return itemsList;
	}

	public void updateElement(Element e, double weight, String imgFile)
	{
		e.setWeight(weight);
		e.set
	}
}
