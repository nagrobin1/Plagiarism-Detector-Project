package com.msd.team33.models.factors;

import com.github.javaparser.ast.expr.LiteralExpr;

import java.util.ArrayList;
import java.util.List;

/**
 * A concrete class to represent the outcome of comparing the literal expressions in two source code submissions,
 * where the score represents the matching percentage between the two submissions being compared.
 * For example, if no matching literal expressions are found, a score of 0.0 is returned.
 * If all literal expressions from one submission match literal expressions from the other submission,
 * a score of 1.0 is returned.
 * If half of the literal expressions from one submission match literal expressions from the other submission,
 * a score of 0.5 is returned.
 */
public class LiteralFactor extends Factor {

    /**
     * A constructor to create a LiteralFactor from the given lists of comments.
     *
     * @param litExpr1 a list of literal values from the first submission
     * @param litExpr2 a list of literal values from the second submission
     */
    public LiteralFactor(List<LiteralExpr> litExpr1, List<LiteralExpr> litExpr2) {
        this.name = "Literal";

        List<String> litStrings1 = new ArrayList<>();
        List<String> litStrings2 = new ArrayList<>();

        for (LiteralExpr le : litExpr1) {
            litStrings1.add(le.toString());
        }

        for (LiteralExpr le : litExpr2) {
            litStrings2.add(le.toString());
        }

        // get the list of matching literals
        this.matches = findMatches(litStrings1, litStrings2);

        // calculate the score
        if (litStrings1.size() == 0 || litStrings2.size() == 0) {
            this.score = -1.0;
        } else {
            this.score = (double) matches.size() / (double) Math.min(litStrings1.size(), litStrings2.size());
        }

        // create the description
        this.description = String.format("Literal Matching Score: %4f (%d matches found)", score, matches.size());
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
     * A method to get the score for this factor, between 0.0 and 1.0, where the score represents the percentage
     * of literal expressions from the smaller list that were also present in the larger list,
     * with higher scores representing higher likelihood that the two submissions being compared
     * contain plagiarized code.
     *
     * @return the score for this factor, representing the percentage of literal values in common
     */
    @Override
    public double getScore() {
        return this.score;
    }

    /**
     * A method to get a summary description of the matching literal expressions found during comparison.
     *
     * @return a String describing the matching literal values found during comparison
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * A method to get the literal values that were found in both submissions.
     *
     * @return a list of literal values that are present in both submissions
     */
    @Override
    public List<String> getMatches() {
        return this.matches;
    }
}
