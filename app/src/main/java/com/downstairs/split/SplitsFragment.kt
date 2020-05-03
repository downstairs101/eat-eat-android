package com.downstairs.split

import android.widget.FrameLayout
import com.downstairs.R
import com.downstairs.eatat.core.components.BottomSheetFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior

class SplitsFragment : BottomSheetFragment(R.layout.splits_fragment) {

    override fun onCreateBehavior(behavior: BottomSheetBehavior<FrameLayout>) {
        behavior.isFitToContents = false
    }
}