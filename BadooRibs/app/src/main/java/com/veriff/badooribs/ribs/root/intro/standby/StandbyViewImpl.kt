package com.veriff.badooribs.ribs.root.intro.standby

import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.badoo.ribs.core.view.AndroidRibView
import com.jakewharton.rxrelay2.PublishRelay
import com.veriff.badooribs.databinding.ItemVerificationConfigBinding
import com.veriff.badooribs.databinding.RibStandbyBinding
import com.veriff.badooribs.domain.model.MockVerificationConfig
import com.veriff.badooribs.util.inflater
import io.reactivex.ObservableSource

internal class StandbyViewImpl(
    private val binding: RibStandbyBinding,
    private val events: PublishRelay<StandbyView.Event> = PublishRelay.create(),
) :
    AndroidRibView(),
    StandbyView,
    ObservableSource<StandbyView.Event> by events {

    override val androidView: ViewGroup
        get() = binding.root

    init {
        binding.startButton.setOnClickListener {
            events.accept(StandbyView.Event.StartClicked)
        }

        binding.configPicker.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                events.accept(StandbyView.Event.ScenarioSelected(position))
            }
        })
    }

    override fun accept(viewModel: StandbyView.ViewModel) {
        when (viewModel) {
            is StandbyView.ViewModel.Loading -> {
                binding.startButton.isEnabled = false
                binding.loading.isVisible = true
            }

            is StandbyView.ViewModel.Loaded -> {
                binding.startButton.isEnabled = true
                binding.loading.isVisible = false
                bindConfigs(viewModel.configs, viewModel.selectedIndex)
            }
        }
    }

    private fun bindConfigs(configs: List<MockVerificationConfig>, selectedIndex: Int) {
        val picker = binding.configPicker
        if (picker.adapter?.itemCount != configs.size) {
            binding.configPicker.adapter = ConfigAdapter(configs)
        }
        if (picker.currentItem != selectedIndex) {
            picker.currentItem = selectedIndex
        }
    }

    private class ConfigAdapter(var items: List<MockVerificationConfig>) :
        RecyclerView.Adapter<ConfigHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConfigHolder {
            return ConfigHolder(ItemVerificationConfigBinding.inflate(parent.inflater(), parent, false))
        }

        override fun onBindViewHolder(holder: ConfigHolder, position: Int) {
            val item = items[position]
            with(holder.binding) {
                title.text = item.title
                description.text = item.description
            }
        }

        override fun getItemCount(): Int = items.size
    }

    private class ConfigHolder(val binding: ItemVerificationConfigBinding) :
        RecyclerView.ViewHolder(binding.root)
}
