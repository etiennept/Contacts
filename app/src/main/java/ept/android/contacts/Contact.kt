package ept.android.contacts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ContactDetailView ( contact: Contact  ){

    Column() {
        Box(Modifier.fillMaxWidth()) {
            ContactPhotoView(photo = contact.photo , Modifier.align(Alignment.Center)  )
        }
        Row  {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.Call, contentDescription ="call"  )
            }
            IconButton(onClick = { /*TODO*/ }) {
               Icon(Icons.Default.Refresh, contentDescription = "sms")
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.Email  , contentDescription = "email")
            }

        }
    }





}