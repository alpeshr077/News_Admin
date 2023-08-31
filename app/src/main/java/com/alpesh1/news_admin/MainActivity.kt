package com.alpesh1.news_admin

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alpesh1.news_admin.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class MainActivity : AppCompatActivity() {

    lateinit var reference: DatabaseReference
    lateinit var storageReference: StorageReference
    lateinit var binding: ActivityMainBinding
    lateinit var btnSubmit: TextView
    lateinit var Uri: Uri
    lateinit var imgImage: ImageView
    lateinit var edtTitle: EditText
    lateinit var edtDate: EditText
    lateinit var edtDes: EditText
    lateinit var edtContent: EditText
    var image_Code = 11

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        reference = FirebaseDatabase.getInstance().reference
        storageReference = FirebaseStorage.getInstance().reference

        btnSubmit = findViewById(R.id.btnSubmit)
        edtTitle = findViewById(R.id.edtTitle)
        edtDate = findViewById(R.id.edtDate)
        edtDes = findViewById(R.id.edtDes)
        edtContent = findViewById(R.id.edtContent)
        imgImage = findViewById(R.id.imgImage)


        imgImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, image_Code)
        }


        btnSubmit.setOnClickListener {

            val title = edtTitle.text.toString()
            val date = edtDate.text.toString()
            val des = edtDes.text.toString()
            val content = edtContent.text.toString()

            if (title.isEmpty()) {
                Toast.makeText(this@MainActivity, "Fill Title", Toast.LENGTH_SHORT).show()
            } else if (date.isEmpty()) {
                Toast.makeText(this@MainActivity, "insert date", Toast.LENGTH_SHORT).show()
            } else if (des.isEmpty()) {
                Toast.makeText(this@MainActivity, "insert description", Toast.LENGTH_SHORT).show()
            } else if (content.isEmpty()) {
                Toast.makeText(this@MainActivity, "fill content", Toast.LENGTH_SHORT).show()
            } else {
                val key = reference.root.push().key
                val model = UserModel(key, title, date, des, content)
                reference.root.child("User").child(key!!).setValue(model)

                edtTitle.setText("")
                edtDate.setText("")
                edtDes.setText("")
                edtContent.setText("")

                Toast.makeText(this@MainActivity, "Data Submit", Toast.LENGTH_SHORT).show()


                val ref = storageReference.child("images/${Uri.lastPathSegment}.jpg")
                var Upload = ref.putFile(Uri)

                val url = Upload.continueWithTask { task ->
                    if (!task.isSuccessful) {
                        task.exception?.let {
                            throw it
                        }
                    }
                    ref.downloadUrl
                }.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val downloadUri = task.result
                        var Key = reference.root.push().key
                        reference.root.child("Images").child(Key!!).child("imagesss")
                            .setValue(downloadUri.toString())

                    } else {

                    }
                }
                
            }
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {

            if (requestCode == image_Code) {

                var Uri = data?.data!!
                binding.imgImage.setImageURI(Uri)

            }

        }
    }


}