package db.migration

import db.migration.ActivityStream.autoIncrement
import db.migration.ActivityStream.primaryKey
import db.migration.Comments.autoIncrement
import db.migration.Comments.primaryKey
import db.migration.UserAccount.autoIncrement
import db.migration.UserAccount.primaryKey
import org.flywaydb.core.api.migration.BaseJavaMigration
import org.flywaydb.core.api.migration.Context
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.transactions.transaction

class V20190101_1__InitDb : BaseJavaMigration() {
    override fun migrate(context: Context?) {
//        Database.connect({context!!.connection})
        Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver", user = "sa", password = "")
        transaction {
            SchemaUtils.create(Comments, ActivityStream, UserAccount, User, Account)
        }
    }

}

object Comments : Table("m_comment") {
    val id = integer("id").autoIncrement().primaryKey()
    val comment = text("comment")
}

object ActivityStream: Table("s_activitystream") {
    val id = integer("id").autoIncrement().primaryKey()
}

object UserAccount: Table("s_user_account") {
    val id = integer("id").autoIncrement().primaryKey()
}

object User: Table("s_user") {
    val username = varchar("username", length = 45).primaryKey()
}

object Account: Table("s_account") {
    val id = integer("id").autoIncrement().primaryKey()
    val createdTime = datetime("createdTime")

}