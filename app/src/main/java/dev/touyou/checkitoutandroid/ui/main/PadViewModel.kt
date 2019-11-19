package dev.touyou.checkitoutandroid.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.touyou.checkitoutandroid.entity.PlayMode
import dev.touyou.checkitoutandroid.R
import io.realm.Realm

class PadViewModel : ViewModel() {
    val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }

    val assignedSound by lazy {
        MutableLiveData<List<Int?>>(sounds.toList())
    }
    var currentMode = MutableLiveData<PlayMode>(PlayMode.PLAY)
    var selectedPad: Int? = null

    var sounds = mutableListOf(
        R.raw.touyou, R.raw.chekera_cut, R.raw.minnade_cut, null,
        R.raw.touyou, R.raw.chekera_cut, R.raw.minnade_cut, null,
        R.raw.touyou, R.raw.chekera_cut, R.raw.minnade_cut, null,
        R.raw.touyou, R.raw.chekera_cut, R.raw.minnade_cut, null
    )

    override fun onCleared() {
        realm.close()
        super.onCleared()
    }

    fun changeSound(index: Int, resId: Int) {
        sounds[index] = resId
        assignedSound.value = sounds.toList()
    }

    fun changeMode(mode: PlayMode) {
        currentMode.value = mode
        if (mode != PlayMode.EDIT) selectedPad = null
    }
}