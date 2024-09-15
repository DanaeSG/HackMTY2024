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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

data class p_Invoice(
    val date: String,
    val description: String,
    val paymentMethod: String,
    val status: String,
    val amount: String
)

val pastInvoices = listOf(
    p_Invoice("August 31", "Stripe oxxo smar", "Card payment", "Finished", "$27.80"),
    p_Invoice("August 31", "Stripe oxxo smar", "Card payment", "Finished", "$257.80"),
    p_Invoice("September 30", "Stripe oxxo smar", "Card payment", "Finished", "$27.80"),
    p_Invoice("September 30", "Stripe oxxo smar", "Card payment", "Finished", "$257.80")
)

@Composable
fun p_InvoiceCard(invoice: p_Invoice, onCardClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onCardClick() }, // Disparamos el callback al hacer clic
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
fun p_InvoiceList(snackbarHostState: SnackbarHostState) {
    val coroutineScope = rememberCoroutineScope()

    LazyColumn(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        pastInvoices.groupBy { it.date }.forEach { (date, invoices) ->
            item {
                Text(
                    text = date,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            items(invoices) { invoice ->
                p_InvoiceCard(invoice = invoice) {
                    // Mostrar Snackbar al hacer clic en una tarjeta
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("Archivo descargado en el teléfono")
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun past_invoices(navController: NavController) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = {
            CustomTopAppBar(title = "Past Invoices")
        },
        bottomBar = {
            CustomBottomAppBar(page = 1, navController)
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) // Snackbar host para mostrar mensajes emergentes
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding)
        ) {
            p_InvoiceList(snackbarHostState = snackbarHostState)
        }
    }
}
