package com.test.authorization.di

import com.test.authorization.domain.CheckAuthCodeUseCase
import com.test.authorization.domain.RegistrationUseCase
import com.test.authorization.domain.SaveRefreshTokenUseCase
import com.test.authorization.domain.SaveUserUseCase
import com.test.authorization.domain.SendAuthCodeUseCase
import com.test.authorization.presentation.viewmodel.CheckAuthCodeViewModelFactory
import com.test.authorization.presentation.viewmodel.RegistrationViewModelFactory
import com.test.authorization.presentation.viewmodel.SendAuthCodeViewModelFactory
import com.test.data.api.AppDataPreference
import com.test.data.api.repository.AuthorizationRepository
import com.test.data.api.repository.RegistrationRepository
import com.test.data.api.repository.UserRepository
import dagger.Module
import dagger.Provides

@Module
internal class AuthorizationModule {

    @Provides
    fun providerCheckAuthCodeViewModelFactory(checkAuthCodeUseCase: CheckAuthCodeUseCase): CheckAuthCodeViewModelFactory {
        return CheckAuthCodeViewModelFactory(checkAuthCodeUseCase = checkAuthCodeUseCase)
    }

    @Provides
    fun providerSaveRefreshTokenUseCase(preference: AppDataPreference): SaveRefreshTokenUseCase {
        return SaveRefreshTokenUseCase(preference = preference)
    }

    @Provides
    fun providerRegistrationViewModelFactory(registrationUseCase: RegistrationUseCase, saveRefreshTokenUseCase: SaveRefreshTokenUseCase, saveUserUseCase:SaveUserUseCase): RegistrationViewModelFactory {
        return RegistrationViewModelFactory(registrationUseCase = registrationUseCase, saveTokenUseCase = saveRefreshTokenUseCase, saveUserUseCase = saveUserUseCase)
    }

    @Provides
    fun providerSendAuthCodeViewModelFactory(sendAuthCodeUseCase: SendAuthCodeUseCase): SendAuthCodeViewModelFactory {
        return SendAuthCodeViewModelFactory(sendAuthCodeUseCase = sendAuthCodeUseCase)
    }

    @Provides
    fun providerSendAuthCodeUseCase(repository: AuthorizationRepository): SendAuthCodeUseCase{
        return SendAuthCodeUseCase(repository = repository)
    }

    @Provides
    fun providerCheckAuthCodeUseCase(repository: AuthorizationRepository): CheckAuthCodeUseCase {
        return CheckAuthCodeUseCase(repository = repository)
    }

    @Provides
    fun providerRegistrationUseCase(repository: RegistrationRepository): RegistrationUseCase {
        return RegistrationUseCase(repository = repository)
    }

    @Provides
    fun providerSaveUserUseCase(repository: UserRepository): SaveUserUseCase {
        return SaveUserUseCase(repository = repository)
    }
}