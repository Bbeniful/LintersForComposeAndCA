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
import org.jetbrains.uast.UCallExpression
import org.jetbrains.uast.UMethod
import org.jetbrains.uast.visitor.AbstractUastVisitor

class SetTagInComposableDetector : Detector(), SourceCodeScanner {

    override fun getApplicableUastTypes() = listOf(UMethod::class.java)

    override fun createUastHandler(context: JavaContext) = object : UElementHandler() {

        override fun visitMethod(node: UMethod) {
            if (!node.hasAnnotation("androidx.compose.runtime.Composable")) return

            if (node.hasAnnotation("com.bbeniful.annotation_processor.annotation.SkipSetTagCheck")) return

            val body = node.uastBody ?: return

            var foundTestTag = false
            var shouldSkip = false


            body.accept(object : AbstractUastVisitor() {
                override fun visitCallExpression(node: UCallExpression): Boolean {

                    if (node.methodName == "skipTestTag") {
                        shouldSkip = true
                        return false
                    }

                    if (node.methodName == "testTag") {
                        foundTestTag = true
                        return true
                    }

                    return super.visitCallExpression(node)
                }
            })

            if (shouldSkip) return

            if (!foundTestTag) {
                context.report(
                    issue = ISSUE,
                    scopeClass = node,
                    location = context.getLocation(node),
                    message = "Composable function '${node.name}' is missing a `.testTag()` call. " +
                            "Use Modifier.testTag(...) or mark with @SkipSetTagCheck."
                )
            }
        }
    }

    companion object {
        val ISSUE: Issue = Issue.create(
            id = "MissingSetTagInComposable",
            briefDescription = "Missing .setTag() in Composable",
            explanation = """
                Every Composable function should include a `.setTag()` call for testability and accessibility, 
                unless explicitly skipped with @SkipSetTagCheck.
            """.trimIndent(),
            category = Category.CORRECTNESS,
            priority = 6,
            severity = Severity.WARNING,
            implementation = Implementation(
                SetTagInComposableDetector::class.java,
                Scope.JAVA_FILE_SCOPE
            )
        )
    }
}