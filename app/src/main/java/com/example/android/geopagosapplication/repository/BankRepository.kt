package com.example.android.geopagosapplication.repository

import com.example.android.geopagosapplication.api.ApiHelper
import com.example.android.geopagosapplication.dto.cardissues.CardIssueDTO
import com.example.android.geopagosapplication.mapper.CardIssueMapper
import com.example.android.geopagosapplication.model.CardIssue
import retrofit2.Response

class BankRepository(private val apiHelper: ApiHelper) {
    private val cardIssueMapper = CardIssueMapper()

    suspend fun getCardIssuers(paymentMethodId: String) = apiHelper.getCardIssues(paymentMethodId)

    fun mapCardIssues(cardIssues: Response<List<CardIssueDTO>>): List<CardIssue> {
        return cardIssueMapper.map(cardIssues.body().orEmpty())
    }
}