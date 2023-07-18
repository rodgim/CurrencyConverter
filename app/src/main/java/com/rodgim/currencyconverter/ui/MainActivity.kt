package com.rodgim.currencyconverter.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.rodgim.currencyconverter.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnConvert.setOnClickListener {
            viewModel.convert(
                binding.etFrom.text.toString(),
                binding.spFromCurrency.selectedItem.toString(),
                binding.spToCurrency.selectedItem.toString()
            )
        }

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    when(uiState) {
                        is UiState.Success -> {
                            binding.progressBar.isVisible = false
                            binding.tvResult.setTextColor(Color.BLACK)
                            binding.tvResult.text = uiState.resultText
                        }
                        is UiState.Failure -> {
                            binding.progressBar.isVisible = false
                            binding.tvResult.setTextColor(Color.RED)
                            binding.tvResult.text = uiState.errorText
                        }
                        UiState.Loading -> {
                            binding.progressBar.isVisible = true
                        }
                        UiState.Empty -> Unit
                    }
                }
            }
        }
    }
}