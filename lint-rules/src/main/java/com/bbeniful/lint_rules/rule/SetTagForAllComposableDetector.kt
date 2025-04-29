package com.bbeniful.lint_rules.rule

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.SourceCodeScanner
import org.jetbrains.uast.UCallExpression
import org.jetbrains.uast.UMethod
import org.jetbrains.uast.visitor.AbstractUastVisitor
import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity

class SetTagForAllComposableDetector  : Detector(), SourceCodeScanner {

    companion object {
        val ISSUE = Issue.create(
            id = "MissingTestTagOnComposable",
            briefDescription = "Missing `.testTag()` on Composable Modifier",
            explanation = """
                Each Composable UI element should apply a `.testTag()` on its Modifier for testability and accessibility.
                If intentionally skipped, use @SkipSetTagCheck.
            """.trimIndent(),
            category = Category.CORRECTNESS,
            priority = 7,
            severity = Severity.WARNING,
            implementation = Implementation(
                SetTagForAllComposableDetector::class.java,
                Scope.JAVA_FILE_SCOPE
            )
        )

        // List of composables we want to enforce
        private val composableElements = setOf(
            "Box", "Row", "Column", "Text", "Button", "Icon", "Image", "Card", "Surface"
        )
    }

    override fun getApplicableUastTypes() = listOf(UMethod::class.java)

    override fun createUastHandler(context: JavaContext) = object : UElementHandler() {

        override fun visitMethod(node: UMethod) {
            if (!node.hasAnnotation("androidx.compose.runtime.Composable")) return
            if (node.hasAnnotation("com.bbeniful.annotation_processor.annotation.SkipSetTagCheck")) return

            val body = node.uastBody ?: return


            body.accept(object : AbstractUastVisitor() {
                override fun visitCallExpression(node: UCallExpression): Boolean {
                    val methodName = node.methodName ?: return super.visitCallExpression(node)

                    if (methodName in composableElements) {
                        val modifierArgument = node.valueArguments.firstOrNull { arg ->
                            arg.asSourceString().contains("Modifier")
                        }

                        if (modifierArgument == null) {
                            context.report(
                                ISSUE,
                                node,
                                context.getLocation(node),
                                "Composable '$methodName' is missing a Modifier with `.testTag()`."
                            )
                        } else {
                            val modifierSource = modifierArgument.asSourceString()

                            val shouldSkip = modifierSource.contains("skipTestTag()")

                            if (!shouldSkip && !modifierSource.contains("testTag")) {
                                context.report(
                                    ISSUE,
                                    node,
                                    context.getLocation(node),
                                    "Composable '$methodName' is missing `.testTag()` on its Modifier."
                                )
                            }
                        }
                    }
                    return super.visitCallExpression(node)
                }
            })
        }
    }
}