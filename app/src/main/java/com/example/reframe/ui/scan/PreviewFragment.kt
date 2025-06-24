package com.example.reframe.ui.scan

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.example.reframe.data.SessionManager
import com.example.reframe.databinding.ActivityPreviewBinding

class PreviewFragment : Fragment() {

    private var _binding: ActivityPreviewBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ScanViewModel by viewModels()
    private var imageUri: Uri? = null

    // Fragment 생성 시 데이터를 전달받기 위한 newInstance 패턴
    companion object {
        private const val ARG_IMAGE_URI = "imageUri"

        fun newInstance(uri: Uri): PreviewFragment {
            val fragment = PreviewFragment()
            val args = Bundle()
            args.putString(ARG_IMAGE_URI, uri.toString())
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = ActivityPreviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString(ARG_IMAGE_URI)?.let {
            imageUri = Uri.parse(it)
            binding.previewImageView.load(imageUri)
        }

        binding.retakeButton.setOnClickListener { parentFragmentManager.popBackStack() }

        binding.registerButton.setOnClickListener {
            val memberId = SessionManager.getMemberId(requireContext())
            if (imageUri != null) {
                viewModel.uploadReceipt(requireContext(), memberId, imageUri!!)
            }
            // else 토스트 제거
        }
    }

    private fun observeViewModel() {
        viewModel.uploadState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {
                    binding.progressBar.isVisible = true
                    binding.registerButton.isEnabled = false
                }
                is UiState.Success -> {
                    binding.progressBar.isVisible = false
                    Toast.makeText(requireContext(), "업로드 성공!", Toast.LENGTH_SHORT).show()
                    val purchaseId = state.data.purchaseId // 오류 해결

                    // 부모 Activity의 함수를 호출하여 화면 전환
                    (activity as? ScanActivity)?.navigateToReward(purchaseId)
                }
                is UiState.Error -> {
                    binding.progressBar.isVisible = false
                    binding.registerButton.isEnabled = true
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}