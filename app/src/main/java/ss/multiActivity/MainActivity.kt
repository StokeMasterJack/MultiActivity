package ss.multiActivity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.Text
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.material.MaterialTheme
import androidx.ui.material.TopAppBar
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp

enum class TabAction {
    Blue,
    Red
}

typealias TabDispatch = (ev: TabAction) -> Unit


var backFromChild = false
var aString: String? = null

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainMenu(this)
        }


    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.w("MultiActivity","ActivityResult")
        if (requestCode == 111) {
            if (resultCode == Activity.RESULT_OK) {
                // code for result
                aString = data!!.extras!!.getString("result")
                backFromChild = true

                Log.w("MultiActivity","ActivityResult: ${aString}")
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                // Write your code on no result return
            }
        }
    }


}

fun Intent.putUser(user: User) {
    putExtras(user.toBundle())
}

@Composable
fun MainMenu(ctx: Activity) {

    val b = Bundle()
    b.putString("userName", "ffff")

    fun startBlueActivity() {
        val intent = Intent(ctx, BlueActivity::class.java).apply {
            putUser(User("dford", "Dave", "Ford"))
        }
//        ctx.startActivity(intent)
        ctx.startActivityForResult(intent, 111)

    }

    fun startRedActivity() {
        val intent = Intent(ctx, RedActivity::class.java)
        ctx.startActivity(intent)
    }

    fun dispatch(action: TabAction) {
        when (action) {
            TabAction.Blue -> startBlueActivity()
            TabAction.Red -> startRedActivity()
        }
    }

    MainMenuVu(::dispatch)

}

@Composable
fun MainMenuVu(dispatch: TabDispatch) {
    MaterialTheme {
        Column {
            TopAppBar(
                title = { Text("Multi Activity") }
            )
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalGravity = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.padding(8.dp).fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalGravity = Alignment.CenterHorizontally
                ) {
                    Button(
                        text = { Text("Start Blue Activity") },
                        modifier = Modifier.padding(8.dp).width(200.dp),
                        onClick = { dispatch(TabAction.Blue) }
                    )
                    Button(
                        text = { Text("Start Red Activity") },
                        modifier = Modifier.padding(8.dp).width(200.dp),
                        onClick = { dispatch(TabAction.Red) }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview2() {
    MainMenuVu {}
}
