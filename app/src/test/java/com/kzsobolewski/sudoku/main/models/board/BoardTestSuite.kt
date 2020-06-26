package com.kzsobolewski.sudoku.main.models.board

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    LineCheckTest::class,
    VerticalCheckTest::class,
    HorizontalCheckTest::class,
    BoxCheckTest::class,
    Validate9x9Test::class
)
class BoardTestSuite