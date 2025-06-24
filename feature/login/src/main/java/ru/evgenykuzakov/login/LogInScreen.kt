package ru.evgenykuzakov.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ru.evgenykuzakov.resources.R
import ru.evgenykuzakov.ui.theme.Blue
import ru.evgenykuzakov.ui.theme.OrangeGradientEnd
import ru.evgenykuzakov.ui.theme.OrangeGradientStart

@Composable
fun LoginScreen(
    viewModel: SignInViewModel = koinViewModel(),
    signInButtonListener: () -> Unit,
    onVKButtonClicked: () -> Unit,
    onOKButtonClicked: () -> Unit,
) {
    val isContinueButtonActive = viewModel.isFormValid
    Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Spacer(Modifier.height(100.dp))
            Text(
                modifier = Modifier.align(Alignment.Start),
                text = stringResource(R.string.sign_in),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Spacer(Modifier.height(28.dp))
            Text(
                modifier = Modifier.align(Alignment.Start),
                text = stringResource(R.string.email),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(Modifier.height(8.dp))
            TextFieldBuilder(
                text = viewModel.email.value,
                placeholderText = stringResource(R.string.example_email),
                onValueChanged = { viewModel.onEmailTextChanged(it) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                visualTransformation = VisualTransformation.None
            )
            Spacer(Modifier.height(16.dp))
            Text(
                modifier = Modifier.align(Alignment.Start),
                text = stringResource(R.string.password),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(Modifier.height(8.dp))
            TextFieldBuilder(
                text = viewModel.password.value,
                placeholderText = stringResource(R.string.enter_password),
                onValueChanged = { viewModel.onPasswordTextChanged(it) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(Modifier.height(24.dp))
            Button(
                onClick = {
                    signInButtonListener()
                },
                enabled = isContinueButtonActive.value,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    disabledContainerColor =  MaterialTheme.colorScheme.secondaryContainer
                ),
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                Text(
                    stringResource(R.string.sign_in),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(vertical = 10.dp),
                )
            }
            Spacer(Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.labelSmall,
                    text = stringResource(R.string.quess_no_account)
                )
                Text(
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .clickable { },
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.labelSmall,
                    text = stringResource(R.string.sign_up)
                )
            }
            Spacer(Modifier.height(8.dp))
            Text(
                modifier = Modifier
                    .clickable { },
                text = stringResource(R.string.forgot_password),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelSmall
            )

            Spacer(Modifier.height(32.dp))
            HorizontalDivider()
            Spacer(Modifier.height(32.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { onVKButtonClicked() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = MaterialTheme.colorScheme.onSurface
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(100.dp))
                        .background(color = Blue),
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_vk),
                        contentDescription = stringResource(R.string.redirect_to_vk)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    onClick = { onOKButtonClicked() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = MaterialTheme.colorScheme.onSurface
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(100.dp))
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    OrangeGradientStart,
                                    OrangeGradientEnd
                                )
                            )
                        )
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_ok),
                        contentDescription = stringResource(R.string.redirect_to_ok)
                    )
                }
            }
        }
    }
}

@Composable
private fun TextFieldBuilder(
    text: String,
    placeholderText: String,
    onValueChanged: (newText: String) -> Unit,
    keyboardOptions: KeyboardOptions,
    visualTransformation: VisualTransformation
) {
    TextField(
        value = text,
        onValueChange = { onValueChanged(it) },
        placeholder = {
            Text(
                text = placeholderText,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer
        ),
        maxLines = 1,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .clip(RoundedCornerShape(100.dp))
    )
}


