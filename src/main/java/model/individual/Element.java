package model.individual;

public class Element {
	public static final int MAX_ELEMENTS = 20;

	private double weight;
	private String ImageFile;

	public Element(double weight){
		this.weight = weight;
	}

	public Element(double weight, String ImageFile){
		this.weight = weight;
		this.ImageFile = ImageFile;
	}

	public double getWeight() {
		return this.weight;
	}

	public String getImageFile() {
		return ImageFile;
	}

	public boolean equals(Element e){
		return this.ImageFile.equals(e.ImageFile);
	}

}
