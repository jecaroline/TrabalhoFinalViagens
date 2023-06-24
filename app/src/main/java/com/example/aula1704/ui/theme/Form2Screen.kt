package com.example.aula1704.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
@ExperimentalMaterialApi

fun Form2Screen(param: String?) {
/* Column(modifier = Modifier
.fillMaxSize()
.background(Color.Green)) {

Text(
text = "Form2",
textAlign = TextAlign.Center,
)
Text(
text = "Nome: $param",
textAlign = TextAlign.Center,
)
}*/
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation() {
                BottomNavigationItem(
                    selected = true,
                    onClick = { navController.navigate("tela1")
                    },
                    label = {
                        Text(text = "Viagens")
                    },
                    icon = {
                        Icon(Icons.Filled.Home, contentDescription = "")
                    }
                )
                BottomNavigationItem(
                    selected = true,
                    onClick = { navController.navigate("tela2")
                    },
                    label = {
                        Text(text = "Adicionar")
                    },
                    icon = {
                        Icon(Icons.Filled.Add, contentDescription = "")
                    }
                )
                BottomNavigationItem(
                    selected = true,
                    onClick = { navController.navigate("tela3")
                    },
                    label = {
                        Text(text = "Sobre")
                    },
                    icon = {
                        Icon(Icons.Filled.AccountBox, contentDescription = "")
                    }
                )
            }
        }) {
        NavHost(
            navController = navController,
            startDestination = "tela1",
            modifier = Modifier.padding(paddingValues = it)
        )
        {
            composable("tela1") {
                ListViagem(onClickCard = {
                    navController.navigate("telaDespesa")
                })
            }
            composable("tela2") {
                Tela2()
            }

            composable("tela3") {
                Tela3()
            }
            composable("telaDespesa") {
                ListDespesas(onClickCardDep = {
                    navController.navigate("telaCadDespesa")
                })
            }
            composable("telaCadDespesa") {
                Tela4()
            }
        }
    }
}