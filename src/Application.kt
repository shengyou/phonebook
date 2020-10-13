package io.kraftsman

import com.github.javafaker.Faker
import io.kraftsman.responses.Contact
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.jackson.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import org.joda.time.DateTime
import java.util.*
import kotlin.random.Random

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    install(ContentNegotiation) {
        jackson {

        }
    }

    routing {

        get("/") {
            call.respondText("Hello, Ktor")
        }

        get("/api/v1/contacts") {
            val faker = Faker(Locale("en"))
            val tasks = mutableListOf<Contact>()

            for (i in 1..10) {
                tasks.add(
                    Contact(
                        id = i.toLong(),
                        name = faker.name().fullName(),
                        email = faker.internet().emailAddress(),
                        mobile = faker.phoneNumber().cellPhone(),
                        address = faker.address().fullAddress(),
                        createdAt = DateTime.now().plusDays(i).toString("yyyy-MM-dd HH:mm:ss"),
                        updatedAt = DateTime.now().plusHours(Random.nextInt(1, 10)).toString("yyyy-MM-dd HH:mm:ss")
                ))
            }

            call.respond(mapOf("data" to tasks))
        }
    }
}

