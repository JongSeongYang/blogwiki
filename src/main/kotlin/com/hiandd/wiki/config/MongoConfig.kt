package com.hiandd.wiki.config

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration

class MongoConfig : AbstractMongoClientConfiguration() {

    override fun getDatabaseName(): String {
        return "ourblogtest"
    }

    override fun mongoClient(): MongoClient {
        val connectionString = ConnectionString("mogodb://workingscorpion.com:50809/ourblogtest")
        val mongoClientSettings = MongoClientSettings
                .builder().applyConnectionString(connectionString)
                .build()

        return MongoClients.create(mongoClientSettings)
    }
}