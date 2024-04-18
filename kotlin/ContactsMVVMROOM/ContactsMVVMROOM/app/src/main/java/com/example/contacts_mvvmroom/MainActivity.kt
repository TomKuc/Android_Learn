package com.example.contacts_mvvmroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contacts_mvvmroom.databinding.ActivityMainBinding
import com.example.contacts_mvvmroom.repository.ContactRepository
import com.example.contacts_mvvmroom.room.Contact
import com.example.contacts_mvvmroom.room.ContactDatabase
import com.example.contacts_mvvmroom.view.MyRecyclerViewAdapter
import com.example.contacts_mvvmroom.viewmodel.ContactViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var contactViewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val dao = ContactDatabase.getInstance(applicationContext).contactDAO
        val repository = ContactRepository(dao)

        contactViewModel = ViewModelProvider(this).get(ContactViewModel::class.java)
        binding.contactViewModel = contactViewModel

        binding.lifecycleOwner = this

        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        DisplayUsersList()
    }

    private fun DisplayUsersList(){
        contactViewModel.contacts.observe(this, Observer {
            binding.recyclerView.adapter = MyRecyclerViewAdapter(
                it,{ selectedItem: Contact -> listItemClicked(selectedItem)}
            )
        })
    }

    private fun listItemClicked(selectedItem: Contact){
        Toast.makeText(this,
            "Selected name is ${selectedItem.contact_name}",
            Toast.LENGTH_LONG).show()

        contactViewModel.initUpdateAndDelete(selectedItem)
    }
}