package ro.marketing.offers.views.review

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ro.marketing.offers.R
import ro.marketing.offers.databinding.FragmentReviewBinding
import ro.marketing.offers.repo.model.Offers
import ro.marketing.offers.utils.EmailConstants.MESSAGE_TYPE
import ro.marketing.offers.utils.EmailConstants.RECEIVER_EMAIL
import ro.marketing.offers.views.chooseOffer.adapter.OfferTabAdapter
import ro.marketing.offers.views.offers.OffersViewModel
import java.lang.Exception
import javax.inject.Inject

@AndroidEntryPoint
class ReviewFragment : Fragment() {

    private var binding: FragmentReviewBinding? = null
    private val reviewFragmentBinding get() = binding!!
    private val channelPosition by lazy {
        navArgs<ReviewFragmentArgs>().value.channelPosition
    }
    private val offerPosition by lazy {
        navArgs<ReviewFragmentArgs>().value.offerPosition
    }

    private val offersViewModel: OffersViewModel by activityViewModels()

    @Inject
    lateinit var adapter: OfferTabAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReviewBinding.inflate(inflater, container, false)
        return reviewFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showData()
    }

    private fun showData() {
        reviewFragmentBinding.selectedOfferDetailRecyclerView.layoutManager =
            LinearLayoutManager(requireContext())
        reviewFragmentBinding.selectedOfferDetailRecyclerView.adapter = adapter
        offersViewModel.offers.value?.let {
            adapter.offerDetails =
                it[channelPosition].packages[offerPosition].receivables
            reviewFragmentBinding.price.text =
                resources.getString(
                    R.string.review_price,
                    it[channelPosition].packages[offerPosition].fee
                )
            setupListeners(it[channelPosition].packages[offerPosition])
        }
    }

    private fun setupListeners(offer: Offers) {
        reviewFragmentBinding.btnSelectOffer.setOnClickListener {
            sendEmail(offer)
        }
    }

    private fun sendEmail(offer: Offers) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = MESSAGE_TYPE
        intent.putExtra(Intent.EXTRA_EMAIL, RECEIVER_EMAIL)
        intent.putExtra(Intent.EXTRA_SUBJECT, resources.getString(R.string.review_subject))
        intent.putExtra(
            Intent.EXTRA_TEXT,
            resources.getString(R.string.review_text, offer.fee, offer.receivables)
        )
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        try {
            startActivity(
                Intent.createChooser(
                    intent,
                    resources.getString(R.string.review_email_apps)
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}