package features.list.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.Constants
import com.example.network.domain.model.ListDrugsItemModel
import features.list.databinding.ListDrugsItemBinding

class ListOfDrugsPagingAdapter(
    private val goToOneItemFragment: (oneDrug: ListDrugsItemModel?) -> Unit
) :
    PagingDataAdapter<ListDrugsItemModel, ListOfDrugsPagingAdapter.ViewHolder>(MovieDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, goToOneItemFragment)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(
        private val binding: ListDrugsItemBinding,
        ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ListDrugsItemModel?, goToOneItemFragment: (oneDrug: ListDrugsItemModel?) -> Unit) {
            if (item != null) {
                Glide
                    .with(binding.iv.context)
                    .load("${Constants().linkToServer}/${item.image}")
                    .into(binding.iv)
                binding.tvNameDrug.text = item.name
                binding.tvDescription.text = item.description
                binding.cvDrug.setOnClickListener {
                    goToOneItemFragment(item)
                }
            }
        }
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListDrugsItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }
    class MovieDiffCallback: DiffUtil.ItemCallback<ListDrugsItemModel>() {
        override fun areItemsTheSame(oldItem: ListDrugsItemModel, newItem: ListDrugsItemModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ListDrugsItemModel, newItem: ListDrugsItemModel): Boolean {
            return newItem.equals(oldItem)
        }
    }
}