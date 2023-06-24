package com.example.aula1704.ui.theme

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aula1704.viewModel.ListDespesasViewModel
import com.example.aula1704.viewModel.ListDespesasViewModelFactory

@Composable
fun ListDespesas(onClickCardDep: () -> Unit) {
    val application = LocalContext.current.applicationContext as Application
    val viewModel: ListDespesasViewModel = viewModel(
        factory = ListDespesasViewModelFactory (application)
    )

    viewModel.buscarDespesas()

    var openDialogRemove by remember { mutableStateOf(false) }
    ConfirmDeleteDespesa(openDialog = openDialogRemove,
        onRemove = {
            openDialogRemove = false
            viewModel.deleteDespesa()
        },
        onClose = { openDialogRemove = false }
    )


    Column(modifier = Modifier
        .fillMaxWidth()
        .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Despesas cadastradas",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h5,
            color = Color.Gray,
        )

        Button(onClick = {
                    onClickCardDep()
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF1E90FF))) {
            Text(text = "Adicionar despesa")
        }

        LazyColumn() {

            items(items = viewModel.despesas.value) {

                Card(
                    elevation = 4.dp,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp)
                        .clickable { }
                ) {
                    Row( modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                        //Image(
                         //   painter = painterResource(id = R.drawable.imagemviagem),
                         //   contentDescription = "",
                         //   contentScale = ContentScale.Crop,
                         //   modifier = Modifier
                         //       .weight(1f)
                         //       .size(50.dp),
                        //)
                        Column(modifier = Modifier.weight(3f)){
                            Text(
                                text = "${it.data}",
                                style = MaterialTheme.typography.h6,
                                textAlign = TextAlign.Start
                            )
                            Text(
                                text = "${it.valor}",
                            )
                            Text(
                                text = "${it.tipo}",
                            )
                            Text(
                                text = "${it.descricao}",
                            )
                            Text(
                                text = "${it.viagem}",
                            )
                            Spacer(Modifier.weight(1f))

                        }
                        IconButton(
                            onClick = {
                                viewModel.despesaForDelete = it
                                openDialogRemove = true

                            },
                        ) {
                            Icon(
                                Icons.Default.Delete,
                                contentDescription = "",
                                modifier = Modifier
                                    .weight(1f)
                                    .size(30.dp),
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ConfirmDeleteDespesa(openDialog: Boolean, onClose: () -> Unit, onRemove: () -> Unit) {
    if (openDialog) {
        AlertDialog(
            onDismissRequest = {
                onClose()
            },
            title = {
                Text(text = "Confirmação de exclusão")
            },
            text = {
                Column() {
                    Text("Tem certeza que deseja excluir a despesa?")
                }
            },
            confirmButton = {
                Button(onClick = { onRemove() }) {
                    Text(text = "Sim")
                }
            },
            dismissButton = {
                Button(onClick = { onClose() }) {
                    Text(text = "Não")
                }
            },
        )
    }
}
