package com.example.pointmax2.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.pointmax2.R
import com.example.pointmax2.data.database.CardRoomDatabase
import com.example.pointmax2.data.database.entities.CardItem
import com.example.pointmax2.data.repositories.DefaultCardRepository
import com.example.pointmax2.ui.wallet.WalletFragmentDirections
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class PointMaxActivity : AppCompatActivity() {

    lateinit var viewModel: PointMaxViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Plant tree to enable Debugging with Timber
        Timber.plant(Timber.DebugTree())

        // Find the bottomNavigation bar
        val navView: BottomNavigationView = findViewById(R.id.bottom_navigation_view)

        // Find the fragment that will host the different fragments
        val navController = findNavController(R.id.nav_host_fragment)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                    R.id.navigation_home, R.id.navigation_wallet
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        // Set the visibility of the navigation bar and the FAB
        navController.addOnDestinationChangedListener{_, destination, _ ->
            when (destination.id) {
                R.id.navigation_home -> {
                    navView.visibility = View.VISIBLE
                    walletFAB.visibility = View.GONE
                }
                R.id.navigation_wallet -> {
                    navView.visibility = View.VISIBLE
                    walletFAB.visibility = View.VISIBLE
                }
                R.id.navigation_addCustomCardFragment -> {
                    navView.visibility = View.GONE
                    walletFAB.visibility = View.GONE
                }
            }
        }

        walletFAB.setOnClickListener {
            val action = WalletFragmentDirections.actionNavigationWalletToNavigationAddCustomCardFragment(CardItem(cardName = ""))
            navController.navigate(action)
        }

        // Move this to Dependency Injection.
        val database = CardRoomDatabase
        //val repository = CardRepository(database.getDatabase(this, application).cardDao())
        val repository = DefaultCardRepository(CardRoomDatabase(this))
        val viewModelFactory = PointMaxViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory)[PointMaxViewModel::class.java]

    }
}