package com.msd.team33.models.factors;

import java.util.List;

/**
 * An interface for the functions needed to represent the outcome of comparing two source code submissions
 * according to one specific comparison strategy, where the score represents the matching percentage
 * between the two submissions being compared.
 * For example, if no matches are found according to this specific strategy, a score of 0.0 is returned.
 * If all possible matches are found, a score of 1.0 is returned.
 * If half of all possible matches are found, a score of 0.5 is returned.
 */
public interface ComparisonFactor {
    /**
     * A method to get the name of this factor.
     *
     * @return the name of this factor
     */
    public String getName();

    /**
     * A method to get the score for this factor, where the score represents the matching percentage
     * between the two submissions being compared
     * according to this factor's specific method of comparison.
     *
     * @return the score for a specific comparison of two submissions according to a specific comparison method
     */
    public double getScore();

    /**
     * A method to get a summary description of the results of this factor sub-comparison.
     *
     * @return a String describing the results of a particular factor sub-comparison
     */
    public String getDescription();

    /**
     * A method to get the matches found during this factor sub-comparison.
     *
     * @return a list of matches found during comparison by this factor's strategy
     */
    public List<String> getMatches();
}