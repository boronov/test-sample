package ru.appsmile.test.hotel.presentation.room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.appsmile.test.hotel.R
import ru.appsmile.test.hotel.databinding.FragmentRoomBinding
import ru.appsmile.test.hotel.domain.Resource
import ru.appsmile.test.hotel.presentation.common.RoomsAdapter

@AndroidEntryPoint
class RoomFragment : Fragment() {

    private var _binding: FragmentRoomBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RoomViewModel by viewModels()

    private val roomsAdapter: RoomsAdapter by lazy { RoomsAdapter{
        val action = RoomFragmentDirections.actionNavRoomToNavBooking()
        findNavController().navigate(action)
    } }

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
        binding.recyclerViewRooms.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL).apply {
            ContextCompat.getDrawable(requireContext(), R.drawable.divider)?.let { setDrawable(it) }
        })
        binding.recyclerViewRooms.adapter = roomsAdapter
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.rooms.collect {
                    when (it) {
                        is Resource.Success -> {
                            binding.progressBar.isVisible = false
                            binding.recyclerViewRooms.isVisible = it.data != null
                            roomsAdapter.items = it.data
                        }

                        is Resource.Loading -> {
                            binding.progressBar.isVisible = true
                            binding.recyclerViewRooms.isVisible = false
                        }

                        is Resource.Error -> {
                            binding.progressBar.isVisible = false
                            binding.recyclerViewRooms.isVisible = it.data != null
                            roomsAdapter.items = it.data
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