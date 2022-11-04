package sol;

import src.DecisionTreeCSVParser;
import src.IDataset;
import src.ITreeNode;
import src.Row;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A class that implements the IDataset interface,
 * representing a training data set.
 */
public class Dataset implements IDataset {

    private List<String> attributes;
    private List<Row> data;
    //added
    private String targetAttribute;

    public Dataset(List<String> attributeList, List<Row> dataObjects) {
        this.attributes = attributeList;
        this.data = dataObjects;
        this.targetAttribute = null;
    }

    /**
     *
     * Dataset takes in an attribute list, a list of rows, and a target attribute
     *
     * @param attributeList
     * @param dataObjects
     * @param targetAttribute
     */

    public Dataset(List<String> attributeList, List<Row> dataObjects, String targetAttribute) {
        this.attributes = attributeList;
        this.data = dataObjects;
        this.targetAttribute = targetAttribute;
    }

    /**
     *
     * Manually sets new target attribute field in the dataset
     *
     * @param s
     */
    public void setTargetAttribute(String s) {
        this.targetAttribute = s;
    }

    /**
     *
     * Returns the list of rows
     *
     * @return List<Row>
     */

    public List<Row> getDataObjects() {
        return this.data;
    }

    /**
     *
     * Returns a
     *
     * @return
     */

    /**
     *
     * A required function that counts the size of the data (number of rows)
     *
     * @return int representing total row count
     */

    public int size() {
        int count = 0;
        for (Row r : this.data) {
            count++;
        }
        return count;
    }

    //added

    /**
     *
     * A function that automatically builds a new dataset given a CSV name
     * and a targetAttribute
     *
     * @param CSVName
     * @param targetAttribute
     * @return new Dataset
     */

    public Dataset buildDataset(String CSVName, String targetAttribute) {
        String TRAINING_PATH = CSVName; // TODO: replace with your own input file
        String TARGET_ATTRIBUTE = targetAttribute;

        List<Row> dataObjects = DecisionTreeCSVParser.parse(TRAINING_PATH);
        List<String> attributeList = new ArrayList<>(dataObjects.get(0).getAttributes());

        Dataset myDataset = new Dataset(attributeList, dataObjects);
        myDataset.targetAttribute = targetAttribute;
        return myDataset;
    }

    /**
     *
     * returns the attribute list
     *
     * @return List<String></String>
     */
    public List<String> getAttributeList() {
        return this.attributes;
    }

    /**
     *
     * returns dataset's list of rows
     *
     * @return List<Row></Row>
     */

    public List<Row> getData() {
        return this.data;
    }

    /**
     *
     * Returns the target attribute field
     *
     * @return string (attribute)
     */

    public String getTargetAttribute() {
        return this.targetAttribute;
    }

    /**
     *
     * Returns the attribute list size
     *
     * @return int
     */
    public int listLength() {
        int count = 0;
        for (String s : this.attributes) {
            count = count + 1;
        }
        return count;
    }

    //TODO: start deleting these functions if none of them get used

    /**
     *
     * This method removes the target attribute from the dataset attributes
     * list. It then sets the attributes field to the edited list,
     * modifying the dataset
     *
     * @param target
     */
    public Dataset pullTarget(String target) {
        ArrayList<String> aL = new ArrayList<>();
        for (String s : this.attributes) {
            if (!(s.equals(target))) {
                aL.add(s);
            }
        }
        Dataset d = new Dataset(aL, this.data, target);
        return d;
    }

    /**
     *
     * Gets a random number that is no more than the attribute list size
     *
     * @return int (random)
     */

    public int getRandomNumber() {
        int i = this.listLength();
        Random random = new Random();
        int randomNum = random.nextInt(i);
        return randomNum;
    }

    /**
     *
     * Creates a random number based on the size of the attribute list and
     * then gets a random attribute from the list. Tries again if the
     * attribute is the target
     *
     * @return
     */

    public String getRandomAttribute() {
        int upperBound = this.attributes.size();
        Random random = new Random();
        int randomNum = random.nextInt(upperBound);
        String s = this.attributes.get(randomNum);

        if (s.equals(this.targetAttribute)) {
            return this.getRandomAttribute();
        }
        return s;
    }

    //TODO: Stop deleting

    /**
     *
     * Generates a random number based on the upper bound of the atttribute
     * list size.
     *
     * @return string selected random attribute
     */

    public String randomAttribute() {
        Random random = new Random();
        int upperBound = this.listLength();
        int randomNum = random.nextInt(upperBound);
        return this.attributes.get(randomNum);
    }

    /**
     *
     * Numgen reorders a dataset, modifying the attributes list such that
     * the target attribute is always at the end of the list in order for
     * the random number generator to exclude it.
     *
     * @param target
     * @return Dataset with target attribute at the end of attribute list
     */

    public Dataset numgen(String target) {
        ArrayList<String> l = new ArrayList<>();
        String t = null;
        for (String s : this.attributes) {
            if (!(s.equals(target))) {
                l.add(s);
            } else {
                t = s;
            }
        }
        l.add(t);
        Dataset x = new Dataset(l, this.data, target);
        x.setTargetAttribute(target);
        return x;
    }

