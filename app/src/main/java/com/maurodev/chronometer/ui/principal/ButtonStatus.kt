package com.maurodev.chronometer.ui.principal

import com.maurodev.chronometer.utils.DEFAULT_VALUE

data class ButtonStatus(
    var valuePlay: Int? = DEFAULT_VALUE,
    var valueStop: Int? = DEFAULT_VALUE,
    var valuePause: Int? = DEFAULT_VALUE
)