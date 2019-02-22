package componentTest.io.github.douglasgabriel.application.web.controllers

import com.github.kittinunf.fuel.Fuel
import componentTest.components.DIMock
import io.github.douglasgabriel.application.Application
import io.github.douglasgabriel.application.web.controllers.UsersController
import org.eclipse.jetty.http.HttpStatus
import kotlin.test.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object UsersControllerTest: Spek({

    describe("${UsersController::class.simpleName}") {
        val host = "http://localhost:7000/users"

        before {
            DIMock.mockModules()
            Application()

            //TODO: Criar usuário
            //TODO: Criar relação de amizade com usuário
            //TODO: Recuperar usuário e avaliar relacionamentos

        }

        it("teste") {
            Fuel
                    .post(host)
                    .response()
                    .let { (_, response, _) ->
                        assertEquals(HttpStatus.OK_200, response.statusCode)
                    }
        }
    }

})
