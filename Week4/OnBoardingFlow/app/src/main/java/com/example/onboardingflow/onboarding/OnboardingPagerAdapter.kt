package com.example.onboardingflow.onboarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.onboardingflow.onboarding.fragments.OnboardingFragment1
import com.example.onboardingflow.onboarding.fragments.OnboardingFragment2
import com.example.onboardingflow.onboarding.fragments.OnboardingFragment3
import com.example.onboardingflow.onboarding.fragments.OnboardingFragment4

class OnboardingPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OnboardingFragment1()
            1 -> OnboardingFragment2()
            2 -> OnboardingFragment3()
            3 -> OnboardingFragment4()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }
}