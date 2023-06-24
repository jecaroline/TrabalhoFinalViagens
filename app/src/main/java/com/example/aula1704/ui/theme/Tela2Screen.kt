package com.example.aula1704.ui.theme

import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aula1704.R
import com.example.aula1704.viewModel.RegisterNewUserViewModel
import com.example.aula1704.viewModel.RegisterNewUserViewModelFactory
import com.example.aula1704.viewModel.RegisterViagemViewModel
import com.example.aula1704.viewModel.RegisterViagemViewModelFactory
import kotlinx.coroutines.flow.collectLatest

@Composable
fun Tela2() {

    val application = LocalContext.current.applicationContext as Application
    val viewModel: RegisterViagemViewModel = viewModel(
        factory = RegisterViagemViewModelFactory(application)
    )

    val ctx = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.toastMessage.collectLatest {
            Toast.makeText(ctx, it, Toast.LENGTH_SHORT).show()

        }
    }

    val focusManager = LocalFocusManager.current

   // val NovoDestino = remember {
        //lembrar da variavel
   //     mutableStateOf(value = "")
   // }
   // val NovaDataIn = remember {
  //      mutableStateOf(value = "")
  //  }
   // val NovaDataFi = remember {
        //lembrar da variavel
   //     mutableStateOf(value = "")
    //}
   // val NovoOrcamento = remember {
        //lembrar da variavel
   //     mutableStateOf(value = "")
    //}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF8F7F4)),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Text(
            text = "Nova viagem",
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center,
            color = Color.Gray,
        )
        Row( modifier = Modifier
            .padding(20.dp)) {

            Text(
                text = "Destino   ",
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Start,
                modifier = Modifier.alignByBaseline()
                    //.size(80.dp)
            )
            Spacer(modifier = Modifier.size(17.dp))
            OutlinedTextField(
                value = viewModel.destino,
                onValueChange = {
                    viewModel.destino = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.alignByBaseline()
            )
        }

        Spacer(modifier = Modifier.size(5.dp))

        Row(modifier = Modifier.padding(1.dp)) {
            Text(
                text = "Data inicial",
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Start,
                modifier = Modifier.alignByBaseline()

            )

            Spacer(modifier = Modifier.size(10.dp))
            OutlinedTextField(
                value = viewModel.dataInicio,
                onValueChange = {
                    viewModel.dataInicio = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.alignByBaseline()
            )
        }

        Spacer(modifier = Modifier.size(10.dp))

        Row(modifier = Modifier.padding(4.dp)) {
            Text(
                text = "Data final",
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Start,
                modifier = Modifier.alignByBaseline()
            )
            Spacer(modifier = Modifier.size(25.dp))
            OutlinedTextField(
                value = viewModel.dataFim,
                onValueChange = {
                    viewModel.dataFim = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.alignByBaseline()
            )
        }

        Spacer(modifier = Modifier.size(2.dp))

        Row(modifier = Modifier.padding(10.dp)) {
            Text(
                text = "Orçamento",
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Start,
                modifier = Modifier.alignByBaseline()
            )
            Spacer(modifier = Modifier.size(10.dp))
            OutlinedTextField(
                value = viewModel.orcamento,
                onValueChange = {
                    viewModel.orcamento = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.alignByBaseline()
            )
        }

        Spacer(modifier = Modifier.size(30.dp))


        Row() {
            Button(onClick = {
                focusManager.clearFocus()
                viewModel.registrar(onSuccess ={})
            }, colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF1E90FF)))
            {
                Text(text = "Salvar viagem")
            }

            Spacer(modifier = Modifier.size(15.dp))
        }
    }
}