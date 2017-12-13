package com.msd.team33.models.comparison;

import com.msd.team33.models.factors.*;
import com.msd.team33.models.response.ComparisonDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that compares two submissions for plagiarism likelihood,
 * creating an account of the details for each comparison.
 */
public class Comparison implements ComparisonInterface {
    private PreprocessedSubmission preprocessedSubmission1;
    private PreprocessedSubmission preprocessedSubmission2;
    private List<Factor> factors;
    private ComparisonDetails details;

    /**
     * A constructor that compares the given preprocessed submissions to determine the relative likelihood
     * that one of the submissions contains plagiarism.
     *
     * @param preprocessedSubmission1 student1's preprocessed object
     * @param preprocessedSubmission2 student2's preprocessed object
     */
    public Comparison(PreprocessedSubmission preprocessedSubmission1, PreprocessedSubmission preprocessedSubmission2) {
        this.preprocessedSubmission1 = preprocessedSubmission1;
        this.preprocessedSubmission2 = preprocessedSubmission2;

        // for each strategy, create a factor
        this.factors = createFactors();

        // create details from factors
        this.details = createComparisonDetails();
    }

    /**
     * A helper method to create the comparison factors used for this comparison.
     *
     * @return a list of comparison factors used for this comparison
     */
    private List<Factor> createFactors() {
        List<Factor> factors = new ArrayList<>();

        factors.add(new IdentifierFactor(preprocessedSubmission1.getIdentifierNames(), preprocessedSubmission2.getIdentifierNames()));
        factors.add(new CommentFactor(preprocessedSubmission1.getComments(), preprocessedSubmission2.getComments()));
        factors.add(new LiteralFactor(preprocessedSubmission1.getLiterals(), preprocessedSubmission2.getLiterals()));
        factors.add(new StatementFactor(preprocessedSubmission1.getBlockStatements(), preprocessedSubmission2.getBlockStatements()));

        return factors;
    }

    /**
     * A helper method to create the ComparisonDetails object that contains the details of this comparison.
     *
     * @return the ComparisonDetails object that contains the details of this comparison
     */
    private ComparisonDetails createComparisonDetails() {
        double score = 0.0;
        List<String> matches = new ArrayList<>();
        List<String> factorBreakdown = new ArrayList<>();

        int significantFactors = 0;

        for (Factor f : factors) {
            if (f.getScore() >= 0.0) {
                score += f.getScore();
                matches.addAll(f.getMatches());
                factorBreakdown.add(f.getDescription());
                significantFactors++;
            }
        }

        score = score / (double) significantFactors;

        String path1 = preprocessedSubmission1.getSubmission().getPath().toString();
        String path2 = preprocessedSubmission2.getSubmission().getPath().toString();

        return new ComparisonDetails(path1, path2, score, matches, factorBreakdown);
    }

    /**
     * Returns the ComparisonDetails object for this comparison,
     * which contains the details of this comparison needed for display.
     */
    @Override
    public ComparisonDetails getDetails() {
        return this.details;
    }
}