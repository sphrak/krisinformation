package fi.kroon.krisinformation.presentation

import android.os.Bundle
import android.view.View
import fi.kroon.krisinformation.R
import fi.kroon.krisinformation.common.extensions.failure
import fi.kroon.krisinformation.common.extensions.observe
import fi.kroon.krisinformation.common.extensions.viewModel
import fi.kroon.krisinformation.data.exception.Failure
import fi.kroon.krisinformation.data.feed.model.FeedResponse
import fi.kroon.krisinformation.presentation.viewmodel.FeedViewModel

class FeedFragment : BaseFragment() {

    override fun layoutId() = R.layout.feed_fragment

    private lateinit var feedViewModel: FeedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cmp.inject(this)

        feedViewModel = viewModel(viewModelFactory) {
            observe(feedResponse, ::renderFeed)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialiseView()
        loadFeed()
    }

    private fun initialiseView() {
    }

    private fun loadFeed() {
        // emptyView toGone()
        // movielist.tovisible()
        // showprogress()
        // feedViewModel.get()
    }

    private fun renderFeed(feedResponse: FeedResponse?) {
        feedResponse.let {
        }
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.IOException -> renderFailure(R.string.io_exception)
            is Failure.NetworkException -> renderFailure(R.string.network_failure)
            is Failure.NetworkOfflineFailure -> renderFailure(R.string.no_network_available)
            is Failure.HttpBadRequest400 -> renderFailure(R.string.http_bad_request_400)
            is Failure.HttpInternalServerError500 -> renderFailure(R.string.http_internal_server_error_500)
            is Failure.HttpForbidden403 -> renderFailure(R.string.http_forbidden_403)
            is Failure.HttpServiceUnavailable503 -> renderFailure(R.string.http_service_unavailable_503)
            is Failure.HttpGatewayTimeout504 -> renderFailure(R.string.http_gateway_timeout_504)
        }
    }
}