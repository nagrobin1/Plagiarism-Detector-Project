package com.msd.team33.models.strategy;

import com.github.javaparser.ast.CompilationUnit;
import com.msd.team33.models.visitors.IdentifierVisitor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A class that represents a strategy for getting data from an AST that will be used for AST comparison
 * according to the similarity of the class, interface, method, and variable identifiers contained in each AST.
 */
public class IdentifierStrategy {
    private List<String> visitationResults;

    /**
     * A constructor that creates an internal list of class, interface, method, and variable identifiers
     * contained in the given ASTs.
     *
     * @param asts the ASTs that this IdentifierStrategy will process
     */
    public IdentifierStrategy(List<CompilationUnit> asts) {
        visitationResults = createVisitationResults(asts);
    }

    /**
     * A helper method that uses an IdentifierVisitor to get the relevant identifiers from each of the given ASTs
     * and adds these to a master list of identifiers.
     *
     * @param asts the ASTs that this IdentifierStrategy will visit
     * @return a list of unique class, interface, method, and variable identifiers contained within the given ASTs
     */
    private List<String> createVisitationResults(List<CompilationUnit> asts) {
        Set<String> identifiers = new HashSet<>();
        IdentifierVisitor visitor = new IdentifierVisitor();

        // for each ast, pass the IdentifierVisitor and accumulating all identifiers found
        for (CompilationUnit ast : asts) {
            ast.accept(visitor, null);
            identifiers.addAll(visitor.getIdentifiers());
        }

        List<String> identifierList = new ArrayList(identifiers);
        identifierList.sort(String::compareTo);
        return identifierList;
    }

    /**
     * A method to get a list of all unique class, interface, method, and variable identifiers
     * contained in the ASTs given at construction.
     *
     * @return a list of unique class, interface, method, and variable identifiers from the ASTs given at construction
     */
    public List<String> getVisitationResults() {
        return this.visitationResults;
    }
}