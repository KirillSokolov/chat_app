package com.test.authorization.di

import com.test.authorization.presentation.ui.CheckAuthCodeFragment
import com.test.authorization.presentation.ui.RegistrationFragment
import com.test.authorization.presentation.ui.SendAuthCodeFragment
import dagger.Component

@Component(modules = [AuthorizationModule::class], dependencies = [AuthorizationFeatureDeps::class])
@AuthorizationScope
internal interface AuthorizationComponent {
    fun inject(fragment: SendAuthCodeFragment)
    fun inject(fragment: CheckAuthCodeFragment)
    fun inject(fragment: RegistrationFragment)

    @Component.Builder
    interface Builder{
        fun addDeps(deps:AuthorizationFeatureDeps): Builder
        fun build(): AuthorizationComponent
    }

}