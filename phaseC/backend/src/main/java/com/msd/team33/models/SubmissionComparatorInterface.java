package com.msd.team33.models;

import com.msd.team33.models.response.ComparisonDetails;

import java.io.IOException;
import java.util.List;

/**
 * An interface to represent all functions needed for a model (in the model-view-controller design pattern)
 * that compares source code from software projects (passed as path identifierNames to project directories)
 * to identify potential plagiarism.
 */
public interface SubmissionComparatorInterface {
    /**
     * A method to return the text of a code submission given the path name to the project directory.
     *
     * @param path the path name to the project directory for a code submission
     * @return the text of the submission as a string
     */
    public String getText(String path);

    /**
     * A method to compare code submissions from a given array of filepath strings to identify potential plagiarism.
     *
     * @param filePaths an array of strings representing file paths to the submissions that will be compared
     * @return a sorted list of comparison details summarizing project-to-project comparisons in descending order
     */
    public List<ComparisonDetails> compare(String[] filePaths) throws IOException;
}