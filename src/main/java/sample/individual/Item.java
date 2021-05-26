package sample.individual;

public class Item implements Element {
	private static final int MAX_ITEMS = 20;
	private double weight;
	private String ImageFile;

	public Item(double weight){
		this.weight = weight;
	}

	public Item(double weight, String ImageFile){
		this.weight = weight;
		this.ImageFile = ImageFile;
	}


	public double getWeight() {
		return this.weight;
	}

	public String getImageFile() {
		return ImageFile;
	}

	public void setWeight(double weight){
		this.weight = weight;
	}
}
