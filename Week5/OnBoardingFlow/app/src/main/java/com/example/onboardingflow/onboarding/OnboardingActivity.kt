package com.example.onboardingflow.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.viewpager2.widget.ViewPager2
import com.example.onboardingflow.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import android.view.View
import android.widget.TextView

class OnboardingActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var btnNext: Button
    private lateinit var btnBack: Button
    private lateinit var tvSkip: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        initViews()
        setupViewPager()
        setupListeners()
    }

    private fun initViews() {
        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabLayout)
        btnNext = findViewById(R.id.btnNext)
        btnBack = findViewById(R.id.btnBack)
        tvSkip = findViewById<TextView>(R.id.tvSkip)
    }

    private fun setupViewPager() {
        val adapter = OnboardingPagerAdapter(this)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
        }.attach()

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                updateButtonText(position)
            }
        })
    }

    private fun setupListeners() {
        btnNext.setOnClickListener {
            val currentItem = viewPager.currentItem
            if (currentItem < 3) {
                viewPager.currentItem = currentItem + 1
            } else {
                viewPager.currentItem = 3
            }
        }
        btnBack.setOnClickListener {
            val currentItem = viewPager.currentItem
            if (currentItem > 0) {
                viewPager.currentItem = currentItem - 1
            }
        }
        tvSkip.setOnClickListener {
            viewPager.currentItem = 3
        }
    }

    private fun updateButtonText(position: Int) {
        when (position) {
            0 -> {
                btnBack.visibility = View.GONE
                btnNext.visibility = View.VISIBLE
                btnNext.text = "Next"
            }
            1 -> {
                btnBack.visibility = View.VISIBLE
                btnNext.visibility = View.VISIBLE
                btnNext.text = "Next"
            }
            2 -> {
                btnBack.visibility = View.VISIBLE
                btnNext.visibility = View.VISIBLE
                btnNext.text = "Get Started"
            }
            3 -> {
                btnBack.visibility = View.GONE
                btnNext.visibility = View.GONE
                tvSkip.visibility = View.GONE
            }
        }
    }

}