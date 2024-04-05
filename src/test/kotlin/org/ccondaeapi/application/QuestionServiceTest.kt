package org.ccondaeapi.application

import jakarta.transaction.Transactional
import org.assertj.core.api.Assertions.assertThat
import org.ccondaeapi.entity.Question
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
        var question = Question(
                title = "Sample Title",
                text = "Sample Text",
                likeCount = 0,
                viewCount = 0,
                categories = listOf(),
                comments = listOf()
        )

        // when
        var savedQuestion = questionService.save(question)

        // then
        assertThat(savedQuestion.id).isNotNull()
        assertThat(savedQuestion.createdAt).isNotNull()
        assertThat(savedQuestion.title).isEqualTo(question.title)
    }

    @Test
    fun ID로_찾기_TEST() {
        // given
        var question = Question(
                title = "Sample Title",
                text = "Sample Text",
                likeCount = 0,
                viewCount = 0,
                categories = listOf(),
                comments = listOf()
        )

        // when
        var savedQuestion = questionService.save(question)
        var searchedQuestion = questionService.findById(savedQuestion.id!!)

        // then
        assertThat(searchedQuestion.id).isNotNull()
        assertThat(searchedQuestion.createdAt).isNotNull()
        assertThat(searchedQuestion.title).isEqualTo(question.title)
    }

}