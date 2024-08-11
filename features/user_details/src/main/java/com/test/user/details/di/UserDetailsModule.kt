package com.test.user.details.di

import com.test.data.api.repository.UserRepository
import com.test.user.details.domain.EditUserUseCase
import com.test.user.details.domain.GetUserUseCase
import com.test.user.details.presentation.viewmodel.UserDetailsEditViewModelFactory
import com.test.user.details.presentation.viewmodel.UserDetailsViewModelFactory
import dagger.Module
import dagger.Provides

@Module
internal class UserDetailsModule {

    @Provides
    fun providerUserDetailsViewModelFactory(getUserUseCase: GetUserUseCase): UserDetailsViewModelFactory {
        return UserDetailsViewModelFactory(getUserUseCase = getUserUseCase)
    }

    @Provides
    fun providerUserDetailsEditViewModelFactory(editUserUseCase: EditUserUseCase, getUserUseCase: GetUserUseCase): UserDetailsEditViewModelFactory {
        return UserDetailsEditViewModelFactory(editUserUseCase = editUserUseCase, getUserUseCase = getUserUseCase)
    }

    @Provides
    fun providerEditUserUseCase(repository: UserRepository): EditUserUseCase {
        return EditUserUseCase(repository = repository)
    }

    @Provides
    fun providerGetUserUseCase(repository: UserRepository): GetUserUseCase {
        return GetUserUseCase(repository = repository)
    }

}