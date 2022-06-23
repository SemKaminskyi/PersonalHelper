package com.gmail.kaminskysem.PersnalHelper.Main

import android.content.Context
import com.gmail.kaminskysem.PersnalHelper.R
import timber.log.Timber
import kotlin.random.Random

class Autoscrolling {
    fun scroll(context: Context) {
        val arrayWorld = context.resources.getStringArray(R.array.words)
        val numRandom = Random.nextInt(0,arrayWorld.size)

        Timber.d("numRnd is $numRandom array size ${arrayWorld.size}")

    }
}