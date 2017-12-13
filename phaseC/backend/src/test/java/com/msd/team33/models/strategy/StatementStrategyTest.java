package com.msd.team33.models.strategy;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.msd.team33.models.comparison.PreprocessedSubmission;
import com.msd.team33.models.comparison.Submission;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * A JUnit test class for the StatementStrategy class.
 */
public class StatementStrategyTest {
    StatementStrategy statementStrategy;
    Submission sub;
    PreprocessedSubmission preSub;

    /**
     * A method to create the objects needed for testing.
     */
    @Before
    public void setUp() throws IOException {
        sub = new Submission("src/test/java/com/msd/team33/models/comparison/testDataFiles/set04/Sample1");
        preSub = new PreprocessedSubmission(sub);
        statementStrategy = new StatementStrategy(preSub.getASTs());
    }

    /**
     * A method to test the getBlockStatements() method from the StatementStrategy class.
     */
    @Test
    public void getBlockStatements() throws Exception {
        JavaParser jp = new JavaParser();
        File f1 = new File("src/test/java/com/msd/team33/models/comparison/testDataFiles/set04/Sample1/DLL.java");
        File f2 = new File("src/test/java/com/msd/team33/models/comparison/testDataFiles/set04/Sample1/DLLNode.java");
        CompilationUnit cu1 = jp.parse(f1);
        CompilationUnit cu2 = jp.parse(f2);
        List<BlockStmt> blocks = new ArrayList();
        blocks.addAll(cu1.findAll(new BlockStmt().getClass()));
        blocks.addAll(cu2.findAll(new BlockStmt().getClass()));

        assertEquals(blocks.size(), preSub.getBlockStatements().size());

        for (BlockStmt bs : preSub.getBlockStatements()) {
            assertTrue(blocks.contains(bs));
        }
    }
}