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
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.reframe.databinding.FragmentPreviewBinding

class PreviewFragment : Fragment() {

    private var _binding: FragmentPreviewBinding? = null
    private val binding get() = _binding!!

    private val args: PreviewFragmentArgs by navArgs()
    private val viewModel: ScanViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPreviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageUri = Uri.parse(args.imageUri)
        binding.previewImageView.load(imageUri)

        binding.retakeButton.setOnClickListener { findNavController().popBackStack() }
        binding.registerButton.setOnClickListener {
            // 실제 로그인된 사용자 ID를 가져와야 함.
            val memberId = 1L
            viewModel.uploadReceipt(requireContext(), memberId, imageUri)
        }

        observeViewModel()
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
                    Toast.makeText(context, "업로드 성공!", Toast.LENGTH_SHORT).show()
                    val purchaseId = state.data.purchaseId
                    val action = PreviewFragmentDirections.actionPreviewFragmentToRewardFragment(purchaseId)
                    findNavController().navigate(action)
                }
                is UiState.Error -> {
                    binding.progressBar.isVisible = false
                    binding.registerButton.isEnabled = true
                    Toast.makeText(context, "업로드 실패: ${state.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}