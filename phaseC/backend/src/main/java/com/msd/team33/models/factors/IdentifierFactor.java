package com.msd.team33.models.factors;

import java.util.List;

/**
 * A concrete class to represent the outcome of comparing the identifiers (names) used for class declarations,
 * interface declarations, method declarations, and variable declarations in two source code submissions,
 * where the score represents the matching percentage between the two submissions being compared.
 * For example, if no matching identifiers are found, a score of 0.0 is returned.
 * If all identifiers from one submission match identifiers from the other submission, a score of 1.0 is returned.
 * If half of the identifiers from one submission match identifiers from the other submission,
 * a score of 0.5 is returned.
 */

public class IdentifierFactor extends Factor {

    /**
     * A constructor to create an IdentifierFactor from the given lists of identifiers.
     *
     * @param identifiers1 a list of unique identifiers used in submission 1
     * @param identifiers2 a list of unique identifiers used in submission 2
     */
    public IdentifierFactor(List<String> identifiers1, List<String> identifiers2) {
        this.name = "Identifier";

        // get the list of matching identifiers
        this.matches = findMatches(identifiers1, identifiers2);

        // calculate the score
        if (identifiers1.size() == 0 || identifiers2.size() == 0) {
            this.score = -1.0;
        } else {
            this.score = (double) matches.size() / (double) Math.min(identifiers1.size(), identifiers2.size());
        }

        // create the description
        this.description = String.format("Identifier Matching Score: %4f (%d matches found)", score, matches.size());
    }

    /**
     * A method to get the name of this factor.
     *
     * @return the name of this factor
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * A method to get the identifiers that were found in both submissions.
     *
     * @return a list of identifiers that are present in both submissions
     */
    @Override
    public List<String> getMatches() {
        return this.matches;
    }

    /**
     * A method to get the score for this factor, between 0.0 and 1.0, where the score represents the percentage
     * of identifiers from the smaller list that were also present in the larger list,
     * with higher scores representing higher likelihood that the two submissions being compared
     * contain plagiarized code.
     *
     * @return the score for this factor, representing the percentage of identifiers in common
     */
    @Override
    public double getScore() {
        return this.score;
    }

    /**
     * A method to get a description of the matching identifiers found during comparison.
     *
     * @return a String describing the matching identifiers found during comparison
     */
    @Override
    public String getDescription() {
        return this.description;
    }
}
