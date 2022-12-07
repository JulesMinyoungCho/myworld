package com.my.api.rest.v1.controller

import com.my.api.common.RestUris
import com.my.api.domain.dto.req.LoginRequest
import com.my.api.rest.AbstractRestController
import com.my.api.rest.v1.service.UserService
import io.swagger.annotations.Api
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping(RestUris.API_V1 + RestUris.AUTH)
@Api(value = "로그인 컨트롤러")
@Validated
class LoginController (
    val userService: UserService
) : AbstractRestController() {

    @PostMapping("/login")
    fun login(@RequestBody @Valid loginRequest: LoginRequest) : ResponseEntity<Any>{
        val loginResult = userService.login(loginRequest.id!!, loginRequest.password!!)
        return response(loginResult)
    }

}