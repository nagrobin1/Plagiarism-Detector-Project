package com.msd.team33.models.factors;

import com.msd.team33.models.comparison.PreprocessedSubmission;
import com.msd.team33.models.comparison.Submission;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the StatementFactor class.
 */
public class StatementFactorTest {

    StatementFactor statementFactor;
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
        statementFactor = new StatementFactor(preSub1.getBlockStatements(), preSub2.getBlockStatements());
    }

    @Test
    public void getName() throws Exception {
        assertEquals("Statement", statementFactor.getName());
    }


    @Test
    public void getScore() throws Exception {
        // Round to two decimal places
        double actual = statementFactor.getScore();
        actual = Math.round(actual * 100);
        actual = actual/100;
        assertEquals("The two blocks are different",0.00, actual, 0.0000);
    }

    @Test
    public void getDescription() throws Exception {

        String expected = "Statement Matching Score: 0.000000 (0 matches found)";
        assertEquals("The description does not match", expected, statementFactor.getDescription());
    }

    @Test
    public void getMatches() throws Exception {
        List<String> expected = new ArrayList<>();
        assertEquals("There are no matches between the two submissions", expected, statementFactor.getMatches());
    }

}