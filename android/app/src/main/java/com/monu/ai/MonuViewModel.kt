package com.monu.ai

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

data class MonuUiState(
    val conversations: List<ConversationEntity> = emptyList(),
    val messages: List<MessageEntity> = emptyList(),
    val selectedConversationId: Long? = null,
    val searchQuery: String = "",
    val isDrawerOpen: Boolean = false,
    val isPlusMenuOpen: Boolean = false,
    val currentScreen: String = "CHAT"
)

class MonuViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository = MonuRepository(application)

    private val brain = MonuBrain()

    private val _state =
        MutableStateFlow(MonuUiState())

    val state: StateFlow<MonuUiState> =
        _state.asStateFlow()

    init {
        observeConversations()
        createInitialChat()
    }

    private fun observeConversations() {

        viewModelScope.launch {

            repository.conversations()
                .collectLatest { chats ->

                    _state.value =
                        _state.value.copy(
                            conversations = chats
                        )
                }
        }
    }

    private fun observeMessages(
        conversationId: Long
    ) {

        viewModelScope.launch {

            repository.messages(conversationId)
                .collectLatest { messages ->

                    _state.value =
                        _state.value.copy(
                            messages = messages,
                            selectedConversationId =
                                conversationId
                        )
                }
        }
    }

    private fun createInitialChat() {

        viewModelScope.launch {

            val chats = _state.value.conversations

            if (
                chats.isEmpty() &&
                _state.value.selectedConversationId == null
            ) {

                val id =
                    repository.newConversation()

                observeMessages(id)
            }
        }
    }

    fun newChat() {

        viewModelScope.launch {

            val id =
                repository.newConversation()

            observeMessages(id)

            _state.value =
                _state.value.copy(
                    isDrawerOpen = false
                )
        }
    }

    fun selectChat(
        conversationId: Long
    ) {

        observeMessages(conversationId)

        _state.value =
            _state.value.copy(
                isDrawerOpen = false,
                currentScreen = "CHAT"
            )
    }

    fun sendMessage(
        text: String
    ) {

        val conversationId =
            _state.value.selectedConversationId
                ?: return

        if (text.isBlank()) return

        viewModelScope.launch {

            repository.addMessage(
                conversationId = conversationId,
                role = "user",
                content = text,
                route = "user"
            )

            val decision =
                brain.decide(text)

            val response =
                buildMonuResponse(
                    text,
                    decision
                )

            repository.addMessage(
                conversationId = conversationId,
                role = "assistant",
                content = response,
                route = decision.routes.joinToString()
            )
        }
    }

    private fun buildMonuResponse(
        input: String,
        decision: BrainDecision
    ): String {

        return when (
            decision.action
        ) {

            MonuAction.REMEMBER ->
                "मैंने इस जानकारी को MONU की permanent memory workflow के लिए तैयार कर लिया है।"

            MonuAction.SEARCH ->
                "मैंने आपके प्रश्न को MONU Server, Local Memory, Gemini और Wikipedia routing pipeline में भेज दिया है।"

            MonuAction.IMAGE ->
                "Image processing module चयनित है।"

            MonuAction.VIDEO ->
                "Video processing module चयनित है।"

            MonuAction.MUSIC ->
                "Music module चयनित है।"

            MonuAction.CANVAS ->
                "Canvas module चयनित है।"

            MonuAction.LEARNING ->
                "Guided Learning module चयनित है।"

            else ->
                "मैं MONU हूँ। आपका संदेश Central AI Brain द्वारा process किया गया है।"
        }
    }

    fun toggleDrawer() {

        _state.value =
            _state.value.copy(
                isDrawerOpen =
                    !_state.value.isDrawerOpen
            )
    }

    fun closeDrawer() {

        _state.value =
            _state.value.copy(
                isDrawerOpen = false
            )
    }

    fun togglePlusMenu() {

        _state.value =
            _state.value.copy(
                isPlusMenuOpen =
                    !_state.value.isPlusMenuOpen
            )
    }

    fun closePlusMenu() {

        _state.value =
            _state.value.copy(
                isPlusMenuOpen = false
            )
    }

    fun openScreen(
        screen: String
    ) {

        _state.value =
            _state.value.copy(
                currentScreen = screen,
                isDrawerOpen = false,
                isPlusMenuOpen = false
            )
    }

    fun searchChats(
        query: String
    ) {

        _state.value =
            _state.value.copy(
                searchQuery = query
            )

        viewModelScope.launch {

            repository.searchChats(query)
                .collectLatest { chats ->

                    _state.value =
                        _state.value.copy(
                            conversations = chats
                        )
                }
        }
    }

    fun togglePin(
        conversation: ConversationEntity
    ) {

        viewModelScope.launch {

            repository.togglePin(
                conversation
            )
        }
    }

    fun deleteChat(
        conversation: ConversationEntity
    ) {

        viewModelScope.launch {

            repository.deleteConversation(
                conversation
            )

            if (
                _state.value.selectedConversationId ==
                conversation.id
            ) {

                newChat()
            }
        }
    }

    fun regenerate(
        message: MessageEntity
    ) {

        val conversationId =
            _state.value.selectedConversationId
                ?: return

        viewModelScope.launch {

            repository.addMessage(
                conversationId = conversationId,
                role = "assistant",
                content =
                    "Regenerated response: MONU is processing your request again through the Central AI Brain.",
                route = "regenerate"
            )
        }
    }
}
