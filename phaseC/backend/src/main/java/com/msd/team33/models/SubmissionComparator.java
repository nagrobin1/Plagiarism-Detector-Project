package com.msd.team33.models;

import com.msd.team33.models.comparison.PreprocessedSubmission;
import com.msd.team33.models.comparison.Submission;
import com.msd.team33.models.response.ComparisonDetails;
import com.msd.team33.models.comparison.Comparison;
import com.msd.team33.models.comparison.ComparisonInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A class to represent all functions needed for a model (in the model-view-controller design pattern)
 * that compares source code from software projects (passed as path strings for project directories)
 * to identify potential plagiarism and reports the details of such comparisons.
 */
public class SubmissionComparator implements SubmissionComparatorInterface {
    List<Submission> submissions;
    List<PreprocessedSubmission> preprocessedSubmissions;
    List<ComparisonDetails> comparisonDetails;

    /**
     * A method to compare code submissions from a given array of filepath strings to identify potential plagiarism.
     *
     * @param filePaths an array of strings representing file paths to the submissions that will be compared
     * @return a sorted list of comparison details summarizing project-to-project comparisons in descending order
     * @throws IOException when an issue arises opening/reading the files within a given directory path
     */
    @Override
    public List<ComparisonDetails> compare(String[] filePaths) throws IOException {

        // create Submissions
        submissions = new ArrayList<>();

        for (String path : filePaths) {
            submissions.add(new Submission(path));
        }

        // create Preprocessed Submissions
        preprocessedSubmissions = new ArrayList<>();
        for (Submission sub : submissions) {
            preprocessedSubmissions.add(new PreprocessedSubmission(sub));
        }

        //get comparisons of each AST with each other AST
        comparisonDetails = getComparisons(preprocessedSubmissions);

        return comparisonDetails;
    }

    /**
     * A helper method to compare each given preprocessed submission with each other.
     *
     * @param preprocessedSubmissions a list of preprocessed submissions to be compared
     * @return a list of ComparisonDetails, where each holds the details for each comparison made
     */
    private List<ComparisonDetails> getComparisons(List<PreprocessedSubmission> preprocessedSubmissions) {
        ComparisonInterface comparison;
        List<ComparisonDetails> comparisonDetails = new ArrayList<>();

        // compare each preprocessed submission in the given list...
        for (int i = 0; i < preprocessedSubmissions.size(); i++) {
            // ...with each processed submission further down the list (to avoid duplicating comparisons)
            for (int j = i + 1; j < preprocessedSubmissions.size(); j++) {
                comparison = new Comparison(preprocessedSubmissions.get(i), preprocessedSubmissions.get(j));
                comparisonDetails.add(comparison.getDetails());
            }
        }

        return comparisonDetails;
    }

    /**
     * A method to get the text from the .java source files of a given submission.
     *
     * @param path the path name to the project directory for a code submission
     * @return text from the .java source files of the given submission
     * @throws NoSuchElementException
     */
    @Override
    public String getText(String path) throws NoSuchElementException {
        for (PreprocessedSubmission ps : preprocessedSubmissions) {
            if (ps.getSubmission().getPathNameAsString().equals(path)) {
                return ps.getSourceText();
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Source text for ");
        sb.append(path);
        sb.append(" does not exists; please verify proper path name.");
        throw new NoSuchElementException(sb.toString());
    }
}