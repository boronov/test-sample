package ru.appsmile.test.hotel.presentation.booking

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.appsmile.test.hotel.databinding.FragmentBookingBinding
import ru.appsmile.test.hotel.domain.Resource
import kotlin.random.Random

@AndroidEntryPoint
class BookingFragment : Fragment() {

    private var _binding: FragmentBookingBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BookingViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookingBinding.inflate(layoutInflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()

        binding.buttonNext.setOnClickListener {
            val action = BookingFragmentDirections.actionNavBookingToNavSuccessPaid(
                Random.nextInt(
                    0,
                    10_000
                )
            )
            findNavController().navigate(action)
        }
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.bookingInfo.collect {
                    when (it) {
                        is Resource.Success -> {
                            Log.d("TAG_TEST", "setupObserver: success: ${it.data}")
                        }

                        is Resource.Loading -> {
                            Log.d("TAG_TEST", "setupObserver: loading...")
                        }

                        is Resource.Error -> {
                            Log.d("TAG_TEST", "setupObserver: error: ${it.error}")
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}