package com.example.sandeep.passingdatatoandroidndk

class NativeLibrary {

    lateinit var call:callBack

    fun s(callBack: callBack){
        call=callBack
    }

    companion object {
        // Used to load the 'passingdatatoandroidndk' library on application startup.
        init {
            System.loadLibrary("passingdatatoandroidndk")
        }
    }


    external fun stringFromJNI(): String
    external fun passDataToJni(array: IntArray, number: Int, fnumber: Float): Int
    external fun passObjectToJni(sampleobj: Sample): Int
    external fun getDataObj():Sample
    external fun callBackMethod()


     fun oncall(data: Int, value: String) {
        call.onsucess(value+data)
    }
}
 interface callBack{
        fun onsucess(s:String)
}


