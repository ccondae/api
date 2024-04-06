package org.ccondaeapi.infrastructure.repository.impl

import jakarta.transaction.Transactional
import org.ccondaeapi.application.QuestionService
import org.ccondaeapi.domain.dto.QuestionUpload
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest @Transactional
class QuestionRepositoryImplTest(
        @Autowired
        private val questionRepositoryImpl: QuestionRepositoryImpl,
        @Autowired
        private val questionService: QuestionService
){
    @Test
    fun 커멘트가_달리지_않은_질문_조회(){
        // given
        var question = QuestionUpload(
                title = "Sample Title",
                text = "Sample Text",
                categoryIds = listOf(1,2)
        )

        // when
        var savedQuestion = questionService.upload(question)

        // when
        val result = questionRepositoryImpl.notAnsweredQuestion()
        // then
        assertNotNull(result)
    }


}