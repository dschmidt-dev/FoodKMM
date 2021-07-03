package dev.dschmidt.foodkmm.android.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.dschmidt.foodkmm.domain.model.NegativeAction
import dev.dschmidt.foodkmm.domain.model.PositiveAction

@Composable
fun GenericDialog(
    modifier: Modifier = Modifier,
    onDismiss: (() -> Unit)?,
    title: String,
    description: String? = null,
    positiveAction: PositiveAction?,
    negativeAction: NegativeAction?,
    onRemoveHeadFromQueue : () -> Unit,
) {
    androidx.compose.material.AlertDialog(
        modifier = modifier,
        onDismissRequest = {
            onDismiss?.invoke()
            onRemoveHeadFromQueue()
        },
        text = {
            if (description != null) {
                Text(
                    text = description,
                    style = MaterialTheme.typography.body1
                )
            }
        },
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.h3
            )
        },
        buttons = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                if (negativeAction != null) {
                    Button(
                        modifier = Modifier.padding(end = 8.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onError),
                        onClick = {
                            negativeAction.onNegativeAction()
                            onRemoveHeadFromQueue()
                        }
                    ) {
                        Text(
                            text = negativeAction.negativeBtnTxt,
                            style = MaterialTheme.typography.button
                        )
                    }
                }
                Button(
                    modifier = Modifier.padding(end = 8.dp),
                    onClick = {
                        positiveAction?.onPositiveAction
                        onRemoveHeadFromQueue()
                    }
                ) {
                    Text(
                        text = positiveAction?.positiveBtnTxt ?: "OK",
                        style = MaterialTheme.typography.button
                    )
                }
            }
        }
    )
}