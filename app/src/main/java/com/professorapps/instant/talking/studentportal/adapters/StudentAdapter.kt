package com.professorapps.instant.talking.studentportal.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.professorapps.instant.talking.studentportal.R
import com.professorapps.instant.talking.studentportal.model.StudentModel

class StudentAdapter(
    val context: Context,
    val studentList: ArrayList<StudentModel>,
    private val onDeleteClick: (Int) -> Unit

) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StudentViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_student_view, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: StudentViewHolder,
        position: Int
    ) {
        val model = studentList[position]
        holder.sname.text = model.Student_name
        holder.sage.text = model.Student_Age.toString()
        holder.btndelete.setOnClickListener {
            onDeleteClick(position)
        }
        holder.isSelected.isChecked = model.isSelected
        holder.isSelected.setOnCheckedChangeListener { _, isChecked ->
            model.isSelected = isChecked
        }
    }

    override fun getItemCount(): Int {
        return studentList.size
    }


    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val sname = itemView.findViewById<TextView>(R.id.tvsName)
        val sage = itemView.findViewById<TextView>(R.id.tvsAge)
        val btndelete = itemView.findViewById<ImageView>(R.id.btndeleteitem)

        val isSelected = itemView.findViewById<CheckBox>(R.id.chkitem)
        val btnupdate=itemView.findViewById<ImageView>(R.id.btnupdate)

    }
}