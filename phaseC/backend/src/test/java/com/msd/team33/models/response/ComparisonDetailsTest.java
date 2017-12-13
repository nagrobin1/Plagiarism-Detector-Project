package com.msd.team33.models.response;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * A JUnit test class for the ComparisonDetails class.
 */
public class ComparisonDetailsTest {
    ComparisonDetails compDetails;
    String subPath1;
    String subPath2;
    double finalScore;
    List<String> matched;
    List<String> factorBreakdown;

    /**
     * A method to create the objects needed for testing.
     */
    @Before
    public void setUp() {
        subPath1 = "src/test/java/com/msd/team33/models/comparison/testDataFiles/set00/Student1";
        subPath2 = "src/test/java/com/msd/team33/models/comparison/testDataFiles/set00/Student2";
        finalScore = 2.0/3.0;
        matched = new ArrayList();
        matched.add("list of matches");
        factorBreakdown = new ArrayList();
        factorBreakdown.add("factor breakdown");

        compDetails = new ComparisonDetails(subPath1, subPath2, finalScore, matched, factorBreakdown);
    }

    /**
     * A method to test that the constructor sets the first submission path properly
     * and the getSubmissionPath1() method returns the first submission path properly.
     */
    @Test
    public void getSubmissionPath1() {
        assertEquals(subPath1, compDetails.getSubmissionPath1());
    }

    /**
     * A method to test that the setSubmissionPath1() method properly sets the first submission path.
     */
    @Test
    public void setSubmissionPath1() {
        compDetails.setSubmissionPath1(subPath2);
        assertEquals(subPath2, compDetails.getSubmissionPath1());
    }

    /**
     * A method to test that the constructor sets the second submission path properly
     * and the getSubmissionPath2() method returns the second submission path properly.
     */
    @Test
    public void getSubmissionPath2() {
        assertEquals(subPath2, compDetails.getSubmissionPath2());
    }

    /**
     * A method to test that the setSubmissionPath2() method properly sets the second submission path.
     */
    @Test
    public void setSubmissionPath2() {
        compDetails.setSubmissionPath2(subPath1);
        assertEquals(subPath1, compDetails.getSubmissionPath2());
    }

    /**
     * A method to test that the constructor sets the final score properly
     * and the getFinalScore() method returns the final score properly.
     */
    @Test
    public void getFinalScore() {
        assertEquals(finalScore, compDetails.getFinalScore(), 0.0000001);
    }

    /**
     * A method to test that the setFinalScore() method properly sets the final score.
     */
    @Test
    public void setFinalScore() {
        compDetails.setFinalScore(2.2222222);
        assertEquals(2.2222222, compDetails.getFinalScore(), 0.0000001);
    }

    /**
     * A method to test that the constructor sets the list of matches found during comparison properly
     * and the getMatched() method returns the list of matches found during comparison  properly.
     */
    @Test
    public void getMatched() {
        assertEquals(matched, compDetails.getMatched());
    }

    /**
     * A method to test that the setMatched() method properly sets the list of matches found during comparison.
     */
    @Test
    public void setMatched() {
        compDetails.setMatched(factorBreakdown);
        assertEquals(factorBreakdown, compDetails.getMatched());
    }

    /**
     * A method to test that the constructor sets the factor breakdown properly
     * and the getFactorBreakdown() method returns the factor breakdown properly.
     */
    @Test
    public void getFactorBreakdown() {
        assertEquals(factorBreakdown, compDetails.getFactorBreakdown());
    }

    /**
     * A method to test that the setFactorBreakdown() method properly sets the factor breakdown.
     */
    @Test
    public void setFactorBreakdown() {
        compDetails.setFactorBreakdown(matched);
        assertEquals(matched, compDetails.getFactorBreakdown());
    }
}