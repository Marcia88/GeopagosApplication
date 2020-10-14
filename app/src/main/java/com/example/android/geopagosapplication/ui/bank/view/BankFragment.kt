package com.example.android.geopagosapplication.ui.bank.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.geopagosapplication.R
import com.example.android.geopagosapplication.databinding.FragmentBankListBinding
import com.example.android.geopagosapplication.model.CardIssue
import com.example.android.geopagosapplication.ui.bank.viewmodel.BankViewModel
import com.example.android.geopagosapplication.utils.Status
import org.koin.android.viewmodel.ext.android.viewModel

class BankFragment : Fragment(), CardIssuesAdapter.OnCardIssueSelected {
    private val bankViewModel: BankViewModel by viewModel()
    private lateinit var binding: FragmentBankListBinding
    private lateinit var cardIssuesAdapter: CardIssuesAdapter

    private fun setupObserver() {
        bankViewModel.cardIssues.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { cardIssues -> renderList(cardIssues) }
                    binding.recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                    hideNetworkError()
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    showNetworkError()
                }
            }
        })
    }

    private fun renderList(cardIssues: List<CardIssue>) {
        cardIssuesAdapter.addCardIssues(cardIssues)
        cardIssuesAdapter.notifyDataSetChanged()
    }

    private fun setupPaymentMethodId() {
        bankViewModel.fetchCardIssues()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        cardIssuesAdapter = CardIssuesAdapter(mutableListOf(), this)
        binding.recyclerView.adapter = cardIssuesAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_bank_list,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.viewModel = bankViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPaymentMethodId()
        setupRecyclerView()
        setupObserver()
    }

    override fun onClick(cardIssue: CardIssue) {
        bankViewModel.onCardIssueSelected(cardIssue)
        findNavController().navigate(R.id.action_bankFragment_to_installmentFragment)
    }

    private fun hideNetworkError() {
        binding.error.visibility = View.GONE
        binding.retry.visibility = View.GONE
    }

    private fun showNetworkError() {
        binding.error.visibility = View.VISIBLE
        binding.retry.visibility = View.VISIBLE
    }
}