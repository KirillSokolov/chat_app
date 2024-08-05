package com.test.chatapp.di

import dagger.Binds
import dagger.Module

@Module
interface FeaturesModule {
    @Binds
    fun bindChatFeature(featureApi: NewsFeatureImpl): NewsFeatureApi

    @Binds
    fun bindChatListFeature(featureApi: ArticleDetailsFeatureImpl): ArticleDetailsFeatureApi

    fun bindAuthorizationFeature(featureApi: ArticleDetailsFeatureImpl): ArticleDetailsFeatureApi
}
