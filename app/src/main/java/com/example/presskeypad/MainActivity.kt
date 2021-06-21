package com.example.presskeypad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    private var leftHandValue: Int = 10
    private var rightHandValue: Int = 12

    private var baseHand: String = ""
    private val leftHand: String = "L"
    private val rightHand: String = "R"
    private var tempHand: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val numbers1: ArrayList<Int> = arrayListOf(1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5) //  "right"	"LRLLLRLLRRL"
        val numbers2: ArrayList<Int> = arrayListOf(7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2) // 	"left"	"LRLLRRLLLRR"
        val numbers3: ArrayList<Int> = arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 0)    //  "right"	"LLRLLRLLRL"

        solution(numbers2, leftHand)

    }

    private fun solution(numbers: ArrayList<Int>, hand: String): String {
        var answer = ""
        baseHand = if (hand == "right") rightHand else leftHand
        val stringBuffer = StringBuffer()

        for (i in numbers) {
            stringBuffer.append(initHandPostion(i))
        }

        answer = stringBuffer.toString()

        return answer
    }

    private fun initHandPostion(num: Int): String {
        return when (num) {
            1, 4, 7 -> {
                tempHand = leftHand
                leftHandValue = num
                return tempHand as String
            }
            3, 6, 9 -> {
                tempHand = rightHand
                rightHandValue = num
                return tempHand as String
            }
            0 -> checkMiddle(11)
            else -> checkMiddle(num)
        }
    }

    private fun checkMiddle(middleNum: Int): String {

        val rightResult = distance(middleNum, rightHandValue)
        val leftResult = distance(middleNum, leftHandValue)

        Log.e("middleNum:", "$middleNum")
        Log.e(TAG, "left => Value: $leftHandValue Result: $leftResult")
        Log.e(TAG, "right => Value: $rightHandValue, Result: $rightResult")

        return when {
            leftResult < rightResult -> {
                leftHandValue = middleNum
                return leftHand
            }
            leftResult > rightResult -> {
                rightHandValue = middleNum
                return rightHand
            }
            leftResult == rightResult -> {
                if (baseHand == leftHand) leftHandValue = middleNum else rightHandValue = middleNum
                return  if (baseHand == leftHand) leftHand else rightHand
            }
            else -> "Nottings"
        }

    }

    private fun distance(num:Int, hand: Int) : Int {
        return (Math.abs(num - hand)) % 3  +  (Math.abs(num - hand)) / 3
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

}