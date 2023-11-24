package ru.appsmile.test.hotel.presentation.common

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.appsmile.test.hotel.databinding.ListItemPeculiaritiesBinding

class PeculiaritiesAdapter : ListDelegationAdapter<List<String>>(
    adapterDelegateViewBinding(
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
    }
)