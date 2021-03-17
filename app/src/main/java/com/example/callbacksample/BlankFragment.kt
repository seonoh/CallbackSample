package com.example.callbacksample

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BlankFragment : Fragment {

    constructor(listener: MainActivity.IOnChangedTextCallBack){
        this.callback = listener
    }

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var et: EditText
    lateinit var changedText: String
    lateinit var callback: MainActivity.IOnChangedTextCallBack

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_blank, container, false)
        et = view.findViewById(R.id.blankEt)
        et.doOnTextChanged { text, start, before, count ->
            Log.e("SeonohTest#1", "text : $text")
            Log.e("SeonohTest#1", "start : $start")
            Log.e("SeonohTest#1", "before : $before")
            Log.e("SeonohTest#1", "count : $count")
            changedText = text.toString()
            callback.onTextChanged(text.toString())
        }
        return view
    }

    fun getChangedText() {
//        Toast.makeText(activity, changedText, Toast.LENGTH_LONG).show()
    }

//    companion object {
//        @JvmStatic
//        fun newInstance(listener: MainActivity.IOnChangedTextCallBack) =
//            BlankFragment(listener).apply {
//                this.callback = listener
//            }
//    }


}