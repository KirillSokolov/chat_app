package com.test.chatapp.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        RepositoriesModule::class,
        FeaturesModule::class]
)
interface AppComponent: NewsFeatureDeps, ArticleDetailsFeatureDeps {

    override val articleRepository: ArticleRepository
    override val articleDetailsFeatureApi: ArticleDetailsFeatureApi
    override val router: Router

    fun inject(fragment: NavigatorFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}