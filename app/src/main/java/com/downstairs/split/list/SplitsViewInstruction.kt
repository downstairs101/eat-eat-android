package com.downstairs.split.list

import com.downstairs.R
import com.downstairs.core.tools.Navigation
import com.downstairs.core.tools.ViewInstruction
import com.downstairs.split.data.SplitUiModel
import javax.inject.Inject

class SplitsViewInstruction @Inject constructor() : ViewInstruction() {

    fun navigateToSplitDetails(splitUiModel: SplitUiModel): Navigation {
        return Navigation(R.id.fromSplitsToSplitDetails).apply {
            putArguments("splitID" to splitUiModel.id)
        }
    }
}