package com.bbeniful.lint_rules.rule

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.android.tools.lint.detector.api.SourceCodeScanner
import org.jetbrains.uast.UImportStatement

class CleanArchitectureLayerDetector : Detector(), SourceCodeScanner {

    override fun getApplicableUastTypes() = listOf(UImportStatement::class.java)

    override fun createUastHandler(context: JavaContext) = object : UElementHandler() {

        override fun visitImportStatement(node: UImportStatement) {
            val importPath = node.importReference?.asRenderString() ?: return
            val filePackage = context.uastFile?.packageName ?: return

            val isPresentationFile = filePackage.contains(".presentation")
            val isDomainFile = filePackage.contains(".domain")
            val isDataFile = filePackage.contains(".data")

            val importingFromData = importPath.contains(".data")
            val importingFromPresentation = importPath.contains(".presentation")

            when {
                isPresentationFile && importingFromData -> {
                    context.report(
                        ISSUE,
                        node,
                        context.getLocation(node),
                        "Presentation layer should not depend on Data layer."
                    )
                }

                isDomainFile && importingFromData -> {
                    context.report(
                        ISSUE,
                        node,
                        context.getLocation(node),
                        "Domain layer should not depend on Data layer."
                    )
                }

                isDomainFile && importingFromPresentation -> {
                    context.report(
                        issue = ISSUE,
                        scope = node,
                        location = context.getLocation(node),
                        message = "Domain layer should not depend on Presentation layer."
                    )
                }

                isDataFile && importingFromPresentation -> {
                    context.report(
                        ISSUE,
                        node,
                        context.getLocation(node),
                        "Data layer should not depend on Presentation layer."
                    )
                }
            }
        }
    }

    companion object {
        val ISSUE = Issue.create(
            id = "CleanArchitectureViolation",
            briefDescription = "Clean Architecture Dependency Violation",
            explanation = """
                Enforces Clean Architecture layer rules:
                - Presentation -> Domain (allowed)
                - Data -> Domain (allowed)
                - Presentation -> Data (forbidden)
                - Domain -> Data or Domain -> Presentation (forbidden)
            """.trimIndent(),
            category = Category.CORRECTNESS,
            priority = 7,
            severity = Severity.ERROR,
            implementation = Implementation(
                CleanArchitectureLayerDetector::class.java,
                Scope.JAVA_FILE_SCOPE
            )
        )
    }
}