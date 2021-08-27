package ro.marketing.offers.views.customViews

import android.animation.ObjectAnimator
import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.core.view.setMargins
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*
import ro.marketing.offers.R
import ro.marketing.offers.databinding.DialogLoadingViewBinding
import ro.marketing.offers.utils.LoadingConstants.LOADING_SCREEN_ADD_COLOR_DURATION
import ro.marketing.offers.utils.LoadingConstants.LOADING_SCREEN_DELAY
import ro.marketing.offers.utils.LoadingConstants.LOADING_SCREEN_REMOVE_COLOR_DURATION
import ro.marketing.offers.utils.LoadingConstants.LOADING_SCREEN_TRANSLATE_DURATION
import ro.marketing.offers.utils.LoadingConstants.LOADING_SIZE
import ro.marketing.offers.utils.LoadingConstants.LOADING_TRANSLATION_Y
import ro.marketing.offers.utils.inflateLayoutAs
import ro.marketing.offers.utils.setSquareLayout

class LoadingDialog : DialogFragment(),
    CoroutineScope by MainScope() {

    var _binding: DialogLoadingViewBinding? = null
    private val loadingBinding get() = _binding!!
    var startY: Float = 0.0f
    var job: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setDialogWindow()
        _binding = DialogLoadingViewBinding.inflate(inflater, container, false)
        return loadingBinding.root
    }

    private fun setDialogWindow() {
        dialog?.window?.setBackgroundDrawable(
            context?.let {
                ContextCompat.getDrawable(it, R.drawable.white_background)
            })
        isCancelable = true
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        startY = loadingBinding.llLoading.getChildAt(0)
            .findViewById<RelativeLayout>(R.id.loading_circle).y
    }

     private fun setLayoutForDialogWindow() {
        dialog?.window?.setSquareLayout(ratio = LOADING_SIZE)
    }

    fun startAnimation() {
        if (job == null) {
            job = lifecycleScope.launchWhenResumed {
                dotsAnimation()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        setLayoutForDialogWindow()
        startAnimation()
    }

    suspend fun dotsAnimation() {
        var position = 0
        while (true) {
            loadingBinding.llLoading.getChildAt(position % 4)
                .findViewById<RelativeLayout>(R.id.loading_circle).let {
                animateCircle(it)
            }
            delay(LOADING_SCREEN_DELAY)
            position += 1
        }
    }

    private fun animateCircle(view: View) {
        val translationAnimator =
            ObjectAnimator.ofFloat(
                view, "translationY", startY, startY - LOADING_TRANSLATION_Y
            ).apply {
                duration = LOADING_SCREEN_TRANSLATE_DURATION
            }
        val colorAnimation = view.background as TransitionDrawable

        lifecycleScope.launch {
            colorAnimation.startTransition(LOADING_SCREEN_ADD_COLOR_DURATION)
            translationAnimator.start()
            delay(LOADING_SCREEN_DELAY)
            colorAnimation.reverseTransition(LOADING_SCREEN_REMOVE_COLOR_DURATION)
            translationAnimator.reverse()
        }
    }

    private fun setupUI() {
        for (i in 0..3) {
            addLoadingCircles()
        }
    }

    private fun addLoadingCircles() {
        val view = requireContext().inflateLayoutAs<RelativeLayout>(R.layout.loading_circle)
        val layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT, 1.0f).apply {
            setMargins(
                resources.getDimension(R.dimen.loading_circle_margin_6dp).toInt()
            )
        }
        loadingBinding.llLoading.addView(view, layoutParams)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}