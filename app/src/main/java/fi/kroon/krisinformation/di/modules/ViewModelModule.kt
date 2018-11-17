package fi.kroon.krisinformation.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import fi.kroon.krisinformation.presentation.viewmodel.LinksViewModel
import fi.kroon.krisinformation.presentation.viewmodel.CapMessageViewModel
import fi.kroon.krisinformation.presentation.viewmodel.FeedViewModel
import fi.kroon.krisinformation.presentation.viewmodel.ThemeViewModel
import fi.kroon.krisinformation.presentation.viewmodel.AboutViewModel

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CapMessageViewModel::class)
    abstract fun bindsCapMessageViewModel(capMessageViewModel: CapMessageViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FeedViewModel::class)
    abstract fun bindsFeedViewModel(feedViewModel: FeedViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LinksViewModel::class)
    abstract fun bindsLinkViewModel(linksViewModel: LinksViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ThemeViewModel::class)
    abstract fun bindsThemeViewModel(themeViewModel: ThemeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AboutViewModel::class)
    abstract fun bindsAboutViewModel(aboutViewModel: AboutViewModel): ViewModel

    /*
    @Binds
    @IntoMap
    @ViewModelKey(LocationViewModel::class)
    abstract fun bindsLocationViewModel(locationViewModel: LocationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SuggestionViewModel::class)
    abstract fun bindsSuggestionViewModel(suggestionViewModel: SuggestionViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AboutViewModel::class)
    abstract fun bindsAboutViewModel(aboutViewModel: AboutViewModel): ViewModel*/
}