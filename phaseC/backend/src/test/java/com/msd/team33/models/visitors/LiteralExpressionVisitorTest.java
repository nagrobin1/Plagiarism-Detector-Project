package com.msd.team33.models.visitors;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.comments.BlockComment;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.comments.LineComment;
import com.github.javaparser.ast.expr.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

/**
 * A JUnit test class for the LiteralVisitor class.
 */
public class LiteralExpressionVisitorTest {
    private LiteralExpressionVisitor literalExpressionVisitorVisitor;

    private BooleanLiteralExpr booleanExpr;
    private CharLiteralExpr charExpr;
    private DoubleLiteralExpr doubleExpr;
    private IntegerLiteralExpr integerExpr;
    private LongLiteralExpr longExpr;
    private StringLiteralExpr stringExpr;

    /**
     * A method to create the objects needed for testing.
     */
    @Before
    public void setUp() {
        literalExpressionVisitorVisitor = new LiteralExpressionVisitor();

        booleanExpr = new BooleanLiteralExpr(true);
        charExpr = new CharLiteralExpr('c');
        doubleExpr = new DoubleLiteralExpr(3.14);
        integerExpr = new IntegerLiteralExpr(7);
        longExpr = new LongLiteralExpr(7777777);
        stringExpr = new StringLiteralExpr("string literal");
    }

    /**
     * A method to test the getLiteralExpressions() method from LiteralExpressionVisitor.
     */
    @Test
    public void getLiteralExpressions() {
        literalExpressionVisitorVisitor.visit(booleanExpr, null);
        literalExpressionVisitorVisitor.visit(charExpr, null);
        literalExpressionVisitorVisitor.visit(doubleExpr, null);
        literalExpressionVisitorVisitor.visit(integerExpr, null);
        literalExpressionVisitorVisitor.visit(longExpr, null);
        literalExpressionVisitorVisitor.visit(stringExpr, null);

        Set<LiteralExpr> literals = literalExpressionVisitorVisitor.getLiteralExpressions();

        assertEquals(6, literals.size());
        assertTrue(literals.contains(booleanExpr));
        assertTrue(literals.contains(charExpr));
        assertTrue(literals.contains(doubleExpr));
        assertTrue(literals.contains(integerExpr));
        assertTrue(literals.contains(longExpr));
        assertTrue(literals.contains(stringExpr));
    }

    /**
     * A method to test the getLiteralExpressions() method from LiteralExpressionsVisitor when called
     * before the visitor has visited any nodes.
     */
    @Test
    public void getLiteralExpressionsWithoutVisit() {
        Set<LiteralExpr> literals = literalExpressionVisitorVisitor.getLiteralExpressions();

        assertEquals(0, literals.size());
        assertFalse(literals.contains(booleanExpr));
        assertFalse(literals.contains(charExpr));
        assertFalse(literals.contains(doubleExpr));
        assertFalse(literals.contains(integerExpr));
        assertFalse(literals.contains(longExpr));
        assertFalse(literals.contains(stringExpr));
    }

    /**
     * A method to test the getLiteralExpressions() method from LiteralExpressionVisitor when called
     * after visiting nodes that aren't literal expressions or contain any such literal expressions.
     */
    @Test
    public void getLiteralExpressionsVisitingIrrelevantNodes() {
        literalExpressionVisitorVisitor.visit(new ClassOrInterfaceDeclaration(), null);
        literalExpressionVisitorVisitor.visit(new MethodDeclaration(), null);
        literalExpressionVisitorVisitor.visit(new VariableDeclarator(), null);
        literalExpressionVisitorVisitor.visit(new BlockComment(), null);
        literalExpressionVisitorVisitor.visit(new JavadocComment(), null);
        literalExpressionVisitorVisitor.visit(new LineComment(), null);

        Set<LiteralExpr> literals = literalExpressionVisitorVisitor.getLiteralExpressions();

        assertEquals(0, literals.size());
        assertFalse(literals.contains(booleanExpr));
        assertFalse(literals.contains(charExpr));
        assertFalse(literals.contains(doubleExpr));
        assertFalse(literals.contains(integerExpr));
        assertFalse(literals.contains(longExpr));
        assertFalse(literals.contains(stringExpr));
    }

    @Test
    public void visitBooleanLiteralExpr() {
        literalExpressionVisitorVisitor.visit(booleanExpr, null);

        Set<LiteralExpr> literals = literalExpressionVisitorVisitor.getLiteralExpressions();

        assertEquals(1, literals.size());
        assertTrue(literals.contains(booleanExpr));
    }

    @Test
    public void visitCharLiteralExpr() {
        literalExpressionVisitorVisitor.visit(charExpr, null);

        Set<LiteralExpr> literals = literalExpressionVisitorVisitor.getLiteralExpressions();

        assertEquals(1, literals.size());
        assertTrue(literals.contains(charExpr));
    }

    @Test
    public void visitDoubleLiteralExpr() {
        literalExpressionVisitorVisitor.visit(doubleExpr, null);

        Set<LiteralExpr> literals = literalExpressionVisitorVisitor.getLiteralExpressions();

        assertEquals(1, literals.size());
        assertTrue(literals.contains(doubleExpr));
    }

    @Test
    public void visitIntegerLiteralExpr() {
        literalExpressionVisitorVisitor.visit(integerExpr, null);

        Set<LiteralExpr> literals = literalExpressionVisitorVisitor.getLiteralExpressions();

        assertEquals(1, literals.size());
        assertTrue(literals.contains(integerExpr));
    }

    @Test
    public void visitLongLiteralExpr() {
        literalExpressionVisitorVisitor.visit(longExpr, null);

        Set<LiteralExpr> literals = literalExpressionVisitorVisitor.getLiteralExpressions();

        assertEquals(1, literals.size());
        assertTrue(literals.contains(longExpr));
    }

    @Test
    public void visitStringLiteralExpr() {
        literalExpressionVisitorVisitor.visit(stringExpr, null);

        Set<LiteralExpr> literals = literalExpressionVisitorVisitor.getLiteralExpressions();

        assertEquals(1, literals.size());
        assertTrue(literals.contains(stringExpr));
    }
}