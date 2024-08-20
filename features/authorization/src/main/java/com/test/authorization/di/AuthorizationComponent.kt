package com.test.authorization.di

import com.test.authorization.presentation.viewmodel.CheckAuthCodeViewModelFactory
import com.test.authorization.presentation.viewmodel.RegistrationViewModelFactory
import com.test.authorization.presentation.viewmodel.SendAuthCodeViewModelFactory
import com.test.data.api.AppDataPreference
import dagger.Component

@Component(modules = [AuthorizationModule::class], dependencies = [AuthorizationFeatureDeps::class])
@AuthorizationScope
internal interface AuthorizationComponent {

    fun getSendAuthViewModelFactory() : SendAuthCodeViewModelFactory
    fun getCheckAuthViewModelFactory() : CheckAuthCodeViewModelFactory
    fun getRegistrationViewModelFactory() : RegistrationViewModelFactory
    fun getDataPreference() : AppDataPreference

    @Component.Builder
    interface Builder{
        fun addDeps(deps:AuthorizationFeatureDeps): Builder
        fun build(): AuthorizationComponent
    }

}