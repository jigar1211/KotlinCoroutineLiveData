package eventown.com.eventown.view.frgments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import eventown.com.eventown.R
import eventown.com.eventown.model.ApiResponseModel
import eventown.com.eventown.view.adapter.EventPhotoAdapter
import eventown.com.eventown.viewmodel.EventPhotosViewModel
import kotlinx.android.synthetic.main.event_photos_fragment.*

class EventPhotosFragment : Fragment() {

    companion object {
        fun newInstance() = EventPhotosFragment()
    }

    private lateinit var viewModel: EventPhotosViewModel
    private lateinit var eventPhotoAdapter: EventPhotoAdapter
    private lateinit var apiresponseList: MutableList<ApiResponseModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.event_photos_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EventPhotosViewModel::class.java)
        observeViewModel(viewModel)
    }

    private fun observeViewModel(viewModel: EventPhotosViewModel) {

        viewModel.getImageList().observe(this, Observer<List<ApiResponseModel>> { response ->

            run {
                if (response != null) {

                    apiresponseList = mutableListOf()
                    apiresponseList.addAll(response)
                    eventPhotoAdapter = EventPhotoAdapter(this.activity!!, apiresponseList)
                    val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
                    rvPhotos.layoutManager = layoutManager
                    rvPhotos.adapter = eventPhotoAdapter
                }
            }


        })
    }

}
