package ro.marketing.offers.views.offers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ro.marketing.offers.databinding.FragmentOffersBinding
import ro.marketing.offers.views.offers.adapter.OffersAdapter
import javax.inject.Inject

@AndroidEntryPoint
class OffersFragment : Fragment() {

    private var binding: FragmentOffersBinding? = null
    private val offersBinding get() = binding!!
    private val navController
        get() = findNavController()
    private val offersViewModel: OffersViewModel by activityViewModels()
    private val channels by lazy {
        navArgs<OffersFragmentArgs>().value.channels
    }

    @Inject
    lateinit var offersAdapter: OffersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOffersBinding.inflate(inflater, container, false)
        return offersBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchOffers()
    }

    private fun fetchOffers() {
        offersViewModel.getOffers(channels.toList())
        offersBinding.offersChannelRecyclerView.layoutManager = LinearLayoutManager(context)
        offersBinding.offersChannelRecyclerView.adapter = offersAdapter
        offersAdapter.setOnItemClickListener {
            val action = OffersFragmentDirections.actionOffersFragmentToChooseOfferFragment(it)
            navController.navigate(action)
        }
        offersViewModel.offers.observe(viewLifecycleOwner)
        {
            offersAdapter.offersPackages = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}