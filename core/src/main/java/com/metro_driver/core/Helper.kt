package com.metro_driver.core

import java.util.Calendar

class Helper {
    companion object {
        fun getCurrentDateTime(): String {
            //create image name
            val c = Calendar.getInstance()
            val y = c.get(Calendar.YEAR)
            val M = c.get(Calendar.MONTH) + 1
            val d = c.get(Calendar.DAY_OF_MONTH)
            val h = c.get(Calendar.HOUR_OF_DAY)
            val m = c.get(Calendar.MINUTE)
            val s = c.get(Calendar.SECOND)
            val mil = c.get(Calendar.MILLISECOND)
            return "$d-$M-${y}T$h:$m:$s:$mil"
        }
    }
}