public class Item implements Element {

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
		return null;
	}

}
