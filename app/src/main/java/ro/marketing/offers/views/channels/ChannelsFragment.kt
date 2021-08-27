package ro.marketing.offers.views.channels

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ro.marketing.offers.R
import ro.marketing.offers.databinding.FragmentChannelsBinding
import ro.marketing.offers.views.channels.adapter.ChannelsAdapter

@AndroidEntryPoint
class ChannelsFragment : Fragment() {

    private var binding: FragmentChannelsBinding? = null
    private val channelsBinding get() = binding!!
    private val navController
        get() = findNavController()
    private val channelsViewModel: ChannelsViewModel by viewModels()
    private val adapter by lazy { ChannelsAdapter(requireContext(), ::setButtonEnabled) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChannelsBinding.inflate(inflater, container, false)
        return channelsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        setupChannels()
        setupListeners()
    }

    private fun setButtonEnabled() {
        channelsBinding.btnContinue.isEnabled = adapter.channelList.any { it.isSelected }
    }

    private fun setupListeners() {

        channelsBinding.btnContinue.setOnClickListener {
            val action = ChannelsFragmentDirections.actionChannelFragmentToOffersFragment(
                adapter.channelList.filter {
                    it.isSelected
                }.flatMap { it.channels }.toTypedArray()
            )
            navController.navigate(action)
        }
    }


    private fun setupChannels() {
        channelsBinding.btnContinue.isEnabled = false
        navController.navigate(R.id.loadingDialog)
        channelsViewModel.getSpecifics()

        channelsBinding.selectSpecificsRecyclerView.layoutManager =
            GridLayoutManager(requireContext(), 2)
        channelsBinding.selectSpecificsRecyclerView.adapter = adapter

        channelsViewModel.specifics.observe(viewLifecycleOwner) {
            navController.popBackStack()
            adapter.channelList = it
        }
        channelsViewModel.error.observe(viewLifecycleOwner) {
            if (it) navController.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}