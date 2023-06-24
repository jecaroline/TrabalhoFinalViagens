package com.example.aula1704.ui.theme

import android.app.Application
import android.widget.Space
import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.aula1704.viewModel.ListViagensViewModel
import com.example.aula1704.viewModel.ListViagensViewModelFactory

@Composable
@ExperimentalMaterialApi
fun ListViagem(onClickCard: () -> Unit) {
    val application = LocalContext.current.applicationContext as Application
    val viewModel: ListViagensViewModel = viewModel(
        factory = ListViagensViewModelFactory (application)
    )
    val navController = rememberNavController()

    viewModel.buscarViagens()

    var openDialogRemove by remember { mutableStateOf(false) }
    ConfirmDelete(openDialog = openDialogRemove,
        onRemove = {
            openDialogRemove = false
            viewModel.deleteViagem()
        },
        onClose = { openDialogRemove = false }
    )


    Column(modifier = Modifier
        .fillMaxWidth()
        .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Viagens disponíveis",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h5,
            color = Color.Gray,
        )
        LazyColumn() {

            items(items = viewModel.viagens.value) {

                Card (
                    elevation = 4.dp,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp)
                        .clickable { },
                    onClick = {
                        onClickCard()
                    }

                ) {
                    Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = com.example.aula1704.R.drawable.imagemviagem),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .weight(1f)
                                .size(50.dp),
                        )
                        Column(modifier = Modifier.weight(3f)){
                            Text(
                                text = "${it.destino}",
                                style = MaterialTheme.typography.h6,
                                textAlign = TextAlign.Start
                            )
                            Text(
                                text = "${it.dataInicio} até ${it.dataFim}",
                            )
                            Text(
                                text = "R$${it.orcamento},00",
                            )
                            Spacer(Modifier.weight(1f))

                        }
                        IconButton(
                            onClick = {
                                viewModel.viagemForDelete = it
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
fun ConfirmDelete(openDialog: Boolean, onClose: () -> Unit, onRemove: () -> Unit) {
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
                    Text("Tem certeza que deseja excluir a viagem?")
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
