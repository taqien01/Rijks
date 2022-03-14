package id.reza.rijks.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import id.reza.rijks.databinding.FragmentProfileBinding
import id.reza.rijks.ui.login.LoginActivity
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val vm: ProfileViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        vm.getUser()
        initListener()
        initObserver()
        return root
    }

    private fun initListener() {
        binding.btnLogout.setOnClickListener {
            vm.logout()
        }
    }

    private fun initObserver() {
        vm.errorEvent.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), "$it", Toast.LENGTH_LONG).show()
        }

        vm.userEvent.observe(viewLifecycleOwner){
            binding.txtUsername.text = it.username
        }

        vm.logoutEvent.observe(viewLifecycleOwner) {
            val i = Intent(requireContext(), LoginActivity::class.java)
            startActivity(i)
            requireActivity().finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}