package ru.appsmile.test.hotel.presentation.booking.success

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import ru.appsmile.test.hotel.R
import ru.appsmile.test.hotel.databinding.FragmentSuccessPaidBinding

@AndroidEntryPoint
class SuccessPaidFragment : Fragment() {

    private var _binding: FragmentSuccessPaidBinding? = null
    private val binding get() = _binding!!

    private val args: SuccessPaidFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSuccessPaidBinding.inflate(layoutInflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textViewSubtitle.text = getString(R.string.core_confirm_order_subtitle, args.orderId)

        binding.buttonFinish.setOnClickListener {
            val action = SuccessPaidFragmentDirections.actionNavSuccessPaidToNavHotel()
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}