package com.msd.team33.models.factors;

import com.github.javaparser.ast.comments.Comment;
import com.msd.team33.models.comparison.PreprocessedSubmission;
import com.msd.team33.models.comparison.Submission;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Tests for the CommentFactor class.
 */
public class CommentFactorTest {

    CommentFactor commentFactor;
    String path1;
    String path2;
    Submission sub1;
    Submission sub2;
    PreprocessedSubmission preSub1;
    PreprocessedSubmission preSub2;

    @Before
    public void setUp() throws Exception {
        path1 = "src/test/java/com/msd/team33/models/comparison/testDataFiles/set00/Student1";
        path2 = "src/test/java/com/msd/team33/models/comparison/testDataFiles/set00/Student2";
        sub1 = new Submission(path1);
        sub2 = new Submission(path2);
        preSub1 = new PreprocessedSubmission(sub1);
        preSub2 = new PreprocessedSubmission(sub2);
        commentFactor = new CommentFactor(preSub1.getComments(), preSub2.getComments());
    }

    @Test
    public void getName() throws Exception {

        assertEquals("Comment", commentFactor.getName());
    }

    @Test
    public void getSubmission1Comments() throws Exception {
        String [] expec = {" Add the two numbers", "\n" + " * Class to add two numbers.\n" + " "};
        List<String> expected = new ArrayList<>(Arrays.asList(expec));
        List<String> result = new ArrayList<>();
        for(Comment c: preSub1.getComments()){
            result.add(c.getContent()); }
        Collections.sort(expected); Collections.sort(result);
        assertEquals("There are two comments in submisison1", expected, result);
    }

    @Test
    public void getSubmission2Comments() throws Exception {

        String [] expec = {" Print out the result", "\n" + " * Class to add two numbers.\n" + " "};
        List<String> expected = new ArrayList<>(Arrays.asList(expec));
        List<String> result = new ArrayList<>();
        for(Comment c: preSub2.getComments()){
            result.add(c.getContent()); }
        Collections.sort(expected); Collections.sort(result);
        assertEquals("There are two comments in submisison2", expected, result);
    }

    @Test
    public void getScore() throws Exception {

        // Round to two decimal places
        double actual = commentFactor.getScore();
        actual = Math.round(actual * 100);
        actual = actual/100;
        assertEquals("1 of the 2 comments in the shorter submission appear in the larger submission",0.50, actual, 0.0001);
    }

    @Test
    public void getDescription() throws Exception {
        String expected = "Comment Matching Score: 0.500000 (1 matches found)";
        assertEquals("The description does not match", expected, commentFactor.getDescription());
    }

    @Test
    public void getMatches() throws Exception {
        String [] expectedMatches = {"\n" + " * Class to add two numbers.\n" + " "};
        List<String> expected = new ArrayList<>(Arrays.asList(expectedMatches));
        assertEquals("There is one comment match between the two submissions", expected, commentFactor.getMatches());
    }

}