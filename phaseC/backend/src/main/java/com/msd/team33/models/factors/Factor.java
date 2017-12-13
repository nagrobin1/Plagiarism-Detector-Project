package com.msd.team33.models.factors;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * An abstract class to represent the outcome of comparing two source code submissions
 * according to one specific comparison strategy, where the score represents the matching percentage
 * between the two submissions being compared.
 * For example, if no matches are found according to this specific strategy, a score of 0.0 is returned.
 * If all possible matches are found, a score of 1.0 is returned.
 * If half of all possible matches are found, a score of 0.5 is returned.
 */
public abstract class Factor implements ComparisonFactor {

    protected String name;
    protected List<String> matches;
    protected double score;
    protected String description;

    /**
     * A method to find all matches between two lists of strings, with each list of strings
     * representing some set of data culled from a submission according to a specific comparison sub-strategy.
     *
     * @param list1 the list of data from the first submission to be used for comparison
     * @param list2 the list of data from the second submission to be used for comparison
     * @return a list of strings representing the matches found between the two lists
     */
    protected List<String> findMatches(List<String> list1, List<String> list2) {
        List<String> shorter;
        List<String> longer;

        // find the shorter list
        if (list1.size() < list2.size()) {
            shorter = list1;
            longer = list2;
        } else {
            shorter = list2;
            longer = list1;
        }

        List<String> matches = new ArrayList<>();

        // find the items from the shorter list that appear in the longer list
        for (String item : shorter) {
            /* TO-DO
            Use of Binary Search can be faster since we have already sorted the getIdentifierNames in the Visitor class
             */
            if (longer.contains(item)) {
                matches.add(item);
            }
        }

        return matches;
    }
}