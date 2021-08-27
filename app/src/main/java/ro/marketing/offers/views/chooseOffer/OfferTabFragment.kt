package ro.marketing.offers.views.chooseOffer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ro.marketing.offers.databinding.FragmentChooseOfferTabBinding
import ro.marketing.offers.repo.model.Offers
import ro.marketing.offers.utils.KeysConstants.OFFER
import ro.marketing.offers.views.chooseOffer.adapter.OfferTabAdapter
import javax.inject.Inject

class OfferTabFragment(
    private val offer: Offers
) : Fragment() {

    private var binding: FragmentChooseOfferTabBinding? = null
    private val tabOffer get() = binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChooseOfferTabBinding.inflate(inflater, container, false)
        return tabOffer.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupOffers()
    }

    private fun setupOffers() {
        tabOffer.offerDetails.layoutManager = LinearLayoutManager(requireContext())
        val offerTabAdapter = OfferTabAdapter()
        tabOffer.offerDetails.adapter = offerTabAdapter
        offerTabAdapter.offerDetails = offer.receivables
    }

    companion object {
        fun newInstance(offer: Offers) = OfferTabFragment(offer)
    }
}