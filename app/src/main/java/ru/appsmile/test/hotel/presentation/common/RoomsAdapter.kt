package ru.appsmile.test.hotel.presentation.common

import androidx.core.content.ContextCompat
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.flexbox.FlexboxItemDecoration
import com.google.android.flexbox.FlexboxLayoutManager
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.appsmile.test.hotel.R
import ru.appsmile.test.hotel.databinding.ListItemRoomBinding
import ru.appsmile.test.hotel.domain.model.Room

class RoomsAdapter(onRoomChoose: (room: Room) -> Unit) : ListDelegationAdapter<List<Room>>(
    adapterDelegateViewBinding(
        { layoutInflater, root ->
            ListItemRoomBinding.inflate(
                layoutInflater,
                root,
                false
            )
        }
    ) {
        bind {
            val imageList = item.imageUrls.map { SlideModel(it) }
            binding.imageViewSlider.setImageList(imageList, ScaleTypes.CENTER_CROP)

            binding.textViewName.text = item.name
            binding.textViewPrice.text = getString(R.string.core_format_price, item.price.toCurrencyFormat())
            binding.textViewPriceForIt.text = item.pricePer

            binding.recyclerViewPeculiarities.apply {
                layoutManager = FlexboxLayoutManager(binding.root.context)
                addItemDecoration(FlexboxItemDecoration(binding.root.context).apply {
                    this.setDrawable(ContextCompat.getDrawable(binding.root.context, R.drawable.divider))
                })

                adapter = PeculiaritiesAdapter().apply {
                    items = item.peculiarities
                }
            }

            binding.buttonNext.setOnClickListener { onRoomChoose(item) }
        }
    }
)


