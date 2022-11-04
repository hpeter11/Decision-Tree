package sol;

import org.junit.Assert;
import org.junit.Test;
import src.DecisionTreeCSVParser;
import src.Row;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

public class BasicDatasetTest {
    // IMPORTANT: for this filepath to work, make sure the project is open as the top-level directory in IntelliJ
    // (See the first yellow information box in the handout testing section for details)
    String TRAINING_PATH = "data/should-buy.csv"; // TODO: replace with your own input file
    String TARGET_ATTRIBUTE = "buy"; // TODO: replace with your own target attribute

    TreeGenerator testGenerator;

    @Before
    public void buildTreeForTest() {
        List<Row> dataObjects = DecisionTreeCSVParser.parse(TRAINING_PATH);
        List<String> attributeList = new ArrayList<>(dataObjects.get(0).getAttributes());
        Dataset training = new Dataset(attributeList, dataObjects);
        // builds a TreeGenerator object and generates a tree for "foodType"
        testGenerator = new TreeGenerator();
        testGenerator.generateTree(training, TARGET_ATTRIBUTE);
    }

    @Test
    public void testClassification() {
        // makes a new (partial) Row representing the tangerine from the example
        // TODO: make your own rows based on your dataset
        Row four = new Row("test row (four)");
        four.setAttributeValue("expensive", "yes");
        four.setAttributeValue("awesome", "no");
        four.setAttributeValue("buy", "no");
        // TODO: make your own assertions based on the expected classifications
        Assert.assertEquals("no", testGenerator.getDecision(four));
    }
}
