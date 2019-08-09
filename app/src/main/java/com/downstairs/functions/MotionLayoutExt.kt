package com.downstairs.functions

import androidx.constraintlayout.motion.widget.MotionLayout

fun MotionLayout.setTransitionListener(
    onTrigger: () -> Unit = {},
    onStarted: () -> Unit = {},
    onChange: () -> Unit = {},
    onComplete: () -> Unit = {}
) {
    this.setTransitionListener(object : MotionLayout.TransitionListener {
        override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
            onTrigger()
        }

        override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
            onStarted()
        }

        override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
            onChange()
        }

        override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
            onComplete()
        }

    })
}