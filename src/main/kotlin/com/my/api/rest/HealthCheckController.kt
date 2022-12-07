package com.my.api.rest

import com.my.api.common.constants.RestUriProvider
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(RestUriProvider.HEALTH)
class HealthCheckController : BaseRestController(){
    @GetMapping
    fun health(): ResponseEntity<Any>
        = response("OK")
}