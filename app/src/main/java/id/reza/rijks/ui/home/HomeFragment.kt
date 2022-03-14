package id.reza.rijks.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.reza.rijks.databinding.FragmentHomeBinding
import id.reza.rijks.model.ArtObjects
import id.reza.rijks.model.Data
import id.reza.rijks.ui.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), ListMovieAdapter.Interface {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val vm : HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.progressBar.visibility = View.VISIBLE

        vm.getData()
        initObserver()
        return root
    }

    private fun initObserver() {
        vm.loadingEvent.observe(viewLifecycleOwner) {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }

        vm.errorEvent.observe(viewLifecycleOwner){
            binding.rvList.visibility = View.GONE
            binding.rlTrouble.visibility = View.VISIBLE
        }

        vm.dataEvent.observe(viewLifecycleOwner){
            binding.rvList.visibility = View.VISIBLE
            binding.rlTrouble.visibility = View.GONE

            setData(it)
        }
    }

    private fun setData(data: Data) {
        binding.progressBar.visibility = View.GONE

        if (data.count <= 0){
            binding.rvList.visibility = View.GONE
            binding.rlTrouble.visibility = View.VISIBLE
        }else{
            binding.rvList.visibility = View.VISIBLE
            binding.rlTrouble.visibility = View.GONE

            binding.rvList.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

            var adapter = ListMovieAdapter(data.artObjects!!)

            binding.rvList.adapter = adapter
            adapter.notifyDataSetChanged()
            adapter.setInterface(this)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClickDetail(item: ArtObjects?, position: Int) {
        val i = Intent(requireActivity(), DetailActivity::class.java)
        i.putExtra("DATA", item)
        startActivity(i)
//        requireActivity().finish()
    }
}