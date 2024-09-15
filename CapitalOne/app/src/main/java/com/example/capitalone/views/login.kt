import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.Alignment
import com.example.capitalone.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LargeTopAppBarExample() {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF004878),  // Cambiar el color de fondo a #004878
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()  // Asegura que el Row ocupe todo el ancho disponible
                            .wrapContentSize(Alignment.Center)  // Centra el contenido dentro del Row
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.logo),  // Asegúrate de que el archivo logo.png esté en res/drawable
                            contentDescription = "App Logo",
                            modifier = Modifier.size(120.dp),  // Ajusta el tamaño según tu imagen
                            contentScale = ContentScale.Fit
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(innerPadding)
        ){
            ScrollContent()
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScrollContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 40.dp, vertical = 40.dp)  // Padding a los lados
    ) {
        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        Text(
            text = "Account Number",
            style = MaterialTheme.typography.bodyLarge
        )

        // Caja para el TextField con borde
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(BorderStroke(1.dp, Color(0xFFB3B3B3)) // Borde con color #B3B3B3
                )
        ) {
            TextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Account Number") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = TextFieldDefaults.colors(
                ),
                placeholder = { Text("Account Number", color = Color(0xFFB3B3B3)) },
                shape = MaterialTheme.shapes.small.copy(all = CornerSize(4.dp))
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Password",
            style = MaterialTheme.typography.bodyLarge
        )

        // Caja para el TextField con borde
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(BorderStroke(1.dp, Color(0xFFB3B3B3)) // Borde con color #B3B3B3
                )
        ) {
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                visualTransformation = PasswordVisualTransformation(),
                colors = TextFieldDefaults.colors(
                ),
                placeholder = { Text("Password", color = Color(0xFFB3B3B3)) },
                shape = MaterialTheme.shapes.small.copy(all = CornerSize(4.dp))
            )
        }


        Spacer(modifier = Modifier.height(24.dp))

        // Botón azul "Register"
        Button(
            onClick = { /* handle registration */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF004878)),  // Color azul
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 0.dp)  // Padding horizontal para los botones
        ) {
            Text(text = "Register", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón negro "I forgot my password"
        Button(
            onClick = { /* handle forgot password */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2C2C2C)),  // Color negro
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 0.dp)  // Padding horizontal para los botones
        ) {
            Text(text = "I forgot my password", color = Color.White)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Texto y enlace de login
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Already have an account? ")
            Text(
                text = "Login",
                color = Color(0xFF004878),  // Color del enlace
                modifier = Modifier.clickable { /* handle navigation to login */ }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Login() {
    LargeTopAppBarExample()
}
