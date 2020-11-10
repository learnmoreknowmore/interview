package com.decay.jetpack.flow

import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList

object FlowTest {

//    中间操作符是 Flow 的扩展函数，它们最后都是通过 emit 来发射数据
//    末端操作符是一个挂起函数
//    末端操作符无论是 collectLatest , single , reduce , toList 最后都是调用 collect
    suspend fun printValue() = flow<Int> {
        for (index in 1..10) {
            emit(index)
        }
    }.map { it -> it * it } // map, filter, take, zip 等等是中间操作符
        .filter { it -> it > 5 }
        .toList() // 只有遇到末端操作符 collect, collectLatest,single, reduce, toList 等等才会触发所有操作的执行

}