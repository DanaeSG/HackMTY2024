import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.capitalone.R
import com.example.capitalone.ui.theme.CO_Blue
import com.example.capitalone.ui.theme.CO_Grey

data class Invoice(
    val date: String,
    val description: String,
    val paymentMethod: String,
    val status: String,
    val amount: String
)

val upcomingInvoices = listOf(
    Invoice("August 31", "Stripe oxxo smar", "Card payment", "Finished", "$27.80"),
    Invoice("August 31", "Stripe oxxo smar", "Card payment", "Finished", "$257.80"),
    Invoice("September 30", "Stripe oxxo smar", "Card payment", "Finished", "$27.80"),
    Invoice("September 30", "Stripe oxxo smar", "Card payment", "Finished", "$257.80")
)

@Composable
fun InvoiceCard(invoice: Invoice) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = CO_Grey)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = invoice.description, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = invoice.paymentMethod)
            Text(text = invoice.status)
            Text(
                text = invoice.amount,
                modifier = Modifier.align(Alignment.End),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun InvoiceList() {
    LazyColumn(
        modifier = Modifier.padding(16.dp),
        
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        upcomingInvoices.groupBy { it.date }.forEach { (date, invoices) ->
            item {
                Text(
                    text = date,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            items(invoices) { invoice ->
                InvoiceCard(invoice = invoice)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showBackground = false)
@Composable
fun PreviewInvoiceList(innerPaddingValues: PaddingValues) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = CO_Blue,
                    titleContentColor = Color.White,
                ),
                title = {
                    Text("Upcoming Invoices")
                }
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding)
        ){
            InvoiceList()
        }

    }
}

@Preview
@Composable
fun BottomAppBarExample() {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = Color.White,
                actions = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ){
                        IconButton(onClick = { /* do something */ }) {
                            Image(
                                painter = painterResource(id = R.drawable.new_svgrepo_com), // Reemplaza con el nombre del archivo
                                contentDescription = "New Icon",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        IconButton(onClick = { /* do something */ }) {
                            Image(
                                painter = painterResource(id = R.drawable.past_edit_editor_format_text_tool_svgrepo_com),
                                contentDescription = "fdsnjifds",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        IconButton(onClick = { /* do something */ }) {
                            Icon(
                                Icons.Filled.Settings,
                                contentDescription = "Localized description",
                            )
                        }
                    }

                },
            )
        },
    ) { innerPadding ->
        PreviewInvoiceList(innerPadding)
    }
}