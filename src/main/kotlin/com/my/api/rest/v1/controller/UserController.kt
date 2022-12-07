package com.my.api.rest.v1.controller

import com.my.api.common.RestUris
import com.my.api.rest.AbstractRestController
import com.my.api.rest.v1.service.UserService
import io.swagger.annotations.Api
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(RestUris.API_V1 + RestUris.USER)
@Api(value = "유저 컨트롤러")
class UserController (
    val userService: UserService
) : AbstractRestController() {

    @GetMapping("/list")
    fun getUsers() : ResponseEntity<Any> {
        val result = userService.selectAllUser()
        return response(result)
    }
}