package fi.kroon.krisinformation.presentation

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fi.kroon.krisinformation.R
import fi.kroon.krisinformation.common.extensions.failure
import fi.kroon.krisinformation.common.extensions.observe
import fi.kroon.krisinformation.common.extensions.viewModel
import fi.kroon.krisinformation.data.capmessage.model.CapMessage
import fi.kroon.krisinformation.data.exception.Failure
import fi.kroon.krisinformation.presentation.adapter.CapMessageAdapter
import fi.kroon.krisinformation.presentation.viewmodel.CapMessageViewModel
import kotlinx.android.synthetic.main.capmessage_fragment.*
import javax.inject.Inject

class CapMessageFragment : BaseFragment() {

    override fun layoutId() = R.layout.capmessage_fragment

    private lateinit var capMessageViewModel: CapMessageViewModel

    @Inject
    lateinit var capMessageAdapter: CapMessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cmp.inject(this)
        capMessageViewModel = viewModel(viewModelFactory) {
            observe(capMessageResponse, ::renderCapMessage)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialiseView()
        loadCapMessage()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        capMessageRecyclerView.adapter = null
    }

    private fun loadCapMessage() {
        capMessageViewModel.get()
    }

    private fun initialiseView() {
        refreshCapMessage.setOnRefreshListener {
            loadCapMessage()
            refreshCapMessage.isRefreshing = false
        }
        capMessageRecyclerView.layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
        capMessageRecyclerView.adapter = capMessageAdapter
        capMessageRecyclerView.hasFixedSize()
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

    private fun renderCapMessage(capMessageList: List<CapMessage>?) {
        capMessageList.let {
            capMessageAdapter.collection = it!!.toMutableList()
        }
    }
}