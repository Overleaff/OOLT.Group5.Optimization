package sample.individual;

public interface Element {
	public static final int MAX_ELEMENTS = 20;

	public abstract double getWeight();
	public abstract String getImageFile();
	public abstract void setWeight(double weight);
	public abstract void setImageFile(String imgFile);

}
