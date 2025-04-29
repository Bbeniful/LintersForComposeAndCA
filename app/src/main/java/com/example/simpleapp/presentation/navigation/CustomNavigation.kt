package com.example.simpleapp.presentation.navigation

import android.os.Bundle
import android.util.Base64
import androidx.navigation.NavType
import com.example.simpleapp.domain.model.SimpleItem
import kotlinx.serialization.json.Json

object CustomNavigation: NavType<SimpleItem>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): SimpleItem? {
        return bundle.getParcelable(key)
    }


    override fun put(bundle: Bundle, key: String, value: SimpleItem) {
        bundle.putParcelable(key, value)
    }
    override fun serializeAsValue(value: SimpleItem): String {
        val serializedValue = Json.encodeToString(value)
        return Base64.encodeToString(serializedValue.toByteArray(Charsets.UTF_8), Base64.URL_SAFE or Base64.NO_WRAP)
    }

    override fun parseValue(value: String): SimpleItem {
        val decodedValue = String(Base64.decode(value, Base64.URL_SAFE), Charsets.UTF_8)
        return Json.decodeFromString(decodedValue)
    }
}