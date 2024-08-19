package com.test.user.details.di

import dagger.Component

@Component(modules = [UserDetailsModule::class], dependencies = [UserDetailsFeatureDeps::class])
@UserDetailsScope
internal interface UserDetailsComponent {


    @Component.Builder
    interface Builder{
        fun addDeps(deps: UserDetailsFeatureDeps): Builder
        fun build(): UserDetailsComponent
    }
}