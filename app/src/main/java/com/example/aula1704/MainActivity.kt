package com.example.aula1704

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.aula1704.ui.theme.*
import kotlinx.coroutines.launch
import java.text.Normalizer.Form


@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Aula1704Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
@ExperimentalMaterialApi
fun MyApp(){
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    NavHost(navController = navController, startDestination = "home"){
      composable("home")  {
          HomeScreen(onNavigateForm1 = {navController.navigate("Form1Screen")},
              onAfterLogin = {
                  navController.navigate("form2/${it}")
                  coroutineScope.launch {
                      scaffoldState.snackbarHostState.showSnackbar(
                          message = "Login ok"
                      )
                  }
              }
          )
      }

      composable("Form1Screen"){
          Form1Screen(onNavigate = {navController.navigate(it)}, onBackScreen = {navController.navigateUp()})
          }
          //onNavigateForm2 = {
         //     navController.navigate("form2/${it}")
         // }
      //}

      composable("form2/{name}",
      arguments = listOf(navArgument("name") {type = NavType.StringType})
      ){
          val param = it.arguments?.getString("name")
          Form2Screen(param)
      }
    }
}

@Preview(showBackground = true)
@Composable
@ExperimentalMaterialApi
fun DefaultPreview() {
    Aula1704Theme {
        MyApp()
    }
}