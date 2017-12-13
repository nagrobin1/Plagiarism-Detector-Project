package com.msd.team33.models.comparison;

import com.msd.team33.models.response.ComparisonDetails;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * A JUnit test class for the Comparison class.
 */
public class ComparisonTest {
    Comparison comp;
    Submission sub1;
    Submission sub2;
    PreprocessedSubmission preSub1;
    PreprocessedSubmission preSub2;
    ComparisonDetails compDetails;
    double identifierFactorScore;
    double commentFactorScore;
    double literalFactorScore;
    double statementFactorScore;
    double expectedFinalScore;
    List<String> matches;
    List<String> factorBreakdown;

    /**
     * A method to test that the appropriate ComparisonDetails object is created for submission set 00.
     */
    @Test
    public void getDetailsSet00() throws Exception {
        sub1 = new Submission("src/test/java/com/msd/team33/models/comparison/testDataFiles/set00/Student1");
        sub2 = new Submission("src/test/java/com/msd/team33/models/comparison/testDataFiles/set00/Student2");
        preSub1 = new PreprocessedSubmission(sub1);
        preSub2 = new PreprocessedSubmission(sub2);
        comp = new Comparison(preSub1, preSub2);
        compDetails = comp.getDetails();

        assertEquals(sub1.getPathNameAsString(), compDetails.getSubmissionPath1());
        assertEquals(sub2.getPathNameAsString(), compDetails.getSubmissionPath2());

        identifierFactorScore = 4.0 / 6.0;
        commentFactorScore = 1.0 / 2.0;
        literalFactorScore = 2.0 / 2.0;
        statementFactorScore = 0.0 / 2.0;
        expectedFinalScore = (identifierFactorScore + commentFactorScore + literalFactorScore + statementFactorScore)/(4.0);

        assertEquals(expectedFinalScore, compDetails.getFinalScore(), 0.0000001);

        matches = compDetails.getMatched();

        assertEquals(7, matches.size());
        assertTrue(matches.contains("in"));
        assertTrue(matches.contains("main"));
        assertTrue(matches.contains("y"));
        assertTrue(matches.contains("z"));
        assertTrue(matches.contains("\n * Class to add two numbers.\n "));
        assertTrue(matches.contains("\"Enter two integers to calculate their sum \""));
        assertTrue(matches.contains("\"Sum of entered integers = \""));

        factorBreakdown = compDetails.getFactorBreakdown();

        assertEquals(4, factorBreakdown.size());
        assertTrue(factorBreakdown.contains("Identifier Matching Score: 0.666667 (4 matches found)"));
        assertTrue(factorBreakdown.contains("Comment Matching Score: 0.500000 (1 matches found)"));
        assertTrue(factorBreakdown.contains("Literal Matching Score: 1.000000 (2 matches found)"));
        assertTrue(factorBreakdown.contains("Statement Matching Score: 0.000000 (0 matches found)"));
    }

    /**
     * A method to test that the appropriate ComparisonDetails object is created for submission set 04.
     */
    @Test
    public void getDetailsSet04() throws IOException {
        sub1 = new Submission("src/test/java/com/msd/team33/models/comparison/testDataFiles/set04/Sample1");
        sub2 = new Submission("src/test/java/com/msd/team33/models/comparison/testDataFiles/set04/Sample2");
        preSub1 = new PreprocessedSubmission(sub1);
        preSub2 = new PreprocessedSubmission(sub2);
        comp = new Comparison(preSub1, preSub2);
        compDetails = comp.getDetails();

        assertEquals(sub1.getPathNameAsString(), compDetails.getSubmissionPath1());
        assertEquals(sub2.getPathNameAsString(), compDetails.getSubmissionPath2());

        identifierFactorScore = 21.0 / 24.0;
        literalFactorScore = 7.0 / 7.0;
        statementFactorScore = 29.0 / 29.0;
        expectedFinalScore = (identifierFactorScore + literalFactorScore + statementFactorScore)/(3.0);

        assertEquals(expectedFinalScore, compDetails.getFinalScore(), 0.0000001);

        matches = compDetails.getMatched();

        assertEquals(57, matches.size());
        assertTrue(matches.contains("add"));
        assertTrue(matches.contains("append"));
        assertTrue(matches.contains("current"));
        assertTrue(matches.contains("deleteAtPos"));
        assertTrue(matches.contains("first"));
        assertTrue(matches.contains("getData"));
        assertTrue(matches.contains("getLinkNext"));
        assertTrue(matches.contains("getLinkPrev"));
        assertTrue(matches.contains("i"));
        assertTrue(matches.contains("isEmpty"));
        assertTrue(matches.contains("last"));
        assertTrue(matches.contains("length"));
        assertTrue(matches.contains("n"));
        assertTrue(matches.contains("nptr"));
        assertTrue(matches.contains("p"));
        assertTrue(matches.contains("ptr"));
        assertTrue(matches.contains("ret"));
        assertTrue(matches.contains("setData"));
        assertTrue(matches.contains("setLinkNext"));
        assertTrue(matches.contains("setLinkPrev"));
        assertTrue(matches.contains("toString"));
        assertTrue(matches.contains("null"));
        assertTrue(matches.contains("0"));
        assertTrue(matches.contains("\"\""));
        assertTrue(matches.contains("\" -> \""));
        assertTrue(matches.contains("\"Empty list\""));
        assertTrue(matches.contains("2"));
        assertTrue(matches.contains("1"));

        factorBreakdown = compDetails.getFactorBreakdown();

        assertEquals(3, factorBreakdown.size());
        assertTrue(factorBreakdown.contains("Identifier Matching Score: 0.875000 (21 matches found)"));
        assertTrue(factorBreakdown.contains("Literal Matching Score: 1.000000 (7 matches found)"));
        assertTrue(factorBreakdown.contains("Statement Matching Score: 1.000000 (29 matches found)"));
    }
}