package com.downstairs.tosplit.user.register

import android.app.Dialog
import android.os.Bundle
import android.view.View.inflate
import androidx.fragment.app.DialogFragment
import com.downstairs.tosplit.R

class UserRegisterFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = inflate(context, R.layout.user_register_fragment, null)
        return Dialog(requireContext(), R.style.AppTheme_DayNight).apply {
            setContentView(view)
        }
    }
}