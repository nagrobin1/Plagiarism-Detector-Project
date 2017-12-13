package com.msd.team33.models.visitors;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.comments.BlockComment;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.comments.LineComment;
import com.github.javaparser.ast.type.PrimitiveType;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.EnumSet;

import static org.junit.Assert.*;

/**
 * A JUnit test class for the IdentifierVisitor class.
 */
public class IdentifierVisitorTest {
    private IdentifierVisitor identifierVisitor;

    private ClassOrInterfaceDeclaration classOrInterface;
    private MethodDeclaration method;
    private VariableDeclarator variable;

    /**
     * A method to create the objects needed for testing.
     */
    @Before
    public void setUp() {
        this.identifierVisitor = new IdentifierVisitor();

        this.classOrInterface = new ClassOrInterfaceDeclaration(EnumSet.of(Modifier.PUBLIC), false, "class");
        this.method = new MethodDeclaration(EnumSet.of(Modifier.PUBLIC), new PrimitiveType(PrimitiveType.Primitive.BOOLEAN), "method");
        this.variable = new VariableDeclarator(new PrimitiveType(PrimitiveType.Primitive.BOOLEAN), "variable");
    }

    /**
     * A method to test the getIdentifiers() method from IdentifierVisitor.
     */
    @Test
    public void getIdentifiers() {
        identifierVisitor.visit(classOrInterface, null);
        identifierVisitor.visit(method, null);
        identifierVisitor.visit(variable, null);

        List<String> identifiers = identifierVisitor.getIdentifiers();

        assertEquals(3, identifiers.size());
        assertTrue(identifiers.contains("class"));
        assertTrue(identifiers.contains("method"));
        assertTrue(identifiers.contains("variable"));
    }

    /**
     * A method to test the getIdentifiers() method from IdentifiersVisitor when called
     * before the visitor has visited any nodes.
     */
    @Test
    public void getIdentifiersWithoutVisit() {
        List<String> identifiers = identifierVisitor.getIdentifiers();

        assertEquals(0, identifiers.size());
        assertFalse(identifiers.contains(classOrInterface));
        assertFalse(identifiers.contains(method));
        assertFalse(identifiers.contains(variable));
    }

    /**
     * A method to test the getIdentifiers() method from IdentifierVisitor when called
     * after visiting nodes that aren't class, interface, method, or variable declarations
     * and don't contain any such declarations.
     */
    @Test
    public void getIdentifiersVisitingIrrelevantNodes() {
        identifierVisitor.visit(new BlockComment(), null);
        identifierVisitor.visit(new JavadocComment(), null);
        identifierVisitor.visit(new LineComment(), null);

        List<String> identifiers = identifierVisitor.getIdentifiers();

        assertEquals(0, identifiers.size());
        assertFalse(identifiers.contains(classOrInterface));
        assertFalse(identifiers.contains(method));
        assertFalse(identifiers.contains(variable));
    }

    /**
     * A method to test the visit(ClassOrInterfaceDeclaration) method from IdentifierVisitor.
     */
    @Test
    public void visitClassOrInterfaceDeclaration() {
        identifierVisitor.visit(classOrInterface, null);

        List<String> identifiers = identifierVisitor.getIdentifiers();

        assertEquals(1, identifiers.size());
        assertTrue(identifiers.contains("class"));
    }

    /**
     * A method to test the visit(MethodDeclaration) method from IdentifierVisitor.
     */
    @Test
    public void visitMethodDeclaration() {
        identifierVisitor.visit(method, null);

        List<String> identifiers = identifierVisitor.getIdentifiers();

        assertEquals(1, identifiers.size());
        assertTrue(identifiers.contains("method"));
    }

    /**
     * A method to test the visit(VariableDeclaration) method from IdentifierVisitor.
     */
    @Test
    public void visitVariable() {
        identifierVisitor.visit(variable, null);

        List<String> identifiers = identifierVisitor.getIdentifiers();

        assertEquals(1, identifiers.size());
        assertTrue(identifiers.contains("variable"));
    }
}