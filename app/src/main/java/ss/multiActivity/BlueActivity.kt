package ss.multiActivity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.foundation.Text
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.fillMaxSize
import androidx.ui.material.Button


class BlueActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val user = User.fromBundle(intent.extras)

        setContent { BluePage(user, f = { endActivity() }) }

    }

    fun endActivity() {
        val returnIntent = Intent().apply {
            putExtra("result", "123")
        }
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }

}


@Composable
fun BluePage(user: User, f: () -> Unit) {

    Box(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = Color.Blue.copy(alpha = .2f),
        gravity = ContentGravity.Center
    ) {
        Column() {
            Text(text = user.userName)
            Button(onClick = { f() }) {
                Text("Return to main activity")
            }
        }

    }

}
