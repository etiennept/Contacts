package ept.android.contacts

import android.content.Context
import android.database.ContentObserver
import android.net.Uri
import android.provider.ContactsContract
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest



@Composable
fun Contacts(context: Context = LocalContext.current) {
    var contacts by remember {
        mutableStateOf(context.parser())
    }
    var text by rememberSaveable {
        mutableStateOf("")
    }
    contacts.filter(text)
    DisposableEffect(key1 = contacts , key2 = text , effect = {
        val observer = object : ContentObserver(null) {
            override fun onChange(self: Boolean) {
                contacts = context.parser()
                contacts.filter(text)
            }
        }
        context.contentResolver.registerContentObserver(uri, true, observer)
        onDispose {
            context.contentResolver.unregisterContentObserver(observer)
        }
    })

    Box(modifier = Modifier.fillMaxSize()){
        Column( Modifier.fillMaxSize() )   {
            TextField(value = text, onValueChange ={ it ->
                text = it
                contacts = contacts.filter(  text )
            } , modifier = Modifier.fillMaxWidth() )
            LazyColumn(Modifier.fillMaxSize()) {
                items(contacts) {
                    ContactView(it)
                }
            }
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(Icons.Default.Add, contentDescription = "add Contact"  ,  )
        }
    }


}





fun List<Contact>.filter( text : String   ) = this.filter { it ->
    Regex(text).containsMatchIn( it.displayName)
}
data class Contact(val id: String, val displayName: String, val photo: String?) {

}

@Composable
fun ContactView(contact: Contact ) {
    val size = remember {
        50.dp
    }
    Row(Modifier.fillMaxWidth()) {
        ContactPhotoView(photo = contact.photo , modifier = Modifier
            .width(size)
            .height(size))
        Box(modifier = Modifier
            .height(size)
            .fillMaxWidth() ){
            Text(text = contact.displayName, modifier = Modifier
                .clickable {
                }
                .align(Alignment.CenterStart))


        }

    }
}

@Composable
fun ContactPhotoView( photo: String?  , modifier: Modifier = Modifier ) {
    when(photo){
        null -> {
            Image(  painterResource(id = R.drawable.contact), contentDescription =""  , modifier)
        }
        else ->{
            AsyncImage(
                ImageRequest.Builder(LocalContext.current)
                    .data(Uri.parse(photo ).toString())
                    .crossfade(true)
                    .build()
                , "avatar icon" , modifier =  modifier)
        }
    }
}

