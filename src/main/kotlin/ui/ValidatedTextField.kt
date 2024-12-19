package org.example.ui
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import org.example.core.ValidationContext

@Composable
fun ValidatedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    validationContext: ValidationContext,
    modifier: Modifier = Modifier
) {
    var errorMessage by remember { mutableStateOf<String?>(null) }

    BasicTextField(
        value = value,
        onValueChange = { newValue ->
            onValueChange(newValue)
            val errors = validationContext.validate(newValue)
            errorMessage = errors.firstOrNull()
        },
        modifier = modifier
    )

    errorMessage?.let {
        // Display error below the TextField
    }
}
