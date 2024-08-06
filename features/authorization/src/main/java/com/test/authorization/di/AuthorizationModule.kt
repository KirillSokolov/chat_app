package com.test.authorization.di

import com.test.authorization.domain.CheckAuthCodeUseCase
import com.test.authorization.domain.RegistrationUseCase
import com.test.authorization.domain.SendAuthCodeUseCase
import com.test.authorization.presentation.CheckAuthCodeViewModelFactory
import com.test.authorization.presentation.RegistrationViewModelFactory
import com.test.authorization.presentation.SendAuthCodeViewModelFactory
import com.test.data.api.AuthorizationRepository
import com.test.data.api.RegistrationRepository
import dagger.Module
import dagger.Provides

@Module
class AuthorizationModule {

    @Provides
    fun providerCheckAuthCodeViewModelFactory(checkAuthCodeUseCase: CheckAuthCodeUseCase): CheckAuthCodeViewModelFactory {
        return CheckAuthCodeViewModelFactory(checkAuthCodeUseCase = checkAuthCodeUseCase)
    }

    @Provides
    fun providerRegistrationViewModelFactory(registrationUseCase: RegistrationUseCase): RegistrationViewModelFactory {
        return RegistrationViewModelFactory(registrationUseCase = registrationUseCase)
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
}