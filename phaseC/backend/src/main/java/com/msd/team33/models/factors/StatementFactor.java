package com.msd.team33.models.factors;

import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;

import java.util.ArrayList;
import java.util.List;

/**
 * A concrete class to represent the outcome of comparing blocks of statements in two source code submissions,
 * where the score represents the matching percentage between the two submissions being compared.
 * Statement blocks are compared by determining the number of each type of statement contained within each block.
 * If the counts for each statement type are the same for the two blocks being compared,
 * these two blocks are considered a match.
 * If no matching statement blocks are found, a score of 0.0 is returned.
 * If all statement blocks from one submission match statement blocks from the other submission,
 * a score of 1.0 is returned.
 * If half of the statement blocks from one submission match statement blocks from the other submission,
 * a score of 0.5 is returned.
 */
public class StatementFactor extends Factor {

    /**
     * A constructor to create a StatementFactor from the given lists of BlockStmt.
     *
     * @param blockStmts1 a list of BlockStmt from submission 1
     * @param blockStmts2 a list of BlockStmt from submission 2
     */
    public StatementFactor(List<BlockStmt> blockStmts1, List<BlockStmt> blockStmts2) {
        this.name = "Statement";

        List<BlockStmt> shorter;
        List<BlockStmt> longer;

        // determine which list is shorter
        if (blockStmts1.size() < blockStmts2.size()) {
            shorter = blockStmts1;
            longer = blockStmts2;
        } else {
            shorter = blockStmts2;
            longer = blockStmts1;
        }

        List<List<Statement>> blocksAsListsShort = new ArrayList<>();
        List<List<Statement>> blocksAsListsLong = new ArrayList<>();

        addBlockStatementsToList(shorter, blocksAsListsShort);
        addBlockStatementsToList(longer, blocksAsListsLong);

        List<StatementCounts> statementCountsShort = new ArrayList<>();
        List<StatementCounts> statementCountsLong = new ArrayList<>();

        getStatementCounts(blocksAsListsShort, statementCountsShort);
        getStatementCounts(blocksAsListsLong, statementCountsLong);

        int totalSize = 0;
        int matchingSize = 0;

        this.matches = new ArrayList<>();

        // compare each statement type count in the shorter submission...
        for (StatementCounts scShort : statementCountsShort) {
            // accumulate the total number of statements from the shorter submission
            totalSize += scShort.getSize();

            // ...to each statement type count in the longer submission.
            for (StatementCounts scLong : statementCountsLong) {

                // if a match is found...
                if (scShort.equals(scLong)) {
                    // accumulate the total number of statements from matching blocks
                    matchingSize += scShort.getSize();

                    // create a string representation of this match
                    StringBuilder sb = new StringBuilder();
                    sb.append("The following block...\n");

                    generateDetailsStringBuilder(sb, scShort);

                    sb.append("\n...matches the following block...\n");

                    generateDetailsStringBuilder(sb, scLong);

                    sb.append("\n");

                    // add this string representation to the list of matches found
                    this.matches.add(sb.toString());
                    break;
                }
            }
        }

        // calculate the factor score
        if (totalSize == 0) {
            this.score = -1.0;
        } else {
            this.score = (double) matchingSize / (double) totalSize;
        }

        // build the description string
        this.description = String.format("Statement Matching Score: %4f (%d matches found)", score, matches.size());
    }

    /**
     * Given a StatementCounts object, get the details of each statement, such as class name, and line numbers,
     * as a StringBuilder
     * @param sb a StringBuilder that will contain the details of each statement from the StatementCounts object
     * @param statementCounts a StatementCounts object that will be used for detail extraction
     */
    private void generateDetailsStringBuilder(StringBuilder sb, StatementCounts statementCounts) {

        for (Statement s : statementCounts.getStatements()) {
            sb.append("   ");
            sb.append(s.getClass().toString());
            sb.append(" from lines ");
            sb.append(s.getRange().toString());
            sb.append("\n");
        }
    }

    /**
     * Add each list of statement to the  a list of StatementCounts
     * @param blocksAsLists A list of list of Statement
     * @param statementCounts A list of StatementCounts
     */
    private void getStatementCounts(List<List<Statement>> blocksAsLists, List<StatementCounts> statementCounts) {

        for (List<Statement> statements : blocksAsLists) {
            statementCounts.add(new StatementCounts(statements));
        }
    }

    /**
     * Put each BlockStmt into a list of Statement
     * @param blockStmts A list of of BlockStmt
     * @param listOfStatements A list of list of Statement
     */
    private void addBlockStatementsToList(List<BlockStmt> blockStmts, List<List<Statement>> listOfStatements) {

        for (BlockStmt bs: blockStmts) {
            listOfStatements.add(bs.getStatements());
        }
    }


    /**
     * A helper class that counts the number of each type of Statement from a given list of Statement
     * and compares two counts for equality.
     */
    private class StatementCounts {
        private List<Statement> statements;

        // a count for each type of Statement
        private int assertStmts;
        private int blockStmts;
        private int breakStmts;
        private int continueStmts;
        private int doStmts;
        private int emptyStmts;
        private int explicitConstructorInvocationStmts;
        private int expressionStmts;
        private int foreachStmts;
        private int forStmts;
        private int ifStmts;
        private int labeledStmts;
        private int localClassDeclarationStmts;
        private int returnStmts;
        private int switchEntryStmts;
        private int switchStmts;
        private int synchronizedStmts;
        private int throwStmts;
        private int tryStmts;
        private int unparsableStmts;
        private int whileStmts;

