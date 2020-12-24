package com.downstairs.core.components

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BottomSheetFragment(@LayoutRes private val layout: Int?) : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layout?.let {
            inflater.inflate(layout, container, false)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        onCreateBehavior(dialog.behavior)

        return dialog
    }

    fun show(fragmentManager: FragmentManager) {
        super.show(fragmentManager, getFragmentTag())
    }

    open fun onCreateBehavior(behavior: BottomSheetBehavior<FrameLayout>) {}

    abstract fun getFragmentTag(): String
}