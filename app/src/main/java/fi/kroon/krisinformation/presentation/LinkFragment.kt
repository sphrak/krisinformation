package fi.kroon.krisinformation.presentation

import android.os.Bundle
import fi.kroon.krisinformation.R
import fi.kroon.krisinformation.common.extensions.viewModel
import fi.kroon.krisinformation.presentation.viewmodel.LinksViewModel

class LinkFragment : BaseFragment() {

    override fun layoutId() = R.layout.link_fragment

    private lateinit var linksViewModel: LinksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cmp.inject(this)
        linksViewModel = viewModel(viewModelFactory) {
        }
    }
}