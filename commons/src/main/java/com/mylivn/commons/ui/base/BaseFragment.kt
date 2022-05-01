package com.mylivn.commons.ui.base

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.mylivn.commons.R
import com.mylivn.commons.ui.messagedialog.MessageDialogHelper.showErrorDialog
import com.mylivn.commons.ui.messagedialog.models.MessageDialogAction
import com.mylivn.commons.ui.utils.extensions.getMainNavController
import com.mylivn.commons.ui.utils.extensions.navigateAndPopStack
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

private const val IS_TESTING_SESSION_ARG_KEY = "IS_TESTING_SESSION"

/**
 * Base fragment to standardize and simplify initialization for this component.
 *
 * @param layoutId Layout resource reference identifier.
 * @see Fragment
 */
abstract class BaseFragment<B : ViewDataBinding, M : ViewModel>(
    @LayoutRes
    private val layoutId: Int
) : Fragment(), HasAndroidInjector {


    @Inject
    protected lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModel: M

    private var _viewBinding: B? = null

    // This property is only valid between onCreateView and onDestroyView.
    val viewBinding: B
        get() = _viewBinding!!

    /**
     * Called to Initialize view data binding variables when fragment view is created.
     */
    abstract fun onInitDataBinding()

    /**
     * Called to do any required initialization of the views in the layout file
     */
    abstract fun initView()

    /**
     * Called to fetch & observe data from the [viewModel]
     */
    abstract fun getData()

    /**
     * Called to have the fragment instantiate its user interface view.
     *
     * @param inflater The LayoutInflater object that can be used to inflate any views in the fragment.
     * @param container If non-null, this is the parent view that the fragment's UI should be
     * attached to. The fragment should not add the view itself, but this can be used to generate
     * the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous
     * saved state as given here.
     *
     * @return Return the View for the fragment's UI, or null.
     *
     * @see Fragment.onCreateView
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        _viewBinding?.lifecycleOwner = viewLifecycleOwner
        return viewBinding.root
    }

    /**
     * Called when a fragment is first attached to its context.
     *
     * @param context The application context.
     *
     * @see Fragment.onAttach
     */
    override fun onAttach(context: Context) {
        val isTestingSession: Boolean = arguments?.getBoolean(IS_TESTING_SESSION_ARG_KEY) ?: false
        if (!isTestingSession) {
            AndroidSupportInjection.inject(this)
        }
        super.onAttach(context)
    }

    /**
     * Called to have the fragment instantiate its user interface view.
     *
     * @param view The view returned by onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     * @see Fragment.onViewCreated
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onInitDataBinding()
        initView()
        getData()
        observeUnauthorizedNetworkCalls()
    }

    /**
     * Return the [AppCompatActivity] this fragment is currently associated with.
     *
     * @throws IllegalStateException if not currently associated with an activity or if associated
     * only with a context.
     * @throws TypeCastException if the currently associated activity don't extend [AppCompatActivity].
     *
     * @see requireActivity
     */
    fun requireCompatActivity(): AppCompatActivity {
        requireActivity()
        val activity = requireActivity()
        if (activity is AppCompatActivity) {
            return activity
        } else {
            throw TypeCastException("Main activity should extend from 'AppCompatActivity'")
        }
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    //region:unauthorizedDialog
    private fun observeUnauthorizedNetworkCalls() {
        if (viewModel is BaseViewModel<*>) {
            (viewModel as BaseViewModel<*>).errorData.observe(viewLifecycleOwner) {
                it?.let {
                    if (it.code == 401) {
                        showErrorDialog(
                            activity = requireActivity(),
                            content = it.message
                                ?: getString(R.string.error_dialog_default_unauthorized),
                            action = MessageDialogAction(
                                title = getString(R.string.error_dialog_button_ok),
                                action = {
                                    it.dismiss()
                                    // Navigate to Splash Screen
                                    val uri = Uri.parse(getString(R.string.feed_uri))
                                    requireActivity().getMainNavController()
                                        .navigateAndPopStack(uri)
                                }
                            )
                        )
                    }
                }
            }
        }
    }
    //endregion
}