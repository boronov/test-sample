package ru.appsmile.test.hotel.presentation.room

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
import ru.appsmile.test.hotel.databinding.FragmentRoomBinding
import ru.appsmile.test.hotel.domain.Resource

@AndroidEntryPoint
class RoomFragment : Fragment() {

    private var _binding: FragmentRoomBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RoomViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRoomBinding.inflate(layoutInflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()

        binding.buttonNext.setOnClickListener {
            val action = RoomFragmentDirections.actionNavRoomToNavBooking()
            findNavController().navigate(action)
        }
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.rooms.collect {
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