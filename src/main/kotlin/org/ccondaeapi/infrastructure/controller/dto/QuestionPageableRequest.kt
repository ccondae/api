package org.ccondaeapi.infrastructure.controller.dto

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable
import org.springframework.data.web.PageableDefault

data class QuestionPageableRequest(
        var categories: List<Int>,
) {

}