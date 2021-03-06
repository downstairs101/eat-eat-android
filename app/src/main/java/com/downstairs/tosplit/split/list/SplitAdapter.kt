package com.downstairs.tosplit.split.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.downstairs.tosplit.R
import com.downstairs.tosplit.split.data.SplitUiModel
import kotlinx.android.synthetic.main.split_list_item.view.*

class SplitAdapter : ListAdapter<SplitUiModel, SplitAdapter.SplitsViewHolder>(DIFF_CALLBACK) {

    private var itemClickListener: (SplitUiModel) -> Unit = {}

    fun setOItemClickListener(itemClickListener: (SplitUiModel) -> Unit) {
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SplitsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.split_list_item, parent, false)
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
                return newItem.value == oldItem.value
                        && newItem.payerName == oldItem.payerName
            }
        }
    }

    inner class SplitsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                itemClickListener(getItem(adapterPosition))
            }
        }

        fun bindItem(split: SplitUiModel) {
            itemView.payerNameText.text = split.payerName
            itemView.payedValueText.text = split.value
        }
    }
}