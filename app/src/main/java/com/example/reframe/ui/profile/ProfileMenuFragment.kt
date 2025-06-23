package com.example.reframe.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.reframe.ProfileActivity
import com.example.reframe.databinding.FragmentProfileMenuBinding

class ProfileMenuFragment : Fragment() {

    private var _binding: FragmentProfileMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
    }

    private fun setupClickListeners() {
        val parentActivity = activity as? ProfileActivity

        binding.tvMyInfo.setOnClickListener {
            parentActivity?.replaceFragment(MyInfoFragment())
        }
        binding.tvPointHistory.setOnClickListener {
            parentActivity?.replaceFragment(PointHistoryFragment())
        }
        binding.tvPasswordChange.setOnClickListener {
            parentActivity?.replaceFragment(PasswordChangeFragment())
        }
        binding.tvWithdrawal.setOnClickListener {
            parentActivity?.replaceFragment(WithdrawalFragment())
        }
        binding.tvLogout.setOnClickListener {
            parentActivity?.showLogoutDialog()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}