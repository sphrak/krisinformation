package fi.kroon.krisinformation.presentation

import android.os.Bundle
import fi.kroon.krisinformation.R
import fi.kroon.krisinformation.common.extensions.viewModel
import fi.kroon.krisinformation.presentation.viewmodel.AboutViewModel

class AboutFragment : BaseFragment() {

    override fun layoutId() = R.layout.about_fragment

    private lateinit var aboutViewModel: AboutViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cmp.inject(this)
        aboutViewModel = viewModel(viewModelFactory) {
        }
    }
}