package com.stephen.ricohgrlinkpro

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.stephen.ricohgrlinkpro.theme.GrTheme
import com.stephen.ricohgrlinkpro.ui.shell.AppShell
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    GrTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            val viewModel = remember { AppViewModel() }
            AppShell(viewModel = viewModel)
        }
    }
}
