package com.example.mehfu.mab.activities

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.example.mehfu.mab.R
import com.example.mehfu.mab.db.DbManager
import kotlinx.android.synthetic.main.activity_add_news.*
import com.example.mehfu.mab.java.ImageConverter

class AddNewsActivity : AppCompatActivity() {


    companion object {
        const val REQUEST_CODE_IMAGE_CHOOSER = 101
    }

    var image: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_news)


        btnUploadImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, REQUEST_CODE_IMAGE_CHOOSER)
        }

        btnSave.setOnClickListener {

            val name = etName.text.toString().trim()
            val desc = etDesc.text.toString().trim()
            val duration = etDuration.text.toString().trim().toDouble()
            var isBallInvolved:Boolean = false

            if(radioIsNonBall.checkedRadioButtonId == R.id.rbNonBall)
                isBallInvolved = true


            if(name.isEmpty()){
                etName.error = "Type News Headline"
                etName.requestFocus()
                return@setOnClickListener
            }

            if(desc.isEmpty()){
                etDesc.error = "Type News Description"
                etDesc.requestFocus()
                return@setOnClickListener
            }

            if (DbManager(this).addSportsNews(name, desc, isBallInvolved, duration , image)){
                Toast.makeText(this, name + " Saved Successfully." , Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this, "Some error occured", Toast.LENGTH_LONG).show()
            }

        }



    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_CODE_IMAGE_CHOOSER && resultCode == Activity.RESULT_OK){
            image = ImageConverter.uriImageToString(this, data?.data)

            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, data?.data)

            imageView.setImageBitmap(bitmap)
        }
    }
}

























