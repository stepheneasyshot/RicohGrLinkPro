package com.stephen.ricohgrlinkpro.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.stephen.ricohgrlinkpro.model.FilmSimulation

@Composable
fun FilmSimGrid(
    simulations: List<FilmSimulation>,
    selected: String,
    onSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = "FILM SIMULATION",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
            modifier = Modifier.padding(bottom = 8.dp),
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            simulations.take(2).forEach { sim ->
                FilmSimCard(
                    simulation = sim,
                    isSelected = sim.name == selected,
                    onClick = { onSelected(sim.name) },
                    modifier = Modifier.weight(1f),
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            simulations.drop(2).forEach { sim ->
                FilmSimCard(
                    simulation = sim,
                    isSelected = sim.name == selected,
                    onClick = { onSelected(sim.name) },
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }
}

@Composable
private fun FilmSimCard(
    simulation: FilmSimulation,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .background(
                MaterialTheme.colorScheme.surfaceContainerHigh,
                RoundedCornerShape(6.dp),
            )
            .then(
                if (isSelected) {
                    Modifier.border(
                        1.5.dp,
                        MaterialTheme.colorScheme.primaryContainer,
                        RoundedCornerShape(6.dp),
                    )
                } else {
                    Modifier
                },
            )
            .clickable(onClick = onClick)
            .padding(10.dp),
    ) {
        Text(
            text = simulation.name,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.SemiBold,
            color = if (isSelected) {
                MaterialTheme.colorScheme.onSurface
            } else {
                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
            },
        )
        Text(
            text = simulation.subtitle,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.45f),
            modifier = Modifier.padding(top = 2.dp),
        )
    }
}

@Composable
fun DropdownField(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
            modifier = Modifier.padding(bottom = 6.dp),
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    MaterialTheme.colorScheme.surfaceContainerHigh,
                    RoundedCornerShape(6.dp),
                )
                .padding(horizontal = 12.dp, vertical = 10.dp),
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.weight(1f),
            )
            Text(
                text = "▾",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
            )
        }
    }
}
