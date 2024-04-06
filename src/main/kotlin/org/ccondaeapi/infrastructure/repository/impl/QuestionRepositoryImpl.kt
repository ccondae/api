package org.ccondaeapi.infrastructure.repository.impl

import com.querydsl.jpa.impl.JPAQueryFactory
import org.ccondaeapi.domain.converter.QuestionConverter
import org.ccondaeapi.domain.dto.SimpleQuestionResponse
import org.ccondaeapi.entity.QComment
import org.ccondaeapi.entity.QComment.comment
import org.ccondaeapi.entity.QQuestion
import org.ccondaeapi.entity.QQuestion.question
import org.ccondaeapi.entity.QQuestionCategory
import org.ccondaeapi.entity.Question
import org.ccondaeapi.infrastructure.repository.ifs.QuestionRepositoryCustom
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.support.PageableExecutionUtils

class QuestionRepositoryImpl(
        private val queryFactory: JPAQueryFactory,
        private val questionConverter: QuestionConverter
) : QuestionRepositoryCustom {

    override fun notAnsweredQuestionByCategories(categories: List<Long>, pageable: Pageable): Page<SimpleQuestionResponse> {
        val qQuestion = QQuestion.question
        val qComment = QComment.comment
        val qQuestionCategory = QQuestionCategory.questionCategory

        val questionsQuery = queryFactory.selectFrom(qQuestion)
                .leftJoin(qQuestion.comments, qComment)
                .join(qQuestion.categories, qQuestionCategory)
                .where(qQuestionCategory.category.id.`in`(categories).and(qComment.id.isNull))
                .offset(pageable.offset)
                .limit(pageable.pageSize.toLong())

        val questions: List<Question> = questionsQuery.fetch()
        val dtoList: List<SimpleQuestionResponse> = questions.map { questionConverter.toSimpleResponse(it) }

        val countQuery = queryFactory.select(qQuestion.count())
                .from(qQuestion)
                .leftJoin(qQuestion.comments, qComment)
                .join(qQuestion.categories, qQuestionCategory)
                .where(qQuestionCategory.category.id.`in`(categories).and(qComment.id.isNull))

        val count: Long = countQuery.fetchOne() ?: 0L

        return PageableExecutionUtils.getPage(dtoList, pageable) { count }
    }

    override fun getPopularQuestion(pageable: Pageable): Page<SimpleQuestionResponse> {
        val qQuestion = QQuestion.question
        val qComment = QComment.comment
        val qQuestionCategory = QQuestionCategory.questionCategory
        val questionsQuery = queryFactory.selectFrom(qQuestion)
                .leftJoin(qQuestion.comments, qComment)
                .join(qQuestion.categories, qQuestionCategory)
                .orderBy(qQuestion.likeCount.desc())
                .offset(pageable.offset)
                .limit(pageable.pageSize.toLong())

        val questions: List<Question> = questionsQuery.fetch()
        val dtoList: List<SimpleQuestionResponse> = questions.map { questionConverter.toSimpleResponse(it) }

        val count: Long = queryFactory.select(qQuestion.count()).from(qQuestion).fetchOne() ?: 0L

        return PageableExecutionUtils.getPage(dtoList, pageable) { count }
    }

    fun notAnsweredQuestion(): List<Question> {
        return queryFactory.selectFrom(question)
                .leftJoin(question.comments, comment)
                .where(question.comments.isEmpty)
                .fetch()
    }
}