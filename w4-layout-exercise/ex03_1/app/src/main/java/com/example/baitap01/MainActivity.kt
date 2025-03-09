package com.example.baitap01

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.baitap01.ui.theme.Baitap01Theme
import kotlin.random.Random
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Baitap01Theme {
                RandomBackgroundApp()
            }
        }
    }
}

@Composable
fun RandomBackgroundApp() {
    val backgroundList = listOf(
        R.drawable.bg01,
        R.drawable.bg02,
        R.drawable.bg03,
        R.drawable.bg04,
        R.drawable.bg05,
        R.drawable.bg06,
        R.drawable.bg07,
        R.drawable.bg08,
    )
    var randomBg by remember { mutableStateOf(backgroundList.random()) }
    var isSwitched by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = randomBg),
            contentDescription = "Random Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Change Background", color = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Switch(
                    modifier = Modifier.width(12.dp),
                    checked = isSwitched,
                    onCheckedChange = { newValue ->
                        isSwitched = newValue
                        randomBg = backgroundList.random()
                    }
                )
            }
            StudentInfo(modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
fun StudentInfo(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.avatar),
            contentDescription = "Student Image",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Nguyễn Văn Hoài", fontSize = 18.sp, color = Color.White)
        Text(text = "22110327", fontSize = 14.sp, color = Color.White)

//        Spacer(modifier = Modifier.height(20.dp))
//        Ex01_4()

        Spacer(modifier = Modifier.height(20.dp))
        Ex01_5()
    }
}

@Preview(showBackground = true)
@Composable
fun StudentInfoPreview() {
    Baitap01Theme {
        StudentInfo()
    }
}

fun generateRandomNumbers(size: Int): ArrayList<Int> {
    val randomNumbers = ArrayList<Int>()
    for (i in 0 until size) {
        randomNumbers.add(Random.nextInt(1, 20))
    }
    return randomNumbers
}

@Composable
fun Ex01_4() {
    var numberList by remember { mutableStateOf<ArrayList<Int>>(ArrayList()) }
    var oddNumbers by remember { mutableStateOf<List<Int>>(emptyList()) }
    var evenNumbers by remember { mutableStateOf<List<Int>>(emptyList()) }

    Spacer(modifier = Modifier.height(16.dp))
    Button(onClick = {
        numberList = generateRandomNumbers(10)
        oddNumbers = numberList.filter { it % 2 != 0 }
        evenNumbers = numberList.filter { it % 2 == 0 }
        Log.d("Generated Numbers", "Numbers: $numberList")
        Log.d("Odd Numbers", "Odd: $oddNumbers")
        Log.d("Even Numbers", "Even: $evenNumbers")
    }) {
        Text("Ex01_4")
    }
}

@Composable
fun Ex01_5() {
    var input by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    val context = LocalContext.current

    Spacer(modifier = Modifier.height(20.dp))
    BasicTextField(
        value = input,
        onValueChange = { input = it },
        modifier = Modifier.fillMaxWidth(),
        decorationBox = { innerTextField ->
            Box(modifier = Modifier.padding(16.dp)) {
                if (input.isEmpty()) {
                    Text(text = "Enter a string", color = Color.Gray)
                }
                innerTextField()
            }
        }
    )

    Button(onClick = {
        val reversedString = input.split(" ").reversed().joinToString(" ").uppercase()
        result = reversedString
        Toast.makeText(context, reversedString, Toast.LENGTH_SHORT).show()
    }) {
        Text(text = "Ex01_5")
    }
    Spacer(modifier = Modifier.height(16.dp))
    Text(text = "Reversed and Uppercase: $result", color = Color.White)
}
