package com.professorapps.instant.talking.studentportal

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.professorapps.instant.talking.studentportal.MainActivity.Companion.studentListgen
import com.professorapps.instant.talking.studentportal.adapters.StudentAdapter
import com.professorapps.instant.talking.studentportal.databinding.ActivityViewStudentBinding
import com.professorapps.instant.talking.studentportal.model.StudentModel
import org.jetbrains.annotations.ApiStatus

class ViewStudentActivity : AppCompatActivity() {
    private lateinit var studentadapter: StudentAdapter
    private val binding: ActivityViewStudentBinding by lazy {
        ActivityViewStudentBinding.inflate(layoutInflater)
    }
    private lateinit var studentList: ArrayList<StudentModel>
    private lateinit var originalList: ArrayList<StudentModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        studentList = ArrayList(studentListgen)
        originalList = ArrayList(studentListgen)
        studentadapter = StudentAdapter(this, studentList) { position ->
            studentList.removeAt(position)
            studentadapter.notifyItemRemoved(position)
        }
        binding.recstudent.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = studentadapter
        }
        binding.btnDelete.setOnClickListener {
            studentList.removeAll { it.isSelected }
            originalList.removeAll { it.isSelected } // keep both lists in sync
            studentadapter.notifyDataSetChanged()
        }

        binding.searchInputEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString().trim().lowercase()
                studentList.clear()

                if (query.isEmpty()) {
                    studentList.addAll(originalList)
                } else {
                    val filtered = originalList.filter {
                        it.Student_name.lowercase().contains(query)
                    }
                    studentList.addAll(filtered)
                }

                studentadapter.notifyDataSetChanged()
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
    }

