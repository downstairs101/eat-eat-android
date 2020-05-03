package com.downstairs.home

import android.database.sqlite.SQLiteStatement
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.downstairs.R
import com.downstairs.split.SplitsFragment

class HomeFragment : Fragment(R.layout.home_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        SplitsFragment().show(childFragmentManager)
    }
}