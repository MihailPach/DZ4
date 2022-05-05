package com.example.dz4

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dz4.adapter.CountAdapter
import com.example.dz4.databinding.FragmentListBinding
import com.example.dz4.model.User
import java.io.IOException

class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val adapter by lazy {
        CountAdapter(requireContext()) {
            showDialog(user = it)

        }
    }
    private val userDao by lazy {
        requireContext().appDatabase.userDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentListBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            val layoutManager =
                LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            recyclerView.layoutManager = layoutManager

        }
        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(view.context)
            recyclerView.adapter = adapter

        }
        rebuildResult()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun rebuildResult() {
        val userList = userDao.getCities()
        adapter.submitList(userList)

    }

    private fun deleteCity(user: User): Boolean {
        val userDel = userDao.delete(user).toString()
        return try {
            requireContext().deleteFile(userDel)
        } catch (e: IOException) {
            false
        }
    }

    private fun showDialog(user: User) {
        AlertDialog.Builder(requireContext())
            .setTitle("Delete")
            .setMessage("Attention DELETE")
            .setPositiveButton(android.R.string.ok) { dialog, buttonId ->
                deleteCity(user = user)
                rebuildResult()
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }
}
