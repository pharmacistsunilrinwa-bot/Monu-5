# MONU FINAL-M COMPILATION CONTRACT REPORT

Generated: Sun Sep  6 22:26:48 IST 2026

## ERROR FILES

================================================
FILE: android/app/src/main/java/com/monu/ai/MainActivity.kt
================================================
     1	@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
     2	
     3	package com.monu.ai
     4	
     5	import android.content.Intent
     6	import android.os.Bundle
     7	import android.speech.tts.TextToSpeech
     8	import android.widget.Toast
     9	import androidx.activity.ComponentActivity
    10	import androidx.activity.compose.setContent
    11	import androidx.activity.viewModels
    12	import androidx.compose.foundation.background
    13	import androidx.compose.foundation.clickable
    14	import androidx.compose.foundation.layout.*
    15	import androidx.compose.foundation.lazy.LazyColumn
    16	import androidx.compose.foundation.lazy.items
    17	import androidx.compose.foundation.shape.RoundedCornerShape
    18	import androidx.compose.material3.*
    19	import androidx.compose.runtime.*
    20	import androidx.compose.ui.Alignment
    21	import androidx.compose.ui.Modifier
    22	import androidx.compose.ui.platform.LocalClipboardManager
    23	import androidx.compose.ui.platform.LocalContext
    24	import androidx.compose.ui.text.AnnotatedString
    25	import androidx.compose.ui.text.font.FontWeight
    26	import androidx.compose.ui.unit.dp
    27	import androidx.compose.ui.unit.sp
    28	
    29	class MainActivity : ComponentActivity() {
    30	
    31	    private val viewModel:
    32	        MonuViewModel by viewModels()
    33	
    34	    override fun onCreate(
    35	        savedInstanceState: Bundle?
    36	    ) {
    37	        super.onCreate(savedInstanceState)
    38	
    39	        try {
    40	            MonuRuntimeController.startCoreServices(this)
    41	        } catch (_: Exception) {
    42	        }
    43	
    44	        setContent {
    45	
    46	            MaterialTheme {
    47	
    48	                val state by
    49	                    viewModel.state.collectAsState()
    50	
    51	                MonuApp(
    52	                    state = state,
    53	                    viewModel = viewModel
    54	                )
    55	            }
    56	        }
    57	    }
    58	}
    59	
    60	@Composable
    61	fun MonuApp(
    62	    state: MonuUiState,
    63	    viewModel: MonuViewModel
    64	) {
    65	
    66	    ModalNavigationDrawer(
    67	        drawerContent = {
    68	
    69	            DrawerContent(
    70	                state = state,
    71	                viewModel = viewModel
    72	            )
    73	        },
    74	        gesturesEnabled = state.isDrawerOpen,
    75	        drawerState =
    76	            rememberDrawerState(
    77	                initialValue =
    78	                    if (state.isDrawerOpen)
    79	                        DrawerValue.Open
    80	                    else
    81	                        DrawerValue.Closed
    82	            )
    83	    ) {
    84	
    85	        Scaffold(
    86	
    87	            topBar = {
    88	
    89	                TopAppBar(
    90	
    91	                    title = {
    92	
    93	                        Column {
    94	
    95	                            Text(
    96	                                "MONU AI",
    97	                                fontWeight =
    98	                                    FontWeight.Bold
    99	                            )
   100	
   101	                            Text(
   102	                                "Central AI Brain",
   103	                                fontSize = 11.sp
   104	                            )
   105	                        }
   106	                    },
   107	
   108	                    navigationIcon = {
   109	
   110	                        IconButton(
   111	                            onClick = {
   112	                                viewModel.toggleDrawer()
   113	                            }
   114	                        ) {
   115	
   116	                            Text(
   117	                                "☰",
   118	                                fontSize = 25.sp
   119	                            )
   120	                        }
   121	                    },
   122	
   123	                    actions = {
   124	
   125	                        TextButton(
   126	                            onClick = {
   127	                                viewModel.openScreen(
   128	                                    "CONNECTION"
   129	                                )
   130	                            }
   131	                        ) {
   132	                            Text("●")
   133	                        }
   134	                    }
   135	                )
   136	            },
   137	
   138	            bottomBar = {
   139	
   140	                MonuInputBar(
   141	                    onSend = {
   142	                        viewModel.sendMessage(it)
   143	                    },
   144	                    onPlus = {
   145	                        viewModel.togglePlusMenu()
   146	                    }
   147	                )
   148	            }
   149	
   150	        ) { padding ->
   151	
   152	            Box(
   153	                modifier =
   154	                    Modifier
   155	                        .fillMaxSize()
   156	                        .padding(padding)
   157	            ) {
   158	
   159	                when (
   160	                    state.currentScreen
   161	                ) {
   162	
   163	                    "CHAT" ->
   164	                        ChatScreen(
   165	                            state = state,
   166	                            viewModel = viewModel
   167	                        )
   168	
   169	                    else ->
   170	                        ModuleScreen(
   171	                            name =
   172	                                state.currentScreen
   173	                        )
   174	                }
   175	
   176	                if (
   177	                    state.isPlusMenuOpen
   178	                ) {
   179	
   180	                    PlusMenu(
   181	                        onSelect = {
   182	                            viewModel.openScreen(it)
   183	                        },
   184	                        onClose = {
   185	                            viewModel.closePlusMenu()
   186	                        }
   187	                    )
   188	                }
   189	            }
   190	        }
   191	    }
   192	}
   193	
   194	@Composable
   195	fun DrawerContent(
   196	    state: MonuUiState,
   197	    viewModel: MonuViewModel
   198	) {
   199	
   200	    Column(
   201	        modifier =
   202	            Modifier
   203	                .fillMaxHeight()
   204	                .padding(16.dp)
   205	    ) {
   206	
   207	        Text(
   208	            "MONU",
   209	            fontSize = 28.sp,
   210	            fontWeight = FontWeight.Bold
   211	        )
   212	
   213	        Text(
   214	            "Owner: Sunil Rinwa",
   215	            fontSize = 12.sp
   216	        )
   217	
   218	        Spacer(
   219	            modifier =
   220	                Modifier.height(20.dp)

================================================
FILE: android/app/src/main/java/com/monu/ai/brain/MonuAiOrchestrator.kt
================================================
     1	package com.monu.ai.brain
     2	
     3	import com.monu.ai.MonuCommandRouter
     4	import com.monu.ai.network.MonuAiGateway
     5	
     6	class MonuAiOrchestrator(
     7	    private val commandRouter:
     8	        MonuCommandRouter,
     9	    private val moodController:
    10	        MonuMoodController,
    11	    private val executionPlanner:
    12	        MonuExecutionPlanner,
    13	    private val routeAggregator:
    14	        MonuRouteAggregator,
    15	    private val aiGateway:
    16	        MonuAiGateway
    17	) {
    18	
    19	    suspend fun process(
    20	        request: MonuAiRequest
    21	    ): MonuAiResponse {
    22	
    23	        val command =
    24	            commandRouter.classify(
    25	                request.message
    26	            )
    27	
    28	        val mood =
    29	            moodController.detect(
    30	                request.message
    31	            )
    32	
    33	        val plan =
    34	            executionPlanner.plan(
    35	                command.type
    36	            )
    37	
    38	        val startTime =
    39	            System.currentTimeMillis()
    40	
    41	        return try {
    42	
    43	            val gatewayResponse =
    44	                aiGateway.ask(
    45	                    message =
    46	                        request.message,
    47	                    preferredModel =
    48	                        request.preferredModel
    49	                )
    50	
    51	            val latency =
    52	                System.currentTimeMillis() -
    53	                    startTime
    54	
    55	            val routeResults =
    56	                listOf(
    57	                    MonuRouteResult(
    58	                        route =
    59	                            "AI_GATEWAY",
    60	                        success = true,
    61	                        content =
    62	                            gatewayResponse,
    63	                        latencyMs =
    64	                            latency
    65	                    )
    66	                )
    67	
    68	            val best =
    69	                routeAggregator.aggregate(
    70	                    routeResults
    71	                )
    72	
    73	            MonuAiResponse(
    74	                answer =
    75	                    best?.content
    76	                        ?: "No response",
    77	                model =
    78	                    request.preferredModel,
    79	                mood = mood,
    80	                routes =
    81	                    routeResults,
    82	                diagnostics =
    83	                    routeAggregator.diagnostics(
    84	                        routeResults
    85	                    ) + mapOf(
    86	                        "command" to
    87	                            command.type.name,
    88	                        "targets" to
    89	                            plan.targets.joinToString()
    90	                    )
    91	            )
    92	
    93	        } catch (
    94	            error: Exception
    95	        ) {
    96	
    97	            val failed =
    98	                MonuRouteResult(
    99	                    route =
   100	                        "AI_GATEWAY",
   101	                    success = false,
   102	                    content =
   103	                        error.message
   104	                            ?: "Unknown error"
   105	                )
   106	
   107	            MonuAiResponse(
   108	                answer =
   109	                    "MONU could not complete the request.",
   110	                model =
   111	                    request.preferredModel,
   112	                mood = mood,
   113	                routes =
   114	                    listOf(failed),
   115	                diagnostics =
   116	                    routeAggregator.diagnostics(
   117	                        listOf(failed)
   118	                    )
   119	            )
   120	        }
   121	    }
   122	}

================================================
FILE: android/app/src/main/java/com/monu/ai/brain/MonuMemoryContext.kt
================================================
     1	package com.monu.ai.brain
     2	
     3	import com.monu.ai.MonuRepository
     4	
     5	class MonuMemoryContext(
     6	    private val repository: MonuRepository
     7	) {
     8	
     9	    suspend fun buildContext(
    10	        conversationId: Long?
    11	    ): String {
    12	
    13	        if (conversationId == null) {
    14	            return ""
    15	        }
    16	
    17	        return try {
    18	
    19	            val messages =
    20	                repository.getMessages(
    21	                    conversationId
    22	                )
    23	
    24	            messages
    25	                .takeLast(20)
    26	                .joinToString(
    27	                    separator = "\n"
    28	                ) {
    29	                    "${it.role}: ${it.content}"
    30	                }
    31	
    32	        } catch (_: Exception) {
    33	            ""
    34	        }
    35	    }
    36	}

================================================
FILE: android/app/src/main/java/com/monu/ai/health/MonuDiagnosticEngine.kt
================================================
     1	package com.monu.ai.health
     2	
     3	import com.monu.ai.network.ConnectionState
     4	
     5	data class MonuDiagnosticReport(
     6	    val healthy: Boolean,
     7	    val summary: String,
     8	    val details: List<String>,
     9	    val timestamp: Long =
    10	        System.currentTimeMillis()
    11	)
    12	
    13	class MonuDiagnosticEngine {
    14	
    15	    fun analyze(
    16	        connectionState: ConnectionState?,
    17	        lastSuccessfulCheck: Long?
    18	    ): MonuDiagnosticReport {
    19	
    20	        val details =
    21	            mutableListOf<String>()
    22	
    23	        var healthy = true
    24	
    25	        if (connectionState == null) {
    26	
    27	            healthy = false
    28	
    29	            details.add(
    30	                "Connection state is unavailable"
    31	            )
    32	
    33	        } else {
    34	
    35	            details.add(
    36	                "Connection state: $connectionState"
    37	            )
    38	        }
    39	
    40	        val now =
    41	            System.currentTimeMillis()
    42	
    43	        if (lastSuccessfulCheck == null) {
    44	
    45	            healthy = false
    46	
    47	            details.add(
    48	                "No successful health check recorded"
    49	            )
    50	
    51	        } else {
    52	
    53	            val elapsed =
    54	                now - lastSuccessfulCheck
    55	
    56	            if (
    57	                elapsed >
    58	                5 * 60 * 1000
    59	            ) {
    60	
    61	                healthy = false
    62	
    63	                details.add(
    64	                    "Last successful check exceeded five minutes"
    65	                )
    66	            }
    67	        }
    68	
    69	        return MonuDiagnosticReport(
    70	            healthy = healthy,
    71	            summary =
    72	                if (healthy)
    73	                    "MONU system is healthy"
    74	                else
    75	                    "MONU requires connection diagnostics",
    76	            details = details
    77	        )
    78	    }
    79	}

================================================
FILE: android/app/src/main/java/com/monu/ai/integration/MonuAppIntegration.kt
================================================
     1	package com.monu.ai.integration
     2	
     3	import com.monu.ai.navigation.MonuAppRoute
     4	import com.monu.ai.navigation.MonuNavigationController
     5	
     6	object MonuAppIntegration {
     7	
     8	    fun openFeature(
     9	        featureId: String
    10	    ): Boolean {
    11	
    12	        val feature =
    13	            MonuFeatureRegistry.find(
    14	                featureId
    15	            ) ?: return false
    16	
    17	        val route = when (
    18	            feature.route
    19	        ) {
    20	
    21	            "chat" ->
    22	                MonuAppRoute.Chat
    23	
    24	            "dashboard" ->
    25	                MonuAppRoute.Dashboard
    26	
    27	            "connection" ->
    28	                MonuAppRoute.Connection
    29	
    30	            "models" ->
    31	                MonuAppRoute.Models
    32	
    33	            "settings" ->
    34	                MonuAppRoute.Settings
    35	
    36	            "profile" ->
    37	                MonuAppRoute.Profile
    38	
    39	            "notebooks" ->
    40	                MonuAppRoute.Notebooks
    41	
    42	            "voice" ->
    43	                MonuAppRoute.Voice
    44	
    45	            "library" ->
    46	                MonuAppRoute.Library
    47	
    48	            "search" ->
    49	                MonuAppRoute.Search
    50	
    51	            "timeline" ->
    52	                MonuAppRoute.Timeline
    53	
    54	            "backup" ->
    55	                MonuAppRoute.Backup
    56	
    57	            "privacy" ->
    58	                MonuAppRoute.Privacy
    59	
    60	            else ->
    61	                return false
    62	        }
    63	
    64	        MonuNavigationController.navigate(
    65	            route
    66	        )
    67	
    68	        return true
    69	    }
    70	}

================================================
FILE: android/app/src/main/java/com/monu/ai/integration/MonuDeepLinkResolver.kt
================================================
     1	package com.monu.ai.integration
     2	
     3	import android.net.Uri
     4	import com.monu.ai.navigation.MonuAppRoute
     5	
     6	object MonuDeepLinkResolver {
     7	
     8	    fun resolve(
     9	        uri: Uri?
    10	    ): MonuAppRoute? {
    11	
    12	        val value =
    13	            uri?.lastPathSegment
    14	                ?.lowercase()
    15	                ?: return null
    16	
    17	        return when (value) {
    18	
    19	            "chat" ->
    20	                MonuAppRoute.Chat
    21	
    22	            "connection" ->
    23	                MonuAppRoute.Connection
    24	
    25	            "models" ->
    26	                MonuAppRoute.Models
    27	
    28	            "settings" ->
    29	                MonuAppRoute.Settings
    30	
    31	            "profile" ->
    32	                MonuAppRoute.Profile
    33	
    34	            "notebooks" ->
    35	                MonuAppRoute.Notebooks
    36	
    37	            "voice" ->
    38	                MonuAppRoute.Voice
    39	
    40	            "library" ->
    41	                MonuAppRoute.Library
    42	
    43	            "dashboard" ->
    44	                MonuAppRoute.Dashboard
    45	
    46	            else -> null
    47	        }
    48	    }
    49	}

================================================
FILE: android/app/src/main/java/com/monu/ai/integration/MonuDrawerRouter.kt
================================================
     1	package com.monu.ai.integration
     2	
     3	import com.monu.ai.navigation.MonuAppRoute
     4	import com.monu.ai.navigation.MonuNavigationController
     5	
     6	object MonuDrawerRouter {
     7	
     8	    fun open(
     9	        action: String
    10	    ) {
    11	
    12	        when (
    13	            action.lowercase()
    14	        ) {
    15	
    16	            "chat",
    17	            "new_chat" ->
    18	                MonuNavigationController.navigate(
    19	                    MonuAppRoute.Chat
    20	                )
    21	
    22	            "dashboard" ->
    23	                MonuNavigationController.navigate(
    24	                    MonuAppRoute.Dashboard
    25	                )
    26	
    27	            "connection" ->
    28	                MonuNavigationController.navigate(
    29	                    MonuAppRoute.Connection
    30	                )
    31	
    32	            "models" ->
    33	                MonuNavigationController.navigate(
    34	                    MonuAppRoute.Models
    35	                )
    36	
    37	            "settings" ->
    38	                MonuNavigationController.navigate(
    39	                    MonuAppRoute.Settings
    40	                )
    41	
    42	            "profile" ->
    43	                MonuNavigationController.navigate(
    44	                    MonuAppRoute.Profile
    45	                )
    46	
    47	            "notebooks" ->
    48	                MonuNavigationController.navigate(
    49	                    MonuAppRoute.Notebooks
    50	                )
    51	
    52	            "voice" ->
    53	                MonuNavigationController.navigate(
    54	                    MonuAppRoute.Voice
    55	                )
    56	
    57	            "library" ->
    58	                MonuNavigationController.navigate(
    59	                    MonuAppRoute.Library
    60	                )
    61	
    62	            "search" ->
    63	                MonuNavigationController.navigate(
    64	                    MonuAppRoute.Search
    65	                )
    66	
    67	            "timeline" ->
    68	                MonuNavigationController.navigate(
    69	                    MonuAppRoute.Timeline
    70	                )
    71	
    72	            "backup" ->
    73	                MonuNavigationController.navigate(
    74	                    MonuAppRoute.Backup
    75	                )
    76	
    77	            "privacy" ->
    78	                MonuNavigationController.navigate(
    79	                    MonuAppRoute.Privacy
    80	                )
    81	        }
    82	    }
    83	}

================================================
FILE: android/app/src/main/java/com/monu/ai/integration/MonuFeatureIntegration.kt
================================================
     1	package com.monu.ai.integration
     2	
     3	import android.content.Context
     4	import com.monu.ai.MonuBrain
     5	import com.monu.ai.MonuCommandRouter
     6	
     7	/**
     8	 * Central integration gateway.
     9	 *
    10	 * This class is the bridge between UI, AI brain,
    11	 * commands, memory, network and Android features.
    12	 */
    13	class MonuFeatureIntegration(
    14	    private val context: Context
    15	) {
    16	
    17	    private val brain = MonuBrain()
    18	    private val commandRouter = MonuCommandRouter()
    19	
    20	    data class IntegrationResult(
    21	        val success: Boolean,
    22	        val route: String,
    23	        val message: String,
    24	        val metadata: Map<String, String> = emptyMap()
    25	    )
    26	
    27	    fun processUserInput(
    28	        input: String
    29	    ): IntegrationResult {
    30	
    31	        if (input.isBlank()) {
    32	            return IntegrationResult(
    33	                success = false,
    34	                route = "validation",
    35	                message = "Message cannot be empty"
    36	            )
    37	        }
    38	
    39	        return try {
    40	            val decision = brain.process(input)
    41	
    42	            IntegrationResult(
    43	                success = true,
    44	                route = "central_ai_brain",
    45	                message = input,
    46	                metadata = mapOf(
    47	                    "brain" to decision.toString(),
    48	                    "source" to "apk"
    49	                )
    50	            )
    51	        } catch (error: Exception) {
    52	            IntegrationResult(
    53	                success = false,
    54	                route = "error_recovery",
    55	                message = error.message ?: "Unknown integration error"
    56	            )
    57	        }
    58	    }
    59	}

================================================
FILE: android/app/src/main/java/com/monu/ai/network/MonuHealthMonitor.kt
================================================
     1	package com.monu.ai.network
     2	
     3	import android.content.Context
     4	import kotlinx.coroutines.delay
     5	import kotlinx.coroutines.isActive
     6	import kotlinx.coroutines.CoroutineScope
     7	import kotlinx.coroutines.launch
     8	
     9	data class MonuHealthReport(
    10	    val apkToServer: ConnectionResult,
    11	    val lastCheck: Long,
    12	    val diagnostic: String
    13	)
    14	
    15	class MonuHealthMonitor(
    16	    context: Context
    17	) {
    18	
    19	    private val network =
    20	        MonuNetworkEngine(context)
    21	
    22	    suspend fun checkNow(): MonuHealthReport {
    23	
    24	        val result =
    25	            network.checkServerConnection()
    26	
    27	        val diagnostic =
    28	            when {
    29	
    30	                MonuNetworkConfig.MONU_SERVER_URL.isBlank() ->
    31	                    "MONU Server URL is not configured."
    32	
    33	                !result.connected ->
    34	                    "Connection failed: ${result.message}"
    35	
    36	                else ->
    37	                    "MONU connection healthy. Latency: ${result.latencyMs} ms"
    38	            }
    39	
    40	        return MonuHealthReport(
    41	            apkToServer = result,
    42	            lastCheck =
    43	                System.currentTimeMillis(),
    44	            diagnostic = diagnostic
    45	        )
    46	    }
    47	
    48	    fun startFiveMinuteMonitor(
    49	        scope: CoroutineScope,
    50	        onReport: (
    51	            MonuHealthReport
    52	        ) -> Unit
    53	    ) {
    54	
    55	        scope.launch {
    56	
    57	            while (isActive) {
    58	
    59	                onReport(
    60	                    checkNow()
    61	                )
    62	
    63	                delay(
    64	                    5 * 60 * 1000L
    65	                )
    66	            }
    67	        }
    68	    }
    69	}

================================================
POSSIBLE API DEFINITIONS
================================================
android/app/src/main/java/com/monu/ai/brain/MonuAiOrchestrator.kt:19:    suspend fun process(
android/app/src/main/java/com/monu/ai/network/MonuAiGateway.kt:28:    suspend fun process(
android/app/src/main/java/com/monu/ai/navigation/MonuDestinations.kt:13:    data object Models :
android/app/src/main/java/com/monu/ai/navigation/MonuAppRoutes.kt:9:    data object ModelSelector : MonuAppRoute("models")
android/app/src/main/java/com/monu/ai/integration/MonuMediaIntegration.kt:34:    fun process(
android/app/src/main/java/com/monu/ai/sync/MonuSyncProcessor.kt:5:    suspend fun process(

================================================
RELATED CLASSES
================================================
android/app/src/main/java/com/monu/ai/MonuViewModel.kt
android/app/src/main/java/com/monu/ai/appfeatures/MonuBackup.kt
android/app/src/main/java/com/monu/ai/brain/MonuMemoryContext.kt
android/app/src/main/java/com/monu/ai/config/MonuGeminiModels.kt
android/app/src/main/java/com/monu/ai/health/MonuHealthScheduler.kt
android/app/src/main/java/com/monu/ai/integration/MonuAppIntegration.kt
android/app/src/main/java/com/monu/ai/integration/MonuChatIntegration.kt
android/app/src/main/java/com/monu/ai/integration/MonuFeatureIntegration.kt
android/app/src/main/java/com/monu/ai/integration/MonuMediaIntegration.kt
android/app/src/main/java/com/monu/ai/integration/MonuMemoryIntegration.kt
android/app/src/main/java/com/monu/ai/integration/MonuResponseIntegration.kt
android/app/src/main/java/com/monu/ai/integration/MonuSettingsIntegration.kt
android/app/src/main/java/com/monu/ai/integration/MonuVoiceIntegration.kt
android/app/src/main/java/com/monu/ai/intelligence/MonuActivityTimeline.kt
android/app/src/main/java/com/monu/ai/network/ConnectionState.kt
android/app/src/main/java/com/monu/ai/network/MonuConnectionController.kt
android/app/src/main/java/com/monu/ai/network/MonuHealthMonitor.kt
android/app/src/main/java/com/monu/ai/network/MonuModelPreferences.kt
android/app/src/main/java/com/monu/ai/network/MonuModels.kt
android/app/src/main/java/com/monu/ai/services/MonuVoiceSessionService.kt
android/app/src/main/java/com/monu/ai/smart/MonuModelCapabilities.kt
android/app/src/main/java/com/monu/ai/system/MonuSystemIntegrationController.kt
android/app/src/main/java/com/monu/ai/ui/ConnectionScreen.kt
android/app/src/main/java/com/monu/ai/ui/ModelSelectorScreen.kt
android/app/src/main/java/com/monu/ai/ui/premium/MonuVoiceScreen.kt
android/app/src/main/java/com/monu/ai/voice/MonuVoiceController.kt
android/app/src/main/java/com/monu/ai/voice/MonuVoiceEngine.kt
