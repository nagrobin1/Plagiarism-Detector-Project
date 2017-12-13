package com.msd.team33.models.strategy;

import com.github.javaparser.ast.comments.BlockComment;
import com.github.javaparser.ast.comments.Comment;
import com.msd.team33.models.comparison.PreprocessedSubmission;
import com.msd.team33.models.comparison.Submission;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * A JUnit test class for the CommentStrategy class.
 */
public class CommentStrategyTest {
    CommentStrategy commentStrategy;
    Submission sub;
    PreprocessedSubmission preSub;

    /**
     * A method to create the objects needed for testing.
     */
    @Before
    public void setUp() throws IOException {
        sub = new Submission("src/test/java/com/msd/team33/models/comparison/testDataFiles/set04/Sample1");
        preSub = new PreprocessedSubmission(sub);
        commentStrategy = new CommentStrategy(preSub.getASTs());
    }

    /**
     * A method to test the getVisitationResults() method from the CommentStrategy class.
     */
    @Test
    public void getVisitationResults() {
        List<Comment> comments = commentStrategy.getVisitationResults();

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
}