package com.juicetraker.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.appcompat.R.layout
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.juicetraker.data.JuiceColor
import com.juicetraker.databinding.FragmentEntryDialogBinding
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class EntryDialogFragment : BottomSheetDialogFragment() {
    private val entryViewModel by viewModels<EntryViewModel> { AppViewModelProvider.Factory }
    var selectedColor: JuiceColor = JuiceColor.Red

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentEntryDialogBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val colorLabelMap = JuiceColor.entries.associateBy { getString(it.label) }
        val binding = FragmentEntryDialogBinding.bind(view)
        val args: EntryDialogFragmentArgs by navArgs()
        val juiceId = args.itemId

        if(args.itemId > 0) {
            viewLifecycleOwner.lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    entryViewModel.getJuiceStream(args.itemId).filterNotNull().collect { item ->
                      with(binding) {
                          etJuiceName.setText(item.name)
                          etDescription.setText(item.description)
                          ratingBar.rating = item.rating.toFloat()
                          spinner.setSelection(findColorIndex(item.color))
                      }
                    }
                }
            }
        }

        binding.tvJuiceName.doOnTextChanged { _, start, _, count ->
            // Enable Save button if the current text is longer than 3 characters
            binding.btnSave.isEnabled = (start+count) > 0
        }
        binding.spinner.adapter = ArrayAdapter(
            requireContext(),
            layout.support_simple_spinner_dropdown_item,
            colorLabelMap.map { it.key }
        )
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                // Get the label of the selected item
                val selected = parent.getItemAtPosition(pos).toString()
                // Get the enum value from string
                selectedColor = colorLabelMap[selected] ?: selectedColor
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                //if nothing is selected, assign the first color choice as the selectedColor
                selectedColor = JuiceColor.Red
            }
        }

        binding.btnSave.setOnClickListener {
            entryViewModel.saveJuice(
                juiceId,
                binding.etJuiceName.text.toString(),
                binding.etDescription.text.toString(),
                selectedColor.name,
                binding.ratingBar.rating.toInt()
            )
            dismiss()
        }
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
    }

    private fun findColorIndex(color: String): Int {
        val juiceColor = JuiceColor.valueOf(color)
        return JuiceColor.entries.indexOf(juiceColor)
    }
}