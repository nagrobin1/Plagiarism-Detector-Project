package com.msd.team33.sourcereader;

import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * A JUnit test class for the CompilationUnitProvider class.
 */
public class CompilationUnitProviderTest {

    private CompilationUnitProvider provider = new CompilationUnitProvider();

    /**
     * Test if provider returns list of CompilationUnits from given source directory.
     *
     * @throws Exception
     */
    @Test
    public void getCompilationUnits() throws Exception {
        File sourceDir = new File("src/test/java/com/msd/team33/sourcereader");
        List<CompilationUnit> compilationUnits = provider.getCompilationUnits(sourceDir);
        assertFalse(compilationUnits.isEmpty());
        assertNotNull(compilationUnits);
    }
}