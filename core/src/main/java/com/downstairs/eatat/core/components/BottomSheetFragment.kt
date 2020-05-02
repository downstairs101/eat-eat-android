package com.downstairs.eatat.core.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.downstairs.eatat.core.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

open class BottomSheetFragment(@LayoutRes private val layout: Int?) : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layout?.let {
            inflater.inflate(layout, container, false)
        }
    }
}