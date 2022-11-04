package sol;

import src.ITreeNode;
import src.Row;

/**
 * A class representing a leaf in the decision tree.
 */
public class DecisionLeaf implements ITreeNode {
    // TODO: add fields as needed
    //added
    private String decision;
    private Dataset filteredData;

    /**
     *
     * Constructor for the leaf which takes in a decision and a filtered
     * version of training data using only the remaining rows
     *
     * @param d
     * @param f
     */

    public DecisionLeaf(String d, Dataset f) {
        this.decision = d;
        this.filteredData = f;
    }

    /**
     *
     * A recursive method that simply returns the decision field if ITreeNode
     * is a leaf
     *
     * @param forDatum the datum to lookup a decision for
     * @return
     */

    public String getDecision(Row forDatum) {
        //if a leaf is reached, simply return its decision field
        return this.decision;
    }

}
