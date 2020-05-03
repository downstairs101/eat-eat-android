package com.downstairs.split

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.downstairs.R
import kotlinx.android.synthetic.main.spit_list_item.view.*
import kotlinx.android.synthetic.main.splits_fragment.*

class SplitsFragment : Fragment(R.layout.splits_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupSplitsView()
    }

    private fun setupSplitsView() {
        splitsRecyclerView.adapter = SplitsAdapter()
    }
}

class SplitsAdapter : ListAdapter<SplitUiModel, SplitsAdapter.SplitsViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SplitsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.spit_list_item, parent, false)
        return SplitsViewHolder(view)
    }

    override fun onBindViewHolder(holder: SplitsViewHolder, position: Int) {
        holder.bindItem(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SplitUiModel>() {
            override fun areItemsTheSame(oldItem: SplitUiModel, newItem: SplitUiModel): Boolean {
                return newItem === oldItem
            }

            override fun areContentsTheSame(oldItem: SplitUiModel, newItem: SplitUiModel): Boolean {
                return newItem.equals(oldItem)
            }
        }
    }

    inner class SplitsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(split: SplitUiModel) {
            itemView.payerNameText.text = split.payerName
            itemView.payedValueText.text = split.value
        }
    }
}
