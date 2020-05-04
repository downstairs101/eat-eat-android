package com.downstairs.split.data

import com.downstairs.split.Split

data class SplitUiModel(val payerName: String, val value: String) {

    companion object {

        fun fromDomain(split: Split): SplitUiModel {
            return SplitUiModel(
                split.payer.name,
                "R$ ${split.value}"
            )
        }
    }
}