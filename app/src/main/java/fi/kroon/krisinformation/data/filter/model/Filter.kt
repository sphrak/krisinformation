package fi.kroon.krisinformation.data.filter.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "filter",
    indices = arrayOf(
        Index(
            value = [
                "name",
                "display_name",
                "category"
            ],
            unique = true
        )
    )
)
data class Filter(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "display_name")
    val displayName: String,

    @ColumnInfo(name = "category")
    val category: String,

    @ColumnInfo(name = "is_selected")
    val isSelected: Boolean = false

)