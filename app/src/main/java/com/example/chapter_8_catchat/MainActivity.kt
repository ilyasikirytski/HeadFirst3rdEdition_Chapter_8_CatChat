package com.example.chapter_8_catchat

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolBar = findViewById<MaterialToolbar>(R.id.materialToolbar)
        setSupportActionBar(toolBar)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
//
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val builder = AppBarConfiguration.Builder(
            R.id.inboxFragment,
            R.id.sentItemsFragment,
            R.id.helpFragment
        )
        builder.setOpenableLayout(drawer)
        val appBarConfiguration = builder.build()
        toolBar.setupWithNavController(navController, appBarConfiguration)

// link bottom nav bar to the navigation controller
//        val bottomNavView = findViewById<BottomNavigationView>(R.id.bottom_nav)
//        bottomNavView.setupWithNavController(navController)

        val navView = findViewById<NavigationView>(R.id.nav_view)
        NavigationUI.setupWithNavController(navView, navController)
        navView.menu.getItem(0).isChecked = true
    }

    //    this two methods need only for toolbar menu
//    they are not needed for bottom navigation bar/drawer
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        val navView = findViewById<NavigationView>(R.id.nav_view)
        navView.setCheckedItem(item)
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }
}