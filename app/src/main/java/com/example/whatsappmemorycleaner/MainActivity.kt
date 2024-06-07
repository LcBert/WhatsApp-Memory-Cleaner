package com.example.whatsappmemorycleaner

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.view.View
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.whatsappmemorycleaner.ui.theme.WhatsappMemoryClearerTheme
import java.io.File
import java.lang.StringBuilder
import kotlin.reflect.typeOf

private const val WHATSAPP_PATH = "/storage/emulated/0/Android/media/com.whatsapp/WhatsApp/Media/WhatsApp"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //request for the permission
        if (!Environment.isExternalStorageManager()) {
            val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
            val uri = Uri.fromParts("package", packageName, null)
            intent.data = uri
            startActivity(intent)
        }

        var image_button = findViewById<Button>(R.id.image_button)
        var video_button = findViewById<Button>(R.id.video_button)
        var voice_notes_button = findViewById<Button>(R.id.voice_notes_button)
        var video_notes_button = findViewById<Button>(R.id.video_notes_button)
        var stickers_button = findViewById<Button>(R.id.stickers_button)
        var documents_button = findViewById<Button>(R.id.documents_button)
        var audio_button = findViewById<Button>(R.id.audio_button)
        var animated_gifs_button = findViewById<Button>(R.id.animated_gifs_button)

        var button_list = arrayListOf<Button>(image_button, video_button, voice_notes_button, video_notes_button, stickers_button, documents_button, audio_button, animated_gifs_button)
        var path_list = arrayListOf<String>(" Images/Sent", " Video/Sent", " Voice Notes", " Video Notes", " Stickers", " Documents/Sent", " Audio/Sent", " Animated Gifs/Sent")

        for ((button, path) in button_list.zip(path_list)) {
            button.setOnClickListener(View.OnClickListener { delete_file(button, WHATSAPP_PATH + path) })
            button_text_empty_list(button, WHATSAPP_PATH + path)
        }

        /*
        image_button.setOnClickListener(View.OnClickListener {delete_file(image_button,WHATSAPP_PATH + "Whatsapp Images/Sent")})
        video_button.setOnClickListener(View.OnClickListener {delete_file(video_button,WHATSAPP_PATH + "Whatsapp Video/Sent")})
        voice_notes_button.setOnClickListener(View.OnClickListener {delete_file(voice_notes_button,WHATSAPP_PATH + "Whatsapp Voice Notes")})
        video_notes_button.setOnClickListener(View.OnClickListener {delete_file(video_notes_button,WHATSAPP_PATH + "Whatsapp Video Notes")})
        stickers_button.setOnClickListener(View.OnClickListener {delete_file(stickers_button,WHATSAPP_PATH + "Whatsapp Stickers")})
        documents_button.setOnClickListener(View.OnClickListener {delete_file(documents_button,WHATSAPP_PATH + "Whatsapp Documents/Sent")})
        audio_button.setOnClickListener(View.OnClickListener {delete_file(audio_button,WHATSAPP_PATH + "Whatsapp Audio/Sent")})
        animated_gifs_button.setOnClickListener(View.OnClickListener {delete_file(animated_gifs_button,WHATSAPP_PATH + "Whatsapp Animated Gifs/Sent")})
        */
    }
}

fun is_list_empty(path: String): Boolean{
    for (file in File(path).listFiles())
        if (file.name != ".nomedia") return false
    return true
}

fun button_text_empty_list(button: Button, path: String){
    if (!is_list_empty(path))
        button.text = button.text.toString().plus(" *")
    else
        button.text = button.text.toString().replace(" *", "")
}

fun delete_file(button: Button, path: String) {
    button.text.toString().replace(" *", "")
    for (file in File(path).listFiles()!!) {
        if (file.name == ".nomedia") continue
        if (file.isDirectory) {
            delete_file(button, file.path)
        }
        file.delete()
    }
    button_text_empty_list(button, path)
}
/*
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WhatsappMemoryClearerTheme {
        Greeting("Android")
    }
}*/