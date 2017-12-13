package com.msd.team33.models.strategy;

import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.ast.expr.LiteralExpr;
import com.github.javaparser.ast.expr.NullLiteralExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.msd.team33.models.comparison.PreprocessedSubmission;
import com.msd.team33.models.comparison.Submission;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * A JUnit test class for the LiteralStrategy class.
 */
public class LiteralStrategyTest {
    LiteralStrategy literalStrategy;
    Submission sub;
    PreprocessedSubmission preSub;

    /**
     * A method to create the objects needed for testing.
     */
    @Before
    public void setUp() throws IOException {
        sub = new Submission("src/test/java/com/msd/team33/models/comparison/testDataFiles/set04/Sample1");
        preSub = new PreprocessedSubmission(sub);
        literalStrategy = new LiteralStrategy(preSub.getASTs());
    }

    /**
     * A method to test the getVisitationResults() method from the LiteralStrategy class.
     */
    @Test
    public void getVisitationResults() throws Exception {
        List<LiteralExpr> literals = preSub.getLiterals();

        NullLiteralExpr nullExpr = new NullLiteralExpr();
        IntegerLiteralExpr intExpr0 = new IntegerLiteralExpr(0);
        IntegerLiteralExpr intExpr1 = new IntegerLiteralExpr(1);
        IntegerLiteralExpr intExpr2 = new IntegerLiteralExpr(2);
        StringLiteralExpr string1 = new StringLiteralExpr("Empty list");
        StringLiteralExpr string2 = new StringLiteralExpr("");
        StringLiteralExpr string3 = new StringLiteralExpr(" -> ");

        assertEquals(7, literals.size());
        assertTrue(literals.contains(nullExpr));
        assertTrue(literals.contains(intExpr0));
        assertTrue(literals.contains(intExpr1));
        assertTrue(literals.contains(intExpr2));
        assertTrue(literals.contains(string1));
        assertTrue(literals.contains(string2));
        assertTrue(literals.contains(string3));
    }
}