package sol;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sol.TreeGenerator;
import src.DecisionTreeCSVParser;
import src.Row;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UnitTestSuite {

    TreeGenerator testGenerator;
    Dataset training;

    @Before
    public void createData() {
        String TRAINING_PATH = "training-gender-correlated.csv"; // TODO: replace with your own input file
        String TARGET_ATTRIBUTE = "hired"; // TODO: replace with your own target attribute
        TreeGenerator testGenerator;

        List<Row> dataObjects = DecisionTreeCSVParser.parse(TRAINING_PATH);
        List<String> attributeList = new ArrayList<>(dataObjects.get(0).getAttributes());
        Dataset training = new Dataset(attributeList, dataObjects);
        this.training = training;
        // builds a TreeGenerator object and generates a tree for "foodType"
        testGenerator = new TreeGenerator();
        testGenerator.generateTree(training, TARGET_ATTRIBUTE);
    }

    @Test
    public void filler() {
        Assert.assertEquals(1, 1);
    }

//    @Test
//    public void testClassification() {
//        // makes a new (partial) Row representing the tangerine from the example
//        // TODO: make your own rows based on your dataset
//        Row extra = new Row("test row (extra)");
//        extra.setAttributeValue("leadershipExperience", "TRUE");
//        extra.setAttributeValue("lastPositionDuration", "1-2");
//        extra.setAttributeValue("numWorkExperiences", "1");
//        extra.setAttributeValue("programmingLanguages", "2-1");
//        extra.setAttributeValue("gpa", "2.0-2.9");
//        extra.setAttributeValue("location", "local");
//        extra.setAttributeValue("hired", "FALSE");
//        // TODO: make your own assertions based on the expected classifications
//        Assert.assertEquals("no", testGenerator.getDecision(extra));
//
//    }
}