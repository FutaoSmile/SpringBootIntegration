package com.futao.springmvcdemo

import com.futao.springmvcdemo.model.entity.User
import com.futao.springmvcdemo.utils.PageResultUtils
import com.futao.springmvcdemo.utils.getFieldName
import com.futao.springmvcdemo.utils.md5
import org.junit.Test

/**
 * @author futao
 * Created on 2018/10/15.
 */
class KotlinTest {
    @Test
    fun test2() {
        val pageResultUtils = PageResultUtils<User>()
        println(User::getAddress.getFieldName())
    }

    @Test
    fun test1() {
        println("1".md5())
    }

    @Test
    fun test3() {
        val temp = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)
        println(subFinal10(temp))
    }

    fun <T> subFinal10(list: List<T>): List<T> {
        val size = list.size
        return list.subList(size - 10, size)

        //return list.reversed().subList(0, 10).reversed()
    }


}