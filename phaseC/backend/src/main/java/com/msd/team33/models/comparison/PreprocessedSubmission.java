package com.msd.team33.models.comparison;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.expr.LiteralExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.msd.team33.models.strategy.CommentStrategy;
import com.msd.team33.models.strategy.IdentifierStrategy;
import com.msd.team33.models.strategy.LiteralStrategy;
import com.msd.team33.models.strategy.StatementStrategy;
import com.msd.team33.sourcereader.CompilationUnitProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * A class to represent a programming assignment submission that has been preprocessed in preparation for comparison
 * with other submissions for the detection of likely plagiarism.
 */
public class PreprocessedSubmission {

    private Submission submission;
    private String sourceText;
    private List<CompilationUnit> sourceASTs;

    // various strategies for comparison, each perform a particular preprocessing function and store the results
    private IdentifierStrategy identifierStrategy;
    private CommentStrategy commentStrategy;
    private LiteralStrategy literalStrategy;
    private StatementStrategy statementStrategy;

    /**
     * A constructor that takes a Submission and creates a PreprocessedSubmission
     * by creating an abstract syntax tree from its path,
     * creating a String that represents the concatenation of of all .java files within its path,
     * and executing each pre-processing strategy.
     *
     * @param submission the Submission containing the path from which the PreprocessedSubmission will be built
     * @throws IOException if a problem is encountered reading the .java files
     */
    public PreprocessedSubmission(Submission submission) throws IOException {
        this.submission = submission;

        this.sourceText = getSourceText(this.submission.getPath());

        CompilationUnitProvider cup = new CompilationUnitProvider();
        this.sourceASTs = cup.getCompilationUnits(this.submission.getFile());

        this.identifierStrategy = new IdentifierStrategy(sourceASTs);
        this.commentStrategy = new CommentStrategy(sourceASTs);
        this.literalStrategy = new LiteralStrategy(sourceASTs);
        this.statementStrategy = new StatementStrategy(sourceASTs);
    }

    /**
     * A helper method that creates and returns a String that represents the concatenation of the text
     * from all .java files contained within the directory (and subdirectories) represented by the given Path.
     *
     * @param path the top-level directory that contains all .java files for a particular submission
     * @return a String representing the concatenation of the text from all .java files contained within the given path
     * @throws IOException if a problem is encountered reading the .java files
     */
    private String getSourceText(Path path) throws IOException {
        StringBuilder text = new StringBuilder();
        File file = path.toFile();

        // if the current file is a file (and not a directory)
        if (!file.isDirectory()) {
            // if the current file is a Java file
            if (isJavaFile(file)) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String nextLine = "";

                    // append the text contained within this file to the string builder
                    while ((nextLine = reader.readLine()) != null) {
                        text.append(nextLine);
                        text.append("\n");
                    }
                }
            }
        } else { // if the current file is a directory...
            File[] files = file.listFiles();

            // ...recurse for each file contained within this current directory
            for (File f : files) {
                text.append(getSourceText(f.toPath()));
            }
        }

        // return the accumulated text, including the text from all recursions
        return text.toString();
    }

    /**
     * A helper method to determine if the given file is a .java source code file.
     *
     * @param file the file being checked to see if it's a .java source code file
     * @return true if the given file ends with the appropriate .java extension, false otherwise
     */
    private boolean isJavaFile(File file) {
        String JAVA_EXTENSION = ".java";
        String name = file.toString();
        int expectedDotIndex = name.length() - JAVA_EXTENSION.length();

        if (expectedDotIndex < 0) {
            return false;
        } else {
            String ext = name.substring(expectedDotIndex);
            return ext.equals(JAVA_EXTENSION);
        }
    }

    public Submission getSubmission() {
        return this.submission;
    }

    /**
     * A method to get the source text from this (preprocessed) submission.
     *
     * @return the source text of this (preprocessed) submission
     */
    public String getSourceText() {
        return this.sourceText;
    }

    /**
     * A method to get a list of compilation units from this (preprocessed) submission.
     * A CompilationUnit is created for each .java file in the submission.
     * A CompilationUnit is a type of node that represents the code in a given .java as an abstract syntax tree.
     *
     * @return a list of ASTs that have been generated from the current submission
     */
    public List<CompilationUnit> getASTs() {
        return this.sourceASTs;
    }

    /**
     * A method to get a list of all unique identifier names found in the current submission.
     *
     * @return a list of unique identifier names from the current submission
     */
    public List<String> getIdentifierNames() {
        return this.identifierStrategy.getVisitationResults();
    }

    /**
     * A method to get a list of all comments found in the current submission.
     *
     * @return a list of comments from the current submission
     */
    public List<Comment> getComments() {
        return this.commentStrategy.getVisitationResults();
    }

    /**
     * A method to get a list of all literal expressions found in the current submission.
     *
     * @return a list of literal expressions from the current submission
     */
    public List<LiteralExpr> getLiterals() {
        return this.literalStrategy.getVisitationResults();
    }

    /**
     * A method to get a list of all blocks of statements found in the current submission.
     *
     * @return a list statement blocks from the current submission
     */
    public List<BlockStmt> getBlockStatements() {
        return this.statementStrategy.getBlockStatements();
    }
}