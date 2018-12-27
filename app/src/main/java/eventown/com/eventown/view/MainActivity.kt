package eventown.com.eventown.view

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import eventown.com.eventown.R
import eventown.com.eventown.view.frgments.EventPhotosFragment
import eventown.com.eventown.view.frgments.FavoriteFragment
import eventown.com.eventown.view.frgments.HomeFragment
import eventown.com.eventown.view.frgments.MyAccountFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var currentFragment: Fragment
    private var backStateName: String = ""

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                if (currentFragment !is HomeFragment) {
                    replaceFragment(HomeFragment.newInstance(), getString(R.string.home_fragment))
                    currentFragment = HomeFragment()
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                if (currentFragment !is EventPhotosFragment) {
                    replaceFragment(EventPhotosFragment.newInstance(), getString(R.string.event_photo_fragment))
                    currentFragment = EventPhotosFragment()
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favorite -> {

                if (currentFragment !is FavoriteFragment) {
                    replaceFragment(FavoriteFragment.newInstance(), getString(R.string.favorite_fragment))
                    currentFragment = FavoriteFragment()
                }
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_account -> {

                if (currentFragment !is MyAccountFragment) {
                    replaceFragment(MyAccountFragment.newInstance(), getString(R.string.my_profile_fragment))
                    currentFragment = MyAccountFragment()
                }
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            addFragment(HomeFragment.newInstance(), getString(R.string.home_fragment))
            currentFragment = supportFragmentManager.findFragmentById(R.id.layFrame)!!
        }
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun addFragment(fragment: Fragment, fragmentTitle: String) {

        supportFragmentManager
            .beginTransaction()
            .add(R.id.layFrame, fragment, fragmentTitle)
            .commit()


    }

    private fun replaceFragment(fragment: Fragment, fragmentTitle: String) {

        backStateName = fragment::class.java.simpleName as String

        val fragmentPopped = supportFragmentManager.popBackStackImmediate(backStateName, 0)
        if (!fragmentPopped) {

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.layFrame, fragment, fragmentTitle)
                .addToBackStack(backStateName)
                .commit()
        }

    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
            selectBottomNavigationButton()
        } else {
            super.onBackPressed()
        }

    }

    private fun selectBottomNavigationButton() {
        when (currentFragment) {
            is EventPhotosFragment -> {

                navigation.selectedItemId = R.id.navigation_dashboard
            }
            is FavoriteFragment -> {
                navigation.selectedItemId = R.id.navigation_favorite
            }
            is MyAccountFragment -> {
                navigation.selectedItemId = R.id.navigation_account
            }
        }
    }
}
