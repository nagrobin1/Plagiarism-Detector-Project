package com.msd.team33.models.comparison;

import com.msd.team33.models.response.ComparisonDetails;

/**
 * An interface to represent the functions needed to create the details of a factored project-to-project comparison,
 * where a series of factors are combine to form a final score.
 */
public interface ComparisonInterface {
    /**
     * A method to get a detailed description of the similarities found in the source code for two projects.
     *
     * @return a detailed text description of the similarities found in the source code for two projects.
     */
    public ComparisonDetails getDetails();
}
