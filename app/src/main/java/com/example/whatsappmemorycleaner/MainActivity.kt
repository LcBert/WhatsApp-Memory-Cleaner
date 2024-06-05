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

private const val WHATSAPP_PATH = "/storage/emulated/0/Android/media/com.whatsapp/WhatsApp/Media/"

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

        image_button.setOnClickListener(View.OnClickListener {delete_file(WHATSAPP_PATH + "Whatsapp Images/Sent")})
        video_button.setOnClickListener(View.OnClickListener {delete_file(WHATSAPP_PATH + "Whatsapp Video/Sent")})
        voice_notes_button.setOnClickListener(View.OnClickListener {delete_file(WHATSAPP_PATH + "Whatsapp Voice Notes")})
        video_notes_button.setOnClickListener(View.OnClickListener {delete_file(WHATSAPP_PATH + "Whatsapp voice Notes")})
        stickers_button.setOnClickListener(View.OnClickListener {delete_file(WHATSAPP_PATH + "Whatsapp Stickers")})
        documents_button.setOnClickListener(View.OnClickListener {delete_file(WHATSAPP_PATH + "Whatsapp Documents/Sent")})
        audio_button.setOnClickListener(View.OnClickListener {delete_file(WHATSAPP_PATH + "Whatsapp Audio/Sent")})
        animated_gifs_button.setOnClickListener(View.OnClickListener {delete_file(WHATSAPP_PATH + "Whatsapp Animated Gifs/Sent")})
    }
}

fun delete_file(path:String){
    for (file in File(path).listFiles()!!) {
        if (file.name == ".nomedia") continue
        if (file.isDirectory) {
            delete_file(file.path)
        }
        file.delete()
    }
}

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
}