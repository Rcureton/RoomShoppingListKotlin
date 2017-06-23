package com.bignerdranch.android.roomshoppinglistkotlin


import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

abstract class SLSingleFragmentActivity : AppCompatActivity() {

    protected abstract fun createFragment(): Fragment

    protected val layoutResId: Int
        @LayoutRes
        get() = R.layout.activity_slsingle_fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)

        val fragmentManager = supportFragmentManager
        var fragment: Fragment? = fragmentManager.findFragmentById(R.id.fragment_container)

        if (fragment == null) {
            fragment = createFragment()
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit()
        }
    }
}