        private int size;

        /**
         * A constructor to create a new StatementCounts object from the given list of statements.
         *
         * @param statements the list of statements to count
         */
        public StatementCounts(List<Statement> statements) {
            this.statements = statements;

            size = statements.size();

            assertStmts = 0;
            blockStmts = 0;
            breakStmts = 0;
            continueStmts = 0;
            doStmts = 0;
            emptyStmts = 0;
            explicitConstructorInvocationStmts = 0;
            expressionStmts = 0;
            foreachStmts = 0;
            forStmts = 0;
            ifStmts = 0;
            labeledStmts = 0;
            localClassDeclarationStmts = 0;
            returnStmts = 0;
            switchEntryStmts = 0;
            switchStmts = 0;
            synchronizedStmts = 0;
            throwStmts = 0;
            tryStmts = 0;
            unparsableStmts = 0;
            whileStmts = 0;

            // increment the specific statement type count for each statement in the given list of statements
            for (Statement s : statements) {
                if (s.isAssertStmt()) {
                    assertStmts++;
                } else if (s.isBlockStmt()) {
                    blockStmts++;
                } else if (s.isBreakStmt()) {
                    breakStmts++;
                } else if (s.isContinueStmt()) {
                    continueStmts++;
                } else if (s.isDoStmt()) {
                    doStmts++;
                } else if (s.isEmptyStmt()) {
                    emptyStmts++;
                } else if (s.isExplicitConstructorInvocationStmt()) {
                    explicitConstructorInvocationStmts++;
                } else if (s.isExpressionStmt()) {
                    expressionStmts++;
                } else if (s.isForeachStmt()) {
                    foreachStmts++;
                } else if (s.isForStmt()) {
                    forStmts++;
                } else if (s.isIfStmt()) {
                    ifStmts++;
                } else if (s.isLabeledStmt()) {
                    labeledStmts++;
                } else if (s.isLocalClassDeclarationStmt()) {
                    localClassDeclarationStmts++;
                } else if (s.isReturnStmt()) {
                    returnStmts++;
                } else if (s.isSwitchEntryStmt()) {
                    switchEntryStmts++;
                } else if (s.isSwitchStmt()) {
                    switchStmts++;
                } else if (s.isSynchronizedStmt()) {
                    synchronizedStmts++;
                } else if (s.isThrowStmt()) {
                    throwStmts++;
                } else if (s.isTryStmt()) {
                    tryStmts++;
                } else if (s.isUnparsableStmt()) {
                    unparsableStmts++;
                } else {
                    whileStmts++;
                }
            }


        }

        /**
         * A method that gets the total number of statements counted.
         *
         * @return the total number of statements counted
         */
        public int getSize() {
            return this.size;
        }

        /**
         * A method that gets a list of the statements that have been counted.
         *
         * @return the list of statements that have been counted
         */
        public List<Statement> getStatements() {
            return this.statements;
        }

        /**
         * A method to determine if this StatementCount is equal to the given StatementCount.
         *
         * @param sc2 the StatementCount to be compared with this StatementCount
         * @return true if the count for each statement type is equal for each StatementCount, false otherwise
         */
        public boolean equals(StatementCounts sc2) {
            if (this.assertStmts == sc2.assertStmts
                    && this.blockStmts == sc2.blockStmts
                    && this.breakStmts == sc2.breakStmts
                    && this.continueStmts == sc2.continueStmts
                    && this.doStmts == sc2.doStmts
                    && this.emptyStmts == sc2.emptyStmts
                    && this.explicitConstructorInvocationStmts == sc2.explicitConstructorInvocationStmts
                    && this.expressionStmts == sc2.expressionStmts
                    && this.foreachStmts == sc2.foreachStmts
                    && this.forStmts == sc2.forStmts
                    && this.ifStmts == sc2.ifStmts
                    && this.labeledStmts == sc2.labeledStmts
                    && this.localClassDeclarationStmts == sc2.localClassDeclarationStmts
                    && this.returnStmts == sc2.returnStmts
                    && this.switchEntryStmts == sc2.switchEntryStmts
                    && this.switchStmts == sc2.switchStmts
                    && this.synchronizedStmts == sc2.synchronizedStmts
                    && this.throwStmts == sc2.throwStmts
                    && this.tryStmts == sc2.tryStmts
                    && this.unparsableStmts == sc2.unparsableStmts
                    && this.whileStmts == sc2.whileStmts) {
                return true;
            } else {
                return false;
            }
        }
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
     * of statement blocks from the smaller list that matched those from the larger list,
     * with higher scores representing higher likelihood that the two submissions being compared
     * contain plagiarized code.
     *
     * @return the score for this factor, representing the percentage of matching statement blocks
     */
    @Override
    public double getScore() {
        return this.score;
    }

    /**
     * A method to get a summary description of the matching statement blocks found during comparison.
     *
     * @return a String describing the matching statement blocks found during comparison
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * A method to get the matching statement blocks represented as strings.
     *
     * @return a list of string representations of matching statement blocks
     */
    @Override
    public List<String> getMatches() {
        return this.matches;
    }
}
