package com.msd.team33.models.visitors;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A class to represent a visitor that visits each node in the accepting node
 * and accumulates all identifiers (class names, interface names, method names, and variable names) contained therein.
 */
public class IdentifierVisitor<A> extends VoidVisitorAdapter<A> {
    Set<String> identifiers;

    /**
     * A constructor to create a new IdentifierVisitor with an initialized identifiers HashSet.
     */
    public IdentifierVisitor() {
        identifiers = new HashSet();
    }

    /**
     * A method to get all class, interface, method, and variable declaration identifiers found during a visitation.
     *
     * @return a list of identifier strings found during a visitation
     */
    public List<String> getIdentifiers() {
        List<String> identifierList = new ArrayList(identifiers);
        return identifierList;
    }

    /**
     * A method that visits a ClassOrInterfaceDeclaration node, adding its identifier to the list of identifiers
     * and then visiting its children recursively.
     *
     * @param n the ClassOrInterfaceDeclaration node being visited
     * @param arg a placeholder argument
     */
    @Override
    public void visit(ClassOrInterfaceDeclaration n, A arg) {
        this.identifiers.add(n.getNameAsString());
        super.visit(n, arg);
    }

    /**
     * A method that visits a MethodDeclaration node, adding its identifier to the list of identifiers
     * and then visiting its children recursively.
     *
     * @param n the ClassOrInterfaceDeclaration node being visited
     * @param arg a placeholder argument
     */
    public void visit(MethodDeclaration n, A arg) {
        this.identifiers.add(n.getNameAsString());
        super.visit(n, arg);
    }

    /**
     * A method that visits a VariableDeclaration node, adding its identifier to the list of identifiers
     * and then visiting its children recursively.
     *
     * @param n the ClassOrInterfaceDeclaration node being visited
     * @param arg a placeholder argument
     */
    public void visit(VariableDeclarator n, A arg) {
        this.identifiers.add(n.getNameAsString());
        super.visit(n, arg);
    }
}
