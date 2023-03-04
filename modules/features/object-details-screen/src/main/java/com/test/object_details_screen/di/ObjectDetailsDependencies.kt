package com.test.object_details_screen.di

import com.test.core_domain.usecases.GetObjectDetailsUseCase
import com.test.object_details_screen.ObjectDetailsRouter

interface ObjectDetailsDependencies {
    fun getObjectDetailsRouter(): ObjectDetailsRouter
    fun getObjectDetailsUseCase(): GetObjectDetailsUseCase
}