@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.monu.ai

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    private val viewModel:
        MonuViewModel by viewModels()

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)

        try {
            MonuRuntimeController.startCoreServices(this)
        } catch (_: Exception) {
        }

        setContent {

            MaterialTheme {

                val state by
                    viewModel.state.collectAsState()

                MonuApp(
                    state = state,
                    viewModel = viewModel
                )
            }
        }
    }
}

@Composable
fun MonuApp(
    state: MonuUiState,
    viewModel: MonuViewModel
) {

    ModalNavigationDrawer(
        drawerContent = {

            DrawerContent(
                state = state,
                viewModel = viewModel
            )
        },
        gesturesEnabled = state.isDrawerOpen,
        drawerState =
            rememberDrawerState(
                initialValue =
                    if (state.isDrawerOpen)
                        DrawerValue.Open
                    else
                        DrawerValue.Closed
            )
    ) {

        Scaffold(

            topBar = {

                TopAppBar(

                    title = {

                        Column {

                            Text(
                                "MONU AI",
                                fontWeight =
                                    FontWeight.Bold
                            )

                            Text(
                                "Central AI Brain",
                                fontSize = 11.sp
                            )
                        }
                    },

                    navigationIcon = {

                        IconButton(
                            onClick = {
                                viewModel.toggleDrawer()
                            }
                        ) {

                            Text(
                                "☰",
                                fontSize = 25.sp
                            )
                        }
                    },

                    actions = {

                        TextButton(
                            onClick = {
                                viewModel.openScreen(
                                    "CONNECTION"
                                )
                            }
                        ) {
                            Text("●")
                        }
                    }
                )
            },

            bottomBar = {

                MonuInputBar(
                    onSend = {
                        viewModel.sendMessage(it)
                    },
                    onPlus = {
                        viewModel.togglePlusMenu()
                    }
                )
            }

        ) { padding ->

            Box(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(padding)
            ) {

                when (
                    state.currentScreen
                ) {

                    "CHAT" ->
                        ChatScreen(
                            state = state,
                            viewModel = viewModel
                        )

                    else ->
                        ModuleScreen(
                            name =
                                state.currentScreen
                        )
                }

                if (
                    state.isPlusMenuOpen
                ) {

                    PlusMenu(
                        onSelect = {
                            viewModel.openScreen(it)
                        },
                        onClose = {
                            viewModel.closePlusMenu()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun DrawerContent(
    state: MonuUiState,
    viewModel: MonuViewModel
) {

    Column(
        modifier =
            Modifier
                .fillMaxHeight()
                .padding(16.dp)
    ) {

        Text(
            "MONU",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            "Owner: Sunil Rinwa",
            fontSize = 12.sp
        )

        Spacer(
            modifier =
                Modifier.height(20.dp)
        )

        Button(
            onClick = {
                viewModel.newChat()
            },
            modifier =
                Modifier.fillMaxWidth()
        ) {
            Text("+ New Chat")
        }

        Spacer(
            modifier =
                Modifier.height(12.dp)
        )

        OutlinedTextField(
            value = state.searchQuery,
            onValueChange = {
                viewModel.searchChats(it)
            },
            label = {
                Text("Search chats")
            },
            modifier =
                Modifier.fillMaxWidth()
        )

        Spacer(
            modifier =
                Modifier.height(12.dp)
        )

        val pinned =
            state.conversations.filter {
                it.pinned
            }

        val normal =
            state.conversations.filter {
                !it.pinned
            }

        if (
            pinned.isNotEmpty()
        ) {

            Text(
                "📌 PINNED",
                fontWeight =
                    FontWeight.Bold
            )

            ChatList(
                chats = pinned,
                viewModel = viewModel
            )
        }

        Text(
            "CHATS",
            fontWeight =
                FontWeight.Bold
        )

        ChatList(
            chats = normal,
            viewModel = viewModel
        )

        Spacer(
            modifier =
                Modifier.weight(1f)
        )

        DrawerModule(
            "Images"
        ) {
            viewModel.openScreen(
                "IMAGES"
            )
        }

        DrawerModule(
            "Videos"
        ) {
            viewModel.openScreen(
                "VIDEOS"
            )
        }

        DrawerModule(
            "Library"
        ) {
            viewModel.openScreen(
                "LIBRARY"
            )
        }

        DrawerModule(
            "Notebooks"
        ) {
            viewModel.openScreen(
                "NOTEBOOKS"
            )
        }
    }
}

@Composable
fun DrawerModule(
    name: String,
    action: () -> Unit
) {

    Text(
        text = name,
        modifier =
            Modifier
                .fillMaxWidth()
                .clickable {
                    action()
                }
                .padding(
                    vertical = 10.dp
                ),
        fontSize = 16.sp
    )
}

@Composable
fun ChatList(
    chats: List<ConversationEntity>,
    viewModel: MonuViewModel
) {

    LazyColumn(
        modifier =
            Modifier.heightIn(
                max = 250.dp
            )
    ) {

        items(
            chats,
            key = { it.id }
        ) { chat ->

            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .clickable {
                            viewModel.selectChat(
                                chat.id
                            )
                        }
                        .padding(
                            vertical = 8.dp
                        ),
                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Text(
                    if (chat.pinned)
                        "📌"
                    else
                        "💬"
                )

                Spacer(
                    modifier =
                        Modifier.width(8.dp)
                )

                Text(
                    chat.title,
                    modifier =
                        Modifier.weight(1f),
                    maxLines = 1
                )

                Text(
                    "⋮",
                    modifier =
                        Modifier.clickable {
                            viewModel.togglePin(
                                chat
                            )
                        }
                )
            }
        }
    }
}

@Composable
fun ChatScreen(
    state: MonuUiState,
    viewModel: MonuViewModel
) {

    LazyColumn(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(12.dp),
        verticalArrangement =
            Arrangement.spacedBy(
                10.dp
            )
    ) {

        items(
            state.messages,
            key = { it.id }
        ) { message ->

            MessageBubble(
                message = message,
                viewModel = viewModel
            )
        }
    }
}

@Composable
fun MessageBubble(
    message: MessageEntity,
    viewModel: MonuViewModel
) {

    val context =
        LocalContext.current

    val clipboard =
        LocalClipboardManager.current

    val isUser =
        message.role == "user"

    Column(
        modifier =
            Modifier.fillMaxWidth(),
        horizontalAlignment =
            if (isUser)
                Alignment.End
            else
                Alignment.Start
    ) {

        Surface(
            shape =
                RoundedCornerShape(
                    18.dp
                ),
            tonalElevation = 2.dp
        ) {

            Text(
                text = message.content,
                modifier =
                    Modifier.padding(14.dp),
                fontSize = 16.sp
            )
        }

        if (!isUser) {

            Row {

                TextButton(
                    onClick = {

                        clipboard.setText(
                            AnnotatedString(
                                message.content
                            )
                        )

                        Toast
                            .makeText(
                                context,
                                "Copied",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    }
                ) {
                    Text("📋")
                }

                TextButton(
                    onClick = {

                        var tts: TextToSpeech? = null

                        tts =
                            TextToSpeech(
                                context
                            ) { status ->

                                if (
                                    status ==
                                    TextToSpeech.SUCCESS
                                ) {

                                    tts?.speak(
                                        message.content,
                                        TextToSpeech.QUEUE_FLUSH,
                                        null,
                                        "monu_message"
                                    )
                                }
                            }
                    }
                ) {
                    Text("🔊")
                }

                TextButton(
                    onClick = {

                        val share =
                            Intent(
                                Intent.ACTION_SEND
                            ).apply {

                                type = "text/plain"

                                putExtra(
                                    Intent.EXTRA_TEXT,
                                    message.content
                                )
                            }

                        context.startActivity(
                            Intent.createChooser(
                                share,
                                "Share MONU response"
                            )
                        )
                    }
                ) {
                    Text("↗")
                }

                TextButton(
                    onClick = {
                        viewModel.regenerate(
                            message
                        )
                    }
                ) {
                    Text("↻")
                }
            }
        }
    }
}

@Composable
fun MonuInputBar(
    onSend: (String) -> Unit,
    onPlus: () -> Unit
) {

    var text by remember {
        mutableStateOf("")
    }

    Column {

        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 12.dp
                    ),
            horizontalArrangement =
                Arrangement.SpaceEvenly
        ) {

            TextButton(
                onClick = {}
            ) {
                Text("Photos")
            }

            TextButton(
                onClick = {}
            ) {
                Text("Camera")
            }

            TextButton(
                onClick = {}
            ) {
                Text("Avatar")
            }
        }

        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
            verticalAlignment =
                Alignment.CenterVertically
        ) {

            IconButton(
                onClick = onPlus
            ) {
                Text(
                    "+",
                    fontSize = 28.sp
                )
            }

            OutlinedTextField(
                value = text,
                onValueChange = {
                    text = it
                },
                placeholder = {
                    Text(
                        "Ask MONU..."
                    )
                },
                modifier =
                    Modifier.weight(1f)
            )

            IconButton(
                onClick = {

                    if (
                        text.isNotBlank()
                    ) {

                        onSend(text)
                        text = ""
                    }
                }
            ) {
                Text("➤")
            }
        }
    }
}

@Composable
fun PlusMenu(
    onSelect: (String) -> Unit,
    onClose: () -> Unit
) {

    Surface(
        modifier =
            Modifier
                .fillMaxSize()
                .clickable {
                    onClose()
                }
                .padding(20.dp),
        color =
            MaterialTheme
                .colorScheme
                .surfaceVariant,
        shape =
            RoundedCornerShape(
                20.dp
            )
    ) {

        Column(
            modifier =
                Modifier.padding(20.dp)
        ) {

            Text(
                "MONU TOOLS",
                fontSize = 22.sp,
                fontWeight =
                    FontWeight.Bold
            )

            Spacer(
                modifier =
                    Modifier.height(12.dp)
            )

            val tools =
                listOf(
                    "Files",
                    "Drive",
                    "Notebooks",
                    "Images",
                    "Video",
                    "Music",
                    "Canvas",
                    "Guided Learning",
                    "Personal Intelligence"
                )

            tools.forEach { tool ->

                Button(
                    onClick = {
                        onSelect(
                            tool.uppercase()
                        )
                    },
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(
                                vertical = 3.dp
                            )
                ) {
                    Text(tool)
                }
            }
        }
    }
}

@Composable
fun ModuleScreen(
    name: String
) {

    Box(
        modifier =
            Modifier.fillMaxSize(),
        contentAlignment =
            Alignment.Center
    ) {

        Column(
            horizontalAlignment =
                Alignment.CenterHorizontally
        ) {

            Text(
                name,
                fontSize = 28.sp,
                fontWeight =
                    FontWeight.Bold
            )

            Spacer(
                modifier =
                    Modifier.height(10.dp)
            )

            Text(
                "MONU Central AI Brain module"
            )
        }
    }
}
