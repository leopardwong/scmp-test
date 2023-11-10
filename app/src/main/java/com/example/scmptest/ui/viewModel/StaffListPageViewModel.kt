package com.example.scmptest.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scmptest.api.model.Staff
import com.example.scmptest.api.model.StaffsResponse
import com.example.scmptest.api.repository.ApiRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class StaffListPageViewModel:ViewModel() {
    fun loadMore(page:Int,
                 token: String,
                 existingList:List<Staff>,
                 response:(List<Staff>)->Unit,
                 error:(Boolean)->Unit){
        getStaffList(
            page= page,
            token = token,
            response =  {
               val newList = existingList + it.data
               response(newList) },
            error = {
               error(it)
               Log.d("Error", "LoadMore Button Error - check api")
            })
    }
   //Implemented the loadMore button function and added error handling with logging for button click errors

    fun getStaffList(page:Int = 1,
                     token:String,
                     response:(StaffsResponse)->Unit,
                     error:(Boolean)->Unit){
        viewModelScope.launch {
            ApiRepository.getStaffList(page = page,token = token)
                .collectLatest {
                    it.isSuccess?.let{
                        response(it)
                    }
                    it.isBizError?.let{
                        error(true)
                        Log.d("Error", "BizError: $it")
                    }
                    it.isOtherError?.let{
                        error(true)
                        Log.d("Error", "Throwable: $it")
                    }
                }
        }
    }
}