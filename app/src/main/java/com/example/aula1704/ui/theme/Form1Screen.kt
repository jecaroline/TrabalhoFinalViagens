package com.example.aula1704.ui.theme

import android.app.Application
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aula1704.MyApp
import com.example.aula1704.viewModel.RegisterNewUserViewModel
import com.example.aula1704.viewModel.RegisterNewUserViewModelFactory
import kotlinx.coroutines.flow.collectLatest


@Composable
fun Form1Screen(onNavigate : (route: String) ->Unit, onBackScreen:() -> Unit) {

    val application = LocalContext.current.applicationContext as Application
    val viewModel: RegisterNewUserViewModel = viewModel(
        factory = RegisterNewUserViewModelFactory(application)
    )

    val ctx = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.toastMessage.collectLatest {
            Toast.makeText(ctx, it, Toast.LENGTH_SHORT).show()

        }
    }

    val focusManager = LocalFocusManager.current

    val Novousuario = remember {
//lembrar da variavel
        mutableStateOf(value = "")
    }
    val novoNome = remember {
        mutableStateOf(value = "")
    }
    val Novasenha = remember {
//lembrar da variavel
        mutableStateOf(value = "")
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF8F7F4)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row( modifier = Modifier
            .padding(20.dp)) {
            Text(
                text = stringResource(com.example.aula1704.R.string.cadastrar_nome),
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Start,
                modifier = Modifier.alignByBaseline()

            )

            Spacer(modifier = Modifier.size(16.dp))
            OutlinedTextField(
                value = viewModel.novoNome,
                onValueChange = {
                    viewModel.novoNome = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.alignByBaseline()
            )
        }

        Spacer(modifier = Modifier.size(10.dp))

        Row(modifier = Modifier.padding(20.dp)) {
            Text(
                text = stringResource(com.example.aula1704.R.string.login),
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Start,
                modifier = Modifier.alignByBaseline()

            )

            Spacer(modifier = Modifier.size(5.dp))
            OutlinedTextField(
                value = viewModel.novoUsuario,
                onValueChange = {
                    viewModel.novoUsuario = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.alignByBaseline()
            )
        }

        Spacer(modifier = Modifier.size(10.dp))

        Row(modifier = Modifier.padding(20.dp)) {
            Text(
                text = stringResource(com.example.aula1704.R.string.senha),
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Start,
                modifier = Modifier.alignByBaseline()

            )

            Spacer(modifier = Modifier.size(10.dp))
            OutlinedTextField(
                value = viewModel.novaSenha,
                onValueChange = {
                    viewModel.novaSenha = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.alignByBaseline()
            )
        }

        Spacer(modifier = Modifier.size(20.dp))


        Row() {
            Button(onClick = {
                focusManager.clearFocus()
                viewModel.registrar(onSuccess ={})
                }, colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF1E90FF)))
            {
                Text(text = "Salvar")
            }

            Spacer(modifier = Modifier.size(15.dp))

            Button(onClick = {onBackScreen()},
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF1E90FF))) {
                Text(
                    text = stringResource(com.example.aula1704.R.string.voltar),
                    color = Color.Black
                )
            }
        }
    }
}
