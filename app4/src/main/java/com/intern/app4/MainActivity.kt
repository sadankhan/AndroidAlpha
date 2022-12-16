@file:Suppress("DEPRECATION")

package com.intern.app4

import android.Manifest
import android.app.AlertDialog
import android.content.ContentValues
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.vision.Frame
import com.google.android.gms.vision.text.TextRecognizer
import com.intern.cropper.CropImage
import com.intern.cropper.CropImageView


class MainActivity : AppCompatActivity() {

    private lateinit var mResultEt: EditText
    private lateinit var mPreviewIv: ImageView
    private lateinit var bSave: Button
    private lateinit var resultCard: CardView
    private lateinit var previewCard: CardView

    private lateinit var cameraPermission: Array<String>
    private lateinit var storagePermission: Array<String>

    private lateinit var imageUri: Uri
    private lateinit var croppedImageUri: Uri
    private lateinit var stringBuilder: StringBuilder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.subtitle = "Click + button to insert image"

        mResultEt = findViewById(R.id.resultEt)
        mPreviewIv = findViewById(R.id.imageIv)
        bSave = findViewById(R.id.btSave)
        resultCard = findViewById(R.id.resultCard)
        previewCard = findViewById(R.id.previewCard)


        //Hide Preview Card and Scanned Result Card and Save Button
        resultCard.visibility = View.GONE
        previewCard.visibility = View.GONE
        bSave.visibility = View.GONE

        //camera permission
        cameraPermission = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        //storage permission
        storagePermission = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.addImage -> showImageImportDialog()
            R.id.about -> dialogAbout()
        }
        return true
    }

    private fun dialogAbout() {
        AlertDialog.Builder(this)
            .setTitle("About App")
            .setMessage("Daniyal Khan.")
            .setPositiveButton(
                "CLOSE"
            ) { dialog: DialogInterface, _: Int -> dialog.dismiss() }
            .show()
    }

    private fun showImageImportDialog() {
        val items = arrayOf("Camera", "Gallery")
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Select Image")
        dialog.setItems(items) { _: DialogInterface?, which: Int ->
            if (which == 0) {
                if (!checkCameraPermission()) {
                    //camera permission not allowed, request it
                    requestCameraPermission()
                } else {
                    //permission allowed, take picture
                    pickCamera()
                }
            }
            if (which == 1) {
                if (!checkStoragePermission()) {
                    //storage permission not allowed, request it
                    requestStoragePermission()
                } else {
                    //permission allowed, take picture
                    pickGallery()
                }
            }
        }
        dialog.create().show()
    }

    private fun pickGallery() {
        //intent to pick image from gallery
        val intent = Intent(Intent.ACTION_PICK)
        //set intent type to image
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_GALLERY_CODE)
    }

    private fun pickCamera() {
        //intent to take image from camera, it will also be save to storage to get high quality image
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "NewPick") //title of the picture
        values.put(MediaStore.Images.Media.DESCRIPTION, "Image To Text") //title of the picture
        imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)!!
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        startActivityForResult(cameraIntent, IMAGE_PICK_CAMERA_CODE)
    }

    private fun requestStoragePermission() {
        ActivityCompat.requestPermissions(this, storagePermission, STORAGE_REQUEST_CODE)
    }

    private fun checkStoragePermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestCameraPermission() {
        ActivityCompat.requestPermissions(this, cameraPermission, CAMERA_REQUEST_CODE)
    }

    private fun checkCameraPermission(): Boolean {
        val result = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
        val result1 = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
        return result && result1
    }

    //handle permission result
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            CAMERA_REQUEST_CODE -> if (grantResults.isNotEmpty()) {
                val cameraAccepted = grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED
                if (cameraAccepted) {
                    pickCamera()
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
            STORAGE_REQUEST_CODE -> if (grantResults.isNotEmpty()) {
                val writeStorageAccepted = grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED
                if (writeStorageAccepted) {
                    pickGallery()
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    //handle image result
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == IMAGE_PICK_GALLERY_CODE) {
                //got image from gallery now crop it
                if (data != null) {
                    CropImage.activity(data.data!!)
                        .setGuidelines(CropImageView.Guidelines.ON) //enable image guid lines
                        .start(this)
                }
            }

            if (requestCode == IMAGE_PICK_CAMERA_CODE) {
                //got image from camera now crop it
                CropImage.activity(imageUri)
                    .setGuidelines(CropImageView.Guidelines.ON) //enable image guid lines
                    .start(this)
            }
        }

        //get cropped image
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == RESULT_OK) {
                if (result != null) {
                    croppedImageUri = result.uri
                }
                //set image to image view and make preview card visible
                previewCard.visibility = View.VISIBLE
                mPreviewIv.setImageURI(croppedImageUri)

                //get drawable bitmap for text recognition
                val bitmapDrawable = mPreviewIv.drawable as BitmapDrawable
                val bitmap = bitmapDrawable.bitmap
                val recognizer = TextRecognizer.Builder(applicationContext).build()
                if (!recognizer.isOperational) {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                } else {
                    val frame = Frame.Builder().setBitmap(bitmap).build()
                    val items = recognizer.detect(frame)
                    stringBuilder = StringBuilder()
                    //get text from sb until there is no text
                    for (i in 0 until items.size()) {
                        val myItem = items.valueAt(i)
                        stringBuilder.append(myItem.value)
                        stringBuilder.append("\n")
                    }

                    //set text to edit text
                    resultCard.visibility = View.VISIBLE
                    bSave.visibility = View.VISIBLE
                    mResultEt.setText(stringBuilder.toString())
                }
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                //if there is any error show it
                assert(result != null)
                val error = result!!.error
                Toast.makeText(this, "" + error, Toast.LENGTH_SHORT).show()
            }
        }

        bSave.setOnClickListener {
            //Insert and Remove Information from database
            Toast.makeText(this, "Not Implemented Yet", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        //Permission Code
        private const val CAMERA_REQUEST_CODE = 200
        private const val STORAGE_REQUEST_CODE = 400
        private const val IMAGE_PICK_GALLERY_CODE = 1000
        private const val IMAGE_PICK_CAMERA_CODE = 2001
    }
}