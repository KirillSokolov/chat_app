package com.test.user.details.di

import com.test.user.details.presentation.viewmodel.UserDetailsViewModelFactory
import dagger.Component

@Component(modules = [UserDetailsModule::class], dependencies = [UserDetailsFeatureDeps::class])
@UserDetailsScope
internal interface UserDetailsComponent {

    fun getUserDetailsViewModelFactory(): UserDetailsViewModelFactory

    @Component.Builder
    interface Builder{
        fun addDeps(deps: UserDetailsFeatureDeps): Builder
        fun build(): UserDetailsComponent
    }
}