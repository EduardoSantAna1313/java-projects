package br.com.edu.app.test.containers.infraestructure.rest

import br.com.edu.app.test.containers.domain.Api
import br.com.edu.app.test.containers.infraestructure.dao.ApiDao
import org.springframework.data.domain.PageRequest
import org.springframework.data.web.PagedResourcesAssembler
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.PagedModel
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/apis")
class ApiController (
    private val dao: ApiDao,
    val assembler: PagedResourcesAssembler<Api>
) {

    @GetMapping(produces = [APPLICATION_JSON_VALUE])
    fun list(@RequestParam("page") page: Int, @RequestParam("limit") limit: Int): PagedModel<EntityModel<Api>> {

        val pageable = PageRequest.of(page, limit)

        return assembler.toModel(dao.list(pageable))
    }

}