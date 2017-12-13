package com.msd.team33.models.comparison;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.comments.BlockComment;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.ast.expr.LiteralExpr;
import com.github.javaparser.ast.expr.NullLiteralExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * A JUnit test class for the PreprocessedSubmission class.
 */
public class PreprocessedSubmissionTest {
    PreprocessedSubmission preSub1;
    PreprocessedSubmission preSub2;

    Submission sub1;
    Submission sub2;

    /**
     * A method to create the objects needed for testing.
     */
    @Before
    public void setUp() throws IOException {
        sub1 = new Submission("src/test/java/com/msd/team33/models/comparison/testDataFiles/set00/Student1");
        sub2 = new Submission("src/test/java/com/msd/team33/models/comparison/testDataFiles/set00");

        preSub1 = new PreprocessedSubmission(sub1);
        preSub2 = new PreprocessedSubmission(sub2);
    }

    @Test
    public void getSubmission() {
        assertEquals(sub1.getPathNameAsString(), preSub1.getSubmission().getPathNameAsString());
        assertEquals(sub2.getPathNameAsString(), preSub2.getSubmission().getPathNameAsString());
    }

    /**
     * A method to test the getSourceText() method from the PreprocessedSubmission class
     * for a submission containing a single file.
     */
    @Test
    public void getSourceTextSingleFile() {
        StringBuilder sb = new StringBuilder();

        sb.append("package com.msd.team33.models.comparison.testDataFiles.set00.Student1;\n");
        sb.append("\n");
        sb.append("import java.util.Scanner;\n");
        sb.append("\n");
        sb.append("/**\n");
        sb.append(" * Class to add two numbers.\n");
        sb.append(" */\n");
        sb.append("class AddNumbers {\n");
        sb.append("    public static void main(String args[]) {\n");
        sb.append("        int x, y, z;\n");
        sb.append("        System.out.println(\"Enter two integers to calculate their sum \");\n");
        sb.append("        Scanner in = new Scanner(System.in);\n");
        sb.append("        x = in.nextInt();\n");
        sb.append("        y = in.nextInt();\n");
        sb.append("        // Add the two numbers\n");
        sb.append("        z = x + y;\n");
        sb.append("        System.out.println(\"Sum of entered integers = \" + z);\n");
        sb.append("    }\n");
        sb.append("}\n");

        assertEquals(sb.toString(), preSub1.getSourceText());
    }

    /**
     * A method to test the getSourceText() method from the PreprocessedSubmission class
     * for a submission containing multiple files.
     */
    @Test
    public void getSourceTextMultipleFiles() throws IOException {
        sub1 = new Submission("src/test/java/com/msd/team33/models/comparison/testDataFiles/set21");
        preSub1 = new PreprocessedSubmission(sub1);

        StringBuilder sb = new StringBuilder();

        sb.append("package com.msd.team33.models.comparison.testDataFiles.set21;\n");
        sb.append("\n");
        sb.append("public class DLLNode2<Object> {\n");
        sb.append("	\n");
        sb.append("	protected Object val;\n");
        sb.append("	protected DLLNode2<Object> next, prev;\n");
        sb.append("\n");
        sb.append("	/* Constructor */\n");
        sb.append("\n");
        sb.append("	public DLLNode2() {\n");
        sb.append("		next = null;\n");
        sb.append("		prev = null;\n");
        sb.append("		val = null;\n");
        sb.append("	}\n");
        sb.append("}\n");

        assertTrue(preSub1.getSourceText().contains(sb.toString()));

        sb = new StringBuilder();

        sb.append("package com.msd.team33.models.comparison.testDataFiles.set21;\n");
        sb.append("\n");
        sb.append("import java.util.NoSuchElementException;\n");
        sb.append("\n");
        sb.append("/* Class DLL */\n");
        sb.append("public class DLL2 {\n");
        sb.append("\n");
        sb.append("	/* Constructor */\n");
        sb.append("	public DLL2() {\n");
        sb.append("	}\n");
        sb.append("}\n");

        assertTrue(preSub1.getSourceText().contains(sb.toString()));
    }

    /**
     * A method to test the getAsts() method from the PreprocessedSubmission class.
     */
    @Test
    public void getAsts() throws FileNotFoundException {
        JavaParser jp = new JavaParser();
        File f1 = new File("src/test/java/com/msd/team33/models/comparison/testDataFiles/set00/Student1/AddNumbers.java");
        File f2 = new File("src/test/java/com/msd/team33/models/comparison/testDataFiles/set00/Student2/AddNumbers2.java");

        CompilationUnit cu1 = jp.parse(f1);
        CompilationUnit cu2 = jp.parse(f2);

        List<CompilationUnit> listOfCUs = preSub2.getASTs();

        assertEquals(2, listOfCUs.size());
        assertTrue(listOfCUs.contains(cu1));
        assertTrue(listOfCUs.contains(cu2));
    }

