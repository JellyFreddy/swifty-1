package com.example.swifty_component.base

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.swifty_component.ApiViewModel
import com.example.swifty_component.ApiViewModelProviderFactory
import com.example.swifty_component.api.Resource
import com.example.swifty_component.navgraph.NavGraph
import com.example.swifty_component.repository.Repository
import com.example.swifty_component.utils.Constants

class MainActivity : ComponentActivity() {

    private lateinit var apiViewModel: ApiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = Repository(this)
        val tokenViewModelProviderFactory = ApiViewModelProviderFactory(repository)
        apiViewModel = ViewModelProvider(this, tokenViewModelProviderFactory)[ApiViewModel::class.java]

        if(!apiViewModel.checkIfTokenIsPresent())
            getTokenFunction()

        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.White
            ) {
                val navController = rememberNavController()
                NavGraph(apiViewModel, navController)
            }
        }
    }

    fun getTokenFunction() {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://api.intra.42.fr/oauth/authorize?client_id=${Constants.UID}&redirect_uri=${Constants.REDIRECT_URL}&response_type=code")
        )
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()

        val uri = intent.data
        if (uri != null && uri.toString().startsWith(Constants.REDIRECT_URL)) {
            val code = requireNotNull(uri.getQueryParameter("code"))
            apiViewModel.getToken(
                grantType = "authorization_code",
                clientId = Constants.UID,
                clientSecret = Constants.SECRET,
                code = code
            )
            apiViewModel.responseQueue.value = Resource.Loading()
        }
    }
}
