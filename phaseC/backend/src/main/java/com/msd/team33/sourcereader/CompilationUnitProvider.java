package com.msd.team33.sourcereader;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to fetch CompilationUnit of a submission.
 * A CompilationUnit is an object from the JavaParser class that represents an AST
 */
public class CompilationUnitProvider {

    /**
     * Generates CompilationUnits for all .java files in a package.
     * Takes in source directory root for source code and an empty list of CompilationUnits.
     *
     * @param projectDir
     * @param compilationUnitList
     */
    private static void getAllCompilationUnits(File projectDir, List<CompilationUnit> compilationUnitList) {
        new DirExplorer((level, path, file) -> path.endsWith(".java"), (level, path, file) -> {
            try {
                CompilationUnit compilationUnit = JavaParser.parse(file);
                compilationUnitList.add(compilationUnit);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }).explore(projectDir);
    }

    /**
     * Returns a list of CompilationUnits.
     * Takes in source directory root for source code.
     *
     * @param projectDir
     * @return
     */
    public List<CompilationUnit> getCompilationUnits(File projectDir) {
        List<CompilationUnit> compilationUnitList = new ArrayList<>();
        getAllCompilationUnits(projectDir, compilationUnitList);
        return compilationUnitList;
    }
}