package com.templates

import io.quarkus.qute.Template
import io.quarkus.qute.TemplateInstance
import jakarta.inject.Inject
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.QueryParam
import jakarta.ws.rs.core.MediaType

@Path("/test")
class PageTest {

    @Inject
    lateinit var page: Template


    @GET
    @Produces(MediaType.TEXT_HTML)
    fun get(@QueryParam("name") name: String): TemplateInstance {
        return page.data("name", name)
    }
}