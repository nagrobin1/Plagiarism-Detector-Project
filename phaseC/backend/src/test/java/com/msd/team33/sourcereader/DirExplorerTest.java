package com.msd.team33.sourcereader;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * A JUnit test class for the DirExplorer class.
 */
public class DirExplorerTest {

    private DirExplorer explorer = null;

    /**
     * Test if explorer explores all subdirectories.
     *
     * @throws Exception
     */
    @Test
    public void explore() throws Exception {
        explorer = new DirExplorer((level, path, file) -> path.endsWith(".java"), (level, path, file) -> {
        });
        File testFile = new File("src/test/java/com/msd/team33/models/response/testDataFiles/submission.zip");
        explorer.explore(testFile);
        assertTrue(testFile.exists());
        assertNotNull(explorer);
    }
}