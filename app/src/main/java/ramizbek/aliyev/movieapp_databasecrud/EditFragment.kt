package ramizbek.aliyev.movieapp_databasecrud

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import ramizbek.aliyev.movieapp_databasecrud.Objects.MyData
import ramizbek.aliyev.movieapp_databasecrud.databinding.FragmentEditBinding
import ramizbek.aliyev.movieapp_databasecrud.db.MyDBHelper
import ramizbek.aliyev.movieapp_databasecrud.db.User


class EditFragment : Fragment() {
    private lateinit var binding: FragmentEditBinding
    private lateinit var myDBHelper: MyDBHelper
    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditBinding.inflate(layoutInflater)
        binding.apply {
            myDBHelper = MyDBHelper(root.context)
            val list = myDBHelper.readUser()

            editMovie.setText(list[MyData.myPos].name)
            editAuthorMovie.setText(list[MyData.myPos].author)
            aboutMovie.setText(list[MyData.myPos].about)
            dataMovie.setText(list[MyData.myPos].date)

            editSave.setOnClickListener {
                val name = editMovie.text.toString()
                val author = editAuthorMovie.text.toString()
                val about = aboutMovie.text.toString()
                val date = dataMovie.text.toString()
                if (name.isNotEmpty() && author.isNotEmpty() && about.isNotEmpty() && date.isNotEmpty()) {
                    val user = arguments!!.getSerializable("userInfo") as User
                    user.name = name
                    user.author = author
                    user.about = about
                    user.date = date
                    myDBHelper.updateUser(user)
                    findNavController().popBackStack()
                } else {
                    Toast.makeText(root.context, "Ma'lumot yetarli emas", Toast.LENGTH_SHORT).show()
                }
            }

            return root
        }
    }
}