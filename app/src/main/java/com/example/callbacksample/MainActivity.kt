package com.example.callbacksample

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    interface IOnChangedTextCallBack{
        fun onTextChanged(replaceString: String?)
    }

    private lateinit var nextFragmentType: NEXT_TYPE
    private val blankFragment: BlankFragment by lazy {
        BlankFragment(object : IOnChangedTextCallBack {
            override fun onTextChanged(replaceString: String?) {
                Toast.makeText(applicationContext,"여기타면된다",Toast.LENGTH_LONG).show()

            }
        })

    }

    private val blankFragment2: BlankFragment2 by lazy {
        BlankFragment2.newInstance()
    }

    enum class NEXT_TYPE {
        FIRST_FRAGMENT,
        SECOND_FRAGMENT
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            onReplaceFragment(NEXT_TYPE.FIRST_FRAGMENT)
        }


        val btn: Button = findViewById(R.id.changeBtn)
        btn.setOnClickListener {
            onReplaceFragment(nextFragmentType)
            blankFragment.getChangedText()
        }
    }

    private fun onReplaceFragment(type: NEXT_TYPE) {
        val fragment: Fragment
        when (type) {
            NEXT_TYPE.FIRST_FRAGMENT -> {
                nextFragmentType = NEXT_TYPE.SECOND_FRAGMENT
                fragment = blankFragment

            }
            NEXT_TYPE.SECOND_FRAGMENT -> {
                nextFragmentType = NEXT_TYPE.FIRST_FRAGMENT
                fragment = blankFragment2
            }
        }

        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.apply {
            replace(R.id.fragment_container_view, fragment)
            addToBackStack(null)
            commit()
        }
    }
}