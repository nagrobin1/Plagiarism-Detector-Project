package com.msd.team33.models.strategy;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.stmt.BlockStmt;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that represents a strategy for getting data from an AST that will be used for AST comparison
 * according to the similarity of the statement blocks contained in each AST.
 */
public class StatementStrategy {
    private List<BlockStmt> blockStatements;

    /**
     * A constructor that creates an internal list of block statements contained in the given ASTs.
     *
     * @param asts the ASTs that this StatementStrategy will process
     */
    public StatementStrategy(List<CompilationUnit> asts) {
        blockStatements = new ArrayList<>();

        for (CompilationUnit ast : asts) {
            blockStatements.addAll(ast.findAll(new BlockStmt().getClass()));
        }
    }

    /**
     * A method to get a list of all block statements contained in the ASTs given at construction.
     *
     * @return a list of all block statements contained in the ASTs given at construction
     */
    public List<BlockStmt> getBlockStatements() {
        return this.blockStatements;
    }
}