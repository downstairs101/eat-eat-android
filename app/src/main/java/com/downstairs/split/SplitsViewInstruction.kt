package com.downstairs.split

import com.downstairs.R
import com.downstairs.eatat.core.tools.Navigation
import com.downstairs.eatat.core.tools.ViewInstruction
import javax.inject.Inject

class SplitsViewInstruction @Inject constructor() : ViewInstruction() {
    fun navigateToSplitDetails() = Navigation(R.id.payerNameText)
}