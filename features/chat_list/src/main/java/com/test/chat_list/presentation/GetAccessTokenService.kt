package com.test.chat_list.presentation

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.test.chat_list.di.ChatListFeatureDepsProvider
import com.test.chat_list.di.DaggerChatListComponent
import com.test.data.api.AppDataPreference
import com.test.data.api.repository.UserRepository
import com.test.domain.models.request.RefreshToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.Duration
import javax.inject.Inject


class GetAccessTokenService @Inject constructor(): Service(){

    @Inject
    lateinit var dataStore: AppDataPreference

    @Inject
    lateinit var userRepository: UserRepository

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onCreate() {
        super.onCreate()
        val serviceComponent = DaggerChatListComponent
            .builder()
            .addDeps(ChatListFeatureDepsProvider.deps)
            .build()

        //serviceComponent.inject(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        CoroutineScope(Dispatchers.IO).launch {
            getToken()
        }


        return super.onStartCommand(intent, flags, startId)
    }

    private suspend fun getToken(){
        val refreshToken = dataStore.getRefreshToken()
        userRepository.getAccessToken(RefreshToken(refresh_token = refreshToken))
        delay(Duration.ofMinutes(9L).toMillis())
        getToken()
    }
}