package ro.marketing.offers.views.chooseOffer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import ro.marketing.offers.databinding.FragmentChooseOfferBinding
import ro.marketing.offers.views.chooseOffer.adapter.ChooseOffersAdapter
import ro.marketing.offers.views.offers.OffersViewModel

@AndroidEntryPoint
class ChooseOfferFragment : Fragment() {

    private var binding: FragmentChooseOfferBinding? = null
    private val chooseOffersBinding get() = binding!!
    private val position by lazy {
        navArgs<ChooseOfferFragmentArgs>().value.position
    }
    private val navController
        get() = findNavController()
    private val offersViewModel: OffersViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentChooseOfferBinding.inflate(inflater, container, false)
        return chooseOffersBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        offersViewModel.offers.value?.get(position)?.let { offers ->
            val adapter =
                ChooseOffersAdapter(offers, this)
            chooseOffersBinding.pager.adapter = adapter
            TabLayoutMediator(
                chooseOffersBinding.tabLayout,
                chooseOffersBinding.pager
            ) { tab, position ->
                tab.text = "EUR ${offers.packages[position].fee.toString()}"
            }.attach()
        }
        chooseOffersBinding.btnSelectOffer.setOnClickListener {
            val action = ChooseOfferFragmentDirections.actionChooseOfferFragmentToReviewFragment(
                position, chooseOffersBinding.pager.currentItem
            )
            navController.navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}