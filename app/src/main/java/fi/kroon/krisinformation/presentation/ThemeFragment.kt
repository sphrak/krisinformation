package fi.kroon.krisinformation.presentation

import android.os.Bundle
import fi.kroon.krisinformation.R
import fi.kroon.krisinformation.common.extensions.viewModel
import fi.kroon.krisinformation.presentation.viewmodel.ThemeViewModel

class ThemeFragment : BaseFragment() {
    override fun layoutId() = R.layout.theme_fragment

    private lateinit var themeViewModel: ThemeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cmp.inject(this)
        themeViewModel = viewModel(viewModelFactory) {
        }
    }
}