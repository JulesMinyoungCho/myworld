package com.my.api.rest.v1.repository

import ch.qos.logback.classic.Logger
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.read.ListAppender
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
internal class QDataRepositoryTest {


    @Autowired
    private lateinit var qDataRepository: QDataRepository

    private val listAppender = ListAppender<ILoggingEvent>()


    @BeforeEach
    fun setUp(){
        val sqlLogger = LoggerFactory.getLogger("org.hibernate.SQL") as Logger

        sqlLogger.addAppender(listAppender)
        listAppender.start()
    }
    @AfterEach
    fun tearDown(){
        listAppender.stop()
    }

/*
    select
        ...
    from
        t_data data0_
 */
    @Test
    fun `전체 조회 테스트` () {

        val result = qDataRepository.selectAll()
        assertTrue{ listAppender.list.size == 1 }

        val query = listAppender.list[0].message

        assertTrue{
            query.contains("select")
            query.contains("from")
            query.contains("t_data")
        }
    }
}