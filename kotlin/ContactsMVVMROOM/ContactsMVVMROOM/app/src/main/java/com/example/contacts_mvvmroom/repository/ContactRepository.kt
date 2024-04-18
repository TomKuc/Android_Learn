package com.example.contacts_mvvmroom.repository

import com.example.contacts_mvvmroom.room.Contact
import com.example.contacts_mvvmroom.room.ContactDAO

class ContactRepository(private val contactDAO: ContactDAO) {

    val contacts = contactDAO.getAllContactsInDB()

    suspend fun insert(contact: Contact):Long{
        return contactDAO.insertContact(contact)
    }

    suspend fun delete(contact: Contact){
        return contactDAO.deleteContact(contact)
    }

    suspend fun update(contact: Contact){
        return contactDAO.updateContact(contact)
    }

    suspend fun deleteAll(){
        return contactDAO.deleteAll()
    }
}