    /**
     *
     * Given a column header and an option output, numgen modifies a dataset
     * to exclude rows in data that do not match its type parameter
     *
     * @param attribute
     * @param type
     * @return filtered Dataset
     */
    public Dataset filterRows(String attribute, String type) {
        ArrayList<Row> l = new ArrayList<>();
        for (Row r : this.data) {
            if (r.getAttributeValue(attribute).equals(type)) { //TODO issue here?
                l.add(r);
            }
        }
        Dataset d = new Dataset(this.attributes, l, this.targetAttribute);
        return d;
    }

    /**
     *
     * A setter for the attributes field
     *
     * @param attributes
     */
    public void setAttributes(List<String> attributes) {
        this.attributes = attributes;
    }

    /**
     *
     * Modifies a given dataset, reducing the attribute list by given attribute
     * and filtering the rows by value type
     *
     * @param attribute colum header that is pulled from dataset attr list
     * @param type type of row that is included in dataset, otherwise removed
     * @return Dataset with filtered attribute list and row list
     */

    public Dataset filterAll(String attribute, String type) {
        Dataset d = this.filterRows(attribute, type);
        ArrayList<String> l = new ArrayList<>();
        for (String a : this.attributes) {
            if (!(a.equals(attribute))) {
                l.add(a);
            }
        }

        d.setAttributes(l);
        return d;

    }

    /**
     *
     * Returns a list of the unique answers for a attribute column.
     *
     * @param columnHeader the column header string for a attribute's column
     * @return List<String> of unique options for the inputted columnHeader
     */

    public ArrayList<String> filterUnique(String columnHeader) {
        if (this.data.get(0) == null) {
            throw new RuntimeException("No rows");
        }
        ArrayList<String> u = new ArrayList<>();
        u.add(this.data.get(0).getAttributeValue(columnHeader));
        for (Row r : this.data) {
            if (!(u.contains(r.getAttributeValue(columnHeader)))) {
                u.add(r.getAttributeValue(columnHeader));
            }
        }
        return u;
    }

    /**
     *
     * Outputs a list of ValueEdges populated by the nextNode method,
     * filtered data from the parent node, and the unique list
     *
     * @param uniqueList Inputted list of unique values for the parent node's
     *                   attribute
     * @param nodeAttribute The current node's attribute.
     * @return List<ValueEdge> of ValueEdges for a node.
     */
    public List<ValueEdge> createVEList(ArrayList<String> uniqueList, String nodeAttribute) {
        ArrayList<ValueEdge> output = new ArrayList<>();
        for (String uE : uniqueList) {
            Dataset d = this.filterAll(nodeAttribute, uE);
            ValueEdge vE = new ValueEdge(d.nextNode(), d, uE);
            output.add(vE);
        }
        return output;
    }

    /**
     *
     * This function determines what type should be the default for each node.
     * It does so by setting an index and total field. These are used with the
     * unique set of rows to determine which has the most iterations. The count
     * is maintained for each unique type option, and if it is greater than the
     * last, the count and final index position are reset. Once the final
     * index is calculated, it gets that value of the list of unique options
     *
     * @param
     * @return
     */

    public String getDefault() {
        Integer total = 0; //the count of how many rows match the type
        Integer finalIndex = null; //final index to get
        ArrayList<String> u = this.filterUnique(this.targetAttribute); //unique list
        Integer position = 0; //position for every value in the unique list
        for (String e : u) {
            Integer count = 0; //counts how many matches exist
            for (Row r : this.data) {
                if (r.getAttributeValue(this.targetAttribute).equals(e)) {
                    count++;
                }
                if (count > total) {
                    total = count;
                    finalIndex = position;
                }
            }
            position++;
        }
        return u.get(finalIndex);
    }

    //TODO: delete if debug fails



    /**
    public ITreeNode nextNode() {
        //Dataset d = this.numgen(this.targetAttribute); //ordered list for number generator
        //String s = d.randomAttribute(); //unique column header
        int r = this.getRandomNumber();
        String s = this.attributes.get(r);
        ArrayList<String> l = this.filterUnique(this.targetAttribute); // list of unique row options for
        // given column
        ITreeNode i = null;
        if (l.size() == 1) {
            DecisionLeaf fN = new DecisionLeaf(l.get(0), this);
            i = fN;
        } else {
            ArrayList<String> uL = this.filterUnique(s);
            String def = this.getDefault(s);
            AttributeNode fN = new AttributeNode(this.createVEList(uL, s), s, this, def);
            i = fN;
        }
        return i;
    } */


    /**
     *
     * nextNode decides whether or not to create a new node or leaf based on
     * how many unique type options remain. If only one is left, the recursion
     * ends and a leaf is created. Otherwise, a new node is created with values
     * set by the filtered dataset and recursion runs on all of the new node's
     * edges.
     *
     * @return
     */


    public ITreeNode nextNode() {
        Dataset d = this.numgen(this.targetAttribute); //ordered list for number generator
        String s = d.randomAttribute(); //unique column header
        ArrayList<String> l = this.filterUnique(this.targetAttribute); // list of unique row options for
        // given column
        ITreeNode i = null;
        if (l.size() == 1) { //ends with decision leaf if only 1 option remains
            DecisionLeaf fN = new DecisionLeaf(l.get(0), d);
            i = fN;
        } else { //creates a new node with the given data
            ArrayList<String> uL = this.filterUnique(s);
            String def = this.getDefault();
            AttributeNode fN = new AttributeNode(d.createVEList(uL, s), s, d, def);
            i = fN;
        }
        return i; //returns either option
    }

}
