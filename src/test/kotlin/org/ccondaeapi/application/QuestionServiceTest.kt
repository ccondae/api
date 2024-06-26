package org.ccondaeapi.application

import jakarta.transaction.Transactional
import org.assertj.core.api.Assertions.assertThat
import org.ccondaeapi.domain.dto.QuestionUpload
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@Transactional
class QuestionServiceTest(
        @Autowired
        private val questionService: QuestionService
) {
    @Test
    fun 저장_테스트() {
        // given
        var question = QuestionUpload(
                title = "Sample Title",
                text = "Sample Text",
                categoryIds = listOf(1,2)
        )

        // when
        var savedQuestion = questionService.upload(question)

        // then
        assertThat(savedQuestion.id).isNotNull()
        assertThat(savedQuestion.createdAt).isNotNull()
        assertThat(savedQuestion.categories.count()).isEqualTo(2)
        assertThat(savedQuestion.title).isEqualTo(question.title)
    }

    @Test
    fun ID로_찾기_TEST() {
        // given
        var question = QuestionUpload(
                title = "Sample Title",
                text = "Sample Text",
                categoryIds = listOf(1,2)
        )

        // when
        var savedQuestion = questionService.upload(question)
        var searchedQuestion = questionService.getDetail(savedQuestion.id!!)

        // then
        assertThat(searchedQuestion.id).isNotNull()
        assertThat(searchedQuestion.createdAt).isNotNull()
        assertThat(searchedQuestion.title).isEqualTo(question.title)
    }

}