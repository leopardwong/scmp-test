package com.example.scmptest.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scmptest.api.repository.ApiRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class StaffListPageViewModel:ViewModel() {

    fun getStaffList(token:String){
        viewModelScope.launch {
            ApiRepository.getUserList(token)
                .collectLatest {
                    it.isSuccess?.let{

                    }
                    it.isOtherError?.let{

                    }
                    it.isBizError?.let{

                    }
                }
        }
    }
}