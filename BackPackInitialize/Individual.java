import java.util.*;
public interface Individual {
    public abstract double getWeight();

    public abstract List<Element> getElement();
    public abstract void updateElement(Item i);
}
