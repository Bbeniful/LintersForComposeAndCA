package com.bbeniful.annotation_processor.annotation

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
/**
 *
 * Use this annotation for skipping the check for
 * the testTag in composable functions.
 *
 * **/
annotation class SkipSetTagCheck