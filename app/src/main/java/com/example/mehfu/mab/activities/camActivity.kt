package com.example.mehfu.mab.activities

import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import com.example.mehfu.mab.R
import kotlinx.android.synthetic.main.activity_cam.*

class camActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cam)

        button_cam.setOnClickListener {

            var i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(i,123)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==123)
        {
            var bmp =data?.extras?.get("data") as Bitmap
            imageViewCam.setImageBitmap(bmp)
        }
    }
}
