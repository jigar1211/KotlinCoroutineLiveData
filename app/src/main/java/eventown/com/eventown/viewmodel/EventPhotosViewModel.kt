package eventown.com.eventown.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel;
import eventown.com.eventown.model.ApiResponseModel
import eventown.com.eventown.service.ProjectRepository

class EventPhotosViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private val imageListObservable = ProjectRepository.getInstance().getImageList()

    fun getImageList(): LiveData<List<ApiResponseModel>> {

        return imageListObservable
    }


}
