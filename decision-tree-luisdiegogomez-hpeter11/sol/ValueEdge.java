package sol;

import src.ITreeNode;
import src.Row;

import java.util.List;

/**
 * A class that represents the edge of a decision tree
 */
public class ValueEdge {
    private ITreeNode child;

    //added
    private Dataset filteredData;
    private String attrType;

    /**
     *
     * Takes a child/next ITreeNode field, a filtered dataset, and an attribute
     * type (NOT a column header)
     *
     * @param next
     * @param d
     * @param attr
     */

    public ValueEdge(ITreeNode next, Dataset d, String attr) {
        this.child = next;
        this.filteredData = d;
        this.attrType = attr;
    }

    /**
     *
     * gets the child node
     *
     * @return child node, leaf or node both accepted
     */

    public ITreeNode getChild() {
        return this.child;
    }

    /**
     *
     * Gets the attribute type (string)
     *
     * @return string attribute type
     */
    public String getAttrType() {
        return this.attrType;
    }


}
