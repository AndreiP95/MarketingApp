package ro.marketing.offers.views.customViews


import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.res.ResourcesCompat
import ro.marketing.offers.R
import ro.marketing.offers.utils.ButtonConstants
import ro.marketing.offers.utils.setPercentageWidth

class CustomButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    AppCompatButton(context, attrs, defStyleAttr) {

    init {
        this.background =
            ResourcesCompat.getDrawable(
                resources,
                R.drawable.button_background_selector,
                context.theme
            )
        gravity = Gravity.CENTER
        isClickable = true
        setPercentageWidth(ButtonConstants.WIDTH_PERCENTAGE)
        setPadding(0, ButtonConstants.PADDING, 0, ButtonConstants.PADDING)
        height =
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                ButtonConstants.HEIGHT,
                resources.displayMetrics
            )
                .toInt()
    }
}
