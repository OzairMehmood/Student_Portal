package com.professorapps.instant.talking.studentportal

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.professorapps.instant.talking.studentportal.adapters.StudentAdapter
import com.professorapps.instant.talking.studentportal.databinding.ActivityMainBinding
import com.professorapps.instant.talking.studentportal.model.StudentModel

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnadd.setOnClickListener {
            val name = binding.nameInputEditText.text.toString()
            val age = binding.ageEditText.text.toString()
            val id = binding.idInputEditText.text.toString()
            if (name.isNotEmpty()&&age.isNotEmpty()&&id.isNotEmpty()){
                val id=id.toInt()
                val age=age.toInt()
                studentListgen.add(StudentModel(name,id,age))
                Toast.makeText(this,"Student Added to List",Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this,"Please Enter All Fields",Toast.LENGTH_LONG).show()
            }

        }
        binding.btnext.setOnClickListener {
            val intent = Intent(this, ViewStudentActivity::class.java)
            startActivity(intent)
        }

    }

    companion object {
        val studentListgen = ArrayList<StudentModel>()
    }
}