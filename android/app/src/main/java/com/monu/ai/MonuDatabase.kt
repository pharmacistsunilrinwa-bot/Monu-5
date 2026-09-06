package com.monu.ai

import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.Update
import androidx.room.Delete
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "conversations")
data class ConversationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val title: String = "New Chat",
    val pinned: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "messages")
data class MessageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val conversationId: Long,
    val role: String,
    val content: String,

    val route: String = "local",
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "memories")
data class MemoryEntity(
    @PrimaryKey
    val key: String,

    val value: String,
    val updatedAt: Long = System.currentTimeMillis()
)

@Dao
interface MonuDao {

    @Query("""
        SELECT * FROM conversations
        ORDER BY pinned DESC, updatedAt DESC
    """)
    fun conversations(): Flow<List<ConversationEntity>>

    @Query("""
        SELECT * FROM conversations
        WHERE title LIKE '%' || :query || '%'
        ORDER BY pinned DESC, updatedAt DESC
    """)
    fun searchConversations(
        query: String
    ): Flow<List<ConversationEntity>>

    @Query("""
        SELECT * FROM messages
        WHERE conversationId = :conversationId
        ORDER BY createdAt ASC
    """)
    fun messages(
        conversationId: Long
    ): Flow<List<MessageEntity>>

    @Query("""
        SELECT * FROM memories
        ORDER BY updatedAt DESC
    """)
    fun memories(): Flow<List<MemoryEntity>>

    @Insert
    suspend fun insertConversation(
        conversation: ConversationEntity
    ): Long

    @Insert
    suspend fun insertMessage(
        message: MessageEntity
    ): Long

    @Insert
    suspend fun insertMemory(
        memory: MemoryEntity
    )

    @Update
    suspend fun updateConversation(
        conversation: ConversationEntity
    )

    @Delete
    suspend fun deleteConversation(
        conversation: ConversationEntity
    )

    @Query("""
        DELETE FROM messages
        WHERE conversationId = :conversationId
    """)
    suspend fun deleteMessages(
        conversationId: Long
    )
}

@Database(
    entities = [
        ConversationEntity::class,
        MessageEntity::class,
        MemoryEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class MonuDatabase : RoomDatabase() {

    abstract fun dao(): MonuDao

    companion object {

        @Volatile
        private var INSTANCE: MonuDatabase? = null

        fun get(context: Context): MonuDatabase {

            return INSTANCE ?: synchronized(this) {

                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    MonuDatabase::class.java,
                    "monu_memory.db"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}
