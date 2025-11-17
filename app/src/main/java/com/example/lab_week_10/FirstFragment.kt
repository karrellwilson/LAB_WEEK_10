package com.example.lab_week_10

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.lab_week_10.viewmodels.TotalViewModel // Import ViewModel Anda

class FirstFragment : Fragment() {

    private lateinit var viewModel: TotalViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate layout untuk fragment ini
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Panggil prepareViewModel setelah view dibuat
        prepareViewModel()
    }

    private fun updateText(total: Int) {
        // Penting: Gunakan 'view?.findViewById' di dalam Fragment
        view?.findViewById<TextView>(R.id.text_total)?.text =
            getString(R.string.text_total, total)
    }

    // Ini adalah langkah 25, digabungkan ke sini
    private fun prepareViewModel() {
        // 1. Dapatkan ViewModel yang SAMA dengan Activity
        // 'requireActivity()' memberitahu ViewModelProvider untuk menggunakan
        // 'scope' (lingkup) dari Activity, bukan Fragment.
        viewModel = ViewModelProvider(requireActivity())[TotalViewModel::class.java]

        // 2. Amati (Observe) LiveData
        // 'viewLifecycleOwner' digunakan di Fragment, bukan 'this'
        viewModel.total.observe(viewLifecycleOwner) { total ->
            // Panggil updateText setiap kali data 'total' berubah
            updateText(total)
        }
    }
}