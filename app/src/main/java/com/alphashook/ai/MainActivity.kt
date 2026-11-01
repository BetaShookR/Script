package com.alphashook.ai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private data class ChatMessage(val text: String, val fromUser: Boolean)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlphaShookApp()
        }
    }
}

@Composable
fun AlphaShookApp() {
    var input by remember { mutableStateOf("") }
    val messages = remember {
        mutableStateListOf(
            ChatMessage(
                "¡Hola! Soy AlphaShook AI. Mi interfaz ya está lista; el motor de inteligencia artificial local se integrará en la próxima etapa.",
                false
            )
        )
    }

    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = Color(0xFF64B5F6),
            background = Color(0xFF101318),
            surface = Color(0xFF191E25)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF101318))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF191E25))
                    .padding(18.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("AlphaShook AI", fontSize = 21.sp, color = Color.White)
                Spacer(Modifier.weight(1f))
                Text("LOCAL AI", fontSize = 11.sp, color = Color(0xFF64B5F6))
            }

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                contentPadding = PaddingValues(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(messages) { message ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = if (message.fromUser) Arrangement.End else Arrangement.Start
                    ) {
                        Surface(
                            color = if (message.fromUser) Color(0xFF24496B) else Color(0xFF202630),
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier.widthIn(max = 310.dp)
                        ) {
                            Text(
                                text = message.text,
                                color = Color.White,
                                modifier = Modifier.padding(14.dp),
                                fontSize = 15.sp
                            )
                        }
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF191E25))
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = input,
                    onValueChange = { input = it },
                    modifier = Modifier.weight(1f),
                    placeholder = { Text("Escribí un mensaje...") },
                    maxLines = 4,
                    shape = RoundedCornerShape(22.dp)
                )
                Spacer(Modifier.width(8.dp))
                Button(
                    onClick = {
                        val text = input.trim()
                        if (text.isNotEmpty()) {
                            messages.add(ChatMessage(text, true))
                            messages.add(
                                ChatMessage(
                                    "Todavía estoy esperando la integración del motor local para poder responder.",
                                    false
                                )
                            )
                            input = ""
                        }
                    },
                    enabled = input.isNotBlank(),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 14.dp)
                ) {
                    Text("Enviar")
                }
            }
        }
    }
}
