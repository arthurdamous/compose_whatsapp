package dev.arthurdamous.androidwpp

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
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import dev.arthurdamous.androidwpp.domain.model.Message
import dev.arthurdamous.androidwpp.ui.theme.AndroidWppTheme

val listOfMessages = mutableListOf(
    Message(
        id = 0,
        message = "Olá tudo bem ?",
        hour = "00:23",
        isSent = true
    ),
    Message(
        id = 1,
        message = "Tudo, e com você ?",
        hour = "00:24",
        isSent = false
    ),
    Message(
        id = 3,
        message = "Que bom, o que vais fazer hoje ?",
        hour = "00:25",
        isSent = true
    ),
    Message(
        id = 4,
        message = "Bom, hoje vou a praia",
        hour = "00:26",
        isSent = false
    ),
    Message(
        id = 5,
        message = "Depois vou a sorveteria",
        hour = "00:27",
        isSent = false
    ),
    Message(
        id = 6,
        message = "Por fim, vou visitar meus amigos",
        hour = "00:27",
        isSent = false
    ),
    Message(
        id = 7,
        message = "E você ?",
        hour = "00:28",
        isSent = false
    ),
    Message(
        id = 0,
        message = "Olá tudo bem ?",
        hour = "00:23",
        isSent = true
    ),
    Message(
        id = 1,
        message = "Tudo, e com você ?",
        hour = "00:24",
        isSent = false
    ),
    Message(
        id = 3,
        message = "Que bom, o que vais fazer hoje ?",
        hour = "00:25",
        isSent = true
    ),
    Message(
        id = 4,
        message = "Bom, hoje vou a praia",
        hour = "00:26",
        isSent = false
    ),
    Message(
        id = 5,
        message = "Depois vou a sorveteria",
        hour = "00:27",
        isSent = false
    ),
    Message(
        id = 6,
        message = "Por fim, vou visitar meus amigos",
        hour = "00:27",
        isSent = false
    ),
    Message(
        id = 7,
        message = "E você ?",
        hour = "00:28",
        isSent = false
    ),
    Message(
        id = 0,
        message = "Olá tudo bem ?",
        hour = "00:23",
        isSent = true
    ),
    Message(
        id = 1,
        message = "Tudo, e com você ?",
        hour = "00:24",
        isSent = false
    ),
    Message(
        id = 3,
        message = "Que bom, o que vais fazer hoje ?",
        hour = "00:25",
        isSent = true
    ),
    Message(
        id = 4,
        message = "Bom, hoje vou a praia",
        hour = "00:26",
        isSent = false
    ),
    Message(
        id = 5,
        message = "Depois vou a sorveteria",
        hour = "00:27",
        isSent = false
    ),
    Message(
        id = 6,
        message = "Por fim, vou visitar meus amigos",
        hour = "00:27",
        isSent = false
    ),
    Message(
        id = 7,
        message = "E você ?",
        hour = "00:28",
        isSent = false
    ),
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidWppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
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
                                verticalArrangement = Arrangement.Bottom,
                                modifier = Modifier
                                    .fillMaxSize()
                            ) {
                                items(listOfMessages.size) { i ->
                                    if (i < 1) {
                                        CardDate()
                                    }
                                    MessageItem(
                                        message = listOfMessages[i].message,
                                        date = listOfMessages[i].hour,
                                        isSent = listOfMessages[i].isSent
                                    )
                                }
                            }
                        }
                    }
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

    TopAppBar(

    ) {
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
fun ChatBar() {
    var message by remember {
        mutableStateOf("")
    }

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
                    value = message,
                    onValueChange = { message = it },
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
            Box(
                contentAlignment = Center,
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colors.primary,
                        shape = RoundedCornerShape(100.dp)
                    )
                    .clip(RoundedCornerShape(100.dp))
                    .size(40.dp)
                    .padding(8.dp)
                    .weight(0.1f)
                    .clickable {
                        listOfMessages.add(
                            Message(
                                10,
                                message = message,
                                hour = "00:44",
                                isSent = true
                            )
                        )
                    }
            ) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "",
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