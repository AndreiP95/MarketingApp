package ro.marketing.offers.views.chooseOffer.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ro.marketing.offers.repo.model.OffersPackages
import ro.marketing.offers.views.chooseOffer.OfferTabFragment

class ChooseOffersAdapter(private val offerPackage: OffersPackages, fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = offerPackage.packages.size

    override fun createFragment(position: Int): Fragment =
        OfferTabFragment.newInstance(offerPackage.packages[position])
}