    /**
     * A method to test the getIdentifierNames() method from the PreprocessedSubmission class.
     */
    @Test
    public void getIdentifierNames() {
        List<String> identifiers = preSub1.getIdentifierNames();

        assertEquals(6, identifiers.size());
        assertTrue(identifiers.contains("AddNumbers"));
        assertTrue(identifiers.contains("main"));
        assertTrue(identifiers.contains("x"));
        assertTrue(identifiers.contains("y"));
        assertTrue(identifiers.contains("z"));
        assertTrue(identifiers.contains("in"));
    }

    /**
     * A method to test the getComments() method from the PreprocessedSubmission class.
     */
    @Test
    public void getComments() throws IOException {
        sub1 = new Submission("src/test/java/com/msd/team33/models/comparison/testDataFiles/set04/Sample1");
        preSub1 = new PreprocessedSubmission(sub1);

        List<Comment> comments = preSub1.getComments();

        Comment c1 = new BlockComment(" Function to get val from node ");
        Comment c2 = new BlockComment(" Function to get length of list ");
        Comment c3 = new BlockComment(" Function to display status of list ");
        Comment c4 = new BlockComment(" Function to get link to previous node ");
        Comment c5 = new BlockComment(" Function to check if list is empty ");
        Comment c6 = new BlockComment(" Function to delete node at position ");
        Comment c7 = new BlockComment(" Constructor ");
        Comment c8 = new BlockComment(" Funtion to get link to next node ");
        Comment c9 = new BlockComment("\r\n *  Java Program to Implement Doubly Linked List\r\n ");
        Comment c10 = new BlockComment(" Function to set link to next node ");
        Comment c11 = new BlockComment(" Class DLL ");
        Comment c12 = new BlockComment(" Function to insert element at last ");
        Comment c13 = new BlockComment(" Function to set val to node ");
        Comment c14 = new BlockComment(" Function to insert element at beginning ");
        Comment c15 = new BlockComment(" Function to set link to previous node ");

        assertEquals(15, comments.size());
        assertTrue(comments.contains(c1));
        assertTrue(comments.contains(c2));
        assertTrue(comments.contains(c3));
        assertTrue(comments.contains(c4));
        assertTrue(comments.contains(c5));
        assertTrue(comments.contains(c6));
        assertTrue(comments.contains(c7));
        assertTrue(comments.contains(c8));
        assertTrue(comments.contains(c9));
        assertTrue(comments.contains(c10));
        assertTrue(comments.contains(c11));
        assertTrue(comments.contains(c12));
        assertTrue(comments.contains(c13));
        assertTrue(comments.contains(c14));
        assertTrue(comments.contains(c15));
    }

    /**
     * A method to test the getLiterals() method from the PreprocessedSubmission class.
     */
    @Test
    public void getLiterals() throws IOException {
        sub1 = new Submission("src/test/java/com/msd/team33/models/comparison/testDataFiles/set04/Sample1");
        preSub1 = new PreprocessedSubmission(sub1);

        List<LiteralExpr> literals = preSub1.getLiterals();

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

    /**
     * A method to test the getBlockStatements() method from the PreprocessedSubmission class.
     */
    @Test
    public void getBlockStatements() throws IOException {
        JavaParser jp = new JavaParser();
        File f1 = new File("src/test/java/com/msd/team33/models/comparison/testDataFiles/set04/Sample1/DLL.java");
        File f2 = new File("src/test/java/com/msd/team33/models/comparison/testDataFiles/set04/Sample1/DLLNode.java");
        CompilationUnit cu1 = jp.parse(f1);
        CompilationUnit cu2 = jp.parse(f2);
        List<BlockStmt> blocks = new ArrayList();
        blocks.addAll(cu1.findAll(new BlockStmt().getClass()));
        blocks.addAll(cu2.findAll(new BlockStmt().getClass()));

        sub1 = new Submission("src/test/java/com/msd/team33/models/comparison/testDataFiles/set04/Sample1");
        preSub1 = new PreprocessedSubmission(sub1);

        assertEquals(blocks.size(), preSub1.getBlockStatements().size());

        for (BlockStmt bs : preSub1.getBlockStatements()) {
            assertTrue(blocks.contains(bs));
        }
    }
}