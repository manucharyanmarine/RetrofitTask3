package com.example.task3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task3.data.CatAdapter
import com.example.task3.databinding.ActivityMainBinding
import com.example.task3.entity.Cat
import com.example.task3.viewModel.CatViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val catAdapter = CatAdapter()
    private val viewModel by viewModel<CatViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()
    }

    private fun initAdapter() {
        with(binding) {
            rvList.apply {
                adapter = catAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
        }

        lifecycleScope.launch {
            viewModel.catListR.collectLatest {
                catAdapter.submitList(it)
            }
        }
    }
}