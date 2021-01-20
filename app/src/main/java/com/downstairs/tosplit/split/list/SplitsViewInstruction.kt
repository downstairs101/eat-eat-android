package com.downstairs.tosplit.split.list

import com.downstairs.core.tools.instruction.Direction
import com.downstairs.core.tools.instruction.ViewInstruction
import javax.inject.Inject

class SplitsViewInstruction @Inject constructor() : ViewInstruction() {

    fun navigateToSplitDetails(splitId: Long): Direction {
        return Direction(SplitListFragmentDirections.fromSplitsToSplitDetails(splitId))
    }
}