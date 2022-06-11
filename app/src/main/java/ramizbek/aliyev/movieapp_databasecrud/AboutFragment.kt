package ramizbek.aliyev.movieapp_databasecrud

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import ramizbek.aliyev.movieapp_databasecrud.Objects.MyData
import ramizbek.aliyev.movieapp_databasecrud.databinding.FragmentAboutBinding
import ramizbek.aliyev.movieapp_databasecrud.db.MyDBHelper

class AboutFragment : Fragment() {
    private lateinit var binding: FragmentAboutBinding
    private lateinit var myDBHelper: MyDBHelper
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutBinding.inflate(layoutInflater)
        binding.apply {
            myDBHelper = MyDBHelper(root.context)
            val list = myDBHelper.readUser()

            aboutName.text = "Movie name: ${list[MyData.myPos].name}"
            aboutAuthors.text = "Movie authors: ${list[MyData.myPos].author}"
            aboutAbout.text = "About movie: ${list[MyData.myPos].about}"
            aboutData.text = "Movie date: ${list[MyData.myPos].date}"
            close.setOnClickListener {
                findNavController().popBackStack()
            }

            return root
        }
    }
}