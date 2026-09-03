package com.example.instantgarage.ui.mechanic

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.instantgarage.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MechanicDetailsScreen(
    mechanicDetailsViewModel: MechanicDetailsViewModel = hiltViewModel(),
    mechanicId: Int,
    navController: NavHostController
) {

    val mechanic by mechanicDetailsViewModel.mechanic.collectAsState()
    val isLoading by mechanicDetailsViewModel.isLoading.collectAsState()
    val error by mechanicDetailsViewModel.error.collectAsState()

    LaunchedEffect(mechanicId) {
        mechanicDetailsViewModel.getMechanicById(mechanicId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Garage Details")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->

        when {
            isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            error != null -> {
                Box(
                    modifier = Modifier.fillMaxSize()
                        .padding(innerPadding)
                        .padding(horizontal = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = error ?: "An unexpected error occurred",
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp
                    )
                }
            }

            mechanic != null -> {

                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())
                ) {

                    // Garage Name
                    Text(
                        text = mechanic!!.name,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Rating
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Rating",
                            tint = Color(0xFF007C06),
                            modifier = Modifier.size(18.dp),
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        Text(
                            text = mechanic!!.rating.toString()
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Services
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(12.dp))
                            .padding(12.dp)
                    ) {
                        Text(
                            text = "Services",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        HorizontalDivider()

                        Spacer(modifier = Modifier.height(12.dp))

                        mechanic!!.services.forEach { service ->
                            Text(
                                text = "> $service",
                                color = Color.DarkGray
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Address
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "Address",
                            tint = Color(0xFF005193),
                            modifier = Modifier.size(18.dp),
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        Text(
                            text = mechanic!!.address
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Working Hours
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Schedule,
                            contentDescription = "Working hours",
                            tint = Color(0xFF005193),
                            modifier = Modifier.size(18.dp),
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        Text(
                            text = mechanic!!.workingHours
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Phone
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Call,
                            contentDescription = "Phone",
                            tint = Color(0xFF005193),
                            modifier = Modifier.size(18.dp),
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        Text(
                            text = mechanic!!.phone
                        )
                    }

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )

                    // Request Service
                    Button(
                        onClick = {
                            navController.currentBackStackEntry
                                ?.savedStateHandle
                                ?.set("mechanic", mechanic)

                            navController.navigate(Routes.ServiceRequest.route)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Request Service")
                    }
                }
            }
        }
    }
}