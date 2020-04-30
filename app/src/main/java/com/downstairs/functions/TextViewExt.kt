package com.downstairs.functions

import android.widget.TextView

val TextView.value: String
    get() = this.text.toString()