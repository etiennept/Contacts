package ept.android.contacts

import android.content.Context
import android.net.Uri
import android.provider.ContactsContract
val uri: Uri get() = ContactsContract.Contacts.CONTENT_URI
fun Context.parser(): List<Contact> =
    contentResolver.query(uri,  null ,null , null, null)!!.use {
        val displayNameColumnIndex = it.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
        val idColumnIndex = it.getColumnIndex(ContactsContract.Contacts._ID)
        val photoUriColumnIndex = it.getColumnIndex(ContactsContract.Contacts.PHOTO_URI)
        val contacts = mutableListOf<Contact>()
        println(it.columnNames.toList())
        for (i in 0 until it.count) {
            it.moveToPosition(i)
            contacts.add(
                Contact(
                    it.getString(idColumnIndex), it.getString(displayNameColumnIndex),
                    it.getString(photoUriColumnIndex)
                )
            )
        }
        contacts

    }
fun Context.selectItem( id : String   ) {
    val emails = mutableListOf<String>()
    val phonesNumbers = mutableListOf<String>()



    contentResolver.run {
        query(uri , null  ,"${ContactsContract.Contacts._ID} = $id"  , null , null   )!!.use {
            ContactsContract.CommonDataKinds.Email.DATA
            val hasPhoneNumber = it.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)
            it.moveToFirst()


        }
        query(ContactsContract.CommonDataKinds.Email.CONTENT_URI  ,null  , "${ContactsContract.Contacts._ID} = $id" , null  ,null  ,null  )?.use {


        }
        query( ContactsContract.CommonDataKinds.Phone.CONTENT_URI  , null  , "${ContactsContract.Contacts._ID} = $id" , null   ,  null  , null   )?.use {



        }
    }





}


