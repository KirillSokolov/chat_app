package com.test.data.mappers

import com.data.ChatEntity
import com.test.domain.models.chat.Chat


fun ChatEntity.toDomain(): Chat {
    return Chat(
        id = id,
        name = name,
        membersCount = member.toString()
    )
}