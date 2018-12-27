package eventown.com.eventown.view.frgments

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import eventown.com.eventown.R
import eventown.com.eventown.viewmodel.MyAccountViewModel

class MyAccountFragment : Fragment() {

    companion object {
        fun newInstance() = MyAccountFragment()
    }

    private lateinit var viewModel: MyAccountViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.my_account_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MyAccountViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
