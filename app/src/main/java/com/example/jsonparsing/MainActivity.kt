package com.example.jsonparsing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder

const val baseUrl="https://jsonplaceholder.typicode.com/"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title="Json Parsing Using Retrofit in Kotlin"
        getMyData();



    }

    private fun getMyData() {
        val retrofitBuilder=Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
            .create(apiInterface::class.java)
        val retrofitData=retrofitBuilder.getData()
        retrofitData.enqueue(object : Callback<List<MyDataItem>?> {
            override fun onResponse(
                call: Call<List<MyDataItem>?>,
                response: Response<List<MyDataItem>?>) {
                val responseBody=response.body()!!
                val myStringBuilder=StringBuilder()
                for (myData  in responseBody){
                    myStringBuilder.append("UserId:"+myData.userId+"\n"+"Id:"+myData.id+"\n"+"Title:"+myData.title+"\n"+"Body:"+myData.body)
                    myStringBuilder.append("\n \n \n")
                }
                val textView=findViewById<TextView>(R.id.txtId).apply {
                    text=myStringBuilder
                }





            }

            override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {
                Log.d("MainActivity", "onFailure: "+t.message)

            }
        })

    }
}