package com.example.reframe.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.reframe.R
import com.example.reframe.data.SessionManager
import com.example.reframe.data.dto.UserInfoResponse
import com.example.reframe.databinding.ActivityMyInfoBinding // 바인딩 파일은 그대로 사용
import com.example.reframe.ui.scan.UiState

class MyInfoFragment : Fragment() {

    private var _binding: ActivityMyInfoBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ActivityMyInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        binding.fabEditProfileImage.setOnClickListener {
            // 프로필 이미지 편집 로직
        }

        observeViewModel()

        val memberId = SessionManager.getMemberId(requireContext())
        if (memberId != -1L) {
            viewModel.fetchMyInfo(memberId)
        } else {
            Toast.makeText(requireContext(), "사용자 정보를 불러올 수 없습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupToolbar() {
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarMyInfo)
        (activity as AppCompatActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
        }
        binding.toolbarMyInfo.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun observeViewModel() {
        viewModel.userInfo.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> { /* 로딩 인디케이터 표시 */ }
                is UiState.Success -> {
                    updateUi(state.data)
                }
                is UiState.Error -> {
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun updateUi(userInfo: UserInfoResponse) {
        (binding.toolbarTitleContainer.findViewById<TextView>(R.id.tv_toolbar_user_name)).text = "${userInfo.name}님의 정보"

        // binding.itemName 대신 binding.itemName.root 를 전달합니다.
        setupInfoItem(binding.itemName.root, "이름", userInfo.name)
        setupInfoItem(binding.itemEnglishName.root, "영문이름", userInfo.englishName ?: "없음")
        setupInfoItem(binding.itemBirthDate.root, "생년월일", userInfo.birthDate ?: "없음")
        setupInfoItem(binding.itemPhoneNumber.root, "휴대폰 번호", userInfo.phoneNumber ?: "없음")
        setupInfoItem(binding.itemEmail.root, "이메일 주소", userInfo.email)
        setupInfoItem(binding.itemPassword.root, "비밀번호", "••••••••••")
    }

    private fun setupInfoItem(itemView: View, label: String, value: String) {
        itemView.findViewById<TextView>(R.id.tv_label).text = label
        itemView.findViewById<TextView>(R.id.tv_value).text = value
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}