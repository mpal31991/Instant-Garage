package com.example.instantgarage.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.NearMe
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.instantgarage.data.model.Mechanic

@Composable
fun MechanicCard(
    mechanic: Mechanic,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth()
            .clickable{
                onClick()
            },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Garage Name
                Text(
                    text = mechanic.name,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                // Rating
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Rating",
                        tint = Color(0xFF007C06),
                        modifier = Modifier.size(16.dp),
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Text(
                        text = mechanic.rating.toString()
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            HorizontalDivider()

            Spacer(modifier = Modifier.height(12.dp))

            // Open / Closed Status
            Text(
                text = if (mechanic.isOpen) "Open" else "Closed",
                fontWeight = FontWeight.Bold,
                color = if (mechanic.isOpen) Color(0xFF007C06) else MaterialTheme.colorScheme.error
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Available Services
            Column {
                Text(
                    text = "Services",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = mechanic.services.joinToString(" | "),
                    color = Color.DarkGray
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Location and Distance
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Location
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        imageVector = Icons.Filled.LocationOn,
                        contentDescription = "Location",
                        tint = Color(0xFF005193),
                        modifier = Modifier.size(16.dp),
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Text(
                        text = mechanic.location
                    )
                }

                // Distance
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        imageVector = Icons.Filled.NearMe,
                        contentDescription = "Distance",
                        tint = Color(0xFF005193),
                        modifier = Modifier.size(16.dp),
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Text(
                        text = "${mechanic.distance} km"
                    )
                }
            }
        }
    }
}