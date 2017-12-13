package com.msd.team33.models.strategy;

import com.msd.team33.models.comparison.PreprocessedSubmission;
import com.msd.team33.models.comparison.Submission;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * A JUnit test class for the IdentifierStrategy class.
 */
public class IdentifierStrategyTest {
    IdentifierStrategy identifierStrategy;
    Submission sub;
    PreprocessedSubmission preSub;

    /**
     * A method to create the objects needed for testing.
     */
    @Before
    public void setUp() throws IOException {
        sub = new Submission("src/test/java/com/msd/team33/models/comparison/testDataFiles/set04/Sample1");
        preSub = new PreprocessedSubmission(sub);
        identifierStrategy = new IdentifierStrategy(preSub.getASTs());
    }

    /**
     * A method to test the getVisitationResults() method from the IdentifierStrategy class.
     */
    @Test
    public void getVisitationResults() {
        List<String> identifiers = preSub.getIdentifierNames();

        assertEquals(26, identifiers.size());
        assertTrue(identifiers.contains("DLL"));
        assertTrue(identifiers.contains("first"));
        assertTrue(identifiers.contains("last"));
        assertTrue(identifiers.contains("length"));
        assertTrue(identifiers.contains("isEmpty"));
        assertTrue(identifiers.contains("add"));
        assertTrue(identifiers.contains("nptr"));
        assertTrue(identifiers.contains("append"));
        assertTrue(identifiers.contains("deleteAtPos"));
        assertTrue(identifiers.contains("ptr"));
        assertTrue(identifiers.contains("i"));
        assertTrue(identifiers.contains("p"));
        assertTrue(identifiers.contains("n"));
        assertTrue(identifiers.contains("toString"));
        assertTrue(identifiers.contains("ret"));
        assertTrue(identifiers.contains("current"));
        assertTrue(identifiers.contains("DLLNode"));
        assertTrue(identifiers.contains("val"));
        assertTrue(identifiers.contains("next"));
        assertTrue(identifiers.contains("prev"));
        assertTrue(identifiers.contains("setLinkNext"));
        assertTrue(identifiers.contains("setLinkPrev"));
        assertTrue(identifiers.contains("getLinkNext"));
        assertTrue(identifiers.contains("getLinkPrev"));
        assertTrue(identifiers.contains("setData"));
        assertTrue(identifiers.contains("getData"));
    }
}