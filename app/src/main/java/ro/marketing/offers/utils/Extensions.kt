package ro.marketing.offers.utils

import android.content.Context
import android.content.res.ColorStateList
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment

inline fun <reified T : View> Context.inflateLayoutAs(id: Int): T {
    return View.inflate(this, id, null) as T
}

private fun ratioToTotalPixels(
    ratio: Int, pixels: Int
) = ratio * pixels / 100

fun Button.setPercentageWidth(ratio: Int): Int {
    if (ratio == 1) return -1
    context?.resources?.displayMetrics?.let {
        return ratioToTotalPixels(ratio, it.widthPixels)
    }
    return -1
}

fun Window.setPercentageWidth(ratio: Int): Int {
    context?.resources?.displayMetrics?.let {
        return ratioToTotalPixels(ratio, it.widthPixels)
    }
    return -1
}

fun Button.setPercentageHeight(ratio: Int) {
    context?.resources?.displayMetrics?.let {
        width = ratioToTotalPixels(ratio, it.heightPixels)
    }
}

fun Button.changeButtonState(resourceId: Int, isClickable: Boolean) {
    this.backgroundTintList =
        ColorStateList.valueOf(ResourcesCompat.getColor(resources, resourceId, context?.theme))
    this.isClickable = isClickable
    invalidate()
}

fun Window.setSquareLayout(ratio: Int = -1) {
    val ratioPixels = setPercentageWidth(ratio)
    if (ratioPixels != -1)
        this.setLayout(ratioPixels, ratioPixels)
    else
        this.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
}

