package com.downstairs.tosplit.split.list

import com.downstairs.core.tools.Direction
import com.downstairs.core.tools.ViewInstruction
import com.downstairs.tosplit.R
import com.downstairs.tosplit.split.data.SplitUiModel
import javax.inject.Inject

class SplitsViewInstruction @Inject constructor() : ViewInstruction() {

    fun navigateToSplitDetails(splitUiModel: SplitUiModel): Direction {
        return Direction(R.id.fromSplitsToSplitDetails).apply {
            putArguments("splitID" to splitUiModel.id)
        }
    }
}