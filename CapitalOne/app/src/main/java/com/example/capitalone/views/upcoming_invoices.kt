import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.capitalone.ui.theme.CO_Grey
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.runtime.*
import androidx.compose.ui.window.Dialog

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
fun InvoiceForm(onDismiss: () -> Unit) {
    var ticketNumber by remember { mutableStateOf("") }
    var totalPaid by remember { mutableStateOf("") }
    val rfc = "RFC pre-asignado" // Valor pre-asignado para el RFC

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = "Invoice Form", fontSize = 18.sp, fontWeight = FontWeight.Bold)

                TextField(
                    value = ticketNumber,
                    onValueChange = { ticketNumber = it },
                    label = { Text("Ticket Number") }
                )

                TextField(
                    value = totalPaid,
                    onValueChange = { totalPaid = it },
                    label = { Text("Total Paid") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                TextField(
                    value = rfc,
                    onValueChange = {},
                    label = { Text("RFC") },
                    enabled = false // Para que no sea editable
                )

                Button(
                    onClick = {
                        // Aquí puedes agregar la lógica para guardar los datos
                        onDismiss()
                    }
                ) {
                    Text(text = "Submit")
                }
            }
        }
    }
}


@Composable
fun InvoiceCard(invoice: Invoice, onCardClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onCardClick() }, // Se abrirá el formulario al hacer clic
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
    var showForm by remember { mutableStateOf(false) }

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
                InvoiceCard(invoice = invoice) {
                    showForm = true // Mostrar el formulario al hacer clic en la tarjeta
                }
            }
        }
    }

    // Mostrar el formulario si showForm es verdadero
    if (showForm) {
        InvoiceForm(onDismiss = { showForm = false })
    }
}


@Composable
fun upcoming_invoices(navController: NavController) {
    Scaffold(
        topBar = {
            CustomTopAppBar(title = "Upcoming Invoices")
        },
        bottomBar = {
            CustomBottomAppBar(page = 1, navController)
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding)
        ) {
            InvoiceList() // Tu función para mostrar la lista de facturas
        }
    }
}
