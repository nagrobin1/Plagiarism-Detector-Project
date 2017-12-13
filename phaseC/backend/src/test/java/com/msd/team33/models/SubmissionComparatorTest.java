package com.msd.team33.models;

import com.msd.team33.models.comparison.Comparison;
import com.msd.team33.models.comparison.PreprocessedSubmission;
import com.msd.team33.models.comparison.Submission;
import com.msd.team33.models.response.ComparisonDetails;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * A JUnit test class for the SubmissionComparator class.
 */
public class SubmissionComparatorTest {
    SubmissionComparatorInterface subComp;
    List<ComparisonDetails> compDetailsList;

    /**
     * A method to create the objects needed for testing.
     */
    @Before
    public void setUp() throws IOException {
        // create a new SubmissionComparator
        subComp = new SubmissionComparator();

        // create an array for the three directory paths
        String[] projectPaths = {"src/test/java/com/msd/team33/models/comparison/testDataFiles/set22/Sample1",
                "src/test/java/com/msd/team33/models/comparison/testDataFiles/set22/Sample2",
                "src/test/java/com/msd/team33/models/comparison/testDataFiles/set22/Sample3"};

        // use this array to call the compare() method from SubmissionComparator and get a list of ComparisonDetails
        compDetailsList = subComp.compare(projectPaths);
    }

    /**
     * A method to test that the compare() method from the SubmissionComparator class
     * performs all the proper comparisons for a given list of paths to project directories.
     */
    @Test
    public void compare() throws IOException {
        // the list of comparison details created in setUp() should have a size of three
        assertEquals(3, compDetailsList.size());

        // create three submissions from the same source paths given to the SubmissionComparator constructor above
        Submission sub1 = new Submission("src/test/java/com/msd/team33/models/comparison/testDataFiles/set22/Sample1");
        Submission sub2 = new Submission("src/test/java/com/msd/team33/models/comparison/testDataFiles/set22/Sample2");
        Submission sub3 = new Submission("src/test/java/com/msd/team33/models/comparison/testDataFiles/set22/Sample3");

        // create three preprocessed submissions from these submissions
        PreprocessedSubmission preSub1 = new PreprocessedSubmission(sub1);
        PreprocessedSubmission preSub2 = new PreprocessedSubmission(sub2);
        PreprocessedSubmission preSub3 = new PreprocessedSubmission(sub3);

        // create three comparisons from these preprocessed submissions
        Comparison c1 = new Comparison(preSub1, preSub2);
        Comparison c2 = new Comparison(preSub1, preSub3);
        Comparison c3 = new Comparison(preSub2, preSub3);

        // get the three comparison details from these comparisons
        ComparisonDetails cd1 = c1.getDetails();
        ComparisonDetails cd2 = c2.getDetails();
        ComparisonDetails cd3 = c3.getDetails();

        // for each comparison detail, create a string that concatenates the path names that were compared
        String cd1paths = cd1.getSubmissionPath1() + " " + cd1.getSubmissionPath2();
        String cd2paths = cd2.getSubmissionPath1() + " " + cd2.getSubmissionPath2();
        String cd3paths = cd3.getSubmissionPath1() + " " + cd3.getSubmissionPath2();

        // now, for each comparison detail from the list returned by compare()...
        ComparisonDetails cdl0 = compDetailsList.get(0);
        ComparisonDetails cdl1 = compDetailsList.get(1);
        ComparisonDetails cdl2 = compDetailsList.get(2);

        // ...create a string that concatenates the path names that were compared
        String cdl0paths = cdl0.getSubmissionPath1() + " " + cdl0.getSubmissionPath2();
        String cdl1paths = cdl1.getSubmissionPath1() + " " + cdl1.getSubmissionPath2();
        String cdl2paths = cdl2.getSubmissionPath1() + " " + cdl2.getSubmissionPath2();

        // create a list of these strings
        List<String> comparisonPaths = new ArrayList();
        comparisonPaths.add(cdl0paths);
        comparisonPaths.add(cdl1paths);
        comparisonPaths.add(cdl2paths);

        // this list should contain the same comparison path names as the ones created separately
        // to confirm that the proper comparisons were executed by the compare() method
        assertTrue(comparisonPaths.contains(cd1paths));
        assertTrue(comparisonPaths.contains(cd2paths));
        assertTrue(comparisonPaths.contains(cd3paths));
    }

    /**
     * A method to test that the getText() method from SubmissionComparator
     * returns the appropriate string representation of the source text
     * for the given project directory path.
     */
    @Test
    public void getText() throws IOException {
        String path = "src/test/java/com/msd/team33/models/comparison/testDataFiles/set22/Sample1";

        // create three submissions from the same source paths given to the SubmissionComparator constructor above
        Submission sub1 = new Submission(path);

        // create three preprocessed submissions from these submissions
        PreprocessedSubmission preSub1 = new PreprocessedSubmission(sub1);

        assertEquals(preSub1.getSourceText(), subComp.getText(path));
    }

    /**
     * A method to test that the getText() method from SubmissionComparator
     * throws a NoSuchElementException when passed a path string that wasn't first passed to the compare() method.
     */
    @Test(expected = NoSuchElementException.class)
    public void getTextBadPath() throws IOException {
        String path = "src/test/java/com/msd/team33/models/comparison/testDataFiles/set00/Student1";
        String text = subComp.getText(path);
    }
}