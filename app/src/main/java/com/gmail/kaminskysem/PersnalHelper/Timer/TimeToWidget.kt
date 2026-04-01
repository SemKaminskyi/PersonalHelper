package com.gmail.kaminskysem.PersnalHelper.Timer

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.Serializable

class TimeToWidget : Serializable {
    
    @Transient // to avoid serialization issues with Flow
    private val _timeFlow = MutableStateFlow(0)
    
    val timeFlow: StateFlow<Int> 
        get() = _timeFlow.asStateFlow()

    var time: Int
        get() = _timeFlow.value
        set(value) {
            _timeFlow.value = value
        }
}
