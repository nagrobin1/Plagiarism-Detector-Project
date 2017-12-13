package com.msd.team33.models.factors;

import com.github.javaparser.ast.expr.LiteralExpr;
import com.msd.team33.models.comparison.PreprocessedSubmission;
import com.msd.team33.models.comparison.Submission;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the LiteralFactor class.
 */
public class LiteralFactorTest {

    LiteralFactor literalFactor;
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
        literalFactor = new LiteralFactor(preSub1.getLiterals(), preSub2.getLiterals());
    }

    @Test
    public void getName() throws Exception {

        assertEquals("Literal", literalFactor.getName());
    }

    @Test
    public void getSubmission1Literals() throws Exception {

        List<String> expected = new ArrayList<>();
        expected.add("\""+ "Enter two integers to calculate their sum "+ "\"");
        expected.add("\""+ "Sum of entered integers = " + "\"");
        List<String> result = new ArrayList<>();
        for(LiteralExpr le: preSub1.getLiterals()){
            result.add(le.asLiteralExpr().toString());}
        Collections.sort(expected); Collections.sort(result);
        assertEquals("There are two literals in submisison1", expected, result);
    }

    @Test
    public void getSubmission2Literals() throws Exception {

        List<String> expected = new ArrayList<>();
        expected.add("\""+ "This program adds two numbers " + "\"");
        expected.add("\""+ "Enter two integers to calculate their sum "+ "\"");
        expected.add("\""+ "Sum of entered integers = " + "\"");
        List<String> result = new ArrayList<>();
        for(LiteralExpr le: preSub2.getLiterals()){
            result.add(le.asLiteralExpr().toString());}
        Collections.sort(expected); Collections.sort(result);
        assertEquals("There are three literals in submisison2", expected, result);
    }

    @Test
    public void getScore() throws Exception {
        // Round to two decimal places
        double actual = literalFactor.getScore();
        actual = Math.round(actual * 100);
        actual = actual/100;
        assertEquals("2 of the 2 literals in the shorter submission appear in the larger submission",1.00, actual, 0.0001);
    }

    @Test
    public void getDescription() throws Exception {
        String expected = "Literal Matching Score: 1.000000 (2 matches found)";
        assertEquals("The description does not match", expected, literalFactor.getDescription());
    }

    @Test
    public void getMatches() throws Exception {
        List<String> expected = new ArrayList<>();
        expected.add("\""+ "Enter two integers to calculate their sum "+ "\"");
        expected.add("\""+ "Sum of entered integers = " + "\"");
        List<String> result = literalFactor.getMatches();
        Collections.sort(expected); Collections.sort(result);
        assertEquals("There are two literal matches between the two submissions", expected, result);
    }
}