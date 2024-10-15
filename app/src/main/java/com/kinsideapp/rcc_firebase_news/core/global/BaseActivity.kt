package com.kinsideapp.rcc_firebase_news.core.global

import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ScrollView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    /**
     * Hide keyboard
     * dismiss keyboard
     * @param view view that making request to hide keyboard
     */
    fun hideKeyboard(view :View) {
        //imm = input method manager
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    /**
     * Resize on keyboard open
     * resize screen scroll view when keyboard appears on screen
     * @param scrollView
     */
    fun resizeOnKeyboardOpen(scrollView: ScrollView) {
        scrollView.viewTreeObserver.addOnGlobalLayoutListener {
            val rect = Rect()
            scrollView.getWindowVisibleDisplayFrame(rect) // visible height without keyboard
            val heightDifference = scrollView.rootView.height - rect.bottom
            if (heightDifference > 100) { // Adjust the threshold as needed
                // Keyboard is open
                scrollView.layoutParams.height =
                    scrollView.rootView.height - heightDifference  // you can subtract padding as needed for more Adjustments
                scrollView.requestLayout()
            } else {
                // Keyboard is closed
                scrollView.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
                scrollView.requestLayout()
            }
        }
    }


    /***** SHOWING ON UI *****/
    fun showToastShort(msg: String) {
        val toastMsg = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    fun showToastLong(msg: String) {
        val toastMsg = Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
    /***** -END- SHOWING ON UI *****/
}