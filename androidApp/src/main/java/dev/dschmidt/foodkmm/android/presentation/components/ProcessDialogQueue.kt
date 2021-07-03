package dev.dschmidt.foodkmm.android.presentation.components

import androidx.compose.runtime.Composable
import dev.dschmidt.foodkmm.domain.model.GenericMessageInfo
import dev.dschmidt.foodkmm.domain.util.Queue

@Composable
fun ProcessDialogQueue(
    dialogQueue: Queue<GenericMessageInfo>?,
    onRemoveHeadMessageFromQueue: () -> Unit,
) {
    dialogQueue?.peek()?.let { dialogInfo ->
        GenericDialog(
            title = dialogInfo.title,
            description = dialogInfo.description,
            onRemoveHeadFromQueue = onRemoveHeadMessageFromQueue,
        )
    }
}