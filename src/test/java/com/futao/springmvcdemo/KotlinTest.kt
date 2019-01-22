package com.futao.springmvcdemo

import com.futao.springmvcdemo.model.entity.User
import com.futao.springmvcdemo.service.MailService
import com.futao.springmvcdemo.utils.*
import org.junit.Test
import javax.annotation.Resource

/**
 * @author futao
 * Created on 2018/10/15.
 */
//@SpringBootTest
//@RunWith(SpringRunner::class)
class KotlinTest {
    @Test
    fun test9() {
        println(parseStringToTwoDecimalStr(""))
    }
    @Test
    fun test7() {
//        val connection = URL("https://www.baidu.com").openConnection()
//        connection.setRequestProperty("", "")
//        val inputStream = connection.getInputStream()
//        val bufferedInputStream = BufferedInputStream(inputStream)
//        val streamReader = InputStreamReader(bufferedInputStream)
//        var c = streamReader.read()
//        while ((c = streamReader.read()) > 0) {
//            println(c.toChar())
//        }
    }

    @Resource
    lateinit var mailService: MailService

    @Test
    fun test6() {

    }

    @Test
    fun test4() {
        val arr: Array<String>? = arrayOf("1", "2", "3")
        println(arr!!.joinToString { it })
    }

    @Test
    fun test2() {
        PageResultUtils<User>()
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