package com.msd.team33.models.factors;

import com.msd.team33.models.comparison.PreprocessedSubmission;
import com.msd.team33.models.comparison.Submission;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Tests for the IdentifierFactor class.
 */
public class IdentifierFactorTest {

    IdentifierFactor identifierFactor;
    String path1;
    String path2;
    Submission sub1;
    Submission sub2;
    PreprocessedSubmission preSub1;
    PreprocessedSubmission preSub2;

    @Before
    public void setUp() throws Exception {

        path1 = "src/test/java/com/msd/team33/models/comparison/testDataFiles/set00/Student1";
        path2 = "src/test/java/com/msd/team33/models/comparison/testDataFiles/set00/Student2";
        sub1 = new Submission(path1);
        sub2 = new Submission(path2);
        preSub1 = new PreprocessedSubmission(sub1);
        preSub2 = new PreprocessedSubmission(sub2);
        identifierFactor = new IdentifierFactor(preSub1.getIdentifierNames(), preSub2.getIdentifierNames());
    }

    @Test
    public void getName() throws Exception {

        assertEquals("Identifier", identifierFactor.getName());
    }

    @Test
    public void getSubmission1Identifiers() throws Exception {
        String [] expec = {"AddNumbers", "in", "main", "x", "y", "z"};
        Set<String> expectedSet = new HashSet(new ArrayList<>(Arrays.asList(expec)));
        assertEquals(expectedSet, new HashSet(preSub1.getIdentifierNames()));
    }

    @Test
    public void getSubmission2Identifiers() throws Exception {
        String [] expec = {"AddNumbers2", "a", "in", "main", "y", "z"};
        Set<String> expected = new HashSet(new ArrayList<>(Arrays.asList(expec)));
        assertEquals(expected, new HashSet(preSub2.getIdentifierNames()));
    }

    @Test
    public void getMatchesValid() throws Exception {
        String [] expec = {"in", "main", "y", "z"};
        Set<String> expected = new HashSet(new ArrayList<String>(Arrays.asList(expec)));
        assertEquals("There are 4 identifier matches between these two files. ", expected,
                new HashSet(identifierFactor.getMatches()));
    }

    @Test
    public void getMatchesInvalid() throws Exception {
        String [] expec = {"in", "main", "r", "z"};
        Set<String> expected = new HashSet(new ArrayList<String>(Arrays.asList(expec)));
        assertNotEquals("Wrong identifiers", expected, new HashSet(identifierFactor.getMatches()));
    }

    @Test
    public void getScore() throws Exception {

        // Round to two decimal places
        double actual = identifierFactor.getScore();
        actual = Math.round(actual * 100);
        actual = actual/100;
        assertEquals("4 of the 6 identifiers in the shorter submission appear in the larger submission",0.67, actual, 0.0001);
    }

    @Test
    public void getDescription() throws Exception {
        String expected = "Identifier Matching Score: 0.666667 (4 matches found)";
        assertEquals("The description does not match", expected, identifierFactor.getDescription());
    }
}