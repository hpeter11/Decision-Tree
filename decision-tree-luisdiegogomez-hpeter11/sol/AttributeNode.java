package sol;

import src.ITreeNode;
import src.Row;

import javax.management.Attribute;
import java.util.List;

/**
 * An inner node in the decision tree
 */
public class AttributeNode implements ITreeNode {
    private List<ValueEdge> outgoingEdges;

    //added
    private String attr;
    private Dataset d;
    private String attrDefault;

    /**
     *
     * Takes a list of value edges based on the node's attribute value, that
     * value, a filtered dataset, and a default (calculated by getDefault)
     *
     * @param pointers
     * @param attribute
     * @param data
     * @param def
     */

    public AttributeNode(List<ValueEdge> pointers, String attribute, Dataset data, String def) {
        this.outgoingEdges = pointers;
        this.attr = attribute;
        this.d = data;
        this.attrDefault = def;
    }


    /**
     *
     * Part of an ITreeNode recursive method that recurses through a tree
     * looking for a decision leaf that matches an input row. This branch
     * of get decision only returns the default value if the row type
     * matches none of its edges, otherwise recursing until a leaf is
     * found
     *
     * @param forDatum the datum to lookup a decision for
     * @return a string representation of the final decision
     */

    public String getDecision(Row forDatum) {
        //checks if any value edge matches the row value of the same type
        for (ValueEdge e : this.outgoingEdges) {
            if (e.getAttrType().equals(forDatum.getAttributeValue(this.attr))) {
                //continues recursing if matching types found
                return e.getChild().getDecision(forDatum);
            }
        }
        //if not found, returns the node default
        return this.attrDefault;
    }

}
