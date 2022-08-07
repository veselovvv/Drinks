package com.veselovvv.drinks.presentation.subcategories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.veselovvv.drinks.core.Retry
import com.veselovvv.drinks.databinding.FailFullscreenBinding
import com.veselovvv.drinks.databinding.ProgressFullscreenBinding
import com.veselovvv.drinks.databinding.SubcategoryLayoutBinding

class SubcategoriesAdapter(
    private val retry: Retry,
    private val subcategoryListener: SubcategoryListener
) : RecyclerView.Adapter<SubcategoriesAdapter.SubcategoriesViewHolder>() {
    private val subcategories = ArrayList<SubcategoryUi>()

    fun update(newList: List<SubcategoryUi>) {
        subcategories.clear()
        subcategories.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int) = when (subcategories[position]) {
        is SubcategoryUi.Base -> 0
        is SubcategoryUi.Fail -> 1
        is SubcategoryUi.Progress -> 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        0 -> SubcategoriesViewHolder.Base(
            SubcategoryLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            subcategoryListener
        )
        1 -> SubcategoriesViewHolder.Fail(
            FailFullscreenBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            retry
        )
        else -> SubcategoriesViewHolder.FullscreenProgress(
            ProgressFullscreenBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: SubcategoriesViewHolder, position: Int) =
        holder.bind(subcategories[position])

    override fun getItemCount() = subcategories.size

    abstract class SubcategoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        open fun bind(subcategory: SubcategoryUi) = Unit

        class FullscreenProgress(binding: ProgressFullscreenBinding) : SubcategoriesViewHolder(binding.root)

        class Base(
            private val binding: SubcategoryLayoutBinding,
            private val subcategoryListener: SubcategoryListener
        ) : SubcategoriesViewHolder(binding.root) {
            override fun bind(subcategory: SubcategoryUi) {
                subcategory.map(object : SubcategoryUi.BaseMapper {
                    override fun map(data: String) {
                        binding.subcategoryTextView.text = data
                    }
                })

                itemView.setOnClickListener {
                    subcategory.open(subcategoryListener)
                }
            }
        }

        class Fail(
            private val binding: FailFullscreenBinding,
            private val retry: Retry
        ) : SubcategoriesViewHolder(binding.root) {
            override fun bind(subcategory: SubcategoryUi) {
                subcategory.map(object : SubcategoryUi.BaseMapper {
                    override fun map(data: String) {
                        binding.failMessageTextView.text = data
                    }
                })
                binding.failTryAgainButton.setOnClickListener {
                    retry.tryAgain()
                }
            }
        }
    }

    interface SubcategoryListener {
        fun showSubcategory(subcategory: String)
    }
}