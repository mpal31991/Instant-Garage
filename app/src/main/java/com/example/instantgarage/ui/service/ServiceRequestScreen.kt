package com.example.instantgarage.ui.service

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.instantgarage.data.model.Mechanic

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServiceRequestScreen(
    navController: NavHostController
) {

    val mechanic = navController
        .previousBackStackEntry
        ?.savedStateHandle
        ?.get<Mechanic>("mechanic")

    var customerName by rememberSaveable {
        mutableStateOf("")
    }

    var phoneNumber by rememberSaveable {
        mutableStateOf("")
    }

    var vehicleNumber by rememberSaveable {
        mutableStateOf("")
    }

    var selectedService by rememberSaveable {
        mutableStateOf("")
    }

    var problemDescription by rememberSaveable {
        mutableStateOf("")
    }

    var isServiceMenuExpanded by remember {
        mutableStateOf(false)
    }

    var isSubmitted by rememberSaveable {
        mutableStateOf(false)
    }

    var validationError by rememberSaveable {
        mutableStateOf<String?>(null)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Service Request")
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

            // Mechanic information unavailable
            mechanic == null -> {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Mechanic information not available!",
                        textAlign = TextAlign.Center
                    )
                }
            }

            // Garage is closed
            !mechanic.isOpen -> {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Closed",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.error
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "${mechanic.name} is currently closed.",
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center
                    )
                }
            }

            // Request submitted
            isSubmitted -> {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Request Submitted!",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF007C06)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Your service request has been submitted to ${mechanic.name}.",
                        textAlign = TextAlign.Center
                    )
                }
            }

            // Service request form
            else -> {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(innerPadding)
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    Text(
                        text = mechanic.name,
                        style = MaterialTheme.typography.headlineSmall
                    )

                    Text(
                        text = "Enter your details to request a service.",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    // Customer Name
                    OutlinedTextField(
                        value = customerName,
                        onValueChange = {
                            customerName = it
                            validationError = null
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text("Customer Name")
                        },
                        singleLine = true
                    )

                    // Phone Number
                    OutlinedTextField(
                        value = phoneNumber,
                        onValueChange = {
                            phoneNumber = it
                            validationError = null
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text("Phone Number")
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Phone
                        ),
                        singleLine = true
                    )

                    // Vehicle Number
                    OutlinedTextField(
                        value = vehicleNumber,
                        onValueChange = {
                            vehicleNumber = it
                            validationError = null
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text("Vehicle Number")
                        },
                        singleLine = true
                    )

                    // Select Service
                    ExposedDropdownMenuBox(
                        expanded = isServiceMenuExpanded,
                        onExpandedChange = {
                            isServiceMenuExpanded = !isServiceMenuExpanded
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        OutlinedTextField(
                            value = selectedService,
                            onValueChange = {
                                validationError = null
                            },
                            readOnly = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor(),
                            label = {
                                Text("Select Service")
                            },
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(
                                    expanded = isServiceMenuExpanded
                                )
                            }
                        )

                        ExposedDropdownMenu(
                            expanded = isServiceMenuExpanded,
                            onDismissRequest = {
                                isServiceMenuExpanded = false
                            }
                        ) {

                            mechanic.services.forEach { service ->

                                DropdownMenuItem(
                                    text = {
                                        Text(service)
                                    },
                                    onClick = {
                                        selectedService = service
                                        isServiceMenuExpanded = false
                                    }
                                )
                            }
                        }
                    }

                    // Problem Description
                    OutlinedTextField(
                        value = problemDescription,
                        onValueChange = {
                            problemDescription = it
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp),
                        label = {
                            Text("Problem Description")
                        }
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    // Submit Request
                    Button(
                        onClick = {
                            validationError = when {
                                customerName.isBlank() ->
                                    "Please enter your name"

                                phoneNumber.isBlank() ->
                                    "Please enter your phone number"

                                !phoneNumber.matches(Regex("\\d{10}")) ->
                                    "Please enter a valid 10-digit phone number"

                                vehicleNumber.isBlank() ->
                                    "Please enter your vehicle number"

                                selectedService.isBlank() ->
                                    "Please select a service"

                                problemDescription.isBlank() ->
                                    "Please describe the problem"

                                else -> null
                            }

                            if (validationError == null) {
                                isSubmitted = true
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Submit Request")
                    }

                    validationError?.let { error ->
                        Text(
                            text = "* $error",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}