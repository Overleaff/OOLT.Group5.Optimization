import java.util.*;
public class BackPack implements Individual{

	private static List<Element> itemsList = new ArrayList<Element>();

	private double weight;

	public BackPack(double weight){
		this.weight = weight;
	}

	public BackPack(double weight, List<Element> itemsList){
		this.weight = weight;
		this.itemsList = itemsList;
	}

	public double getWeight() {
		return weight;
	}

	public static List<Element> getElement() {
		return itemsList;
	}

	public void updateElement(Item i) {
	}

	public static List<Element> initItemsList() {
		itemsList.add(new Item(2));
		itemsList.add(new Item(4));
		itemsList.add(new Item(6));
		itemsList.add(new Item(8));
		itemsList.add(new Item(9));
		return itemsList;
	}

}
