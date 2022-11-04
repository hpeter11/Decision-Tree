package sol;

import src.ITreeGenerator;
import src.ITreeNode;
import src.Row;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A class that implements the ITreeGenerator interface
 * used to generate a tree
 */
public class TreeGenerator implements ITreeGenerator<Dataset> {

    /**
     *
     * The top field is the beginning of the tree structure that is created
     * by the generate tree function and is used to begin the recursion of get
     * decision
     *
     */
    private ITreeNode top;

    //added


    /**
     *
     * Returns either a leaf if there is only one possible set of target
     * attributes and otherwise creates a node based on whichever attribute has
     * the most remaining rows
     *
     * @return
     */

    public void generateTree(Dataset trainingData, String targetAttribute) {
        trainingData.setTargetAttribute(targetAttribute);
        // if empty case in dataset, throws exception
        if (trainingData.getAttributeList() == null || trainingData.getData() == null) {
            throw new RuntimeException("Data not found");

        }


        ArrayList<ValueEdge> l = new ArrayList<>();
        //refiltered is a dataset that will work with our random attribute
        // method
        Dataset refiltered = trainingData.numgen(targetAttribute);
        //random attribute is a string variable that is a column header
        refiltered.setTargetAttribute(targetAttribute);
        String randomAttribute = refiltered.randomAttribute();
        //uL is a list of all unique options for random column header
        ArrayList<String> uL = refiltered.filterUnique(randomAttribute);
        //top is our new node that is filled in with new recursively generated
        //list of edges, a random attribute, input dataset, and a default
        //based on the random attribute plugged into our default function
        AttributeNode top = new AttributeNode(refiltered.createVEList(uL,
                randomAttribute), randomAttribute, refiltered,
                refiltered.getDefault());

        //TODO: delete if debug fails

        /**

        ArrayList<ValueEdge> l = new ArrayList<>();
        //refiltered is a dataset that will work with our random attribute
        // method
        Dataset d = trainingData.pullTarget(targetAttribute);
        int i = d.getRandomNumber();
        //random attribute is a string variable that is a column header
        String randomAttribute = d.getAttributeList().get(i);
        //uL is a list of all unique options for random column header
        ArrayList<String> uL = d.filterUnique(randomAttribute);
        //top is our new node that is filled in with new recursively generated
        //list of edges, a random attribute, input dataset, and a default
        //based on the random attribute plugged into our default function
        AttributeNode top = new AttributeNode(d.createVEList(uL,
                randomAttribute), randomAttribute, d,
                d.getDefault(randomAttribute));
         */

        //redefines top field as our new node
        this.top = top;
    }

    /**
     *
     * This method recursively searches in the top node of the tree structure
     * for a decision leaf or a default value, accessing node, edge, and
     * leaf attributes until it reaches a satisfactory option.
     *
     * @param datum the datum to lookup a decision for
     * @return
     */

    public String getDecision(Row datum) {
        return this.top.getDecision(datum);
    }
}
