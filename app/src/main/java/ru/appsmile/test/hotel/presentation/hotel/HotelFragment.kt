package ru.appsmile.test.hotel.presentation.hotel

import android.annotation.SuppressLint
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
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.flexbox.FlexboxItemDecoration
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.appsmile.test.hotel.R
import ru.appsmile.test.hotel.databinding.FragmentHotelBinding
import ru.appsmile.test.hotel.databinding.ListItemPeculiaritiesBinding
import ru.appsmile.test.hotel.domain.Resource
import ru.appsmile.test.hotel.domain.model.Hotel

@AndroidEntryPoint
class HotelFragment : Fragment() {

    private var _binding: FragmentHotelBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HotelViewModel by viewModels()

    private val peculiaritiesAdapterDelegate: ListDelegationAdapter<List<String>> by lazy {
        ListDelegationAdapter(adapterDelegateViewBinding(
            { layoutInflater, root ->
                ListItemPeculiaritiesBinding.inflate(
                    layoutInflater,
                    root,
                    false
                )
            }
        ) {
            bind {
                binding.root.text = item
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHotelBinding.inflate(layoutInflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()

        binding.recyclerViewPeculiarities.apply {
            layoutManager = FlexboxLayoutManager(requireContext())
            addItemDecoration(FlexboxItemDecoration(requireContext()).apply {
                this.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.divider))
            })
        }
    }


    private fun setupObserver() {
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.hotel.collect {
                    when (it) {
                        is Resource.Success -> {
                            binding.content.isVisible = it.data != null
                            binding.progressBar.isVisible = false
                            binding.buttonNext.isVisible = true
                            it.data?.let { hotel -> setupViews(hotel) }
                        }

                        is Resource.Loading -> {
                            binding.content.isVisible = false
                            binding.progressBar.isVisible = true
                            binding.buttonNext.isVisible = false
                        }

                        is Resource.Error -> {
                            binding.progressBar.isVisible = false
                            binding.buttonNext.isVisible = it.data != null
                            binding.content.isVisible = it.data != null
                            it.data?.let { hotel -> setupViews(hotel) }

                            Snackbar.make(binding.root, it.error.orEmpty(), Snackbar.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setupViews(hotel: Hotel) {
        val imageList = hotel.imageUrls.map { SlideModel(it) }
        binding.imageViewSlider.setImageList(imageList, ScaleTypes.CENTER_CROP)

        binding.textViewRating.text = "${hotel.rating} ${hotel.ratingName}"

        binding.textViewHotelName.text = hotel.name
        binding.buttonAddress.text = hotel.address

        binding.textViewMinimalPrice.text = getString(R.string.core_format_minimal_price, hotel.minimalPrice.toLong().toString())
        binding.textViewPriceForIt.text = hotel.priceForIt

        peculiaritiesAdapterDelegate.items = hotel.aboutTheHotel.peculiarities
        binding.recyclerViewPeculiarities.adapter = peculiaritiesAdapterDelegate

        binding.textViewDescription.text = hotel.aboutTheHotel.description

        binding.buttonNext.setOnClickListener {
            val action = HotelFragmentDirections.actionNavHotelToNavRoom(hotel.name)
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}