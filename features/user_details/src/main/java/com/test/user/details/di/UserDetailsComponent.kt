package com.test.user.details.di

import com.test.user.details.presentation.UserDetailsEditFragment
import com.test.user.details.presentation.UserDetailsFragment
import dagger.Component

@Component(modules = [UserDetailsModule::class], dependencies = [UserDetailsFeatureDeps::class])
@UserDetailsScope
internal interface UserDetailsComponent {
    fun inject(fragment: UserDetailsFragment)
    fun inject(fragment: UserDetailsEditFragment)

    @Component.Builder
    interface Builder{
        fun addDeps(deps: UserDetailsFeatureDeps): Builder
        fun build(): UserDetailsComponent
    }
}