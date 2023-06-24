package com.example.aula1704.ui.theme


import android.app.Application
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aula1704.R
import com.example.aula1704.R.string
import com.example.aula1704.viewModel.LoginViewModel
import com.example.aula1704.viewModel.LoginViewModelFactory
import kotlinx.coroutines.flow.collectLatest


@Composable
fun HomeScreen(onNavigateForm1: () -> Unit, onAfterLogin: (nome: String) -> Unit) {



    val application = LocalContext.current.applicationContext as Application
    val viewModel: LoginViewModel = viewModel(
        factory = LoginViewModelFactory(application)
    )

    val ctx = LocalContext.current

    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(Unit) {
        viewModel.toastMessage.collectLatest {
            scaffoldState.snackbarHostState.showSnackbar(
                message = it,
                duration = SnackbarDuration.Long
            )
            // opção para mostrar as mensagens
            // Toast.makeText(ctx, it, Toast.LENGTH_SHORT).show()
        }
    }

    val focusManager = LocalFocusManager.current
    Scaffold(scaffoldState = scaffoldState ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues = it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TrabalhoScreen(viewModel)
                Button(
                    onClick = {
                        focusManager.clearFocus()
                        viewModel.validateLogin(onResult = {
                            if (it) {
                                onAfterLogin(viewModel.name)
                            }
                        })
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF1E90FF)),
                ) {
                    Text(text = stringResource(string.botao_login), color = Color.White)
                }
                Text(
                    text = "",
                    modifier = Modifier.fillMaxWidth(),

                    textAlign = TextAlign.Center,
                )

                Button(colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF1E90FF)),
                    onClick = {
                    onNavigateForm1()
                }) {
                    Text(text = "Realizar cadastro",color = Color.White)

                }

            }
        }
    }
}

@Composable
fun TrabalhoScreen(viewModel: LoginViewModel) {

    Box(modifier = Modifier.fillMaxWidth()) {
/*Image(
painter = painterResource(id = com.example.aulanova.R.drawable.viagem),
contentDescription = "",
contentScale = ContentScale.FillBounds
)*/

        Column(
            modifier = Modifier

                .padding(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Login(viewModel)
        }
    }
}

@Composable
fun Login(viewModel: LoginViewModel) {



    Text(
        text = stringResource(id = com.example.aula1704.R.string.titulo),
        style = MaterialTheme.typography.h5,
        textAlign = TextAlign.Center,
        color = Color.Gray,
        modifier = Modifier
            .padding(bottom = 40.dp)
    )

    Text(
        text = stringResource(id = com.example.aula1704.R.string.login),
        style = MaterialTheme.typography.h6,
        textAlign = TextAlign.Left,
        modifier = Modifier
    )

    OutlinedTextField(
        value = viewModel.name,
        onValueChange = { viewModel.name = it},
        modifier = Modifier.width(400.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )

    Text(
        text = stringResource(id = com.example.aula1704.R.string.senha),
        style = MaterialTheme.typography.h6,
        textAlign = TextAlign.Start
    )

    OutlinedTextField(
        value = viewModel.password,
        onValueChange = { viewModel.password = it},
        modifier = Modifier.width(400.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}
