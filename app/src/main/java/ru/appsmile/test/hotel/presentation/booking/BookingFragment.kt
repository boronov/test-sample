package ru.appsmile.test.hotel.presentation.booking

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Patterns
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
import com.google.android.material.snackbar.Snackbar
import com.santalu.maskara.widget.MaskEditText
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.appsmile.test.hotel.R
import ru.appsmile.test.hotel.databinding.FragmentBookingBinding
import ru.appsmile.test.hotel.domain.Resource
import ru.appsmile.test.hotel.domain.model.BookingInfo
import ru.appsmile.test.hotel.presentation.common.TouristAdapter
import ru.appsmile.test.hotel.presentation.common.setupValidator
import ru.appsmile.test.hotel.presentation.common.toCurrencyFormat
import kotlin.random.Random

@AndroidEntryPoint
class BookingFragment : Fragment() {

    private var _binding: FragmentBookingBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BookingViewModel by viewModels()

    private val touristAdapter = TouristAdapter()

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
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.bookingInfo.collect {
                    when (it) {
                        is Resource.Success -> {
                            binding.progressBar.isVisible = false
                            binding.content.isVisible = it.data != null
                            binding.buttonPay.isVisible = it.data != null
                            it.data?.let { info -> setupViews(info) }
                        }

                        is Resource.Loading -> {
                            binding.content.isVisible = false
                            binding.progressBar.isVisible = true
                            binding.buttonPay.isVisible = it.data != null
                            it.data?.let { info -> setupViews(info) }
                        }

                        is Resource.Error -> {
                            binding.progressBar.isVisible = false
                            binding.buttonPay.isVisible = it.data != null
                            binding.content.isVisible = it.data != null
                            it.data?.let { info -> setupViews(info) }

                            Snackbar.make(binding.root, it.error.orEmpty(), Snackbar.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            }
        }
    }


    @SuppressLint("SetTextI18n")
    private fun setupViews(hotelInfo: BookingInfo) {
        binding.recyclerViewTourist.apply {
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                ).apply {
                    ContextCompat.getDrawable(requireContext(), R.drawable.divider)
                        ?.let { setDrawable(it) }
                })
            adapter = touristAdapter
        }

        binding.textInputPhoneNumber.setupValidator({
            (it as? MaskEditText?)?.isDone ?: false
        }, {
            viewModel.userPhone = it?.text?.toString()
        })

        binding.textInputEmail.setupValidator({
            it?.text?.toString().orEmpty().contains(Patterns.EMAIL_ADDRESS.toRegex())
        }, {
            viewModel.userEmail = it?.text?.toString()
        })

        binding.textViewRating.text = "${hotelInfo.hotelRating} ${hotelInfo.ratingName}"

        binding.textViewTopHotelName.text = hotelInfo.hotelName
        binding.buttonAddress.text = hotelInfo.hotelAddress

        binding.textViewDeparture.text = hotelInfo.departure
        binding.textViewArrivalCountry.text = hotelInfo.arrivalCountry
        binding.textViewTourDate.text = "${hotelInfo.tourDateStart} – ${hotelInfo.tourDateStop}"
        binding.textViewNumberOfNight.text = hotelInfo.numberOfNights.toString()
        binding.textViewHotelName.text = hotelInfo.hotelName
        binding.textViewRoom.text = hotelInfo.room
        binding.textViewNutrition.text = hotelInfo.nutrition

        touristAdapter.items = listOf(TouristAdapter.TouristInfo(id = 1).apply {
            isExpanded = true
        })

        calculatePrice(hotelInfo, touristAdapter.items?.size ?: 0)

        binding.buttonAddTourist.setOnClickListener {
            val newTourist = TouristAdapter.TouristInfo(id = (touristAdapter.items?.size ?: 0) + 1)
            val newTouristList: MutableList<TouristAdapter.TouristInfo> =
                touristAdapter.items?.map { it.copy() }?.toMutableList() ?: mutableListOf()
            newTouristList.add(newTourist)

            touristAdapter.items = newTouristList
            touristAdapter.notifyItemInserted((touristAdapter.items?.size ?: 0) + 1)
            calculatePrice(hotelInfo, touristAdapter.items?.size ?: 0)
        }

        binding.buttonPay.setOnClickListener {
            if (!viewModel.userEmail.isNullOrBlank() && !viewModel.userPhone.isNullOrBlank() && touristAdapter.items?.find { !it.isValidInfo } == null) {
                val action = BookingFragmentDirections.actionNavBookingToNavSuccessPaid(
                    Random.nextInt(
                        0,
                        10_000
                    )
                )
                findNavController().navigate(action)
            } else {
                Snackbar.make(
                    binding.root,
                    getString(R.string.core_fill_all_filed), Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun calculatePrice(hotelInfo: BookingInfo, touristCount: Int) {
        val tourPrice = hotelInfo.tourPrice * touristCount
        binding.textViewTourPrice.text = getString(R.string.core_format_price, tourPrice.toCurrencyFormat())

        val fuelCharge = hotelInfo.fuelCharge * touristCount
        binding.textViewFuelCharge.text = getString(R.string.core_format_price, fuelCharge.toCurrencyFormat())

        val serviceCharge = hotelInfo.serviceCharge * touristCount
        binding.textViewServiceCharge.text = getString(R.string.core_format_price, serviceCharge.toCurrencyFormat())

        val totalPrice = tourPrice + fuelCharge + serviceCharge
        binding.textViewTotalPrice.text = getString(R.string.core_format_price, totalPrice.toCurrencyFormat())

        binding.buttonPay.text = getString(R.string.core_format_pay_price, totalPrice.toCurrencyFormat())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}