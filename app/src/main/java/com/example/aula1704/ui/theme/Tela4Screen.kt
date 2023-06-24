package com.example.aula1704.ui.theme

import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aula1704.viewModel.RegisterDespesaViewModel
import com.example.aula1704.viewModel.RegisterDespesaViewModelFactory
import com.example.aula1704.viewModel.RegisterViagemViewModel
import com.example.aula1704.viewModel.RegisterViagemViewModelFactory
import kotlinx.coroutines.flow.collectLatest

@Composable
@ExperimentalMaterialApi
fun Tela4() {

    val application = LocalContext.current.applicationContext as Application
    val viewModel: RegisterDespesaViewModel = viewModel(
        factory = RegisterDespesaViewModelFactory(application)
    )

    val ctx = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.toastMessage.collectLatest {
            Toast.makeText(ctx, it, Toast.LENGTH_SHORT).show()

        }
    }

    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF8F7F4)),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Text(
            text = "Despesas",
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center,
            color = Color.Gray,
        )
        Row( modifier = Modifier
            .padding(20.dp)) {

            Text(
                text = "Data",
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Start,
                modifier = Modifier.alignByBaseline()
                    //.size(100.dp)
            )
            Spacer(modifier = Modifier.size(40.dp))
            OutlinedTextField(
                value = viewModel.data,
                onValueChange = {
                    viewModel.data = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.alignByBaseline()
            )
        }

        Spacer(modifier = Modifier.size(1.dp))

        Row(modifier = Modifier.padding(1.dp)) {
            Text(
                text = "Valor",
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Start,
                modifier = Modifier.alignByBaseline()

            )

            Spacer(modifier = Modifier.size(40.dp))
            OutlinedTextField(
                value = viewModel.valor,
                onValueChange = {
                    viewModel.valor = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.alignByBaseline()
            )
        }

        Spacer(modifier = Modifier.size(10.dp))

        Row(modifier = Modifier.padding(4.dp)) {
            Text(
                text = "Tipo  ",
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Start,
                modifier = Modifier.alignByBaseline()
            )
            Spacer(modifier = Modifier.size(40.dp))
            OutlinedTextField(
                value = viewModel.tipo,
                onValueChange = {
                    viewModel.tipo = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.alignByBaseline()
            )
        }

        Spacer(modifier = Modifier.size(2.dp))

        Row(modifier = Modifier.padding(10.dp)) {
            Text(
                text = "Descrição",
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Start,
                modifier = Modifier.alignByBaseline()
            )
            Spacer(modifier = Modifier.size(10.dp))
            OutlinedTextField(
                value = viewModel.descricao,
                onValueChange = {
                    viewModel.descricao = it
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
                Text(text = "Salvar despesa")
            }

            Spacer(modifier = Modifier.size(15.dp))

        }
    }
}