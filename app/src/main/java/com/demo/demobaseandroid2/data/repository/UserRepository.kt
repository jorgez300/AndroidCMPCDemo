package com.demo.demobaseandroid2.data.repository

import com.demo.demobaseandroid2.data.dao.UserDao
import com.demo.demobaseandroid2.data.entity.User

class UserRepository(private val userDao: UserDao) {

    suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    suspend fun getUserByUsernameAndPassword(username: String, password: String): User? {
        return userDao.getUserByUsernameAndPassword(username, password)
    }
}