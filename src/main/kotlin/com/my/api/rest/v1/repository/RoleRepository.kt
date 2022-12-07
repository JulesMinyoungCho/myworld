package com.my.api.rest.v1.repository

import com.my.api.rest.v1.entity.Role
import org.springframework.data.repository.CrudRepository

interface RoleRepository : CrudRepository<Role, String>