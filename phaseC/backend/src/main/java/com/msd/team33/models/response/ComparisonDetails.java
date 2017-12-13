package com.msd.team33.models.response;

import java.util.List;

/**
 * A class to represent the details of a one-to-one comparison of source code assignment submissions
 * where comparison is to identify the relative likelihood of plagiarism.
 */
public class ComparisonDetails {
    private String submissionPath1;
    private String submissionPath2;
    private double finalScore;
    private List<String> matched;
    private List<String> factorBreakdown;

    /**
     * A constructor to create a ComparisonDetails object from the given path names, final score,
     * and list of matches found during comparison; the final score ranges from 0.0 to 1.0,
     * where 0.0 means no matches were found between the submissions
     * and 1.0 means every possible match between the submissions was found.
     *
     * @param submissionPath1 the directory path to the first submission of the comparison
     * @param submissionPath2 the directory path to the second submission of the comparison
     * @param finalScore      a score representing the relative likelihood of plagiarism in the given submissions
     * @param matched         a list of identifiers common to both submissions
     * @param factorBreakdown a list of strings describing the results of factor sub-comparisons for this comparison
     */
    public ComparisonDetails(String submissionPath1, String submissionPath2, double finalScore,
                             List<String> matched, List<String> factorBreakdown) {
        this.submissionPath1 = submissionPath1;
        this.submissionPath2 = submissionPath2;
        this.finalScore = finalScore;
        this.matched = matched;
        this.factorBreakdown = factorBreakdown;
    }

    /**
     * A method to get the path of the first submission in the comparison.
     *
     * @return the path of the first submission in the comparison
     */
    public String getSubmissionPath1() {
        return submissionPath1;
    }

    /**
     * A method to set the path of the first submission in the comparison.
     *
     * @param submissionPath1 the path of the first submission in teh comparison.
     */
    public void setSubmissionPath1(String submissionPath1) {
        this.submissionPath1 = submissionPath1;
    }

    /**
     * A method to get the path of the second submission in the comparison.
     *
     * @return the path of the second submission in the comparison
     */
    public String getSubmissionPath2() {
        return submissionPath2;
    }

    /**
     * A method to set the path of the second submission in the comparison.
     *
     * @param submissionPath2 the path of the second submission in teh comparison.
     */
    public void setSubmissionPath2(String submissionPath2) {
        this.submissionPath2 = submissionPath2;
    }

    /**
     * A method to get the final score of the comparison, representing the relative likelihood of plagiarism
     * in the given submissions; the final score averages over relevant factors,
     * where each factor represents the percentage of matches found (with respect to the total possible matches)
     * between the two submissions according to a particular way of comparing (or comparison strategy).
     *
     * @return the final score of the comparison, representing the likelihood of plagiarism
     */
    public double getFinalScore() {
        return finalScore;
    }

    /**
     * A method to set the final score of the comparison, representing the relative likelihood of plagiarism
     * in the given submissions.
     *
     * @param finalScore teh final score of the comparison, representing the likelihood of plagiarism
     */
    public void setFinalScore(double finalScore) {
        this.finalScore = finalScore;
    }

    /**
     * A method to get the list of identifiers common to both submissions for this comparison.
     *
     * @return a list of identifiers common to both submissions
     */
    public List<String> getMatched() {
        return matched;
    }

    /**
     * A method to set the list of shared identifiers for this comparison's two submissions.
     *
     * @param matched the list of shared identifiers for this comparison's two submissions.
     */
    public void setMatched(List<String> matched) {
        this.matched = matched;
    }

    /**
     * A method to get a list of descriptions, one for each factor sub-comparison for this comparison.
     *
     * @return
     */
    public List<String> getFactorBreakdown() {
        return this.factorBreakdown;
    }

    public void setFactorBreakdown(List<String> factorBreakdown) {
        this.factorBreakdown = factorBreakdown;
    }
}