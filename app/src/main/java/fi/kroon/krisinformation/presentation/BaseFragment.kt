package fi.kroon.krisinformation.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import fi.kroon.krisinformation.BaseApplication
import fi.kroon.krisinformation.di.component.KrisInformationComponent
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    abstract fun layoutId(): Int
    protected val subscriptions = CompositeDisposable()

    val cmp: KrisInformationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (activity?.application as BaseApplication).cmp
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(layoutId(), container, false)

    override fun onDestroy() {
        subscriptions.clear()
        super.onDestroy()
    }

    open fun renderFailure(@StringRes message: Int) {
        return Snackbar.make(this.view!!, message, Snackbar.LENGTH_LONG).show()
    }
}