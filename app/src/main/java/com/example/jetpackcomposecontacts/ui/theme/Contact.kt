package com.example.jetpackcomposecontacts.ui.theme

import androidx.compose.runtime.Immutable

@Immutable // пометил так, чтобы дать понять Compose, что класс НЕизменяемый 100%, и в нем нет var, MutableList<String>
data class Contact(
    val name: String, //Имя
    val surname: String? = null, //Отчество
    val familyName: String, //Фамилия
    val imageRes: Int? = null,//Ресурс фотографии
    val isFavorite: Boolean = false,//Признак избранного контакта
    val phone: String, //Телефон
    val address: String, //Адрес
    val email: String? = null, //E-mail
) {
    // переменная создана для подготовки ФИО сразу в дата классе, и чтобы не создавать объекты в UI
    val fullName: String
        get() = buildString {
            append(familyName)
            append(" ")
            append(name)
            if (!surname.isNullOrBlank()) {
                append(" ")
                append(surname)
            }
        }

    /* другой вариант создания
    val fullName: String
    get() = listOfNotNull(
        familyName,
        name,
        surname?.takeIf { it.isNotBlank() }
    ).joinToString(" ")  */

}
