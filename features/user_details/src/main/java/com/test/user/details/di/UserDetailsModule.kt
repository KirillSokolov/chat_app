package com.test.user.details.di

import com.test.data.api.AppDataPreference
import com.test.data.api.repository.UserRepository
import com.test.user.details.domain.EditUserUseCase
import com.test.user.details.domain.GetUserPhotoUseCase
import com.test.user.details.domain.GetUserUseCase
import com.test.user.details.presentation.viewmodel.UserDetailsEditViewModelFactory
import com.test.user.details.presentation.viewmodel.UserDetailsViewModelFactory
import dagger.Module
import dagger.Provides

@Module
internal class UserDetailsModule {

    @Provides
    fun providerUserDetailsViewModelFactory(getUserUseCase: GetUserUseCase, getUserPhotoUseCase: GetUserPhotoUseCase, editUserUseCase: EditUserUseCase): UserDetailsViewModelFactory {
        return UserDetailsViewModelFactory(getUserUseCase = getUserUseCase, getUserPhotoUseCase = getUserPhotoUseCase, editUserUseCase = editUserUseCase)
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
    @Provides
    fun providerGetUserPhotoUseCase(repository: AppDataPreference): GetUserPhotoUseCase {
        return GetUserPhotoUseCase(dataStore = repository)
    }
}