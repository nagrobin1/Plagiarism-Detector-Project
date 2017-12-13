package com.msd.team33.models.strategy;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.comments.Comment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A class that represents a strategy for getting data from an AST that will be used for AST comparison
 * according to the similarity of the comments contained in each AST.
 */
public class CommentStrategy {
    private List<Comment> preprocessingResults;

    /**
     * A constructor that creates an internal list of unique comments contained in the given ASTs.
     *
     * @param asts the ASTs that this CommentStrategy will process
     */
    public CommentStrategy(List<CompilationUnit> asts) {
        Set<Comment> comments = new HashSet<>();

        // for each ast, get all comments and add them to the accumulating list of comments
        for (CompilationUnit ast : asts) {
            comments.addAll(ast.getComments());
        }

        this.preprocessingResults = new ArrayList(comments);
    }

    /**
     * A method to get a list of all unique comments contained in the ASTs given at construction.
     *
     * @return a list of all unique comments contained in the ASTs given at construction
     */
    public List<Comment> getVisitationResults() {
        return this.preprocessingResults;
    }
}