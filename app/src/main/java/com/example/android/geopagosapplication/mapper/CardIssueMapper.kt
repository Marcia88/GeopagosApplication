package com.example.android.geopagosapplication.mapper

import com.example.android.geopagosapplication.dto.cardissues.CardIssueDTO
import com.example.android.geopagosapplication.model.CardIssue

class CardIssueMapper : ListMapper<CardIssueDTO, CardIssue> {

    private fun map(input: CardIssueDTO): CardIssue {
        return CardIssue(
            input.id,
            input.name,
            input.secure_thumbnail
        )
    }

    override fun map(input: List<CardIssueDTO>): List<CardIssue> {
        val cardIssues = mutableListOf<CardIssue>()
        input.forEach {
            cardIssues.add(map(it))
        }
        return cardIssues
    }
}