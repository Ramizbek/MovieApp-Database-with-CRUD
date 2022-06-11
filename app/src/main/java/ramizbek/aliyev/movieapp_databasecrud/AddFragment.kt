package ramizbek.aliyev.movieapp_databasecrud

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import ramizbek.aliyev.movieapp_databasecrud.databinding.FragmentAddBinding
import ramizbek.aliyev.movieapp_databasecrud.db.MyDBHelper
import ramizbek.aliyev.movieapp_databasecrud.db.User

class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private lateinit var myDBHelper: MyDBHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBinding.inflate(layoutInflater)
        binding.apply {
            addSave.setOnClickListener {
                if (addMovie.text.isNotEmpty() && authorMovie.text.isNotEmpty() && aboutMovie.text.isNotEmpty() && dataMovie.text.isNotEmpty()) {
                    val user = User(
                        addMovie.text.toString(),
                        authorMovie.text.toString(),
                        aboutMovie.text.toString(),
                        dataMovie.text.toString()
                    )
                    myDBHelper = MyDBHelper(root.context)
                    myDBHelper.createUser(user)
                    findNavController().popBackStack()
                } else {
                    Toast.makeText(root.context, "Ma'lumot yetarli emas", Toast.LENGTH_SHORT).show()
                }
            }

            return binding.root
        }
    }
}