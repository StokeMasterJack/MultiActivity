package ss.multiActivity

import android.os.Bundle
import androidx.activity.viewModels
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

class RedActivity : AppCompatActivity() {

    private val viewModel by viewModels<MyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { RedPage(viewModel) }
        viewModel.observe(this) {
            setContent { RedPage(viewModel) }
        }
    }

}

@Composable
fun RedPage(viewModel: MyViewModel) {

    Box(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = Color.Red.copy(alpha = .2f),
        gravity = ContentGravity.Center
    ) {
        Column() {
            Text(text = viewModel.count.value.toString())
            Button(onClick = { viewModel.incrementCount() }) {
                Text("Increment Count")
            }
        }
    }

}
