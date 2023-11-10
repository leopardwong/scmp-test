package com.example.scmptest.ui.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.scmptest.api.model.Staff
import com.example.scmptest.ui.viewModel.StaffListPageViewModel

@Composable
fun StaffListPage(token: String) {
    val viewModel = StaffListPageViewModel()
    var currentPage by remember { mutableStateOf(0) }
    var totalPage by remember { mutableStateOf(0) }
    var staffLists by remember { mutableStateOf<List<Staff>>(emptyList()) }
    var errorMsg by remember { mutableStateOf("") }
    var showErrorDialog by remember { mutableStateOf(false) }

    LaunchedEffect(token){
        viewModel.getStaffList(
            token = token,
            response = {response ->
                currentPage = response.page
                totalPage = response.totalPages
                staffLists = response.data
            },
            error = {
                errorMsg = "Please Check your internet!"
                showErrorDialog = it
            }
        )
    }
    //Put the getStaffList function into LaunchedEffect, because the function only needs to be called once, and the token value will not change after the page is created

    ErrorMsgDialog(
        showErrorDialog = showErrorDialog,
        errorMsg = errorMsg,
        onDismiss = { showErrorDialog = false }
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Token: $token",
            modifier = Modifier.padding(16.dp)
        )
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(staffLists.size) {
                StaffItem(staffLists[it])
            }
        }
        if (totalPage > currentPage) {
            Button(
                onClick = {
                    currentPage += 1
                    viewModel.loadMore(
                        page = currentPage,
                        token = token,
                        existingList = staffLists,
                        response = { staffLists = it },
                        error = {
                            errorMsg = "Please Check your internet!"
                            showErrorDialog = it
                        }
                )
                          },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Load More")
            }
            // Implemented loadMore Button
        }
    }
}