<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${daoPackagePath}.${className}Dao">

    <!--配置一个LRU缓存（二级缓存），每隔60秒进行刷新，存储512个对象-->
    <!--
        LRU     最近最少使用策略，移除最长时间不被使用的对象
        FIFO    先进先出策略
        SOFT    软引用策略，移除基于垃圾回收器状态和软引用规则的对象
        WEAK    弱引用策略，更积极地移除基于垃圾回收器状态和软引用规则的对象
    -->
    <cache eviction="LRU" flushInterval="60000" size="512" readOnly="true"/>

    <insert id="add" parameterType="${className?lower_case}">
        insert
        into springmvcdemo.futao_${className?lower_case} ()
        values ();
    </insert>

    <delete id="delete" parameterType="string">
        delete
        from springmvcdemo.futao_${className?lower_case}
        where id = ${r"#{id}"}
    </delete>

    <update id="update" parameterType="${className?lower_case}">
        update springmvcdemo.futao_${className?lower_case}
        set
        where id = ${r"#{id}"}
    </update>

    <select id="list">
        select *
        from springmvcdemo.futao_${className?lower_case}
    </select>

    <select id="byId" parameterType="string">
        select *
        from springmvcdemo.futao_${className?lower_case}
        where id = ${r"#{id}"}
    </select>
</mapper>