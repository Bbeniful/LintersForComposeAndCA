package com.bbeniful.lint_rules.registry

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.Issue
import com.bbeniful.lint_rules.rule.CleanArchitectureLayerDetector
import com.bbeniful.lint_rules.rule.SetTagForAllComposableDetector
import com.bbeniful.lint_rules.rule.SetTagInComposableDetector
import com.bbeniful.lint_rules.rule.VariableNameDetector

class CustomLintRegistry : IssueRegistry() {
    override val issues: List<Issue> = listOf(
        //SetTagInComposableDetector.ISSUE,
        SetTagForAllComposableDetector.ISSUE,
        VariableNameDetector.ISSUE,
        CleanArchitectureLayerDetector.ISSUE
    )

    override val api: Int = com.android.tools.lint.detector.api.CURRENT_API
}