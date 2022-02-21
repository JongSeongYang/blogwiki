package com.hiandd.wiki.config

import com.querydsl.core.types.ExpressionUtils
import com.querydsl.core.types.Ops
import com.querydsl.core.types.Path
import com.querydsl.core.types.Predicate
import com.querydsl.core.types.dsl.Expressions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.mongo.MongoProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.MongoDatabaseFactory
import org.springframework.data.mongodb.MongoTransactionManager
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper
import org.springframework.data.mongodb.core.convert.MappingMongoConverter
import org.springframework.data.mongodb.core.mapping.MongoMappingContext
import org.springframework.data.mongodb.core.query.TextCriteria
import org.springframework.data.mongodb.core.query.Update
import javax.annotation.PostConstruct
import kotlin.reflect.KProperty


@Configuration
@EnableMongoAuditing
class MongoConfig{

    @Autowired
    lateinit var mappingMongoConverter : MappingMongoConverter

    @Bean
    @ConfigurationProperties("spring.data.mongodb")
    fun properties(): MongoProperties {
        return MongoProperties()
    }

    @Bean
    fun mongoClientDatabaseFactory(properties: MongoProperties): SimpleMongoClientDatabaseFactory {
        return SimpleMongoClientDatabaseFactory(properties.uri)
    }

    @Bean
    fun transactionManager(databaseFactory: MongoDatabaseFactory): MongoTransactionManager {
        return MongoTransactionManager(databaseFactory)
    }

    // Querydsl 에서의 text 인덱스에 대한 FTS 를 위한 TextCriteria.toPredicate() 확장 함수 작성
    fun TextCriteria.toPredicate(documentType: Any): Predicate {

        val path: Path<Any>? = ExpressionUtils.path(documentType::class.java, "\$text")
        val value = Expressions.constant(this.criteriaObject["\$text"])

        return ExpressionUtils.predicate(Ops.EQ, path, value)
    }

    // Type-Safe로 작동하는 Update.set() 확장 함수 작성
    fun Update.set(property: KProperty<*>, value: Any?): Update {

        return set(property.name, value)
    }

    // Type-Safe로 작동하는 Update.inc() 확장 함수 작성
    fun Update.inc(property: KProperty<*>, value: Number): Update {

        return inc(property.name, value)
    }

    @PostConstruct
    fun setUpMongoEscapeCharacterConversion() {
        mappingMongoConverter.setTypeMapper(DefaultMongoTypeMapper(null))
    }
}