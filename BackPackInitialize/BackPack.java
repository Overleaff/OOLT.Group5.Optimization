import java.util.*;
public class BackPack implements Individual{

	private static List<Element> itemsList = new ArrayList<Element>();

	private double weight;

	public BackPack(double weight){
		this.weight = weight;
	}

	public double getWeight() {
		return weight;
	}

	public List<Element> getElement() {
		return itemsList;
	}

	public void updateElement(Item i) {
	}
}
