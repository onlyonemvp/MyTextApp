package com.example.mytextapp.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

class TestViewModel :ViewModel() {
    private  var mNameEvent  = MutableLiveData<String>()

    val mName:LiveData<String>
    get() = mNameEvent

    fun setmNane(name:String){
        mNameEvent.value= name
    }
}