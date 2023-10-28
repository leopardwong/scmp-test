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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.scmptest.api.model.Staff
import com.example.scmptest.api.model.StaffsResponse

@Composable
fun StaffListPage(
    token: String,
    staffList: List<Staff>,
    isLastPage: Boolean,
    onLoadMore: () -> Unit
) {
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
            items(staffList.size) {
                StaffItemRow(staffList[it])
            }
        }

        if (isLastPage) {
            Button(
                onClick = { onLoadMore() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Load More")
            }
        }
    }
}

@Composable
fun StaffItemRow(staff:Staff) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {

        AsyncImage(
            modifier = Modifier.size(48.dp)
                .clip(RoundedCornerShape(4.dp)),
            model = staff.avatar,
            contentDescription = null,
        )

        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f)
        ) {
            Text(text = staff.email)
            Text(text = "${staff.lastName} ${staff.firstName}")
        }
    }
}