package algorithm;

import model.*;

import java.util.Arrays;
import java.util.Comparator;

public class HillClimbingAlgorithm extends HeuristicAlgorithm {
    // do steepest-ascent algorithm : examine all neighboring nodes of the current state and
    // selects one neighbor node that closest to the goal state.
    // we find the best neighboring node by :
    // 1. consider adding one of all items that not in backpack yet, memory this best item that closest to goal
    // 2. sort all items that not in backpack, then loop through all items inside backpack, for each loop we use binary
    // search to find the replaced item that let this new state closest to the goal.
    private BackPack bp;

    public HillClimbingAlgorithm() {
        this.bp = (BackPack) getPopVariable().getBestIndividual();
    }

    // return the Element with weight that closest to target and still < target
    private static Element binarySearch(double target, Element[] a) {
        int l = 0;
        int r = a.length - 1;
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (a[mid].getWeight() <= target) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return target - a[l].getWeight() > 0 ? a[l] : new Element(0);
    }

    public static Element[] ElementOutBack(BackPack bp, Element[] PoolElements) {
        Element[] ElementsOutBack = new Element[Element.MAX_ELEMENTS - bp.getNumOfElement() + 1];
        int j = 0;
        for (Element poolElement : PoolElements) {
            if (!bp.isContain(poolElement)) {
                ElementsOutBack[j++] = poolElement;
            }
        }
        ElementsOutBack[j] = new Element(0);
        return ElementsOutBack;
    }

    public static BackPack bestNextState(BackPack bp, Element[] PoolElements) {
        Element[] elementsOutBack = ElementOutBack(bp, PoolElements);
        Arrays.sort(elementsOutBack, Comparator.comparingDouble(Element::getWeight));
        double min = 100;
        int j = 0;
        Element res = new Element(0);
        Element tmp = new Element(0);
        for (int i = 0; i <= bp.getNumOfElement(); i++) {
            double target = Individual.MAX_WEIGHT - bp.getWeight() + bp.getElements()[i].getWeight();
            if (target >= 0)
                tmp = binarySearch(target, elementsOutBack);
            if (Math.abs(tmp.getWeight() - target) < min) {
                min = Math.abs(tmp.getWeight() - target);
                res = tmp;
                j = i;
            }
        }
        BackPack newBp = new BackPack(bp);
        newBp.updateElement(j, res.getWeight(), res.getImageFile());
        System.out.println(newBp);
        return newBp;
    }

    public BackPack doOtherSteps() {
        // return new better state then update to this.bp
        bestNextState(this.bp, PoolElements.getElements());
        return this.bp;
    }
}
