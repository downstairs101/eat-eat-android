package com.downstairs.tosplit.user.data

data class UserUiModel(val name: String) {

    fun toDomain(): User {
        return User(name)
    }
}
