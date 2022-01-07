package com.example.sandeep.passingdatatoandroidndk

 class Sample(){

      var sint:Int=0

     fun sampleInt(sint:Int){
         this.sint=sint
     }
     fun getsampleInt(): Int {
         return sint
     }

     var sboolean:Boolean=false

     fun sampleboolean(sboolean:Boolean){
         this.sboolean=sboolean
     }
     fun getsampleboolean(): Boolean? {
         return sboolean
     }

     var sString:String?=null

     fun sampleString(sString: String){
         this.sString=sString
     }

     fun getsampleString():String?{
         return sString
     }


 }