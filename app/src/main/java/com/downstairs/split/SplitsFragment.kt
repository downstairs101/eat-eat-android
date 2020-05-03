package com.downstairs.split

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.downstairs.R
import com.downstairs.eatat.core.extensions.getCoreComponent
import com.downstairs.injection.DaggerAppComponent
import kotlinx.android.synthetic.main.spit_list_item.view.*
import kotlinx.android.synthetic.main.splits_fragment.*
import javax.inject.Inject

class SplitsFragment : Fragment(R.layout.splits_fragment) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(SplitsViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerAppComponent.factory().create(context.getCoreComponent()).inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupSplitsView()
        setupObservers()
    }

    private fun setupSplitsView() {
        splitsRecyclerView.adapter = SplitsAdapter()
        splitsRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun setupObservers() {
        viewModel.splits.observe(viewLifecycleOwner, Observer {
            getSplitsAdapter()?.submitList(it)
        })
    }

    private fun getSplitsAdapter(): SplitsAdapter? = splitsRecyclerView.adapter as? SplitsAdapter
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
