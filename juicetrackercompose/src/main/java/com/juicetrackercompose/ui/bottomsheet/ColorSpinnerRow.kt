package com.juicetrackercompose.ui.bottomsheet

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import com.juicetrackercompose.R
import com.juicetrackercompose.data.JuiceColor

class SpinnerAdapter(
    val onColorChange: (Int) -> Unit
) : AdapterView.OnItemSelectedListener {
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        onColorChange(position)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        onColorChange(0)
    }
}

@Composable
fun ColorSpinnerRow(
    colorSpinnerPosition: Int,
    onColorChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val juiceColorArray =
        JuiceColor.values().map { juiceColor ->
            stringResource(juiceColor.label)
        }
    InputRow(inputLabel = stringResource(R.string.color), modifier = modifier) {
        AndroidView(
            modifier = Modifier.fillMaxWidth(),
            factory = { ctx ->
                Spinner(ctx).apply {
                    adapter = ArrayAdapter(
                        ctx,
                        android.R.layout.simple_spinner_dropdown_item,
                        juiceColorArray
                    )
                }
            },
            update = { spinner ->
                spinner.setSelection(colorSpinnerPosition)
                spinner.onItemSelectedListener = SpinnerAdapter(onColorChange)
            }
        )
    }
}