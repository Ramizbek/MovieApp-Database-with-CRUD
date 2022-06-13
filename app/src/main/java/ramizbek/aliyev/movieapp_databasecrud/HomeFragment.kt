package ramizbek.aliyev.movieapp_databasecrud

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import ramizbek.aliyev.movieapp_databasecrud.Objects.MyData
import ramizbek.aliyev.movieapp_databasecrud.adapter.MyAdapter
import ramizbek.aliyev.movieapp_databasecrud.adapter.MyOnClickListener
import ramizbek.aliyev.movieapp_databasecrud.databinding.FragmentHomeBinding
import ramizbek.aliyev.movieapp_databasecrud.db.MyDBHelper
import ramizbek.aliyev.movieapp_databasecrud.db.User


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var myAdapter: MyAdapter
    private lateinit var myDBHelper: MyDBHelper
    private lateinit var list: ArrayList<User>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.apply {
            myDBHelper = MyDBHelper(root.context)
            list = myDBHelper.readUser()

            imageAdd.setOnClickListener {
                findNavController().navigate(R.id.addFragment)
            }

            myAdapter = MyAdapter(list, object : MyOnClickListener {
                override fun editMovie(user: User, position: Int) {
                    MyData.myPos = position
                    findNavController().navigate(R.id.editFragment, bundleOf("userInfo" to user))
                }

                override fun deleteMovie(user: User, position: Int) {
                    myDBHelper.deleteUser(user)
                    list.removeAt(position)
                    myAdapter.notifyItemRemoved(position)
                    myAdapter.notifyItemRangeChanged(position, list.size)
                    add()
                }

                override fun onClick(position: Int) {
                    MyData.myPos = position
                    findNavController().navigate(R.id.aboutFragment)
                }
            })

            myRv.adapter = myAdapter
            add()
            return binding.root
        }
    }

    // VISIBLE, INVISIBLE btns
    fun add() {
        binding.apply {
            if (list.isEmpty()) {
                btnAdd.visibility = View.VISIBLE
                imageAdd.visibility = View.INVISIBLE
                btnAdd.setOnClickListener {
                    findNavController().navigate(R.id.addFragment)
                }
            } else {
                btnAdd.visibility = View.INVISIBLE
                imageAdd.visibility = View.VISIBLE
                imageAdd.setOnClickListener {
                    findNavController().navigate(R.id.addFragment)
                }
            }
        }
    }
}