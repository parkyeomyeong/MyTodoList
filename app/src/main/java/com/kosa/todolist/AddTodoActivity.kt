package com.kosa.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.StructuredName
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.kosa.todolist.databinding.ActivityAddTodoBinding
import com.kosa.todolist.db.AppDatabase
import com.kosa.todolist.db.ToDoDao
import com.kosa.todolist.db.ToDoEntity

class AddTodoActivity : AppCompatActivity(){
    lateinit var binding: ActivityAddTodoBinding
    lateinit var db : AppDatabase
    lateinit var todoDao : ToDoDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)!!
        todoDao = db.getTodoDao()

        binding.btnCompletion.setOnClickListener {
            insertTodo()
        }
    }


    private fun insertTodo(){
        val todoTitle = binding.edtTitle.text.toString()
        var todoImportance = binding.radioGroup.checkedRadioButtonId

        when(todoImportance){
            R.id.btn_low ->{
                todoImportance = 3
            }
            R.id.btn_mid->{
                todoImportance = 2
            }
            R.id.btn_high->{
                todoImportance = 1
            }
        }

        //라디오버튼 안눌렀거나 제목 작성 안됐는지 체크
        if(todoImportance == -1 || todoTitle.isBlank()){
            Toast.makeText(this, "모든항목을 채워주세요", Toast.LENGTH_SHORT).show()


        }else{
            Thread{
                todoDao.insertTodo(ToDoEntity(null, todoTitle, todoImportance))
                runOnUiThread {
                    Toast.makeText(this,"추가 되었습니다", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }.start()
        }
    }
}