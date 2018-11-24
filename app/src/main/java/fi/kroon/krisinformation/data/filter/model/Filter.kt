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
                "type"
            ],
            unique = true
        )
    )
)
data class Filter(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long? = null,

    /**
     *  This is the name for a
     *  SenderName, or say Category name
     */
    @ColumnInfo(name = "name")
    val name: String,

    /**
     * Same as name, but with properly
     * formatted text, like Capitalized etc.
     */
    @ColumnInfo(name = "display_name")
    val displayName: String,

    /**
     *  Example:
     *  => Sender/Utgivare,
     *  => MsgType/Meddelandetyp
     */
    @ColumnInfo(name = "type")
    val type: String,

    /**
     * Indicating if its currently being
     * used for filtering.
     */
    @ColumnInfo(name = "is_selected")
    val isSelected: Boolean = false

)