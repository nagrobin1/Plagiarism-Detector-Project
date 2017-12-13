package com.msd.team33;

import com.msd.team33.controllers.RestControllerTest;
import com.msd.team33.models.SubmissionComparatorTest;
import com.msd.team33.models.comparison.ComparisonTest;
import com.msd.team33.models.comparison.PreprocessedSubmissionTest;
import com.msd.team33.models.comparison.SubmissionTest;
import com.msd.team33.models.factors.CommentFactorTest;
import com.msd.team33.models.factors.IdentifierFactorTest;
import com.msd.team33.models.factors.LiteralFactorTest;
import com.msd.team33.models.factors.StatementFactorTest;
import com.msd.team33.models.response.ComparisonDetailsTest;
import com.msd.team33.models.strategy.CommentStrategyTest;
import com.msd.team33.models.strategy.IdentifierStrategyTest;
import com.msd.team33.models.strategy.LiteralStrategyTest;
import com.msd.team33.models.strategy.StatementStrategyTest;
import com.msd.team33.models.visitors.IdentifierVisitorTest;
import com.msd.team33.models.visitors.LiteralExpressionVisitorTest;
import com.msd.team33.sourcereader.ArchiveFileExtractorTest;
import com.msd.team33.sourcereader.CompilationUnitProviderTest;
import com.msd.team33.sourcereader.DirExplorerTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * A test suite for all test classes within the models package; when this class is run,
 * all tests within the listed classes are run.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        RestControllerTest.class,
        ComparisonTest.class,
        PreprocessedSubmissionTest.class,
        SubmissionTest.class,
        CommentFactorTest.class,
        IdentifierFactorTest.class,
        LiteralFactorTest.class,
        StatementFactorTest.class,
        ComparisonDetailsTest.class,
        CommentStrategyTest.class,
        IdentifierStrategyTest.class,
        LiteralStrategyTest.class,
        StatementStrategyTest.class,
        IdentifierVisitorTest.class,
        LiteralExpressionVisitorTest.class,
        SubmissionComparatorTest.class,
        ArchiveFileExtractorTest.class,
        CompilationUnitProviderTest.class,
        DirExplorerTest.class})
public class BackendTestSuite { }