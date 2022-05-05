package com.example.dz4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dz4.databinding.FragmentCountBinding
import com.example.dz4.model.User
import com.google.android.material.textfield.TextInputLayout


class CountFragment : Fragment() {
    private var _binding: FragmentCountBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val cityDao by lazy {
        requireContext().appDatabase.userDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentCountBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            buttonAdd.setOnClickListener {
                val name = containerName.getTextOrSetError()
                val city = containerCity.getTextOrSetError()
                if (name == null || city == null) return@setOnClickListener
                cityDao.insertAll(User(user = name, city = city))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

fun TextInputLayout.getTextOrSetError(): String? {
    return editText?.text?.toString()
        ?.takeIf { it.isNotBlank() }
        .also { text ->
            error = if (text.isNullOrBlank()) {
                "Field is empty"
            } else {
                null
            }
        }
}

