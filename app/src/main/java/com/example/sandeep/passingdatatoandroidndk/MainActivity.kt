package com.example.sandeep.passingdatatoandroidndk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.sandeep.passingdatatoandroidndk.R.id.*
class MainActivity : AppCompatActivity() {

   // var parm:callBack=nativeLib.call
    var nativeLib = NativeLibrary()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Example of a call to a native method
    }

    /**
     * A native method that is implemented by the 'passingdatatoandroidndk' native library,
     * which is packaged with this application.
     */


    fun onclick(view: android.view.View) {

        var btnid = view.id

        when (btnid) {

            btnget -> {
                var c = findViewById<TextView>(txtget)
                c.text = nativeLib.stringFromJNI()
            }

            btnpass -> {
                var array = intArrayOf(1, 2, 3)
                var number = 4
                var fnumber = 5.8f
                var c = findViewById<TextView>(txtpass)
                var result = nativeLib.passDataToJni(array, number, fnumber)
                if (result == 0) {
                    c.text = "Success"
                } else {
                    c.text = "Faild"
                }
            }
            btnobj -> {
                var sampleobj = Sample()
                sampleobj.sampleInt(5)
                sampleobj.sampleboolean(true)
                sampleobj.sampleString("Faild")

                var ret = nativeLib.passObjectToJni(sampleobj)
                var tv = findViewById<TextView>(txtobj)

                if (ret == 5) {
                    tv.text = sampleobj.getsampleString()

                } else {
                    tv.text = "Faild"
                }
            }

            btngetobj -> {
                var dataobj = Sample()
                dataobj = nativeLib.getDataObj()
                var tv = findViewById<TextView>(txtgetobj)
                tv.text = dataobj.getsampleString()

            }

            btncallback -> {


                var tv = findViewById<TextView>(txtcallback)
                oncall1(object : callBack {
                    override fun onsucess(s: String) {
                        tv.text = s                    }

                })
                nativeLib.callBackMethod()


            }
            else -> Toast.makeText(this, "bye", Toast.LENGTH_SHORT).show()
        }

    }

    private fun oncall1(param: callBack) {
        nativeLib.s(param)
    }


}



