package ru.appsmile.test.hotel.presentation.booking.success

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.appsmile.test.hotel.databinding.FragmentSuccessPaidBinding

class SuccessPaidFragment : Fragment() {

    private var _binding: FragmentSuccessPaidBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SuccessPaidViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSuccessPaidBinding.inflate(layoutInflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val action = SuccessPaidFragmentDirections.actionNavSuccessPaidToNavHotel()
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}