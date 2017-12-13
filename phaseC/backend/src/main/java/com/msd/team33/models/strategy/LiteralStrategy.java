package com.msd.team33.models.strategy;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.LiteralExpr;
import com.msd.team33.models.visitors.LiteralExpressionVisitor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A class that represents a strategy for getting data from an AST that will be used for AST comparison
 * according to the similarity of the literal expressions contained in each AST.
 */
public class LiteralStrategy {
    private List<LiteralExpr> visitationResults;

    /**
     * A constructor that creates an internal list of literal expressions contained in the given ASTs.
     *
     * @param asts the ASTs that this LiteralStrategy will process
     */
    public LiteralStrategy(List<CompilationUnit> asts) {
        visitationResults = createVisitationResults(asts);
    }

    /**
     * A helper method that uses an LiteralExpressionVisitor to get the relevant literal expressions
     * from each of the given ASTs and adds these to a master list of literal expressions.
     *
     * @param asts the ASTs that this LiteralExpressionStrategy will visit
     * @return a list of unique literal expressions contained within the given ASTs
     */
    private List<LiteralExpr> createVisitationResults(List<CompilationUnit> asts) {
        Set<LiteralExpr> literals = new HashSet<>();
        LiteralExpressionVisitor visitor = new LiteralExpressionVisitor();

        // for each ast, pass the LiteralExpressionsVisitor and accumulating all literal expressions found
        for (CompilationUnit ast : asts) {
            ast.accept(visitor, null);
            literals.addAll(visitor.getLiteralExpressions());
        }

        return new ArrayList(literals);
    }

    /**
     * A method to get a list of all unique literal expressions contained in the ASTs given at construction.
     *
     * @return a list of unique literal expressions from the ASTs given at construction
     */
    public List<LiteralExpr> getVisitationResults() {
        return this.visitationResults;
    }
}