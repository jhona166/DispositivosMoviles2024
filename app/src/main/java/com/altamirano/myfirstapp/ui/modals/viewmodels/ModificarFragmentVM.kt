package com.altamirano.myfirstapp.ui.modals.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ModificarFragmentVM: ViewModel() {
    var cLive = MutableLiveData<Int>()
    private var c =1
    fun funcionContar(){
        c+=1
        cLive.postValue(c)
    }


}