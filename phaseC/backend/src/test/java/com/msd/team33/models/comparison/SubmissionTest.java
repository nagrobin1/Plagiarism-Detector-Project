package com.msd.team33.models.comparison;

import com.msd.team33.models.comparison.Submission;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

/**
 * Created by Abhishek Mulay on 11/20/17.
 */

/**
 * A JUnit test class to verify that the Submission class is functioning properly.
 */
public class SubmissionTest {
    Submission sub1;

    /**
     * A method to initialize the Submission objects needed for testing.
     */
    @Before
    public void setUp() {
        sub1 = new Submission("pathName");
    }

    /**
     * A method to verify that the Submission constructor and getPath() method are functioning properly.
     */
    @Test
    public void testGetPath() {
        assertEquals(Paths.get("pathName"), sub1.getPath());
    }

    /**
     * A method to verify that the Submission constructor and getPathNameAsString() method are functioning properly.
     */
    @Test
    public void testGetPathNameAsString() {
        assertEquals("pathName", sub1.getPathNameAsString());
    }
}