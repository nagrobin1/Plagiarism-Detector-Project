package com.msd.team33.models.visitors;

import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.HashSet;
import java.util.Set;

/**
 * A class to represent a visitor that visits each node in accepting node
 * and accumulates all literal expressions contained therein:
 * BooleanLiteralExpr, CharLiteralExpr, DoubleLiteralExpr,
 * IntegerLiteralExpr, LongLiteralExpr, NullLiteralExpr, StringLiteralExpr.
 */
public class LiteralExpressionVisitor<A> extends VoidVisitorAdapter<A> {
    Set<LiteralExpr> literalExpressions;

    /**
     * A constructor to create a new LiteralExpressionVisitor object, initializing its list of literal expressions.
     */
    public LiteralExpressionVisitor() {
        this.literalExpressions = new HashSet<>();
    }

    /**
     * A method to get all the literal expressions from the visited abstract syntax tree.
     *
     * @return a list of all literal expressions contained within the visited abstract syntax tree
     */
    public Set<LiteralExpr> getLiteralExpressions() {
        return this.literalExpressions;
    }

    /**
     * A method that visits the given BooleanLiteralExpr node, adding it to the list of literal expressions
     * and then visiting its children recursively.
     *
     * @param n the BooleanLiteralExpr node being visited
     * @param arg a placeholder argument
     */
    @Override
    public void visit(BooleanLiteralExpr n, A arg) {
        this.literalExpressions.add(n);
        super.visit(n, arg);
    }

    /**
     * A method that visits the given CharLiteralExpr node, adding it to the list of literal expressions
     * and then visiting its children recursively.
     *
     * @param n the CharLiteralExpr node being visited
     * @param arg a placeholder argument
     */
    @Override
    public void visit(CharLiteralExpr n, A arg) {
        this.literalExpressions.add(n);
        super.visit(n, arg);
    }

    /**
     * A method that visits the given DoubleLiteralExpr node, adding it to the list of literal expressions
     * and then visiting its children recursively.
     *
     * @param n the DoubleLiteralExpr node being visited
     * @param arg a placeholder argument
     */
    @Override
    public void visit(DoubleLiteralExpr n, A arg) {
        this.literalExpressions.add(n);
        super.visit(n, arg);
    }

    /**
     * A method that visits the given IntegerLiteralExpr node, adding it to the list of literal expressions
     * and then visiting its children recursively.
     *
     * @param n the IntegerLiteralExpr node being visited
     * @param arg a placeholder argument
     */
    @Override
    public void visit(IntegerLiteralExpr n, A arg) {
        this.literalExpressions.add(n);
        super.visit(n, arg);
    }

    /**
     * A method that visits the given LongLiteralExpr node, adding it to the list of literal expressions
     * and then visiting its children recursively.
     *
     * @param n the LongLiteralExpr node being visited
     * @param arg a placeholder argument
     */
    @Override
    public void visit(LongLiteralExpr n, A arg) {
        this.literalExpressions.add(n);
        super.visit(n, arg);
    }

    /**
     * A method that visits the given NullLiteralExpr node, adding it to the list of literal expressions
     * and then visiting its children recursively.
     *
     * @param n the NullLiteralExpr node being visited
     * @param arg a placeholder argument
     */
    @Override
    public void visit(NullLiteralExpr n, A arg) {
        this.literalExpressions.add(n);
        super.visit(n, arg);
    }

    /**
     * A method that visits the given StringLiteralExpr node, adding it to the list of literal expressions
     * and then visiting its children recursively.
     *
     * @param n the StringLiteralExpr node being visited
     * @param arg a placeholder argument
     */
    @Override
    public void visit(StringLiteralExpr n, A arg) {
        this.literalExpressions.add(n);
        super.visit(n, arg);
    }
}