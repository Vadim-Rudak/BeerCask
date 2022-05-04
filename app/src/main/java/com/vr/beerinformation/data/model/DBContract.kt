package com.vr.beerinformation.data.model

import android.provider.BaseColumns

object DBContract {

    /* Inner class that defines the table contents */
    class BeerEntry : BaseColumns {
        companion object {
            val TABLE_NAME = "Beer"
            val COLUMN_ID = "id"
            val COLUMN_NAME = "name"
            val COLUMN_FIRST_BREWED = "first_brewed"
            val COLUMN_DESCRIPTION = "description"
            val COLUMN_IMAGE_URL = "image_url"
        }
    }
}