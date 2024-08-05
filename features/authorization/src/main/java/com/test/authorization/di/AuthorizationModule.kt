package com.test.authorization.di

import com.test.authorization.domain.CheckAuthCodeUseCase
import com.test.authorization.domain.RegistrationUseCase
import com.test.authorization.domain.SendAuthCodeUseCase
import com.test.data_api.AuthorizationRepository
import com.test.data_api.RegistrationRepository
import dagger.Module
import dagger.Provides

@Module
class AuthorizationModule {

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