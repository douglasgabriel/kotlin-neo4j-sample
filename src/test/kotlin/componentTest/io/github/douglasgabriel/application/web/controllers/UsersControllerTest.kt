package componentTest.io.github.douglasgabriel.application.web.controllers

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import componentTest.commons.FuelSimplifier
import componentTest.components.DIMock
import componentTest.components.Neo4JDatasource
import io.github.douglasgabriel.application.Application
import io.github.douglasgabriel.application.web.controllers.UsersController
import io.github.douglasgabriel.domain.user.entities.User
import io.github.douglasgabriel.resources.persistence.user.dtos.UserNode
import org.eclipse.jetty.http.HttpStatus
import kotlin.test.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertFalse

object UsersControllerTest: Spek({

    describe("${UsersController::class.simpleName}") {
        val host = FuelSimplifier("http://localhost:7000")
        lateinit var application: Application

        before {
            DIMock.mockModules()
            application = Application()
        }

        after { application.stop() }

        describe("/users") {
            context("when there is no user") {
                it("should return a HTTP status 200") {
                    host
                            .post("/users", "users/create_user--valid")
                            .let {
                                val user = jacksonObjectMapper().readValue(it.data, User::class.java)

                                assertEquals(HttpStatus.OK_200, it.statusCode)
                                assertEquals("test", user.username)
                                assertEquals(30, user.age)
                                assert(user.friends.isEmpty())
                            }
                }
            }

            context("when an user already exists") {
                val username = "test"
                val userAge = 30

                before {
                    with(Neo4JDatasource.getDatabase().getDatabase()) {
                        save(UserNode(username, userAge))
                    }
                }

                describe("/:id") {
                    it("should return a HTTP status 200 and the requested user") {
                        host
                                .get("/users/$username")
                                .let {
                                    val user = jacksonObjectMapper().readValue(it.data, User::class.java)

                                    assertEquals(HttpStatus.OK_200, it.statusCode)
                                    assertEquals(username, user.username)
                                    assertEquals(userAge, user.age)
                                }
                    }
                }

                describe("/friends") {

                    describe("post") {
                        it("should return a HTTP status 200") {
                            host
                                    .post("/users/$username/friends", "users/add_user_friend--valid")
                                    .let {
                                        val user = jacksonObjectMapper().readValue(it.data, User::class.java)

                                        assertEquals(HttpStatus.OK_200, it.statusCode)
                                        assertEquals(username, user.username)
                                        assertEquals(userAge, user.age)
                                        assertFalse(user.friends.isEmpty())
                                        assertEquals(user.friends.first().username, "friend")
                                    }
                        }
                    }
                }
            }
        }
    }

})
