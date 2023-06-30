package com.templates

import com.templates.models.entities.Email
import com.templates.models.facade.EmailFacade
import com.templates.models.requests.CreateEmailRequest
import io.quarkus.qute.Template
import io.quarkus.qute.TemplateInstance
import jakarta.enterprise.context.RequestScoped
import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import java.util.*

@Path("/mailer")
@RequestScoped
class WelcomePageController(
        private val emailFacade: EmailFacade
) {

    @Inject
    lateinit var welcome: Template

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    //@Produces(MediaType.APPLICATION_JSON)
    fun createEmail(request: CreateEmailRequest): Email {
        val command = request.toCommand()
        return this.emailFacade.save(command)
    }

    @GET
    @Path("/welcome")
    fun getWelcomePage(
            @QueryParam("id") id: UUID,
            @QueryParam("name") name: String
    ): TemplateInstance {
        val email = this.emailFacade.processEmail(id)
        return welcome.data(
                "id", id,
                "name", name)
    }


    /*@GET
fun getWelcomePage(@QueryParam("name") name: String) : TemplateInstance {
    return this.welcome.data("name", name);
}
 */

}