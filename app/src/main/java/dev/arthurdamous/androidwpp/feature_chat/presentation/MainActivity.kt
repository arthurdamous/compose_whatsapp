package dev.arthurdamous.androidwpp.feature_chat.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.arthurdamous.androidwpp.NavGraph
import dev.arthurdamous.androidwpp.R
import dev.arthurdamous.androidwpp.feature_chat.presentation.theme.AndroidWppTheme
import java.time.LocalDateTime

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            AndroidWppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    val scaffoldState = rememberScaffoldState()
                    Scaffold(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        NavGraph(
                            navController = navController,
                            scaffoldState = scaffoldState
                        )
                    }
                }
            }
        }
    }
}

@SuppressLint("NewApi")
@Composable
fun ChatScreen(viewModel: MainViewModel = hiltViewModel()) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                TopBar(name = "Arthur")
            },
            bottomBar = {
                ChatBar()
            }
        ) {
            Image(
                painter = painterResource(id = R.drawable.whatsapp_2),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillWidth,
                alignment = Center
            )
            LazyColumn(
                contentPadding = it,
                reverseLayout = false,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(count = state.listOfMessages.size) { i ->
                    if (i < 1) {
                        CardDate()
                    }
                    MessageItem(
                        message = state.listOfMessages[i].message,
                        date =
                        "${
                            LocalDateTime
                                .parse(state.listOfMessages[i].hour).hour
                        }:${
                            if (LocalDateTime.parse(
                                    state.listOfMessages[i].hour
                                ).minute == 60
                            ) "00" else LocalDateTime.parse(
                                state.listOfMessages[i].hour
                            ).minute
                        }",
                        isSent = state.listOfMessages[i].isSent
                    )
                }
            }
        }
    }
}

@Composable
fun TopBar(
    name: String
) {
    val image = R.drawable.photo2

    TopAppBar {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "",
                modifier = Modifier.weight(0.1f)
            )
            Image(
                painter = painterResource(id = image),
                contentDescription = "",
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .weight(0.1f)
                    .clip(RoundedCornerShape(100.dp))
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(100.dp)
                    )
            )
            Column(
                modifier = Modifier
                    .weight(0.5f)
                    .padding(horizontal = 8.dp)
            ) {
                Text(
                    text = name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "Last seen on friday",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Icon(
                imageVector = Icons.Default.Phone,
                contentDescription = "",
                modifier = Modifier.weight(0.1f)
            )
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier.weight(0.1f)
            )
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "",
                modifier = Modifier.weight(0.1f)
            )

        }
    }
}

@Composable
fun MessageItem(
    message: String,
    date: String,
    isSent: Boolean
) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (isSent) End else Start,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .padding(4.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(if (isSent) MaterialTheme.colors.secondary else Color.White),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = message,
                textAlign = TextAlign.Start,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(4.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = date,
                fontWeight = FontWeight.Light,
                fontSize = 10.sp,
                modifier = Modifier.padding(4.dp)
            )
        }
    }

}

@Composable
fun ChatBar(
    viewModel: MainViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .background(color = Color.Transparent)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 2.dp, end = 4.dp, bottom = 2.dp),
            verticalAlignment = CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(0.9f)
            ) {
                OutlinedTextField(
                    value = state.messageText,
                    onValueChange = { text ->
                        viewModel.onEvent(
                            MessageEvent.OnChangeTextMessage(
                                text
                            )
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
                        keyboardType = KeyboardType.Text,
                        autoCorrect = true,
                        imeAction = ImeAction.Go
                    ),
                    placeholder = { Text(text = "Type a message") },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_outline_emoji),
                            contentDescription = "",
                            modifier = Modifier
                                .padding(4.dp)
                                .clickable {

                                }
                        )
                    },
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_camera),
                            contentDescription = "",
                            modifier = Modifier
                                .padding(6.dp)
                                .clickable {

                                }
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 4.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(4.dp))
                )
            }
            IconButton(
                onClick = { viewModel.onEvent(MessageEvent.OnMessageSent(state.messageText)) },
                enabled = state.isEnabledToSendMessage,
                modifier = Modifier.background(
                    color = if (state.isEnabledToSendMessage) MaterialTheme.colors.primary else Color.Gray,
                    shape = RoundedCornerShape(100.dp)
                )
            ) {
                Icon(
                    imageVector = Icons.Filled.Send,
                    contentDescription = "Send Message",
                    tint = Color.White
                )
            }
        }
    }


}

@Composable
fun CardDate() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = CenterVertically,
            modifier = Modifier
                .padding(8.dp)
                .background(
                    color = MaterialTheme.colors.secondaryVariant,
                    shape = RoundedCornerShape(8.dp)
                )
                .clip(RoundedCornerShape(4.dp))
        ) {
            Text(
                text = "HOJE",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Gray,
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AndroidWppTheme {
        //TopBar("Arthur")
        //ChatBar()
        MessageItem(message = "Hello world", date = "00:24", isSent = true)
    }
}