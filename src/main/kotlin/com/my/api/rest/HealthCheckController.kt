package com.my.api.rest

import com.my.api.common.RestUris
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("${RestUris.API_V1}/health")
class HealthCheckController : AbstractRestController(){

    @GetMapping
    fun healthCheck() : ResponseEntity<Any>
        = response("OK")
}