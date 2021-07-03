package dev.dschmidt.foodkmm.domain.model

sealed class UIComponentType {

    object Dialog: UIComponentType()

    object None: UIComponentType()

    //TODO add snackbar if needed here
}
