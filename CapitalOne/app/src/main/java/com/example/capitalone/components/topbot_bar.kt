import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
//import com.example.capitalone.R
import com.example.capitalone.ui.theme.CO_Blue
import com.example.loginuser.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(title: String) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = CO_Blue,
            titleContentColor = Color.White
        ),
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                Text(title)
            }

        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomBottomAppBar(page: Int) {
    Box(
        modifier = Modifier.drawBehind {
            drawLine(
                color = Color.Gray,
                start = Offset(0f, 0f),
                end = Offset(size.width, 0f),
                strokeWidth = 2.dp.toPx()
            )
        }
    ) {
        BottomAppBar(
            containerColor = Color.White,
            actions = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    IconButton(onClick = { /* do something */ }) {
                        val ISize: Int = if (page == 1) 32 else 24

                        Image(
                            painter = painterResource(id = R.drawable.new_svgrepo_com),
                            contentDescription = "New Icon",
                            modifier = Modifier.size(ISize.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(70.dp))
                    IconButton(onClick = { /* do something */ }) {
                        Image(
                            painter = painterResource(id = R.drawable.past_edit_editor_format_text_tool_svgrepo_com),
                            contentDescription = "Another Icon",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(70.dp))
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            Icons.Filled.Settings,
                            contentDescription = "Settings"
                        )
                    }
                }
            }
        )
    }
}
