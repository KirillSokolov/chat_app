package com.test.chatapp.di

import android.content.Context
import com.test.authorization.di.AuthorizationFeatureDeps
import com.test.chat.di.ChatFeatureDeps
import com.test.chat_api.ChatFeatureApi
import com.test.chat_list.di.ChatListFeatureDeps
import com.test.chat_list_api.ChatListFeatureApi
import com.test.data.api.AppDataPreference
import com.test.data.api.repository.AuthorizationRepository
import com.test.data.api.repository.ChatListRepository
import com.test.data.api.repository.ChatRepository
import com.test.data.api.repository.RegistrationRepository
import com.test.data.api.repository.UserRepository
import com.test.navigation.Router
import com.test.user.details.di.UserDetailsFeatureDeps
import com.test.user_details_api.UserDetailsFeatureApi
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        RepositoriesModule::class,
        DataBaseModule::class,
        FeaturesModule::class]
)
interface AppComponent: AuthorizationFeatureDeps, ChatFeatureDeps, ChatListFeatureDeps,
    UserDetailsFeatureDeps {

    override val authorizationRepository: AuthorizationRepository
    override val registrationRepository: RegistrationRepository
    override val chatRepository: ChatRepository
    override val userRepository: UserRepository
  //  override val chatListRepository: ChatListRepository
   // override val chatFeatureApi: ChatFeatureApi
   // override val chatListFeatureApi: ChatListFeatureApi
   // override val userDetailsFeatureApi: UserDetailsFeatureApi
    override val preference: AppDataPreference
    override val router: Router

  //  fun inject(fragment: NavigatorFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}