package ru.appsmile.test.hotel.presentation.common

import androidx.core.view.isVisible
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.santalu.maskara.widget.MaskEditText
import ru.appsmile.test.hotel.R
import ru.appsmile.test.hotel.databinding.ListItemTouristBinding

class TouristAdapter : ListDelegationAdapter<List<TouristAdapter.TouristInfo>>(
    adapterDelegateViewBinding(
        { layoutInflater, root ->
            ListItemTouristBinding.inflate(
                layoutInflater,
                root,
                false
            )
        }
    ) {
        bind {
            binding.textViewTouristNumber.text =
                getString(R.string.core_format_tourist_number, item.id.toString())

            binding.textInputName.editText?.setText(item.name)
            binding.textInputName.setupValidator({ it?.text?.isNotEmpty() ?: false }, {
                item.name = it?.text?.toString()
            })

            binding.textInputSurname.editText?.setText(item.surname)
            binding.textInputSurname.setupValidator({ it?.text?.isNotEmpty() ?: false }, {
                item.surname = it?.text?.toString()
            })

            binding.textInputBirthday.editText?.setText(item.birthday)
            binding.textInputBirthday.setupValidator({ (it as? MaskEditText?)?.isDone ?: false }, {
                item.birthday = it?.text?.toString()
            })

            binding.textInputCitizenship.editText?.setText(item.birthday)
            binding.textInputCitizenship.setupValidator({ it?.text?.isNotEmpty() ?: false }, {
                item.citizenship = it?.text?.toString()
            })

            binding.textInputPassportNumber.editText?.setText(item.passportNumber)
            binding.textInputPassportNumber.setupValidator({ it?.text?.isNotEmpty() ?: false }, {
                item.passportNumber = it?.text?.toString()
            })

            binding.textInputValidateDate.editText?.setText(item.passportValidateDate)
            binding.textInputValidateDate.setupValidator({
                (it as? MaskEditText?)?.isDone ?: false
            }, {
                item.passportValidateDate = it?.text?.toString()
            })

            binding.content.isVisible = item.isExpanded

            if (item.isExpanded) {
                binding.buttonExpand.rotateView(0, 180f, 0f)
            } else {
                binding.buttonExpand.rotateView(0, 0f, 180f)
            }

            binding.buttonExpand.setOnClickListener {
                item.isExpanded = !item.isExpanded
                binding.content.isVisible = item.isExpanded

                if (item.isExpanded) {
                    binding.buttonExpand.rotateView(500, 180f, 0f)
                } else {
                    binding.buttonExpand.rotateView(500, 0f, 180f)
                }
            }
        }
    }
) {
    data class TouristInfo(
        val id: Int,
        var name: String? = null,
        var surname: String? = null,
        var birthday: String? = null,
        var citizenship: String? = null,
        var passportNumber: String? = null,
        var passportValidateDate: String? = null
    ) {
        val isValidInfo: Boolean
            get() =
                !name.isNullOrBlank() && !surname.isNullOrBlank() && !birthday.isNullOrBlank() && !citizenship.isNullOrBlank() && !passportNumber.isNullOrBlank() && !passportValidateDate.isNullOrBlank()

        var isExpanded: Boolean = false
    }
}