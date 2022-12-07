package com.my.api.rest.v1.controller

import com.my.api.common.constants.RestUriProvider
import com.my.api.rest.BaseRestController
import com.my.api.rest.v1.domain.req.DataRequest
import com.my.api.rest.v1.service.DataService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(RestUriProvider.DATA)
class DataController(
    private val dataService: DataService
) : BaseRestController(){

    @PostMapping
    fun createData(@RequestBody req: DataRequest): ResponseEntity<Any> {
        return response(dataService.createData(req))
    }

    @GetMapping
    fun getData(): ResponseEntity<Any> {
        return response(dataService.getData())
    }


    // PUT - update

    // PATCH - 일부 update

    // DELETE - 삭제
}