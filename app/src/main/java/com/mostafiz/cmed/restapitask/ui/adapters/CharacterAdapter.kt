package com.mostafiz.cmed.restapitask.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mostafiz.cmed.restapitask.databinding.CardCharacterListBinding
import com.mostafiz.cmed.restapitask.model.CharacterModel
import com.mostafiz.cmed.restapitask.utils.ClickInterface
import javax.inject.Inject

class CharacterAdapter @Inject constructor() :
    RecyclerView.Adapter<CharacterAdapter.EmployeeViewHolder>() {

    private var clickInterface: ClickInterface<CharacterModel>? = null
    var modelList = mutableListOf<CharacterModel>()
    private lateinit var context: Context


    fun updateModelList(modelList: List<CharacterModel>) {
        this.modelList = modelList.toMutableList()
        notifyItemRangeInserted(0, modelList.size)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val binding =
            CardCharacterListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        return EmployeeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {

        val character = modelList[position]
        holder.view.firstNameTv.text = character.name
        holder.view.lastNameTv.text = character.alternateNames?.toString()
        holder.view.mobileTv.text = ""


        Glide
            .with(holder.view.img.context)
            .load(character.image)
            .centerCrop()
            .into(holder.view.img)

        holder.itemView.setOnClickListener {
            clickInterface?.onClick(character)
        }


    }

    override fun getItemCount(): Int {
        return modelList.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    fun setItemClick(clickInterface: ClickInterface<CharacterModel>) {
        this.clickInterface = clickInterface
    }

    class EmployeeViewHolder(val view: CardCharacterListBinding) : RecyclerView.ViewHolder(view.root)
}

