package com.sachith.android.apipractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.sachith.android.apipractice.api.UserAPIService
import com.sachith.android.apipractice.model.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class MainActivity : AppCompatActivity() {
    private val userAPIService = UserAPIService.create()

    private fun init() {
        val btnSearchUser = findViewById<Button>(R.id.btnSearchUser)

        btnSearchUser.setOnClickListener{
            this.retrieveUser()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.init()
    }

    fun retrieveUser() {
        val editTextUserId = findViewById<EditText>(R.id.editTextUserID)
        val editTextName = findViewById<EditText>(R.id.editTextName)
        val editTextUsername = findViewById<EditText>(R.id.editTextUsername)
        val editTextEmail = findViewById<EditText>(R.id.editTextEmail)
        val editTextAddressStreet = findViewById<EditText>(R.id.editTextAddressStreet)
        val editTextAddressSuite = findViewById<EditText>(R.id.editTextAddressSuite)
        val editTextAddressCity = findViewById<EditText>(R.id.editTextAddressCity)
        val editTextAddressZipcode = findViewById<EditText>(R.id.editTextAddressZipcode)

        val requestUser = userAPIService.getUser(editTextUserId.text.toString())

        requestUser.enqueue(object: Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                val user = response.body()

                if (user == null) {
                    Toast.makeText(this@MainActivity, "Oops...can't find the user you are looking for", Toast.LENGTH_LONG)
                } else {
                    editTextName.setText(user.name)
                    editTextUsername.setText(user.username)
                    editTextEmail.setText(user.email)
                    editTextAddressStreet.setText(user.address.street)
                    editTextAddressSuite.setText(user.address.suite)
                    editTextAddressCity.setText(user.address.city)
                    editTextAddressZipcode.setText(user.address.zipcode)
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Unable to retriev data. This may be due to internet connectivity issues.", Toast.LENGTH_LONG).show()
            }
        })
    }
}
