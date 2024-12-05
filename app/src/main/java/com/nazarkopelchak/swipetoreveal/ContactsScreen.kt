package com.nazarkopelchak.swipetoreveal

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun ContactsScreen(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val contacts = remember {
        mutableStateListOf(
            *(1..100).map { // * spreads array across mutable state list
                ContactUi(
                    id = it,
                    name = "Contact $it",
                    isOptionsRevealed = false
                )
            }.toTypedArray()
        )
    }

    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        itemsIndexed(
            items = contacts,
            key = {index, item -> "${item.id}-${index}" }
            //key = {_, contact -> contact.id }   // used to recompose the list when contact id changes. Crashes
        ) { index, contact ->
            SwipeableItemWithActions(
                isRevealed = contact.isOptionsRevealed,
                onExpanded = {
                    contacts[index] = contact.copy(isOptionsRevealed = true)
                },
                onCollapsed = {
                    contacts[index] = contact.copy(isOptionsRevealed = false)
                },
                actions = {
                    ActionIcon(
                        onClick = {
                            contacts.remove(contact)
                            Toast.makeText(
                                context,
                                "Contact ${contact.id} was removed.",
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        backgroundColor = Color.Red,
                        icon = Icons.Default.Delete,
                        modifier = Modifier.fillMaxHeight()
                    )
                    ActionIcon(
                        onClick = {
                            contacts[index] = contact.copy(isOptionsRevealed = false)
                            Toast.makeText(
                                context,
                                "Contact ${contact.id} was sent an email.",
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        backgroundColor = Color.Cyan,
                        icon = Icons.Default.Email,
                        modifier = Modifier.fillMaxHeight()
                    )
                    ActionIcon(
                        onClick = {
                            contacts[index] = contact.copy(isOptionsRevealed = false)
                            Toast.makeText(
                                context,
                                "Contact ${contact.id} was shared.",
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        backgroundColor = Color.Green,
                        icon = Icons.Default.Share,
                        modifier = Modifier.fillMaxHeight()
                    )
                },
            ) {
                Text(
                    text = "Contact ${contact.id}",
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Composable
private fun ContactIcons(contactUi: ContactUi) {
    val context = LocalContext.current
    ActionIcon(
        onClick = {

        },
        backgroundColor = Color.Red,
        icon = Icons.Default.Delete,
        modifier = Modifier.fillMaxHeight()
    )
    ActionIcon(
        onClick = {  },
        backgroundColor = Color.Yellow,
        icon = Icons.Default.Email,
        modifier = Modifier.fillMaxHeight()
    )
    ActionIcon(
        onClick = {  },
        backgroundColor = Color.Green,
        icon = Icons.Default.Share,
        modifier = Modifier.fillMaxHeight()
    )
}