package com.example.baitap01

import android.app.ActionBar.LayoutParams
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.material3.Button
import android.util.Log
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import kotlin.math.log


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Baitap01Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    StudentInfo(modifier = Modifier.padding(innerPadding))
                }
            }
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
        Text(text = "Nguyễn Văn Hoài", fontSize = 18.sp)
        Text(text = "22110327", fontSize = 14.sp)


        Spacer(modifier = Modifier.height(20.dp))
        Ex01_4()

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
        oddNumbers = numberList.filter { it % 2 != 0 } as ArrayList<Int>
        evenNumbers = numberList.filter { it % 2 == 0 } as ArrayList<Int>

        Log.d("Numbers: ", "$numberList")
        Log.d("Odd Numbers: ", "$oddNumbers")
        Log.d("Even Numbers: ", "$evenNumbers")
    }) {
        Text("Numbers")
    }


    Text(text ="Numbers: $numberList")
    Text(text ="Odd Numbers: $oddNumbers")
    Text(text ="Even Numbers: $evenNumbers")

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
        Text(text = "Reverse")
    }
    Spacer(modifier = Modifier.height(16.dp))
    Text(text = "Reversed and Uppercase: $result")
}


