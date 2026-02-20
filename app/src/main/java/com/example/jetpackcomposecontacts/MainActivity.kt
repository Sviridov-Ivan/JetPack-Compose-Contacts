package com.example.jetpackcomposecontacts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecontacts.ui.theme.Contact
import com.example.jetpackcomposecontacts.ui.theme.JetPackComposeContactsTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetPackComposeContactsTheme {
                ContactData(
                    contact = Contact(
                        name = "Евгений",
                        surname = "Андреевич",
                        familyName = "Лукашин",
                        phone = "+7 495 495 95 95",
                        address = "г. Москва, 3-я улица Строителей, д. 25, кв. 12",
                        imageRes = null,
                        isFavorite = true,
                        email = "ELukashin@practicum.ru"
                    )
                )
            }
        }
    }
}

@Composable
fun ContactData(contact: Contact) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AvaSection(contact)

        Spacer(modifier = Modifier.height(16.dp))

        NameSection(contact)

        Spacer(modifier = Modifier.height(32.dp))

        InfoSection(contact)
    }
}

@Composable
fun AvaSection(contact: Contact) {

    Box(
        contentAlignment = Alignment.Center
    ){
        // использую вместо Image удобно для Иконок и Аватарок
        Icon(
            painter = painterResource(id = R.drawable.circle),
            contentDescription = null,
            modifier = Modifier.size(80.dp),
            tint = Color.LightGray/*if (contact.isFavorite) Color.Red else Color.Gray*/
        )

        if (contact.imageRes != null) {

            Image(
                painter = painterResource(id = contact.imageRes),
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )
        } else {

            val initials = contact.name.take(1) + contact.familyName.take(1)

            Text(
                text = initials,
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Black
            )
        }
    }
}

@Composable
fun NameSection(contact: Contact) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = listOfNotNull(contact.name, contact.surname).joinToString(" "), // если нет имени или отчества, то убирается пробел и исп.разделитель пробел
            style = MaterialTheme.typography.titleLarge
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Spacer(
                modifier = Modifier.weight(1f)) // вместо margin в ХМЛ, для занятия свободного пространства перед и для выравнивания

            Text(
                text = contact.familyName,
                style = MaterialTheme.typography.headlineMedium
            )

            if (contact.isFavorite) {
                Spacer(modifier = Modifier.width(8.dp))

                Icon(
                    painter = painterResource(id = android.R.drawable.star_big_on),
                    contentDescription = null,
                    tint = Color(0xFFFFC107)
                )
            }

            Spacer(modifier = Modifier.weight(1f)) // для занятия свободного пространства после звезды и для выравнивания
        }
    }
}

@Composable
fun InfoSection(contact: Contact) {

    // обернул в Row для распределения по горизонтали
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {

        Spacer(
            modifier = Modifier.weight(0.5f) // отступ справа (пустое пространство)
        )

        Column(
            //modifier = Modifier.fillMaxWidth(0.6f),
            modifier = Modifier
                .weight(1f) // занимает оставшееся пространство от Spacer минус padding
                .padding(end = 16.dp)
        ) {

            val phoneText =
                contact.phone.ifBlank { "---" }
            //if (contact.phone.isBlank()) "---" else contact.phone // студия порекомендовала

            InfoRow(
                title = stringResource(R.string.phone),
                value = phoneText
            )

            InfoRow(
                title = stringResource(R.string.address),
                value = contact.address,
                maxLines = 2
            )

            if (!contact.email.isNullOrBlank()) {
                InfoRow(
                    title = stringResource(R.string.email),
                    value = contact.email
                )
            }
        }
    }
}

// универсальная строка для адреса, телефона и почты
@Composable
fun InfoRow(
    title: String,
    value: String,
    maxLines: Int = 1
) {
    Row(
        modifier = Modifier.padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically // для разделения от центра
    ) {

        Text(
            text = "$title: ",
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = maxLines,
            //overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview(name = "portrait", showSystemUi = true)
@Composable
fun ContactColumnListPreview() {
    ContactData(
        contact = Contact(
            name = "Евгений",
            surname = "Андреевич",
            familyName = "Лукашин",
            phone = "+7 495 495 95 95",
            address = "г. Москва, 3-я улица Строителей, д. 25, кв. 12",
            imageRes = null,
            isFavorite = true,
            email = "ELukashin@practicum.ru"
        )
    )
}

@Preview(name = "portrait", showSystemUi = true)
@Composable
fun ContactColumnListPreviewKuzyakin() {
    ContactData(
        contact = Contact(
            name = "Василий",
            surname = "",
            familyName = "Кузякин",
            phone = "",
            address = "Ивановская область, дер.Крутово, д.4",
            imageRes = R.drawable.v_k,
            isFavorite = false,
        )
    )
}