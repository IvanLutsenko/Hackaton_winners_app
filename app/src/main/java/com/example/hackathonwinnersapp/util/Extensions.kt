package ru.awac.cBoRRates.util

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.example.hackathonwinnersapp.util.CustomDividerItemDecorator
import com.example.hackathonwinnersapp.util.StringResource
import com.example.hackatonwinnersapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

object EmptyField {
    const val STRING = "-"
    const val NUMBER = 0
}

fun String?.orEmptyField(): String {
    return if (this == null || this.isEmpty()) EmptyField.STRING
    else this
}

fun Int?.orEmptyField(): Int {
    return this ?: EmptyField.NUMBER
}

fun Float?.orEmptyField(): Float {
    return this ?: EmptyField.NUMBER.toFloat()
}

fun Context.getString(stringResource: StringResource) =
    when (stringResource) {
        is StringResource.ByRes -> getString(stringResource.text)
        is StringResource.ByString -> stringResource.text
    }

fun LifecycleOwner.repeatOnStart(block: suspend CoroutineScope.() -> Unit) {
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            block()
        }
    }
}

fun <T> CoroutineScope.launchCollect(flow: Flow<T>, action: suspend (T) -> Unit) {
    launch {
        flow.collect { action(it) }
    }
}

fun RecyclerView.addDividers() {
    val dividerItemDecoration = CustomDividerItemDecorator(
        AppCompatResources.getDrawable(context, R.drawable.divider)
    )
    addItemDecoration(dividerItemDecoration)
}

inline fun <reified T> Bundle.parcelable(key: String): T? = when {
    Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getParcelable(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelable(key) as? T
}
