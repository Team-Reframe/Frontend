package com.example.reframe.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.reframe.databinding.ActivityPasswordChangeBinding

class PasswordChangeFragment : Fragment() {

    private var _binding: ActivityPasswordChangeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = ActivityPasswordChangeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupListeners()
    }

    private fun setupToolbar() {
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
        }
        binding.toolbar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun setupListeners() {
        binding.btnChangePassword.setOnClickListener {
            validateAndChangePassword()
        }
    }

    private fun validateAndChangePassword() {
        val currentPassword = binding.etCurrentPassword.text.toString()
        val newPassword = binding.etNewPassword.text.toString()
        val confirmPassword = binding.etConfirmPassword.text.toString()

        binding.tilCurrentPassword.error = null
        binding.tilNewPassword.error = null
        binding.tilConfirmPassword.error = null

        if (currentPassword.isEmpty()) {
            binding.tilCurrentPassword.error = "현재 비밀번호를 입력해주세요."
            return
        }
        if (newPassword.length < 7) {
            binding.tilNewPassword.error = "비밀번호는 7자리 이상이어야 합니다."
            return
        }
        if (newPassword != confirmPassword) {
            binding.tilConfirmPassword.error = "새 비밀번호가 일치하지 않습니다."
            return
        }

        Toast.makeText(requireContext(), "비밀번호 변경이 요청되었습니다.", Toast.LENGTH_SHORT).show()
        parentFragmentManager.popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}