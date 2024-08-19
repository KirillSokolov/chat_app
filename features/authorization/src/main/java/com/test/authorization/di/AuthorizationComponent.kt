package com.test.authorization.di

import com.test.authorization.presentation.viewmodel.SendAuthCodeViewModelFactory
import dagger.Component

@Component(modules = [AuthorizationModule::class], dependencies = [AuthorizationFeatureDeps::class])
@AuthorizationScope
internal interface AuthorizationComponent {

    fun getSendAuthViewModelFactory() : SendAuthCodeViewModelFactory

    @Component.Builder
    interface Builder{
        fun addDeps(deps:AuthorizationFeatureDeps): Builder
        fun build(): AuthorizationComponent
    }

}