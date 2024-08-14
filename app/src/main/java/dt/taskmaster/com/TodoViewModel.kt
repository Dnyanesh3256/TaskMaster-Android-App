package dt.taskmaster.com

import android.icu.text.CaseMap.Title
import android.widget.Toast
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TodoViewModel : ViewModel() {

    private val _todoList = MutableLiveData<List<Todo>>()
    val todoList : LiveData<List<Todo>> = _todoList

    // LiveData to trigger the Toast message
    private val _toastMessage = MutableLiveData<String?>()
    val toastMessage: LiveData<String?> = _toastMessage

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    fun getAllTodo(){
        _todoList.value = TodoManager.getAllTodo().reversed()
    }

    fun addTodo(title: String){
       // TodoManager.addTodo(title)
       // getAllTodo()
        if(title.isNotEmpty()){
            TodoManager.addTodo(title)
            getAllTodo()
            _toastMessage.value = "Todo added!!"
            _errorMessage.value = null // Clear any previous errors
        } else {
            _errorMessage.value = "Please enter the title of  the Todo!!"
        }
    }

    fun deleteTodo(id : Int){
        TodoManager.deleteTodo(id)
        getAllTodo()
        _toastMessage.value = "Todo deleted!!"
    }

    // Clear the toast message after showing it
    fun onToastShown() {
        _toastMessage.value = null
    }

    fun onErrorShown() {
        _errorMessage.value = null
    }
}