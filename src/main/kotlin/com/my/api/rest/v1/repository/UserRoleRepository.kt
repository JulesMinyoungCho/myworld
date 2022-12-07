package com.my.api.rest.v1.repository

import com.my.api.rest.v1.entity.UserRoleMap
import org.springframework.data.repository.CrudRepository

interface UserRoleRepository : CrudRepository<UserRoleMap, Long>
