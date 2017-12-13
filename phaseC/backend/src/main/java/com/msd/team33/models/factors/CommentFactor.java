package com.msd.team33.models.factors;

import com.github.javaparser.ast.comments.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * A concrete class to represent the outcome of comparing the contents of comments in two source code submissions,
 * where the score represents the matching percentage between the two submissions being compared.
 * For example, if no matching comments are found, a score of 0.0 is returned.
 * If all comment contents from one submission match comment contents from the other submission,
 * a score of 1.0 is returned.
 * If half of the comment contents from one submission match comment contents from the other submission,
 * a score of 0.5 is returned.
 */
public class CommentFactor extends Factor {

    /**
     * A constructor to create a CommentFactor from the given lists of comments.
     *
     * @param comments1 a list of comments from submission 1
     * @param comments2 a list of comments from submission 2
     */
    public CommentFactor(List<Comment> comments1, List<Comment> comments2) {
        this.name = "Comment";

        List<String> contents1 = new ArrayList<>();
        List<String> contents2 = new ArrayList<>();

        for (Comment c : comments1) {
            contents1.add(c.getContent());
        }

        for (Comment c : comments2) {
            contents2.add(c.getContent());
        }

        // get the list of matching comments
        this.matches = findMatches(contents1, contents2);

        // calculate the score
        if (contents1.size() == 0 || contents2.size() == 0) {
            this.score = -1.0;
        } else {
            this.score = (double) matches.size() / (double) Math.min(contents1.size(), contents2.size());
        }

        // create the description
        this.description = String.format("Comment Matching Score: %4f (%d matches found)", score, matches.size());
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
     * of comment contents from the smaller list that were also present in the larger list,
     * with higher scores representing higher likelihood that the two submissions being compared
     * contain plagiarized code.
     *
     * @return the score for this factor, representing the percentage of comment contents in common
     */
    @Override
    public double getScore() {
        return this.score;
    }

    /**
     * A method to get a summary description of the matching comment contents found during comparison.
     *
     * @return a String describing the matching comment contents found during comparison
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * A method to get the comment contents that were found in both submissions.
     *
     * @return a list of comment contents that are present in both submissions
     */
    @Override
    public List<String> getMatches() {
        return this.matches;
    }
}
